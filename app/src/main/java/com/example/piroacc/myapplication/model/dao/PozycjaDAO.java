/*
package com.example.piroacc.myapplication.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.piroacc.myapplication.helper.DatabaseHelper;
import com.example.piroacc.myapplication.model.Pozycja;

*/
/**
 * Created by PiroACC on 2015-11-28.
 *//*

public class PozycjaDAO {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.KEY_CHILD_POSITION_ID,DatabaseHelper.KEY_LONGITUDE,DatabaseHelper.KEY_LATITUDE
            ,DatabaseHelper.KEY_POSITION_TIMESTAMP,DatabaseHelper.KEY_IS_SYNCHRONIZED,DatabaseHelper.FK_CHILD};

    public PozycjaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Pozycja createPosition(Pozycja position){
        ContentValues wartosci = new ContentValues();
        wartosci.put(DatabaseHelper.KEY_LONGITUDE, position.getDlugoscGeograficzna());
        wartosci.put(DatabaseHelper.KEY_LATITUDE, position.getSzerokoscGeograficzna());
//        wartosci.put(KEY_POSITION_TIMESTAMP, position.getData());
        wartosci.put(DatabaseHelper.KEY_IS_SYNCHRONIZED, position.isCzyZsynchronizowano());
        wartosci.put(DatabaseHelper.FK_CHILD, position.getFkDzieckoId());

        Cursor cursor = database.query(DatabaseHelper.TABLE_CHILD_POSITION,
                allColumns, null,
                null, null, null);

        db.insertOrThrow(TABLE_CHILD_POSITION, null, wartosci);
    }
}
*/
