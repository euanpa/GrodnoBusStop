package by.euanpa.gbs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import by.euanpa.gbs.database.contracts.BindContract;
import by.euanpa.gbs.database.contracts.BusStopContract;
import by.euanpa.gbs.database.contracts.RouteContract;
import by.euanpa.gbs.database.contracts.TimeContract;

/**
 * Created by google on 30.01.14.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "gbs.db";
    public static final String ROUTE = "ROUTE";
    public static final String BUS_STOP = "BUS_STOP";
    public static final String TIME = "TIME";
    public static final String BIND = "BIND";

    public static final String[] ROUTE_COLUMNS = {
            RouteContract.RouteColumns.ROUTE_ID, RouteContract.RouteColumns.NUMBER_ROUTE,
            RouteContract.RouteColumns.NAME_ROUTE};

    public static final String[] BUS_STOP_COLUMNS = {
            BusStopContract.BusStopColumns.BUS_STOP_ID, BusStopContract.BusStopColumns.BUS_STOP_NAME,
            BusStopContract.BusStopColumns.LATITUDE, BusStopContract.BusStopColumns.LONGITUDE};

    public static final String[] TIME_COLUMNS = {
            TimeContract.TimeColumns.TIME_ID, TimeContract.TimeColumns.HOUR,
            TimeContract.TimeColumns.MINUTE, TimeContract.TimeColumns.DAY_TYPE,
            TimeContract.TimeColumns.TIME_BIND_ID};

    public static final String[] BIND_COLUMNS = {
            BindContract.BindColumns.BIND_ID, BindContract.BindColumns.BIND_ROUTE_ID,
            BindContract.BindColumns.BIND_BUS_STOP_ID,BindContract.BindColumns.BIND_NEXT_BUS_STOP_ID};

    public static final int ONE_INDEX = 0;
    public static final int TWO_INDEX = 1;
    public static final int THREE_INDEX = 2;
    public static final int FOUR_INDEX = 3;
    public static final int FIVE_INDEX = 4;


    public static final String CREATE_ROUTE_TABLE = "CREATE TABLE "
            + ROUTE + " (" + ROUTE_COLUMNS[ONE_INDEX]
            + " INTEGER PRIMARY KEY, " + ROUTE_COLUMNS[TWO_INDEX]
            + " VARCHAR, " + ROUTE_COLUMNS[THREE_INDEX]
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
            + " INTEGER, " + BIND_COLUMNS[FOUR_INDEX]
            + " INTEGER);";



    public static final String DROP_ROUTE_TABLE = "DROP TABLE IF EXISTS "
            + ROUTE;
    public static final String DROP_BS_TABLE = "DROP TABLE IF EXISTS "
            + BUS_STOP;
    public static final String DROP_TIME_TABLE = "DROP TABLE IF EXISTS "
            + TIME;
    public static final String DROP_BIND_TABLE = "DROP TABLE IF EXISTS "
            + BIND;

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
            //Log.d(TAG, CREATE_GBS_DATABASE);
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

    public long addItem(ContentValues values, String table) {
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG, values.toString());
        return db.insert(table, null, values);
    }

    public Cursor getItems(String table, String[] projection, String selection,
                            String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(table, projection, selection,
                selectionArgs, null, null, sortOrder);
        return cursor;
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

    public int deleteTable(String table, String selection, String[] selectionArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int delete = 0;
        try {
            db.beginTransaction();
            delete  = db.delete(table, selection, selectionArgs);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return delete;
    }
}