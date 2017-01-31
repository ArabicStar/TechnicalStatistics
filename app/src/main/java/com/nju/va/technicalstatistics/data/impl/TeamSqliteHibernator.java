package com.nju.va.technicalstatistics.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nju.va.technicalstatistics.data.MemberHibernator;
import com.nju.va.technicalstatistics.data.TeamHibernator;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_ID_COL;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_IMG_COL;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_NAME_COL;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.TEAM_TABLE_NAME;
import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.DATABASE_NAME;

/**
 * Created by tinke on 2017/1/31.
 */

public class TeamSqliteHibernator implements TeamHibernator {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private MemberHibernator memberDb;

    public TeamSqliteHibernator( Context context ) {
        helper = new DatabaseHelper( context, DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION );
        db = helper.getWritableDatabase();
        memberDb = new MemberSqliteHibernator( helper );
    }

    @Override public void close() {
        helper.close();
    }

    @Override public boolean save( Team t ) {
        if (t == null) return false;

        ContentValues values = team2ContentValues( t );

        boolean isSuccessful = false;
        db.beginTransaction();
        try {
            isSuccessful = db.insert( TEAM_TABLE_NAME, null, values ) >= 0L;
            if (isSuccessful) isSuccessful = memberDb.save( t.getMembers() );
            if (isSuccessful) db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return isSuccessful;
    }


    @Override public boolean delete( int teamId ) {
        if (teamId < 0) return false;

        memberDb.deleteByTeam( teamId );

        final String where = TEAM_ID_COL + "=?";
        final String[] args = { String.valueOf( teamId ) };

        db.beginTransaction();

        boolean result = false;
        try {
            result = db.delete( TEAM_TABLE_NAME, where, args ) > 0;
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean update( Team t ) {
        final List< Member > oldMembers = memberDb.findByTeam( t.getId() );
        if (oldMembers == null) return false;

        final List< Member > newMembers = t.getMembers();
        final List< Member > deleted = getDeletedMember( oldMembers, newMembers );
        for (Member m : deleted)
            memberDb.delete( m.getId() );
        for (Member m : newMembers)
            memberDb.saveOrUpdate( m );

        final ContentValues values = team2ContentValues( t );
        final String where = TEAM_ID_COL + "=?";
        final String[] args = { String.valueOf( t.getId() ) };

        boolean result = false;
        db.beginTransaction();
        try {
            result = db.update( TEAM_TABLE_NAME, values, where, args ) > 0;
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public Team find( int id ) {
        if (id < 0) return null;
        final String sql = "SELECT DISTINCT * FROM " + TEAM_TABLE_NAME + " WHERE " + TEAM_ID_COL + "=?";
        final String[] args = { String.valueOf( id ) };

        db.beginTransaction();
        Cursor cursor;
        try {
            cursor = db.rawQuery( sql, args );
        } finally {
            db.endTransaction();
        }

        Team t = null;
        if (cursor.moveToNext()) {
            t = new Team( cursor.getString( 1 ) );
            t.setId( cursor.getInt( 0 ) );
            t.setImgId( cursor.getInt( 2 ) );
        }
        cursor.close();
        if (t == null) return null;

        List< Member > list = memberDb.findByTeam( id );
        for (Member m : list)
            t.addMember( m );

        return t;
    }

    private static final ContentValues team2ContentValues( Team t ) {
        ContentValues values = new ContentValues();
        values.put( TEAM_NAME_COL, t.getName() );
        values.put( TEAM_IMG_COL, t.getImgId() );

        return values;
    }

    private static final List< Member > getDeletedMember( final List< Member > oldMembers, final List< Member > newMembers ) {
        Map< Integer, Member > newNames = membersWithId( newMembers );

        List< Member > deleted = new ArrayList<>( oldMembers.size() );
        Member tmpMember;
        for (Member m : oldMembers)
            if (( tmpMember = newNames.remove( m.getId() ) ) == null) deleted.add( tmpMember );

        return deleted;

    }

    private static final Map< Integer, Member > membersWithId( final List< Member > list ) {
        Map< Integer, Member > map = new HashMap<>( list.size() );

        for (Member m : list)
            map.put( m.getId(), m );

        return map;
    }
}
