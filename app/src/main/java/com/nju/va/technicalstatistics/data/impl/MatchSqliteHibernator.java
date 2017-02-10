package com.nju.va.technicalstatistics.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nju.va.technicalstatistics.data.MatchHibernator;
//import com.nju.va.technicalstatistics.info.Exchange;
import com.nju.va.technicalstatistics.data.MemberHibernator;
import com.nju.va.technicalstatistics.data.TeamHibernator;
import com.nju.va.technicalstatistics.info.Game;
import com.nju.va.technicalstatistics.info.Match;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Point;
import com.nju.va.technicalstatistics.info.Team;
//import com.nju.va.technicalstatistics.info.Stop;

import java.util.ArrayList;
import java.util.List;

import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.*;

/**
 * Created by tinke on 2017/2/6.
 */

public class MatchSqliteHibernator implements MatchHibernator {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private TeamHibernator teamDb;
    private MemberHibernator memberDb;

    public MatchSqliteHibernator( Context context ) {
        helper = new DatabaseHelper( context, DatabaseHelper.DATABASE_NAME, null,
                DatabaseHelper.DATABASE_VERSION );
        db = helper.getWritableDatabase();
        teamDb = new TeamSqliteHibernator( helper );
        memberDb = teamDb.getMemberHibernator();
    }

