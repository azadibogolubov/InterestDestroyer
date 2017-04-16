package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_ID_QUERIES = "idqueries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRINCIPAL = "principal";
    public static final String COLUMN_RATE = "rate";
    public static final String COLUMN_NUM_MONTHS = "numMonths";
    public static final String COLUMN_EXTRA_PAYMENT = "extraPayment";

    private static final String DATABASE_NAME = "idqueries.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ID_QUERIES + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_PRINCIPAL
            + " integer not null, " + COLUMN_RATE
            + " decimal(5, 2) not null, " + COLUMN_NUM_MONTHS
            + " integer not null, " + COLUMN_EXTRA_PAYMENT
            + " integer not null)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ID_QUERIES);
        onCreate(db);
    }

}