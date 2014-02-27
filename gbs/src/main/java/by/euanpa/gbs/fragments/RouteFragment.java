package by.euanpa.gbs.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import by.euanpa.gbs.R;
import by.euanpa.gbs.adapters.BusStopListAdapter;
import by.euanpa.gbs.adapters.RouteListAdapter;
import by.euanpa.gbs.database.contracts.RouteContract;

/**
 * Created by google on 12.02.14.
 */
public class RouteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RouteListAdapter routeListAdapter;
    private ListView routeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.route_fragment, container, false);

        routeList = (ListView) rootView.findViewById(R.id.route_list);
        routeListAdapter = new RouteListAdapter(getActivity(),null,true);
        routeList.setAdapter(routeListAdapter);
        getLoaderManager().initLoader(0, null, this);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), RouteContract.RouteColumns.ROUTE_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        routeListAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        routeListAdapter.swapCursor(null);
    }
}