    @Override public boolean save( Match match ) {
        if( match == null ) return false;

        ContentValues values = match2ContentValues( match );
        boolean result = false;
        db.beginTransaction();
        try {
            long id = db.insert( MATCH_TABLE_NAME, null, values );
            if( id != -1 ) {
                result = true;
                for ( Game g : match.getGames() )
                    if( !( result = saveGame( id, g ) ) ) break;

                if( result ) db.setTransactionSuccessful();
            }
        } catch ( Exception e ) {
            result = false;
            Log.w( LOG_TAG, "save match failed: ", e );
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean delete( long id ) {
        if( id < 0 ) return false;

        String[] args = { String.valueOf( id ) };
        String where = MATCH_ID_COL + "=?";
        boolean result = true;
        db.beginTransaction();
        try {
            db.update( MATCH_TABLE_NAME, INVALIDATE, where, args );
            db.setTransactionSuccessful();
        } catch ( Exception e ) {
            result = false;
            Log.w( LOG_TAG, "delete match failed: ", e );
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public Match find( long id ) {
        return find( id, true );
    }

    @Override public Match find( long id, boolean ignoreDeleted ) {
        if( id < 0 ) return null;
        final String sql =
                "SELECT DISTINCT * FROM " + MATCH_TABLE_NAME + " WHERE " + MEMBER_ID_COL +
                        "=? AND " +
                        VALID_COL + "=?";
        final String[] args = { String.valueOf( id ), String.valueOf( ignoreDeleted ) };

        db.beginTransaction();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery( sql, args );
        } catch ( Exception e ) {
            Log.w( LOG_TAG, "find match failed: ", e );
        } finally {
            db.endTransaction();
        }
        if( cursor == null ) return null;

        Match m = null;
        if( cursor.moveToNext() ) {
            Team left = teamDb.find( cursor.getLong( 2 ), false );
            Team right = teamDb.find( cursor.getLong( 3 ), false );
            if( left != null || right != null ) {
                m = new Match( left, right );
                m.setId( cursor.getLong( 0 ) );
                m.setName( cursor.getString( 1 ) );
                m.setFinish( cursor.getInt( 4 ) == 1 );
            }
        }
        cursor.close();
        if( m == null ) return null;


        return m;
    }

    @Override public List< Match > find( String name ) {
        return null;
    }

    private boolean saveGame( long matchId, Game g ) {
        if( matchId <= 0 || g == null ) return false;

        ContentValues values = game2ContentValues( g, matchId );
        boolean result = false;
        db.beginTransaction();
        try {
            long id = db.insert( GAME_TABLE_NAME, null, values );
            if( id != -1L ) {
                result = true;
                for ( Point p : g.getLeftPoints() )
                    if( !( result = savePoint( id, p, true ) ) ) break;

                if( result ) for ( Point p : g.getRightPoints() )
                    if( !( result = savePoint( id, p, false ) ) ) break;

                if( result ) db.setTransactionSuccessful();
            }
        } finally {
            db.endTransaction();
        }
        return result;
    }

    private boolean savePoint( long gameId, Point point, boolean isLeft ) {
        if( point == null ) return false;

        ContentValues content = point2ContentValues( point, gameId, isLeft );

        boolean result = false;
        //transaction 0: outer transaction
        db.beginTransaction();
        try {
            //save point
            long id = db.insert( POINT_TABLE_NAME, null, content );
            result = id >= 0L;
            if( id != -1 ) {//if INSERT succeeds
                String sql = "INSERT INTO " + PT$MEM_TABLE_NAME + " VALUES(?,?)";
                Object[] bindArgs = { id, null };

                // transaction 1: point-member relation
                db.beginTransaction();
                try {
                    for ( Member m : point.getRelMembers() ) {
                        bindArgs[1] = m.getId();
                        db.execSQL( sql, bindArgs );
                    }
                    db.setTransactionSuccessful();
                } catch ( Exception e ) {
                    result = false;
                    Log.w( LOG_TAG, "save point-member relation failed: ", e );
                } finally {
                    db.endTransaction();
                }

                if( result ) db.setTransactionSuccessful();
            }
        } catch ( Exception e ) {
            result = false;
            Log.w( LOG_TAG, "save point failed: ", e );
        } finally {
            db.endTransaction();
        }//end transaction 0

        return result;
    }

    //    private boolean saveStop( int gameId, Stop stop ) {
    //        if( stop == null ) return false;
    //
    //        ContentValues content = stop2ContentValues( stop, gameId );
    //
    //        boolean result = false;
    //        db.beginTransaction();
    //        try {
    //            if( result = ( db.insert( STOP_TABLE_NAME, null, content ) != -1 ) )
    //                db.setTransactionSuccessful();
    //        } finally {
    //            db.endTransaction();
    //        }
    //
    //        return result;
    //    }

    //    private boolean saveExchange( int gameId, Exchange exchange ) {
    //        return false;
    //    }
    //
    //    private static final ContentValues stop2ContentValues( Stop stop, int gameId ) {
    //        ContentValues contentValues = new ContentValues();
    //        contentValues.put( STOP_ID_COL, stop.getId() );
    //        contentValues.put( STOP_PTA_COL, stop.getPointA() );
    //        contentValues.put( STOP_PTB_COL, stop.getPointB() );
    //        contentValues.put( STOP_TEAM_COL, stop.getStopTeam() );
    //        contentValues.put( GAME_ID_COL, gameId );
    //        return contentValues;
    //    }
    private List< Game > findGameByMatch( long matchId ) {
        if( matchId < 0 ) return null;

        final String sql =
                "SELECT DISTINCT * FROM " + GAME_TABLE_NAME + " WHERE " + GAME_ID_COL + "=?";
        final String[] args = { String.valueOf( matchId ) };

        //db query
        Cursor cursor = null;
        db.beginTransaction();
        try {
            cursor = db.rawQuery( sql, args );
            db.setTransactionSuccessful();
        } catch ( Exception e ) {
            Log.w( LOG_TAG, "find point failed: ", e );
        } finally {
            db.endTransaction();
        }
        if( cursor == null ) return null;

        //instantiate Games
        List< Game > list = new ArrayList<>( cursor.getCount() );
        Game tmp;
        while ( cursor.moveToNext() ) {
            tmp = new Game();
            tmp.setId( cursor.getLong( 0 ) );
            tmp.setFinish( cursor.getInt( 2 ) == 1 );
            list.add( tmp );
        }
        cursor.close();

        //fetch Points
        for ( Game g : list ) {
            long gameId = g.getId();
            for ( Point p : findPointByGame( gameId, true ) )
                g.addLeftPoint( p );

            for ( Point p : findPointByGame( gameId, false ) )
                g.addRightPoint( p );
        }

        return list;
    }

    private List< Point > findPointByGame( long gameId, boolean isLeft ) {
        if( gameId < 0 ) return null;

        final String sql =
                "SELECT DISTINCT * FROM " + POINT_TABLE_NAME + " WHERE " + GAME_ID_COL + "=? AND " +
                        POINT_LR_COL + "=?";
        final String[] args = { String.valueOf( gameId ), String.valueOf( isLeft ) };

        Cursor cursor = null;
        db.beginTransaction();
        try {
            cursor = db.rawQuery( sql, args );
            db.setTransactionSuccessful();
        } catch ( Exception e ) {
            Log.w( LOG_TAG, "find point failed: ", e );
        } finally {
            db.endTransaction();
        }
        if( cursor == null ) return null;

        List< Point > list = new ArrayList<>( cursor.getCount() );
        Point p;
        while ( cursor.moveToNext() ) {
            p = new Point();
            p.setId( cursor.getLong( 0 ) );
            p.setWinnerTeam( cursor.getLong( 2 ) );
            p.setActive( cursor.getInt( 3 ) == 1 );
            p.setMethod( cursor.getString( 4 ) );
            p.setRemark( cursor.getString( 5 ) );
            list.add( p );
        }
        cursor.close();

        final String[] ptId = { null };

        for ( Point point : list ) {
            ptId[0] = String.valueOf( point.getId() );
            Cursor c = db.query( false, PT$MEM_TABLE_NAME, new String[]{ MEMBER_ID_COL },
                    POINT_ID_COL + "=?", ptId, null, null, null, null );

            while ( c.moveToNext() ) point.addRelMember( memberDb.find( c.getLong( 0 ) ) );
        }
        return list;
    }

    private static ContentValues game2ContentValues( Game g, long matchId ) {
        ContentValues values = new ContentValues();
        values.put( GAME_ID_COL, g.getId() );
        values.put( GAME_FINISH_COL, g.isFinish() );
        values.put( MATCH_ID_COL, matchId );
        return values;
    }

    private static ContentValues point2ContentValues( Point p, long gameId, boolean isLeft ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( POINT_ID_COL, p.getId() );
        contentValues.put( GAME_ID_COL, gameId );
        contentValues.put( TEAM_ID_COL, p.getWinnerTeamId() );
        contentValues.put( POINT_LR_COL, isLeft );
        contentValues.put( POINT_ACTIVE_COL, p.isActive() );
        contentValues.put( POINT_METHOD_COL, p.getMethod() );
        contentValues.put( POINT_REMARK_COL, p.getRemark() );
        return contentValues;
    }

    private static ContentValues match2ContentValues( Match match ) {
        ContentValues values = new ContentValues();
        values.put( MATCH_ID_COL, match.getId() );
        values.put( MATCH_NAME_COL, match.getName() );
        values.put( MATCH_LEFT_TEAM_COL, match.getLeftTeamId() );
        values.put( MATCH_RIGHT_TEAM_COL, match.getRightTeamId() );
        values.put( MATCH_FINISH_COL, match.isFinish() );
        return values;
    }

    //    private static final ContentValues exchange2ContentValues( Exchange exchange, int gameId ) {
    //        ContentValues contentValues = new ContentValues();
    //        contentValues.put( EXCHANGE_ID_COL, exchange.getId() );
    //        contentValues.put( EXCHANGE_TEAM_COL, exchange.getExchangeTeam() );
    //        return contentValues;
    //    }
}
