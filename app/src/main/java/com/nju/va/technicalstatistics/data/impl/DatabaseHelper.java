package com.nju.va.technicalstatistics.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tinke on 2017/1/31.
 */

public final class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = "Database";

    public static final String DATABASE_NAME = "TECH_STATS";
    public static final int DATABASE_VERSION = 1;

    public static final String MEMBER_TABLE_NAME = "MEMBERS";
    public static final String MEMBER_ID_COL = "MEMBER_ID";
    public static final String MEMBER_NAME_COL = "MEMBER_NAME";
    public static final String MEMBER_NUM_COL = "MEMBER_NUM";
    public static final String MEMBER_POS_COL = "MEMBER_POS";
    public static final String MEMBER_TEAM_COL = "MEMBER_TEAM";

    public static final String TEAM_TABLE_NAME = "TEAMS";
    public static final String TEAM_ID_COL = "TEAM_ID";
    public static final String TEAM_NAME_COL = "TEAM_NAME";
    public static final String TEAM_IMG_COL = "TEAM_IMG";

    private static final String CREATE_MEM_TABLE = "CREATE TABLE IF NOT EXISTS " + MEMBER_TABLE_NAME + "(" +
            MEMBER_ID_COL + " INTEGER AUTOINCREMENT, " +
            MEMBER_NAME_COL + " VARCHAR(20) NOT NULL, " +
            MEMBER_NUM_COL + " INTEGER NOT NULL CHECK(MEMBER_NUM>0), " +
            MEMBER_POS_COL + " INTEGER NOT NULL CHECK(MEMBER_POS>=0 AND MEMBER_POS<=4, " +
            MEMBER_TEAM_COL + " INTEGER NOT NULL, " +
            "PRIMARY KEY (" + MEMBER_ID_COL + "), " +
            "FOREIGN KEY (" + MEMBER_TEAM_COL + ") REFERENCES TEAMS(TEAM_ID)" + ")";
    private static final String CREATE_TEAM_TABLE = "CREATE TABLE IF NOT EXISTS " + TEAM_TABLE_NAME + "(" +
            TEAM_ID_COL + " INTEGER AUTOINCREMENT, " +
            TEAM_NAME_COL + " VARCHAR(100) NOT NULL, " +
            TEAM_IMG_COL + " INTEGER, " +
            "PRIMARY KEY (" + TEAM_ID_COL + ")" + ")";
    private static final String DROP_DB = "DROP DATABASE IF EXISTS " + MEMBER_TABLE_NAME;

    public DatabaseHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override public void onCreate( SQLiteDatabase sqLiteDatabase ) {
        sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL( CREATE_TEAM_TABLE );
        sqLiteDatabase.execSQL( CREATE_MEM_TABLE );
        sqLiteDatabase.endTransaction();

        Log.d( LOG_TAG, "create database" );
    }

    @Override public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {
        sqLiteDatabase.execSQL( DROP_DB );
        onCreate( sqLiteDatabase );

        Log.d( LOG_TAG, "upgrade database" );
    }
}
