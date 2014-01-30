package by.euanpa.gbs.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.util.Log;

import by.euanpa.gbs.database.contracts.BindContract;
import by.euanpa.gbs.database.contracts.BusStopContract;
import by.euanpa.gbs.database.contracts.RouteContract;
import by.euanpa.gbs.database.contracts.TimeContract;


/**
 * Created by google on 30.01.14.
 */
public class GbsProvider extends ContentProvider {

    DbHelper dbHelper;


    private static final int ROUTE_INDEX = 0;
    private static final int BUS_STOP_INDEX = 1;
    private static final int TIME_INDEX = 2;
    private static final int BIND_INDEX = 3;

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);


    private static final String TAG = GbsProvider.class.getSimpleName();

    static {

        sURIMatcher.addURI(RouteContract.AUTHORITY,
                RouteContract.RouteColumns.ROUTE_PATH, ROUTE_INDEX);
        sURIMatcher.addURI(BusStopContract.AUTHORITY,
                BusStopContract.BusStopColumns.BUS_STOP_PATH, BUS_STOP_INDEX);
        sURIMatcher.addURI(TimeContract.AUTHORITY,
                TimeContract.TimeColumns.TIME_PATH, TIME_INDEX);
        sURIMatcher.addURI(BindContract.AUTHORITY,
                BindContract.BindColumns.BIND_PATH, BIND_INDEX);


    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case ROUTE_INDEX:
                return RouteContract.RouteColumns.ROUTE_TYPE;
            case BUS_STOP_INDEX:
                return BusStopContract.BusStopColumns.BUS_STOP_TYPE;
            case TIME_INDEX:
                return TimeContract.TimeColumns.TIME_TYPE;
            case BIND_INDEX:
                return BindContract.BindColumns.BIND_TYPE;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri _uri = null;
        long id = 0;
        switch (sURIMatcher.match(uri)) {
            case ROUTE_INDEX:
                id = dbHelper.addRoute(values);

                _uri = ContentUris.withAppendedId(
                        RouteContract.RouteColumns.ROUTE_URI, id);
                getContext().getContentResolver().notifyChange(_uri, null);

            case BUS_STOP_INDEX:
                id = dbHelper.addBusStop(values);

                _uri = ContentUris.withAppendedId(
                        BusStopContract.BusStopColumns.BUS_STOP_URI, id);
                getContext().getContentResolver().notifyChange(_uri, null);

            case TIME_INDEX:
                id = dbHelper.addTime(values);

                _uri = ContentUris.withAppendedId(
                        TimeContract.TimeColumns.TIME_URI, id);
                getContext().getContentResolver().notifyChange(_uri, null);

            case BIND_INDEX:

                id = dbHelper.addBind(values);

                _uri = ContentUris.withAppendedId(
                        BindContract.BindColumns.BIND_URI, id);
                getContext().getContentResolver().notifyChange(_uri, null);
                break;
            default:
                throw new SQLException("Failed to insert row into " + uri);
        }
        return _uri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        Log.i(TAG, "bulk insert (Provider checked) " + values[0]);
        DbHelper.bulkInsertTime(dbHelper, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return values.length;

    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext(), null, null, 0);
        if (dbHelper != null) {
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (sURIMatcher.match(uri)) {
            case ROUTE_INDEX:
               //cursor = dbHelper.getTweets(projection,selection,
               //       selectionArgs, sortOrder);
               // cursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;

            default:
                throw new SQLException("Failed to insert row into " + uri);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
