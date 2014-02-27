package by.euanpa.gbs.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Currency;

import by.euanpa.gbs.R;
import by.euanpa.gbs.database.contracts.BusStopContract;

/**
 * Created by google on 14.02.14.
 */
public class BusStopListAdapter extends CursorAdapter {

    public BusStopListAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return View.inflate(context, R.layout.bus_stop_item_adapter, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView busStopName = (TextView) view.findViewById(R.id.bus_stop_name);

        busStopName.setText(cursor.getString(cursor.getColumnIndex(BusStopContract.BusStopColumns.BUS_STOP_NAME)));
    }
}
