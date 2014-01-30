package by.euanpa.gbs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by google on 30.01.14.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "gbs.data.db";
    public static final String ROUTE = "ROUTE";
    public static final String BUS_STOP = "BUS_STOP";
    public static final String TIME = "TIME";
    public static final String BIND = "BIND";

    public static final String[] ROUTE_COLUMNS = {
            Contract.GbsColumns.ROUTE_ID, Contract.GbsColumns.NUMBER_ROUTE,
            Contract.GbsColumns.NAME_ROUTE};

    public static final String[] BUS_STOP_COLUMNS = {
            Contract.GbsColumns.BUS_STOP_ID, Contract.GbsColumns.BUS_STOP_NAME,
            Contract.GbsColumns.LATITUDE, Contract.GbsColumns.LONGITUDE};

    public static final String[] TIME_COLUMNS = {
            Contract.GbsColumns.TIME_ID, Contract.GbsColumns.HOUR,
            Contract.GbsColumns.MINUTE, Contract.GbsColumns.DAY_TYPE,
            Contract.GbsColumns.BS_PLUS_ROUTE};

    public static final String[] BIND_COLUMNS = {
            Contract.GbsColumns.BIND_ID, Contract.GbsColumns.BIND_ROUTE_ID,
            Contract.GbsColumns.BIND_BUS_STOP_ID};

    public static final int ONE_INDEX = 0;
    public static final int TWO_INDEX = 1;
    public static final int THREE_INDEX = 2;
    public static final int FOUR_INDEX = 3;
    public static final int FIVE_INDEX = 4;

    public static final String CREATE_GBS_DATABASE = "CREATE TABLE "
            + ROUTE + " (" + ROUTE_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + ROUTE_COLUMNS[TWO_INDEX]
            + " INTEGER, " + ROUTE_COLUMNS[THREE_INDEX]
            + " VARCHAR NOT NULL); " + "CREATE TABLE " + BUS_STOP + " (" + BUS_STOP_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + BUS_STOP_COLUMNS[TWO_INDEX]
            + " VARCHAR NOT NULL, " + BUS_STOP_COLUMNS[THREE_INDEX]
            + " REAL, " + BUS_STOP_COLUMNS[FOUR_INDEX]
            + " REAL); " + "CREATE TABLE " + TIME + " (" + TIME_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + TIME_COLUMNS[TWO_INDEX]
            + " INTEGER, " + TIME_COLUMNS[THREE_INDEX]
            + " INTEGER, " + TIME_COLUMNS[FOUR_INDEX]
            + " INTEGER); " + "CREATE TABLE " + BIND + " (" + BIND_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + BIND_COLUMNS[TWO_INDEX]
            + " INTEGER, " + BIND_COLUMNS[THREE_INDEX]
            + " INTEGER, " + BIND_COLUMNS[FOUR_INDEX]
            + " INTEGER);";

    public static final String CREATE_ROUTE_TABLE = "CREATE TABLE "
            + ROUTE + " (" + ROUTE_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + ROUTE_COLUMNS[TWO_INDEX]
            + " INTEGER, " + ROUTE_COLUMNS[THREE_INDEX]
            + " VARCHAR NOT NULL);";

    public static final String CREATE_BUS_STOP_TABLE = "CREATE TABLE " + BUS_STOP + " (" + BUS_STOP_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + BUS_STOP_COLUMNS[TWO_INDEX]
            + " VARCHAR NOT NULL, " + BUS_STOP_COLUMNS[THREE_INDEX]
            + " REAL, " + BUS_STOP_COLUMNS[FOUR_INDEX]
            + " REAL);";

    public static final String CREATE_TIME_TABLE = "CREATE TABLE " + TIME + " (" + TIME_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + TIME_COLUMNS[TWO_INDEX]
            + " INTEGER, " + TIME_COLUMNS[THREE_INDEX]
            + " INTEGER, " + TIME_COLUMNS[FOUR_INDEX]
            + " INTEGER, " + TIME_COLUMNS[FIVE_INDEX]
            + " INTEGER);";

    public static final String CREATE_BIND_TABLE = "CREATE TABLE " + BIND + " (" + BIND_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + BIND_COLUMNS[TWO_INDEX]
            + " INTEGER, " + BIND_COLUMNS[THREE_INDEX]
            + " INTEGER);";



    public static final String DROP_ROUTE_TABLE = "DROP TABLE IF EXISTS "
            + ROUTE;
    public static final String DROP_BS_TABLE = "DROP TABLE IF EXISTS "
            + ROUTE;
    public static final String DROP_TIME_TABLE = "DROP TABLE IF EXISTS "
            + ROUTE;
    public static final String DROP_BIND_TABLE = "DROP TABLE IF EXISTS "
            + ROUTE;

    public static final String DROP_GBS_DATABASE = DROP_ROUTE_TABLE
            + DROP_BS_TABLE + DROP_TIME_TABLE + DROP_BIND_TABLE;

    private static final String TAG = DbHelper.class.getSimpleName();

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(CREATE_ROUTE_TABLE);
            db.execSQL(CREATE_BUS_STOP_TABLE);
            db.execSQL(CREATE_TIME_TABLE);
            db.execSQL(CREATE_BIND_TABLE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            Log.d(TAG, CREATE_GBS_DATABASE);
        }

    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL(DROP_GBS_DATABASE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        onCreate(db);
    }

    public long addRoute(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG, values.toString());
        return db.insert(ROUTE, null, values);
    }

    public long addBusStop(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG, values.toString());
        return db.insert(BUS_STOP, null, values);
    }

    public long addTime(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG, values.toString());
        return db.insert(TIME, null, values);
    }

    public long addBind(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG, values.toString());
        return db.insert(BIND, null, values);
    }

    public static void bulkInsertTime(DbHelper dbHelper, ContentValues[] values) {
        SQLiteDatabase sqld = dbHelper.getWritableDatabase();
        sqld.beginTransaction();
        try {
            Log.i(TAG, "bulkinsertTime (dbHelper checked)" + values[0]);
            for (int i = 0; i < values.length; i++) {
                sqld.insert(TIME, null, values[i]);
            }
            sqld.setTransactionSuccessful();
        } finally {
            Log.i(TAG, "bulkinsertTime (dbHelper checked) Transaction complete");

            sqld.endTransaction();
        }

    }

}