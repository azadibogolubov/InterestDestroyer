package com.tutorazadi.interestdestroyerandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class IDQueryDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_PRINCIPAL,
            SQLiteHelper.COLUMN_RATE,
            SQLiteHelper.COLUMN_NUM_MONTHS,
            SQLiteHelper.COLUMN_EXTRA_PAYMENT};

    public IDQueryDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public IDQuery createIDQueryt(int principal, double rate, int numMonths, int extraPayment) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_PRINCIPAL, principal);
        values.put(SQLiteHelper.COLUMN_RATE, rate);
        values.put(SQLiteHelper.COLUMN_NUM_MONTHS, numMonths);
        values.put(SQLiteHelper.COLUMN_EXTRA_PAYMENT, extraPayment);

        long insertId = database.insert(SQLiteHelper.TABLE_ID_QUERIES, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_ID_QUERIES,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        IDQuery newQuery = cursorToIDQuery(cursor);
        cursor.close();
        return newQuery;
    }

    public void deleteIDQuery(IDQuery query) {
        long id = query.getId();
        System.out.println("IDQuery deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_ID_QUERIES, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<IDQuery> getAllQueries() {
        List<IDQuery> queries = new ArrayList<IDQuery>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_ID_QUERIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            IDQuery query = cursorToIDQuery(cursor);
            queries.add(query);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return queries;
    }

    private IDQuery cursorToIDQuery(Cursor cursor) {
        IDQuery query = new IDQuery();
        query.setId(cursor.getLong(0));
        query.setPrincipal(cursor.getInt(1));
        query.setRate(cursor.getDouble(2));
        query.setNumMonths(cursor.getInt(3));
        query.setExtraPayment(cursor.getInt(4));
        return query;
    }
}
