package by.euanpa.gbs.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import by.euanpa.gbs.ContextHolder;
import by.euanpa.gbs.database.contracts.BusStopContract;
import by.euanpa.gbs.http.HttpManager;
import by.euanpa.gbs.models.BusStopModel;
import by.euanpa.gbs.models.RouteModel;

/**
 * Created by google on 20.02.14.
 */
public class UploadBusStops extends AsyncTask<Void, Void, JSONObject> {

    private static final String TAG = UploadBusStops.class.getSimpleName();
    private HttpClient mClient;
    private JSONObject jsObject;
    private JSONArray jArray;
    private JSONObject jso;

    @Override
    protected void onPreExecute() {
        mClient = (new HttpManager()).getClient();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {

            String responseRoutes;
            responseRoutes = new HttpManager().loadAsString("http://1-dot-bsupdateprovider.appspot.com/busstops");

            jsObject = new JSONObject(responseRoutes);
        }  catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Context ctx = ContextHolder.getInstance().getContext();
        try {
            Log.d(TAG, "Timeline: "
                    + jsonObject.getString("entitiesCount"));

            jArray = jsonObject.getJSONArray("entities");

            for(int i = 0; i < jArray.length(); i ++){
                jso = jArray.getJSONObject(i);

                ctx.getContentResolver()
                        .insert(BusStopContract.BusStopColumns.BUS_STOP_URI,
                                BusStopModel.toValues(jso));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
