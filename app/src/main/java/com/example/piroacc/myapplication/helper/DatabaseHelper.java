package com.example.piroacc.myapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.piroacc.myapplication.activity.child.DzieckoRegistrationActivity;
import com.example.piroacc.myapplication.model.Dziecko;
import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.Uzytkownik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;

    private static final String DEBUG_LOG = "SQL CREATOR : ";
    private static final String DEBUG_LOG_INSERT = "SQL INSERT : ";
    private static final String DEBUG_LOG_SELECT = "SQL SELECT : ";
    private static final String DEBUG_LOG_UPDATE = "SQL UPDATE : ";
    private static final String DEBUG_LOG_GET = "DATE FROM DATABASE ";

    public static final String DATABASE_NAME = "USER_DATABASE",

    TABLE_PARENT = "parent",
            PARENT_KEY_ID = "user_id",
            PARENT_KEY_CREATION_DATE = "creation_date",
            PARENT_KEY_PASSWORD = "password",
            PARENT_KEY_NAME = "name",
            PARENT_KEY_EMAIL = "email",
            PARENT_KEY_PHONE_NUMER = "phone_number",

    TABLE_CHILD = "child",
            CHILD_KEY_ID = "child_id",
            CHILD_KEY_NAME = "imie",
            CHILD_KEY_PASSWORD = "password",
            CHILD_FK_PARENT_ID = "fk_parent_id",


    TABLE_CHILD_POSITION = "child_position",
            POSITION_KEY_ID = "position_id",
            POSITION_KEY_LONGITUDE = "longitude",
            POSITION_KEY_LATITUDE = "latitude",
            POSITION_KEY_POSITION_TIMESTAMP = "date",
            POSITION_KEY_IS_SYNCHRONIZED = "is_synchronized",
            FK_CHILD = "fk_child_id";


    public static final String CREATE_PARENT= "CREATE TABLE " + TABLE_PARENT + " (" +
            PARENT_KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            PARENT_KEY_CREATION_DATE + " varchar(255) , " +
            PARENT_KEY_PASSWORD + " varchar(255) NOT NULL, " +
            PARENT_KEY_NAME + "   integer(10) NOT NULL, " +
            PARENT_KEY_EMAIL + " varchar(255), " +
            PARENT_KEY_PHONE_NUMER + " varchar(255), ";
    //            KEY_IS_RODZIC + " integer(1));";
    public static final String CREATE_CHILD = "CREATE TABLE " + TABLE_CHILD + " (" +
            CHILD_KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            CHILD_KEY_NAME + " varchar(255) );" +
            CHILD_KEY_PASSWORD + " varchar(255)" +
            CHILD_FK_PARENT_ID + " integer(10) , " +
            " FOREIGN KEY (" + CHILD_FK_PARENT_ID + ") REFERENCES " + TABLE_PARENT + "(" + PARENT_KEY_ID + "));";

    public static final String CREATE_CHILD_POSITION = "CREATE TABLE " + TABLE_CHILD_POSITION + " (" +
            POSITION_KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            POSITION_KEY_LONGITUDE + " double(10) NOT NULL, " +
            POSITION_KEY_LATITUDE + " double(10) NOT NULL, " +
            POSITION_KEY_POSITION_TIMESTAMP + " varchar(255) , " +
            POSITION_KEY_IS_SYNCHRONIZED + " integer(1) NOT NULL, " +
            FK_CHILD + " integer(10) , " +
            "  FOREIGN KEY(" + FK_CHILD + ") REFERENCES " + TABLE_CHILD + "(" + CHILD_KEY_ID + "));";

    private static final String DROP_USER = "DROP TABLE IF EXISTS telefon_uzytkownik;";
    private static final String DROP_CHILD = "DROP TABLE IF EXISTS telefon_dziecko;";
    private static final String DROP_CHILD_POSITION = "DROP TABLE IF EXISTS telefon_pozycja;";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            return new DatabaseHelper(context);
        } else return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DEBUG_LOG, "START");
        db.execSQL(CREATE_PARENT);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_PARENT);
        db.execSQL(CREATE_CHILD);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_CHILD);
        db.execSQL(CREATE_CHILD_POSITION);
        Log.d(DEBUG_LOG, "EXECUTE + " + CREATE_CHILD_POSITION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL(DROP_USER);
        db.execSQL(DROP_CHILD_POSITION);
        db.execSQL(DROP_CHILD);
        onCreate(db);
    }

    public void insertDziecko(Dziecko dziecko) {
        Log.d(DEBUG_LOG_INSERT, dziecko + "to : " + TABLE_CHILD);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(CHILD_KEY_ID, dziecko.getId());
//        wartosci.put(CHILD_KEY_NAME, dziecko.getImie());
        db.insertOrThrow(TABLE_CHILD, null, wartosci);
        Log.d(DEBUG_LOG_INSERT, dziecko + "to : " + TABLE_CHILD + "OK!");
    }

    public void addChildrenForParent(Dziecko dziecko) {
        Log.d(DEBUG_LOG_INSERT, dziecko + "to : " + TABLE_CHILD);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(CHILD_KEY_ID, dziecko.getId());
        wartosci.put(CHILD_KEY_NAME, dziecko.getImie());
        db.insertOrThrow(TABLE_CHILD, null, wartosci);
        Log.d(DEBUG_LOG_INSERT, dziecko + "to : " + TABLE_CHILD + "OK!");
    }

    public void insertPozycja(Pozycja position) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(POSITION_KEY_ID, DzieckoRegistrationActivity.DZIECKO_ID);    //TODO MOCKING TO DELETE

        wartosci.put(POSITION_KEY_LONGITUDE, position.getDlugoscGeograficzna());
        wartosci.put(POSITION_KEY_LATITUDE, position.getSzerokoscGeograficzna());
        wartosci.put(POSITION_KEY_POSITION_TIMESTAMP, position.getData());
        wartosci.put(POSITION_KEY_IS_SYNCHRONIZED, position.isCzyZsynchronizowano());
        wartosci.put(FK_CHILD, position.getFkDzieckoId());
        Log.d(DEBUG_LOG_INSERT, "INSERT POZYCJA :" + wartosci);
        db.insertOrThrow(TABLE_CHILD_POSITION, null, wartosci);
    }

    public void insertUzytkownikDziecko(Uzytkownik user) {
        Log.d(DEBUG_LOG_INSERT, "try to inser user data as child : " + user);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(PARENT_KEY_ID, user.getId());
        wartosci.put(PARENT_KEY_PASSWORD, user.getHaslo());
        wartosci.put(PARENT_KEY_NAME, user.getImie());
//        wartosci.put(KEY_IS_RODZIC, 0);
        db.insertOrThrow(TABLE_PARENT, null, wartosci);
        Log.d(DEBUG_LOG_INSERT, "user data inserted as child : " + wartosci);
        DzieckoRegistrationActivity.DZIECKO_ID = user.getId();
        Log.d(DEBUG_LOG_INSERT, "SETS USER ID : " + DzieckoRegistrationActivity.DZIECKO_ID);
    }

    public void insertUzytkownikAsParent(Uzytkownik user) {
        Log.d(DEBUG_LOG_INSERT, "try to inser user data as parent : " + user);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(PARENT_KEY_ID, user.getId());
        wartosci.put(PARENT_KEY_CREATION_DATE, user.getDataUtworzenia().toString());
        wartosci.put(PARENT_KEY_PASSWORD, user.getHaslo());
        wartosci.put(PARENT_KEY_NAME, user.getImie());
        wartosci.put(PARENT_KEY_EMAIL, user.getEmail());
        wartosci.put(PARENT_KEY_PHONE_NUMER, user.getNumerTelefonu());
//        wartosci.put(KEY_IS_RODZIC, 1);
        db.insertOrThrow(TABLE_PARENT, null, wartosci);
        Log.d(DEBUG_LOG_INSERT, "user data inserted as parent : " + wartosci);
    }

    public Uzytkownik getCurrentRodzic() {
        Log.d(DEBUG_LOG_GET, "in : getCurrentRodzic");
        Log.d(DEBUG_LOG_GET, "Get data from :" + TABLE_PARENT);
        String[] columns = {PARENT_KEY_ID, PARENT_KEY_CREATION_DATE, PARENT_KEY_PASSWORD,
                PARENT_KEY_NAME, PARENT_KEY_EMAIL, PARENT_KEY_PHONE_NUMER};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PARENT, columns, null, null, null, null, null);
        cursor.moveToNext();
        Uzytkownik currentUser = new Uzytkownik();
        currentUser.setId(cursor.getInt(0));
        currentUser.setDataUtworzenia(cursor.getString(1));
        currentUser.setHaslo(cursor.getString(2));
        currentUser.setImie(cursor.getString(3));
        currentUser.setEmail(cursor.getString(4));
        currentUser.setNumerTelefonu(cursor.getString(5));
        currentUser.setIsParent(true);
        Log.d(DEBUG_LOG_GET, "Current user data:" + currentUser);
        return currentUser;
    }

    public List<Uzytkownik> getUsers() {
        String[] columns = {PARENT_KEY_ID, PARENT_KEY_CREATION_DATE, PARENT_KEY_PASSWORD,
                PARENT_KEY_NAME, PARENT_KEY_EMAIL, PARENT_KEY_PHONE_NUMER};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PARENT, columns, null, null, null, null, null);
        List<Uzytkownik> users = new ArrayList();
        while (cursor.moveToNext()) {
            Uzytkownik tempUser = new Uzytkownik();
            tempUser.setImie(cursor.getString(4));
            users.add(tempUser);
        }
        return users;
    }

    public List<Uzytkownik> getParents() {
        Log.d(DEBUG_LOG_GET, "STARTED");
        String[] columns = {PARENT_KEY_ID, PARENT_KEY_CREATION_DATE, PARENT_KEY_PASSWORD,
                PARENT_KEY_NAME, PARENT_KEY_EMAIL, PARENT_KEY_PHONE_NUMER};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PARENT, columns, null + "=?", new String[]{"1"}, null, null, null);
