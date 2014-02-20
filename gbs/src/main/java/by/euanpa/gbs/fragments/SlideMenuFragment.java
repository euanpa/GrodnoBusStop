package by.euanpa.gbs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import by.euanpa.gbs.MainActivity;
import by.euanpa.gbs.R;

/**
 * Created by google on 12.02.14.
 */
public class SlideMenuFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] colors = {"BusStop","Route"};
        ArrayAdapter<String> slideMenuAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, colors);
        setListAdapter(slideMenuAdapter);
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        Fragment newContent = null;
        switch (position) {
            case 0:
                newContent = new BusStopFragment();
                break;
            case 1:
                newContent = new RouteFragment();
                break;
            }
        if (newContent != null)
            switchFragment(newContent);
    }

    // the meat of switching the above fragment
    private void switchFragment(Fragment fragment) {

            MainActivity fca = (MainActivity) getActivity();
            fca.switchContent(fragment);

    }


}
