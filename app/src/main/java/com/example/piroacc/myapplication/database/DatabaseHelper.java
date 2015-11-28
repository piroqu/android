package com.example.piroacc.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.model.Uzytkownik;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String DEBUG_LOG = "SQL CREATOR : ";
    private static final String DATABASE_NAME = "USER_DATABASE",
            TABLE_USER = "telefon_uzytkownik ",
            KEY_ID = "telefon_uzytkownik_id",
            KEY_CREATION_DATE = "data_utworzenia",
            KEY_PASSWORD = "haslo",
            KEY_IMIE = "imie",
            KEY_EMAIL = "email",
            KEY_PHONE_NUMER = "numer_telefonu",
            KEY_IS_RODZIC = "czy_rodzic";


    private static final String CREATE_UZYTKOWNIK = "CREATE TABLE " + TABLE_USER + " (" +
            KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_CREATION_DATE + " timestamp , " +
            KEY_PASSWORD + " varchar(255) NOT NULL, " +
            KEY_IMIE + "   integer(10) NOT NULL, " +
            KEY_EMAIL+" varchar(255), " +
            KEY_PHONE_NUMER +" varchar(255), " +
            KEY_IS_RODZIC+" integer(1));";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DEBUG_LOG, "START");
        db.execSQL(CREATE_UZYTKOWNIK);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_UZYTKOWNIK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUzytkownik(Uzytkownik user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(KEY_PASSWORD,user.getHaslo());
        wartosci.put(KEY_IMIE, user.getImie());
        db.insertOrThrow(TABLE_USER, null, wartosci);
    }
}