//        Cursor cursor = db.query(TABLE_PARENT, columns, KEY_IS_RODZIC + "=?", new String[]{"1"}, null, null, null);
        List<Uzytkownik> parents = new ArrayList();
        while (cursor.moveToNext()) {
            Uzytkownik tempUser = new Uzytkownik();
            tempUser.setId(cursor.getInt(0));
            tempUser.setDataUtworzenia(cursor.getString(1));
            tempUser.setHaslo(cursor.getString(2));
            tempUser.setImie(cursor.getString(3));
            tempUser.setEmail(cursor.getString(4));
            tempUser.setNumerTelefonu(cursor.getString(5));
            parents.add(tempUser);
            Log.d(DEBUG_LOG_GET, tempUser.toString());
        }
        Log.d(DEBUG_LOG_GET, "Size of all parents :" + parents.size());
        return parents;
    }

    public List<Uzytkownik> getChilds() {
        Log.d(DEBUG_LOG_GET, "STARTED");
        String[] columns = {PARENT_KEY_ID, PARENT_KEY_CREATION_DATE, PARENT_KEY_PASSWORD,
                PARENT_KEY_NAME, PARENT_KEY_EMAIL, PARENT_KEY_PHONE_NUMER};
        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(TABLE_PARENT, columns, KEY_IS_RODZIC + "=?", new String[]{"0"}, null, null, null);
        Cursor cursor = db.query(TABLE_PARENT, columns, null + "=?", new String[]{"0"}, null, null, null);
        List<Uzytkownik> childs = new ArrayList();
        while (cursor.moveToNext()) {
            Uzytkownik tempUser = new Uzytkownik();
            tempUser.setId(cursor.getInt(0));
            tempUser.setDataUtworzenia(cursor.getString(1));
            tempUser.setHaslo(cursor.getString(2));
            tempUser.setImie(cursor.getString(3));
            tempUser.setEmail(cursor.getString(4));
            tempUser.setNumerTelefonu(cursor.getString(5));
            childs.add(tempUser);
            Log.d(DEBUG_LOG_GET, tempUser.toString());
        }
        Log.d(DEBUG_LOG_GET, "Size of all childs :" + childs.size());
        return childs;
    }

    public List<Pozycja> getPositions() {
        String[] positionColumns = {POSITION_KEY_ID, POSITION_KEY_LONGITUDE, POSITION_KEY_LATITUDE, POSITION_KEY_POSITION_TIMESTAMP, POSITION_KEY_IS_SYNCHRONIZED, FK_CHILD};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHILD_POSITION, positionColumns, null, null, null, null, null);
        List<Pozycja> positions = new ArrayList();
        while (cursor.moveToNext()) {
            Pozycja tempPosition = new Pozycja();
            tempPosition.setId(cursor.getInt(0));
            tempPosition.setDlugoscGeograficzna(cursor.getDouble(1));
            tempPosition.setSzerokoscGeograficzna(cursor.getDouble(2));
            tempPosition.setData(cursor.getString(3));
            boolean value = cursor.getInt(4) > 0;
            tempPosition.setCzyZsynchronizowano(value);
            tempPosition.setFkDzieckoId(cursor.getInt(5));
            positions.add(tempPosition);
        }
        return positions;
    }

    public List<Pozycja> getChildsPosition(String childId) {
        String[] positionColumns = {POSITION_KEY_ID, POSITION_KEY_LONGITUDE, POSITION_KEY_LATITUDE, POSITION_KEY_POSITION_TIMESTAMP, POSITION_KEY_IS_SYNCHRONIZED, FK_CHILD};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHILD_POSITION, positionColumns, POSITION_KEY_ID + "=?", new String[]{childId}, null, null, null);
        List<Pozycja> positions = new ArrayList();
        while (cursor.moveToNext()) {
            Pozycja tempPosition = new Pozycja();
            tempPosition.setId(cursor.getInt(0));
            tempPosition.setDlugoscGeograficzna(cursor.getDouble(1));
            tempPosition.setSzerokoscGeograficzna(cursor.getDouble(2));
            tempPosition.setData(cursor.getString(3));
            boolean value = cursor.getInt(4) > 0;
            tempPosition.setCzyZsynchronizowano(value);
            tempPosition.setFkDzieckoId(cursor.getInt(5));
            positions.add(tempPosition);
        }
        return positions;
    }

    public void updateSynchronizedPositions(List<Pozycja> positions) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getReadableDatabase();
        for (Pozycja tempPosition : positions) {
            contentValues.put(POSITION_KEY_ID, tempPosition.getId());
            contentValues.put(POSITION_KEY_LONGITUDE, tempPosition.getDlugoscGeograficzna());
            contentValues.put(POSITION_KEY_LATITUDE, tempPosition.getSzerokoscGeograficzna());
            contentValues.put(POSITION_KEY_POSITION_TIMESTAMP, tempPosition.getData());
            contentValues.put(POSITION_KEY_IS_SYNCHRONIZED, tempPosition.isCzyZsynchronizowano());
            contentValues.put(FK_CHILD, tempPosition.getFkDzieckoId());
            Log.d(DEBUG_LOG_UPDATE, "TO UPDATE: " + contentValues.toString());
            db.update(TABLE_CHILD_POSITION, contentValues, POSITION_KEY_ID + "=" + tempPosition.getId(), null);
            Log.d(DEBUG_LOG_UPDATE, "UDPATED : " + contentValues.toString());
        }
        Log.d(DEBUG_LOG_UPDATE, "ALL DATA UPDATED : ");
    }

    public List<Pozycja> getPositionsToSync() {
        Log.d(DEBUG_LOG_SELECT, "select positions to sync");
        String[] positionColumns = {POSITION_KEY_ID, POSITION_KEY_LONGITUDE, POSITION_KEY_LATITUDE, POSITION_KEY_POSITION_TIMESTAMP, POSITION_KEY_IS_SYNCHRONIZED, FK_CHILD};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHILD_POSITION, positionColumns, POSITION_KEY_IS_SYNCHRONIZED + "=?", new String[]{"0"}, null, null, null);
        List<Pozycja> positions = new ArrayList();
        while (cursor.moveToNext()) {
            Pozycja tempPosition = new Pozycja();
            tempPosition.setId(cursor.getInt(0));
            tempPosition.setDlugoscGeograficzna(cursor.getDouble(1));
            tempPosition.setSzerokoscGeograficzna(cursor.getDouble(2));
            tempPosition.setData(cursor.getString(3));
            boolean value = cursor.getInt(4) > 0;
            tempPosition.setCzyZsynchronizowano(value);
            tempPosition.setFkDzieckoId(cursor.getInt(5));
            positions.add(tempPosition);
        }
        Log.d(DEBUG_LOG_SELECT, "select positions to sync :" + positions.size());
        return positions;
    }

    public Integer getCurrentChildId() {
        Log.d(DEBUG_LOG_SELECT, "select current child id");
        String[] childColumns = {CHILD_KEY_ID, CHILD_KEY_NAME};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHILD, childColumns, null, null, null, null, null);
        cursor.moveToNext();
        Integer chilId = cursor.getInt(0);
        Log.d(DEBUG_LOG_SELECT, "select current child id : " + chilId);
        return chilId;
    }
}
