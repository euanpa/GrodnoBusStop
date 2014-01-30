package by.euanpa.gbs.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by google on 30.01.14.
 */
public class BindContract {
    public static final String AUTHORITY = "by.euanpa.gbs.database.GbsProvider";

    public BindContract() {
    }

    public static final class BindColumns implements BaseColumns {

        private BindColumns() {
        }
        public static final String BIND_PATH = "bind";

        public static final Uri BIND_URI = Uri.parse("content://" + AUTHORITY + "/" + BIND_PATH);

        public static final String BIND_TYPE = "vnd.android.cursor.dir/" + BIND_PATH;

        public static final String BIND_ID = _ID;
        public static final String BIND_ROUTE_ID = "ROUTE_ID";
        public static final String BIND_BUS_STOP_ID = "BUS_STOP_ID";
        public static final String BIND_NEXT_BUS_STOP_ID = "NEXT_BUS_STOP_ID";




    }
}
