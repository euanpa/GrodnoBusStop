package by.euanpa.gbs.models;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import by.euanpa.gbs.database.contracts.RouteContract;

/**
 * Created by google on 17.02.14.
 */
public class RouteModel extends JSONModel {

    private static final String ROUTE_NUMBER = "number";
    private static final String ROUTE_ID = "id";
    private static final String ROUTE_NAME = "name";
    private static RouteModel model;

    public RouteModel(JSONObject jsonObject){
        super(jsonObject);
    }

    public String getNumber() throws JSONException {

        return getString(ROUTE_NUMBER);
    }

    public  String getId() throws JSONException {
        return getString(ROUTE_ID);
    }

    public  String getName() throws JSONException {

        return getString(ROUTE_NAME);

    }

    public static RouteModel createModel(JSONObject jsonObject){
        return new RouteModel(jsonObject);
    }

    public static ContentValues toValues(JSONObject jso) throws JSONException {
        ContentValues values = new ContentValues();
        model = createModel(jso);
        values.put(RouteContract.RouteColumns.ROUTE_ID, model.getId());
        values.put(RouteContract.RouteColumns.NAME_ROUTE, model.getName());
        values.put(RouteContract.RouteColumns.NUMBER_ROUTE, model.getNumber());
        return values;
    }

}
