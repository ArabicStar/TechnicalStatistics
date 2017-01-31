package com.nju.va.technicalstatistics.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nju.va.technicalstatistics.data.MemberHibernator;
import com.nju.va.technicalstatistics.info.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.nju.va.technicalstatistics.data.impl.DatabaseHelper.*;

/**
 * Created by tinker on 2017/1/31.
 */

public class MemberSqliteHibernator implements MemberHibernator {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public MemberSqliteHibernator( SQLiteOpenHelper helper ) {
        this.helper = helper;
        this.db = helper.getWritableDatabase();
    }

    public MemberSqliteHibernator( Context context ) {
        helper = new DatabaseHelper( context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION );
        db = helper.getWritableDatabase();
    }

    @Override public void close() {
        helper.close();
    }

    @Override public boolean save( final Member m ) {
        if (m == null) return false;

        final ContentValues values = member2ContentValues( m );

        db.beginTransaction();
        boolean result = false;
        try {
            result = db.insert( MEMBER_TABLE_NAME, null, values ) >= 0L;
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean save( Collection< Member > members ) {
        boolean result = false;
        db.beginTransaction();
        try {
            for (Member m : members) {
                result = ( db.insert( MEMBER_TABLE_NAME, null, member2ContentValues( m ) ) >= 0L ) && result;
                if (!result) break;
            }
            if (result) db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return result;
    }

    @Override public boolean delete( int memberId ) {
        if (memberId <= 0) return false;

        final String where = MEMBER_ID_COL + "=?";
        final String[] args = { String.valueOf( memberId ) };


        db.beginTransaction();
        boolean result = false;
        try {
            result = db.delete( MEMBER_TABLE_NAME, where, args ) > 0;
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean deleteByTeam( int teamId ) {
        if (teamId <= 0) return false;
        final String where = MEMBER_TEAM_COL + "=?";
        final String[] args = { String.valueOf( teamId ) };

        db.beginTransaction();
        boolean result = false;
        try {
            result = db.delete( MEMBER_TABLE_NAME, where, args ) > 0;
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean update( Member m ) {
        ContentValues values = new ContentValues();
        values.put( MEMBER_ID_COL, m.getId() );
        values.put( MEMBER_NAME_COL, m.getName() );
        values.put( MEMBER_NUM_COL, m.getNumber() );
        values.put( MEMBER_POS_COL, m.getPosition() );
        values.put( MEMBER_TEAM_COL, m.getTeam() );
        final String where = MEMBER_ID_COL + "=?";
        final String[] args = { String.valueOf( m.getId() ) };

        db.beginTransaction();
        boolean result = false;
        try {
            result = db.update( MEMBER_TABLE_NAME, values, where, args ) > 0;
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return result;
    }

    @Override public boolean saveOrUpdate( Member m ) {
        Member tmp = find( m.getId() );
        if (tmp == null) return save( m );
        else return update( m );
    }

    @Override public Member find( int id ) {
        if (id < 0) return null;

        final String sql = "SELECT DISTINCT * FROM " + MEMBER_TABLE_NAME + " WHERE " + MEMBER_ID_COL + "=?";
        final String[] args = { String.valueOf( id ) };

        db.beginTransaction();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery( sql, args );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        if (cursor == null) return null;

        Member m = null;
        if (cursor.moveToNext()) {
            m = new Member( cursor.getString( 1 ), cursor.getInt( 2 ), cursor.getInt( 3 ) );
            m.setId( cursor.getInt( 0 ) );
            m.setTeam( cursor.getInt( 4 ) );
        }

        cursor.close();
        return m;
    }

    @Override public List< Member > findByTeam( int teamId ) {
        if (teamId < 0) return null;
        final String sql = "SELECT * FROM " + MEMBER_TABLE_NAME + " WHERE " + MEMBER_TEAM_COL + "=?";
        final String[] args = { String.valueOf( teamId ) };

        db.beginTransaction();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery( sql, args );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        if (cursor == null) return null;

        List< Member > list = new ArrayList<>( cursor.getCount() );
        Member m;
        if (cursor.moveToNext()) {
            m = new Member( cursor.getString( 1 ), cursor.getInt( 2 ), cursor.getInt( 3 ) );
            m.setId( cursor.getInt( 0 ) );
            m.setTeam( cursor.getInt( 4 ) );
            list.add( m );
        }
        cursor.close();
        return list;
    }

    private static final ContentValues member2ContentValues( final Member m ) {
        ContentValues values = new ContentValues();
        values.put( MEMBER_ID_COL, m.getId() );
        values.put( MEMBER_NAME_COL, m.getName() );
        values.put( MEMBER_NUM_COL, m.getNumber() );
        values.put( MEMBER_POS_COL, m.getPosition() );
        values.put( MEMBER_TEAM_COL, m.getTeam() );
        return values;
    }
}
