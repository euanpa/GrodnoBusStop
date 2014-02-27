package by.euanpa.gbs.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by google on 30.01.14.
 */
public class BusStopContract {

    public static final String AUTHORITY = "by.euanpa.gbs.database.GbsProvider";

    public BusStopContract() {
    }

    public static final class BusStopColumns implements BaseColumns {

        private BusStopColumns() {
        }

        public static final String BUS_STOP_PATH = "bus_stop";

        public static final Uri BUS_STOP_URI = Uri.parse("content://" + AUTHORITY + "/" + BUS_STOP_PATH);

        public static final String BUS_STOP_TYPE = "vnd.android.cursor.dir/" + BUS_STOP_PATH;

        public static final String BUS_STOP_ID = _ID;
        public static final String BUS_STOP_NAME = "BUS_STOP_NAME";
        public static final String LATITUDE = "LATITUDE";
        public static final String LONGITUDE = "LONGITUDE";

    }
}