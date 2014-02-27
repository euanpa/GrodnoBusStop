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

import by.euanpa.gbs.R;
import by.euanpa.gbs.adapters.BusStopListAdapter;
import by.euanpa.gbs.database.contracts.BusStopContract;

/**
 * Created by google on 12.02.14.
 */
public class BusStopFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView busStopList;
    private BusStopListAdapter busStopListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.bus_stop_fragment, container, false);
        busStopListAdapter = new BusStopListAdapter(getActivity(), null);
        busStopList = (ListView) rootView.findViewById(R.id.bus_stop_list);
        busStopList.setAdapter(busStopListAdapter);
        getLoaderManager().initLoader(0, null, this);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), BusStopContract.BusStopColumns.BUS_STOP_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        busStopListAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        busStopListAdapter.swapCursor(null);
    }
}
