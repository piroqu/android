package com.example.piroacc.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.model.Uzytkownik;

import java.util.ArrayList;
import java.util.List;

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
            KEY_IS_RODZIC = "czy_rodzic",

    TABLE_CHILD = "telefon_dziecko",
            KEY_CHILD_KEY_ID = "telefon_dziecko_id",
            KEY_CHILD_NAME = "imie",

    TABLE_CHILD_POSITION ="telefon_pozycja",
            KEY_CHILD_POSITION_ID ="telefon_pozycja_id",
            KEY_LONGITUDE = "dlugosc_geograficzna",
            KEY_LATITUDE = "szerokosc_geograficzna",
            KEY_POSITION_TIMESTAMP = "czas",
            KEY_IS_SYNCHRONIZED = "czy_zsynchronizowana",
            FK_CHILD = "telefon_dziecko_telefon_dziecko_id";


    private static final String CREATE_UZYTKOWNIK = "CREATE TABLE " + TABLE_USER + " (" +
            KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_CREATION_DATE + " timestamp , " +
            KEY_PASSWORD + " varchar(255) NOT NULL, " +
            KEY_IMIE + "   integer(10) NOT NULL, " +
            KEY_EMAIL + " varchar(255), " +
            KEY_PHONE_NUMER + " varchar(255), " +
            KEY_IS_RODZIC + " integer(1));";
    private static final String CREATE_TELEFON_DZIECKO = "CREATE TABLE " + TABLE_CHILD + " (" +
            KEY_CHILD_KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            KEY_CHILD_NAME + "varchar(255) NOT NULL);";
    public static final String CREATE_TELEFON_POZYCJA = "CREATE TABLE "+ TABLE_CHILD_POSITION+ " ("+
            KEY_CHILD_POSITION_ID +" INTEGER NOT NULL PRIMARY KEY, " +
            KEY_LONGITUDE  +" double(10) NOT NULL, " +
            KEY_LATITUDE   +" double(10) NOT NULL, " +
            KEY_POSITION_TIMESTAMP+" timestamp NOT NULL, " +
            KEY_IS_SYNCHRONIZED + " integer(1) NOT NULL, " +
            FK_CHILD +" integer(10) NOT NULL, " +
            "  FOREIGN KEY("+FK_CHILD+") REFERENCES "+TABLE_CHILD+"("+KEY_CHILD_KEY_ID+"));";

    private static final String DROP_USER = "DROP TABLE IF EXISTS telefon_uzytkownik;";
    private static final String DROP_CHILD = "DROP TABLE IF EXISTS telefon_dziecko;";
    private static final String DROP_CHILD_POSITION = "DROP TABLE IF EXISTS telefon_pozycja;";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DEBUG_LOG, "START");
        db.execSQL(CREATE_UZYTKOWNIK);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_UZYTKOWNIK);
        db.execSQL(CREATE_TELEFON_DZIECKO);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_TELEFON_DZIECKO);
        db.execSQL(CREATE_TELEFON_POZYCJA);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_TELEFON_POZYCJA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUzytkownik(Uzytkownik user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(KEY_PASSWORD, user.getHaslo());
        wartosci.put(KEY_IMIE, user.getImie());
        db.insertOrThrow(TABLE_USER, null, wartosci);
    }

    public List<Uzytkownik> getUsers(){
        String[] columns = {KEY_ID,KEY_CREATION_DATE,KEY_PASSWORD,KEY_IMIE,KEY_EMAIL,KEY_PHONE_NUMER,KEY_IS_RODZIC};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, null, null, null, null, null);
        List<Uzytkownik> users = new ArrayList();
        while(cursor.moveToNext()){
            Uzytkownik tempUser = new Uzytkownik();
            tempUser.setImie(cursor.getString(4));
            users.add(tempUser);
        }
        return users;
    }
}
