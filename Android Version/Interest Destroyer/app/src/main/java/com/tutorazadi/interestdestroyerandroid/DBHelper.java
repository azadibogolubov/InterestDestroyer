package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_SAVED_QUERIES = "queries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRINCIPAL = "principal";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_TOTAL_MONTHS = "total_months";
    public static final String COLUMN_EXTRA_PAYMENT = "extra_payment";
    private static final String DATABASE_NAME = "interestdestroyer.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SAVED_QUERIES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_PRINCIPAL
            + " text not null, "
            + COLUMN_RATE + " text not null, "
            + COLUMN_TOTAL_MONTHS + " text not null, "
            + COLUMN_EXTRA_PAYMENT + " text not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_QUERIES);
        onCreate(db);
    }
}