package by.euanpa.gbs.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import by.euanpa.gbs.R;
import by.euanpa.gbs.database.contracts.RouteContract;

/**
 * Created by google on 14.02.14.
 */
public class RouteListAdapter extends CursorAdapter {


    public RouteListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return View.inflate(context, R.layout.route_item_adapter, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView routeNumber = (TextView) view.findViewById(R.id.route_number);
        TextView routeName = (TextView) view.findViewById(R.id.route_name);

        routeNumber.setText(cursor.getString(cursor.getColumnIndex(RouteContract.RouteColumns.NUMBER_ROUTE)));
        routeName.setText(cursor.getString(cursor.getColumnIndex(RouteContract.RouteColumns.NAME_ROUTE)));

    }
}
