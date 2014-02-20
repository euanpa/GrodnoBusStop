package by.euanpa.gbs.models;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import by.euanpa.gbs.database.contracts.BindContract;
import by.euanpa.gbs.database.contracts.RouteContract;

/**
 * Created by google on 18.02.14.
 */
public class BindModel extends JSONModel {

    private static final String BUS_STOP_ID = "busStopId";
    private static final String NEXT_BUS_STOP_ID = "nextBusStopId";
    private static final String ID = "id";
    private static final String ROUTE_ID = "routeId";
    private static BindModel model;

    public BindModel(JSONObject jsonObject) {
        super(jsonObject);
    }


    public String getBusStopId() throws JSONException {
        return getString(BUS_STOP_ID);
    }

    public String getNextBusStopId() throws JSONException {
        return getString(NEXT_BUS_STOP_ID);
    }

    public String getBindId() throws JSONException {
        return getString(ID);
    }

    public String getRouteId() throws JSONException {
        return getString(ROUTE_ID);
    }

    public static BindModel createModel(JSONObject jsonObject){
        return new BindModel(jsonObject);
    }

    public static ContentValues toValues(JSONObject jso) throws JSONException {
        ContentValues values = new ContentValues();
        model = createModel(jso);
        values.put(BindContract.BindColumns.BIND_ID, model.getBindId());
        values.put(BindContract.BindColumns.BIND_BUS_STOP_ID, model.getBusStopId());
        values.put(BindContract.BindColumns.BIND_ROUTE_ID, model.getRouteId());
        values.put(BindContract.BindColumns.BIND_NEXT_BUS_STOP_ID, model.getNextBusStopId());

        return values;
    }
}
