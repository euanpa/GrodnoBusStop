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
import java.util.zip.GZIPInputStream;

import by.euanpa.gbs.ContextHolder;
import by.euanpa.gbs.database.contracts.RouteContract;
import by.euanpa.gbs.http.HttpManager;
import by.euanpa.gbs.models.RouteModel;

/**
 * Created by google on 15.02.14.
 */
public class UploadRoutes extends AsyncTask<Void,Void,JSONObject> {
    private static final String TAG = UploadRoutes.class.getSimpleName();
    private JSONObject jsObject;
    private HttpClient mClient;
    private GZIPInputStream in;
    private JSONArray jArray;
    private JSONObject jso;
    private Context ctx;

    @Override
    protected void onPreExecute() {
        mClient = (new HttpManager()).getClient();
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        try {

            String responseRoutes;
            responseRoutes = new HttpManager().loadAsString("http://1-dot-bsupdateprovider.appspot.com/routes");

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
    protected void onPostExecute(JSONObject array) {
        ctx = ContextHolder.getInstance().getContext();
        try {
            Log.d(TAG, "Timeline: "
                    + array.getString("entitiesCount"));

        jArray = array.getJSONArray("entities");

        for(int i = 0; i < jArray.length(); i ++){
            jso = jArray.getJSONObject(i);

                ctx.getContentResolver()
                .insert(RouteContract.RouteColumns.ROUTE_URI,
                        RouteModel.toValues(jso));
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
