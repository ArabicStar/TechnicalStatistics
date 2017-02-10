package com.nju.va.technicalstatistics.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tinke on 2017/1/31.
 */

public final class DatabaseHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = "Database";

    public static final String DATABASE_NAME = "TECH_STATS";
    public static final int DATABASE_VERSION = 3;

    public static final String VALID_COL = "VALID";

    public static final String MEMBER_TABLE_NAME = "MEMBERS";
    public static final String MEMBER_ID_COL = "MEMBER_ID";
    public static final String MEMBER_NAME_COL = "MEMBER_NAME";
    public static final String MEMBER_NUM_COL = "MEMBER_NUM";
    public static final String MEMBER_POS_COL = "MEMBER_POS";

    public static final String TEAM_TABLE_NAME = "TEAMS";
    public static final String TEAM_ID_COL = "TEAM_ID";
    public static final String TEAM_NAME_COL = "TEAM_NAME";
    public static final String TEAM_IMG_COL = "TEAM_IMG";

    public static final String MATCH_TABLE_NAME = "MATCHES";
    public static final String MATCH_ID_COL = "MATCH_ID";
    public static final String MATCH_NAME_COL = "MATCH_NAME";
    public static final String MATCH_LEFT_TEAM_COL = "MATCH_LEFT_TEAM";
    public static final String MATCH_RIGHT_TEAM_COL = "MATCH_RIGHT_TEAM";
    public static final String MATCH_FINISH_COL = "MATCH_FINISH";

    public static final String GAME_TABLE_NAME = "GAMES";
    public static final String GAME_ID_COL = "GAME_ID";
    public static final String GAME_FINISH_COL = "GAME_FININSH";

    public static final String POINT_TABLE_NAME = "POINTS";
    public static final String POINT_ID_COL = "POINT_ID";
    public static final String POINT_ACTIVE_COL = "POINT_ACTIVE";
    public static final String POINT_METHOD_COL = "POINT_METHOD";
    public static final String POINT_REMARK_COL = "POINT_REMARK";
    public static final String POINT_LR_COL = "POINT_LR";

    public static final String PT$MEM_TABLE_NAME = "PT$MEM";

    public static final String STOP_TABLE_NAME = "STOPs";
    public static final String STOP_ID_COL = "STOP_ID";
    public static final String STOP_PTA_COL = "STOP_PT_A";
    public static final String STOP_PTB_COL = "STOP_PT_B";
    public static final String STOP_TEAM_COL = "STOP_TEAM";

    public static final String EXCHANGE_TABLE_NAME = "EXCHANGES";
    public static final String EXCHANGE_ID_COL = "EXCHANGE_ID";
    public static final String EXCHANGE_TEAM_COL = "EXCHANGE_TEAM";
    public static final String EXCHANGE_TEAM_PT_COL = "EXCHANGE_TEAM_PT";
    public static final String EXCHANGE_OTHER_PT_COL = "EXCHANGE_OTHER_PT";
    public static final String EXCHANGE_OLD_COL = "EXCHANGE_OLD_COL";
    public static final String EXCHANGE_NEW_COL = "EXCHANGE_NEW_COL";

    public static final ContentValues INVALIDATE = new ContentValues();

    static {
        INVALIDATE.put( VALID_COL, Boolean.FALSE );
    }

    private static final String CREATE_TEAM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TEAM_TABLE_NAME +
                    "(" +
                    TEAM_ID_COL + " INTEGER AUTO_INCREMENT, " +
                    TEAM_NAME_COL + " VARCHAR(100) NOT NULL, " +
                    TEAM_IMG_COL + " INTEGER, " +
                    VALID_COL + " BOOLEAN, " +
                    "PRIMARY KEY (" + TEAM_ID_COL + ") " +
                    ")";

    private static final String CREATE_MEM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + MEMBER_TABLE_NAME +
                    "(" +
                    MEMBER_ID_COL + " INTEGER AUTO_INCREMENT, " +
                    MEMBER_NAME_COL + " VARCHAR(20) NOT NULL, " +
                    MEMBER_NUM_COL + " INTEGER NOT NULL CHECK(MEMBER_NUM>0), " +
                    MEMBER_POS_COL + " INTEGER NOT NULL CHECK(MEMBER_POS>=0 AND MEMBER_POS<=4), " +
                    TEAM_ID_COL + " INTEGER NOT NULL, " +
                    VALID_COL + " BOOLEAN, " +
                    "PRIMARY KEY (" + MEMBER_ID_COL + "), " +
                    "FOREIGN KEY (" + TEAM_ID_COL + ") REFERENCES " + TEAM_TABLE_NAME + "(" +
                    TEAM_ID_COL + ") " +
                    ")";

    private static final String CREATE_MATCH_TABLE = VALID_COL + " BOOLEAN, " +
            "PRIMARY KEY (" + MATCH_ID_COL + "), " +
            "FOREIGN KEY (" + MATCH_LEFT_TEAM_COL + ") REFERENCES " + TEAM_NAME_COL + "(" +
            TEAM_ID_COL + "), " +
            "FOREIGN KEY (" + MATCH_RIGHT_TEAM_COL + ") REFERENCES " + TEAM_NAME_COL + "(" +
            TEAM_ID_COL + ") " +
            ")";

    private static final String CREATE_GAME_TABLE =
            "CREATE TABLE IF NOT EXISTS " + GAME_TABLE_NAME +
                    "(" +
                    GAME_ID_COL + " INTEGER AUTO_INCREMENT, " +
                    MATCH_ID_COL + " INTEGER NOT NULL, " +
                    GAME_FINISH_COL + " BOOLEAN, " +
                    "PRIMARY KEY (" + GAME_ID_COL + "), " +
                    "FOREIGN KEY (" + MATCH_ID_COL + ") REFERENCES " + MATCH_ID_COL + "(" +
                    MATCH_ID_COL + ") " +
                    ")";

    private static final String CREATE_POINT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + POINT_TABLE_NAME +
                    "(" +
                    POINT_ID_COL + " INTEGER AUTO_INCREMENT, " +
                    GAME_ID_COL + " INTEGER NOT NULL, " +
                    TEAM_ID_COL + " INTEGER NOT NULL, " +
                    POINT_LR_COL + " BOOLEAN, " +//true indicates left while false indicates right.
                    POINT_ACTIVE_COL + " BOOLEAN, " +
                    POINT_METHOD_COL + " VARCHAR(20), " +
                    POINT_REMARK_COL + " VARCHAR(255), " +
                    "PRIMARY KEY (" + POINT_ID_COL + "), " +
                    "FOREIGN KEY (" + GAME_ID_COL + ") REFERENCES " + GAME_TABLE_NAME + "(" +
                    GAME_ID_COL + "), " +
                    "FOREIGN KEY (" + TEAM_ID_COL + ") REFERENCES " + TEAM_TABLE_NAME + "(" +
                    TEAM_ID_COL + ") " +
                    ")";

    private static final String CREATE_PT$MEM_TABLE =
            "CREATE TABLE IF NOT EXISTS" + PT$MEM_TABLE_NAME +
                    "(" +
                    POINT_ID_COL + " INTEGER, " +
                    MEMBER_ID_COL + " INTEGER, " +
                    "PRIMARY KEY (" + POINT_ID_COL + "," + MEMBER_ID_COL + "), " +
                    "FOREIGN KEY (" + POINT_ID_COL + ") REFERENCES " + POINT_TABLE_NAME + "(" +
                    POINT_ID_COL + ")," +
                    "FOREIGN KEY (" + MEMBER_ID_COL + ") REFERENCES " + MEMBER_TABLE_NAME + "(" +
                    MEMBER_ID_COL + ")" +
                    ")";

    private static final String CREATE_STOP_TABLE = "CREATE TABLE IF NOT EXISTS" + STOP_TABLE_NAME +
            "(" +
            STOP_ID_COL + " INTEGER AUTO_INCREMENT, " +
            GAME_ID_COL + " INTEGER NOT NULL, " +
            STOP_TEAM_COL + " INTEGER NOT NULL, " +
            STOP_PTA_COL + " INTEGER NOT NULL, " +
            STOP_PTB_COL + " INTEGER NOT NULL, " +
            "PRIMARY KEY (" + STOP_ID_COL + "), " +
            "FOREIGN KEY (" + GAME_ID_COL + ") REFERENCES " + GAME_TABLE_NAME + "(" + GAME_ID_COL +
            ") " +
            ")";

    private static final String CREATE_EXCHANGE_TABLE =
            "CREATE TABLE IF NOT EXISTS" + EXCHANGE_TABLE_NAME +
                    "(" +
                    EXCHANGE_ID_COL + " INTEGER AUTO_INCREMENT, " +
                    GAME_ID_COL + " INTEGER NOT NULL, " +
                    EXCHANGE_TEAM_COL + " INTEGER NOT NULL, " +
                    EXCHANGE_TEAM_PT_COL + " INTEGER NOT NULL, " +
                    EXCHANGE_OTHER_PT_COL + " INTEGER NOT NULL, " +
                    EXCHANGE_OLD_COL + " INTEGER NOT NULL, " +
                    EXCHANGE_NEW_COL + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + EXCHANGE_ID_COL + "), " +
                    "FOREIGN KEY (" + GAME_ID_COL + ") REFERENCES " + GAME_TABLE_NAME + "(" +
                    GAME_ID_COL + "), " +
                    "FOREIGN KEY (" + TEAM_ID_COL + ") REFERENCES " + TEAM_TABLE_NAME + "(" +
                    TEAM_ID_COL + "), " +
                    "FOREIGN KEY (" + EXCHANGE_OLD_COL + ") REFERENCES " + MEMBER_TABLE_NAME + "(" +
                    MEMBER_ID_COL + "), " +
                    "FOREIGN KEY (" + EXCHANGE_NEW_COL + ") REFERENCES " + MEMBER_TABLE_NAME + "(" +
                    MEMBER_ID_COL + ") " +
                    ")";
    ;

    private static final String DROP_DB = "DROP DATABASE IF EXISTS " + MEMBER_TABLE_NAME;

    public DatabaseHelper( Context context, String name, SQLiteDatabase.CursorFactory factory,
                           int version ) {
        super( context, name, factory, version );
    }

    @Override public void onCreate( SQLiteDatabase sqLiteDatabase ) {
        sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL( CREATE_TEAM_TABLE );
        sqLiteDatabase.execSQL( CREATE_MEM_TABLE );
        sqLiteDatabase.execSQL( CREATE_MATCH_TABLE );
        sqLiteDatabase.execSQL( CREATE_GAME_TABLE );
        sqLiteDatabase.execSQL( CREATE_POINT_TABLE );
        sqLiteDatabase.execSQL( CREATE_PT$MEM_TABLE );
        //        sqLiteDatabase.execSQL( CREATE_STOP_TABLE );
        //        sqLiteDatabase.execSQL( CREATE_EXCHANGE_TABLE );
        sqLiteDatabase.endTransaction();

        Log.d( LOG_TAG, "create database" );
    }

    @Override public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {
        sqLiteDatabase.execSQL( DROP_DB );
        onCreate( sqLiteDatabase );

        Log.d( LOG_TAG, "upgrade database" );
    }
}
