package by.euanpa.gbs.models;

import android.content.ContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.euanpa.gbs.database.contracts.BindContract;
import by.euanpa.gbs.database.contracts.TimeContract;

/**
 * Created by google on 17.02.14.
 */
public class TimeModel extends JSONModel {

    private static final String TIME = "timeRecords";
    private static final String BIND_ID = "id";

    List<HashMap<Integer, int[]>> timeTable = new ArrayList<HashMap<Integer, int[]>>();
    HashMap<Integer,int[]> timeArray = new HashMap<Integer,int[]>();

    public TimeModel(JSONObject jsonObject) {
        super(jsonObject);
    }

    public HashMap<Integer,int[]> getTimeArray(String typeDay) throws JSONException {

        for (int i = 0; i < 24; i++){
            JSONArray day = null;
            try {
                day = getSubObject(TIME).getJSONObject(typeDay).getJSONArray(Integer.toString(i));
            } catch (JSONException e) {
                //e.printStackTrace();
                continue;
            }
            if(day != null){
                    int[] minute = new int[day.length()];
                    for(int j = 0; j < day.length(); j++){
                        minute[j] = day.getInt(j);
                    }
                    timeArray.put(i, minute);
                }

        }
        return timeArray;
    }

    public List<HashMap<Integer, int[]>> getTimeTable() throws JSONException {

        String[] hui = {"WEEKDAY", "SATURDAY", "SUNDAY"};
        for(String h : hui){
            timeTable.add(getTimeArray(h));
        }
        return timeTable;
    }

    public static TimeModel createModel(JSONObject jsonObject){
        return new TimeModel(jsonObject);
    }

    public static ContentValues[] listToValues(JSONObject jso) throws JSONException {
        TimeModel model = createModel(jso);
        List<HashMap<Integer, int[]>> list = model.getTimeTable();
        ContentValues[] values = new ContentValues[getCountRecords(list)];
        int h = 0;
        for (int i = 0; i < 3; i++) {
            HashMap<Integer, int[]> timeArray = list.get(i);
            for(int j = 0; j < 24; j++){
                if(timeArray.containsKey(j)){

                for(int k = 0; k < timeArray.get(j).length; k++){
                    values[h] = new ContentValues();
                    values[h].put(TimeContract.TimeColumns.TIME_BIND_ID, model.getBindId());
                    values[h].put(TimeContract.TimeColumns.DAY_TYPE, i);
                    values[h].put(TimeContract.TimeColumns.HOUR, j);
                    values[h].put(TimeContract.TimeColumns.MINUTE, timeArray.get(j)[k]);
                    h++;
                }
              }

            }
        }
        return values;
    }

    public static int getCountRecords(List<HashMap<Integer, int[]>> list){

        int h = 0;
        for (int i = 0; i < 3; i++) {
            HashMap<Integer, int[]> timeArray = list.get(i);
            for(int j = 0; j < 24; j++){
                if(timeArray.containsKey(j)){

                    for(int k = 0; k < timeArray.get(j).length; k++){
                        h++;
                    }
                }

            }
        }
        return h;
    }

    private String getBindId() throws JSONException {
        return getString(BIND_ID);
    }
}
