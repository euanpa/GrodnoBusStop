package by.euanpa.gbs.models;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import by.euanpa.gbs.database.contracts.BusStopContract;
import by.euanpa.gbs.database.contracts.RouteContract;

/**
 * Created by google on 17.02.14.
 */
public class BusStopModel extends JSONModel {

    private static final String BUS_STOP_ID = "id";

    private static final String BUS_STOP_NAME = "name";

    private static final String LONGITUDE = "longitude";

    private static final String LATITUDE = "latitude";
    private static BusStopModel model;

    public BusStopModel(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getId() throws JSONException {
        return getString(BUS_STOP_ID);
    }

    public  String getName() throws JSONException {
        return getString(BUS_STOP_NAME);
    }

    public String getLatitude() throws JSONException {
        return getString(LATITUDE);
    }

    public String getLongitude() throws JSONException {
        return getString(LONGITUDE);
    }

    public static BusStopModel createModel(JSONObject jsonObject){
        return new BusStopModel(jsonObject);
    }

    public static ContentValues toValues(JSONObject jso) throws JSONException {
        ContentValues values = new ContentValues();
        model = createModel(jso);
        values.put(BusStopContract.BusStopColumns.BUS_STOP_ID, model.getId());
        values.put(BusStopContract.BusStopColumns.BUS_STOP_NAME, model.getName());
        values.put(BusStopContract.BusStopColumns.LATITUDE, model.getLatitude());
        values.put(BusStopContract.BusStopColumns.LONGITUDE, model.getLongitude());
        return values;
    }

}
