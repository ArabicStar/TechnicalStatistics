package com.nju.va.technicalstatistics.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.LongSparseArray;

import com.nju.va.technicalstatistics.data.MemberHibernator;
import com.nju.va.technicalstatistics.data.TeamHibernator;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.List;

import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.DATABASE_NAME;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.INVALIDATE;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.LOG_TAG;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_ID_COL;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_IMG_COL;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_NAME_COL;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_TABLE_NAME;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TRUE;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.VALID_COL;


/**
 * Sqlite implementation of {@link TeamHibernator}
 */
public class TeamSqliteHibernator implements TeamHibernator {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private MemberHibernator memberDb;

    public TeamSqliteHibernator( SQLiteOpenHelper helper ) {
        this.helper = helper;
        db = helper.getWritableDatabase();
        memberDb = new MemberSqliteHibernator( helper );
    }

    /**
     * Instantiates a new Team sqlite hibernator by given context.
     *
     * @param context the context
     */
    public TeamSqliteHibernator( Context context ) {
        helper =
                new DatabaseHelper( context, DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION );
        db = helper.getWritableDatabase();
        memberDb = new MemberSqliteHibernator( helper );
    }

    @Override public void close() {
        helper.close();
    }

    @Override public boolean save( Team t ) {
        if( t == null ) return false;

        ContentValues values = team2ContentValues( t );

        boolean result = false;
        db.beginTransaction();
        try {
            //insert team entity
            result = db.insert( TEAM_TABLE_NAME, null, values ) >= 0L;
            //insert related member entities
            if( result ) result = memberDb.save( t.getMembers() );
            //roll back if any operation fails
            if( result ) db.setTransactionSuccessful();
        } catch ( Exception e ) {
            result = false;
            Log.w( LOG_TAG, "save team failed: ", e );
        } finally {
            db.endTransaction();
        }

        return result;
    }


    @Override public boolean delete( long teamId ) {
        if( teamId < 0 ) return false;

        memberDb.deleteByTeam( teamId );

        final String where = TEAM_ID_COL + "=?";
        final String[] args = { String.valueOf( teamId ) };

        boolean result = true;
        db.beginTransaction();
        try {
            db.update( TEAM_TABLE_NAME, INVALIDATE, where, args );
            db.setTransactionSuccessful();
        } catch ( Exception e ) {
            result = false;
            Log.w( LOG_TAG, "delete team failed: ", e );
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean update( Team t ) {
        Cursor tmp = db.rawQuery(
                "SELECT DISTINCT" + VALID_COL + " FROM " + TEAM_TABLE_NAME + " WHERE " +
                        TEAM_ID_COL + "=? AND " + VALID_COL + "=" + TRUE,
                new String[]{ String.valueOf( t.getId() ) } );
        if( tmp.getCount() == 0 ) return false;
        tmp.close();


        final ContentValues values = team2ContentValues( t );
        final String where = TEAM_ID_COL + "=?";
        final String[] args = { String.valueOf( t.getId() ) };

        boolean result = true;
        db.beginTransaction();
        try {
            //update information of the team itself
            db.update( TEAM_TABLE_NAME, values, where, args );

            //cascade update its members' information
            //add or update members in new team
            final List< Member > newMembers = t.getMembers();
            for ( Member m : newMembers )
                if( !( result = memberDb.update( m ) ) ) break;


            if( result ) {
                //delete members who are in old team but not in new team
                final List< Member > oldMembers = memberDb.findByTeam( t.getId() );
                if( oldMembers != null )
                    for ( Member m : getDeletedMember( oldMembers, newMembers ) )
                        if( !( result = memberDb.delete( m.getId() ) ) ) break;

            }
            //end cascade

            if( result ) db.setTransactionSuccessful();
        } catch ( Exception e ) {
            result = false;
            Log.w( LOG_TAG, "update team failed: ", e );
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public Team find( long id ) {
        return find( id, true );
    }

    @Override public Team find( long id, boolean ignoreDeleted ) {
        if( id < 0 ) return null;
        final String sql =
                "SELECT DISTINCT * FROM " + TEAM_TABLE_NAME + " WHERE " + TEAM_ID_COL + "=? AND " +
                        VALID_COL + "=?";
        final String[] args = { String.valueOf( id ), String.valueOf( ignoreDeleted ) };

        db.beginTransaction();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery( sql, args );
        } catch ( Exception e ) {
            Log.w( LOG_TAG, "find team failed: ", e );
        } finally {
            db.endTransaction();
        }
        if( cursor == null ) return null;

        Team t = null;
        if( cursor.moveToNext() ) {
            t = new Team( cursor.getString( 1 ) );
            t.setId( cursor.getInt( 0 ) );
            t.setImgId( cursor.getInt( 2 ) );
        }
        cursor.close();
        if( t == null ) return null;

        List< Member > list = memberDb.findByTeam( id );
        if( list == null ) return t;
        for ( Member m : list )
            t.addMember( m );

        return t;
    }

    @Override public List< Team > findByName( String keyword ) {
        if( keyword == null ) return null;

        final String sql = "SELECT * FROM " + TEAM_TABLE_NAME + " WHERE " + TEAM_NAME_COL +
                " LIKE ? OR " + TEAM_NAME_COL + " LIKE ? AND " + VALID_COL + "=" + TRUE;
        final String[] args = { keyword + "%", "%" + keyword };

        db.beginTransaction();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery( sql, args );
        } catch ( Exception e ) {
            Log.w( LOG_TAG, "find team by name failed: ", e );
        } finally {
            db.endTransaction();
        }
        if( cursor == null ) return new ArrayList<>();

        List< Team > list = new ArrayList<>( cursor.getCount() );
        Team tmp;
        while ( cursor.moveToNext() ) {
            tmp = new Team( cursor.getString( 1 ) );
            tmp.setId( cursor.getInt( 0 ) );
            tmp.setImgId( cursor.getInt( 2 ) );
            list.add( tmp );
        }
        cursor.close();

        //retrieve members
        List< Member > members;
        for ( Team t : list ) {
            members = memberDb.findByTeam( t.getId() );
            for ( Member m : members )
                t.addMember( m );
        }

        return list;
    }

    @Override public MemberHibernator getMemberHibernator() { return memberDb; }

    private static ContentValues team2ContentValues( Team t ) {
        ContentValues values = new ContentValues();
        values.put( TEAM_NAME_COL, t.getName() );
        values.put( TEAM_IMG_COL, t.getImgId() );
        values.put( VALID_COL, TRUE );
        return values;
    }

    private static List< Member > getDeletedMember( final List< Member > oldMembers,
                                                    final List< Member > newMembers ) {
        LongSparseArray< Member > newNames = membersWithId( newMembers );

        List< Member > deleted = new ArrayList<>( oldMembers.size() );
        for ( Member m : oldMembers )
            if( null == newNames.get( m.getId() ) ) deleted.add( m );

        return deleted;

    }

    private static LongSparseArray< Member > membersWithId( final List< Member > list ) {
        LongSparseArray< Member > map = new LongSparseArray<>( list.size() );

        for ( Member m : list )
            map.put( m.getId(), m );

        return map;
    }
}
