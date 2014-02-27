package by.euanpa.gbs;

import android.content.Context;

/**
 * Created by google on 18.02.14.
 */
public class ContextHolder {

    private static ContextHolder instance;
    private Context mContext;

    public static ContextHolder getInstance() {
        if (instance == null) {
            instance = new ContextHolder();
        }
        return instance;
    }



    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }

}

