package by.euanpa.gbs;


import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import by.euanpa.gbs.database.contracts.BindContract;
import by.euanpa.gbs.database.contracts.BusStopContract;
import by.euanpa.gbs.database.contracts.RouteContract;
import by.euanpa.gbs.database.contracts.TimeContract;
import by.euanpa.gbs.fragments.BusStopFragment;
import by.euanpa.gbs.fragments.SlideMenuFragment;
import by.euanpa.gbs.tasks.UploadBind;
import by.euanpa.gbs.tasks.UploadBusStops;
import by.euanpa.gbs.tasks.UploadRoutes;
import by.euanpa.gbs.utility.slidingmenu.SlidingFragmentActivity;
import by.euanpa.gbs.utility.slidingmenu.SlidingMenu;

public class MainActivity extends SlidingFragmentActivity {

    private Fragment mContent;
    protected ListFragment mFrag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextHolder.getInstance().setContext(getApplicationContext());
        if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }
        if (mContent == null) {
            mContent = new BusStopFragment();
        }

        // set the Above View
        setContentView(R.layout.content_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mContent)
                .commit();

        // set the Behind View
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new SlideMenuFragment())
                .commit();

        // customize the SlidingMenu

        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        setBehindContentView(R.layout.menu_frame);
        if (savedInstanceState == null) {

            mFrag = (ListFragment) this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
        }

        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }

    public void switchContent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        showContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drop:
                return true;
            case R.id.recordDb:
                new UploadRoutes().execute();
                new UploadBusStops().execute();
                new UploadBind().execute();
                return true;
            case R.id.deleteDb:
                ContentResolver contentResolver = getContentResolver();
                deleteEntity(RouteContract.RouteColumns.ROUTE_URI, contentResolver);
                deleteEntity(BusStopContract.BusStopColumns.BUS_STOP_URI, contentResolver);
                deleteEntity(BindContract.BindColumns.BIND_URI, contentResolver);
                deleteEntity(TimeContract.TimeColumns.TIME_URI, contentResolver);
                Toast.makeText(this, "Delete Database", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteEntity(final Uri uri, final ContentResolver contentResolver) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                contentResolver.delete(uri, null, null);

            }
        }).start();
    }
}