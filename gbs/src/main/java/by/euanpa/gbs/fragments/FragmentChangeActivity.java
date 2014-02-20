package by.euanpa.gbs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import by.euanpa.gbs.BaseActivity;
import by.euanpa.gbs.R;
import by.euanpa.gbs.utility.slidingmenu.SlidingMenu;

/**
 * Created by google on 12.02.14.
 */
public class FragmentChangeActivity extends BaseActivity {

    private Fragment mContent;


    public FragmentChangeActivity() {
        super(R.string.changing_fragments);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the Above View
        if (savedInstanceState != null)
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        if (mContent == null)
            mContent = new BusStopFragment();

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
        getSlidingMenu().showContent();
    }

}
