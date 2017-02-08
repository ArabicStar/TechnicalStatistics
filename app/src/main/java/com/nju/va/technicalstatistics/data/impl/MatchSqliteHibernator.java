package com.nju.va.technicalstatistics.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nju.va.technicalstatistics.data.MatchHibernator;
import com.nju.va.technicalstatistics.info.Exchange;
import com.nju.va.technicalstatistics.info.Game;
import com.nju.va.technicalstatistics.info.Match;
import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Point;
import com.nju.va.technicalstatistics.info.Stop;

import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.*;

/**
 * Created by tinke on 2017/2/6.
 */

public class MatchSqliteHibernator implements MatchHibernator {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public MatchSqliteHibernator( SQLiteOpenHelper helper ) {
        this.helper = helper;
        this.db = helper.getWritableDatabase();
    }

    public MatchSqliteHibernator( Context context ) {
        helper = new DatabaseHelper( context, DatabaseHelper.DATABASE_NAME, null,
                DatabaseHelper.DATABASE_VERSION );
        db = helper.getWritableDatabase();
    }

    @Override public boolean save( Match match ) {
        return false;
    }

    private boolean saveGame( int matchId, Game g ) {
        return false;
    }

    private boolean savePoint( int gameId, Point point, boolean isLeft ) {
        if( point == null ) return false;

        ContentValues content = point2ContentValues( point, gameId, isLeft );

        boolean result = false;
        //transaction 0: outer transaction
        db.beginTransaction();
        try {
            //save point
            long id = db.insert( POINT_TABLE_NAME, null, content );
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
                    result = true;
                } finally {
                    db.endTransaction();
                }//end transaction 1

                db.setTransactionSuccessful();
            }
        } finally {
            db.endTransaction();
        }//end transaction 0

        return result;
    }

    private boolean saveStop( int gameId, Stop stop ) {
        if( stop == null ) return false;

        ContentValues content = stop2ContentValues( stop, gameId );

        boolean result = false;
        db.beginTransaction();
        try {
            if( result = ( db.insert( STOP_TABLE_NAME, null, content ) != -1 ) )
                db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    private boolean saveExchange( int gameId, Exchange exchange ) {
        return false;
    }

    private static final ContentValues stop2ContentValues( Stop stop, int gameId ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( STOP_ID_COL, stop.getId() );
        contentValues.put( STOP_PTA_COL, stop.getPointA() );
        contentValues.put( STOP_PTB_COL, stop.getPointB() );
        contentValues.put( STOP_TEAM_COL, stop.getStopTeam() );
        contentValues.put( GAME_ID_COL, gameId );
        return contentValues;
    }

    private static final ContentValues point2ContentValues( Point p, int gameId, boolean isLeft ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( POINT_ID_COL, p.getId() );
        contentValues.put( POINT_ACTIVE_COL, p.isActive() );
        contentValues.put( POINT_WHY_COL, p.getWhy() );
        contentValues.put( GAME_ID_COL, gameId );
        contentValues.put( POINT_LR_COL, isLeft );
        return contentValues;
    }

    private static final ContentValues exchange2ContentValues( Exchange exchange, int gameId ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( EXCHANGE_ID_COL, exchange.getId() );
        contentValues.put( EXCHANGE_TEAM_COL, exchange.getExchangeTeam() );
        return contentValues;
    }
}