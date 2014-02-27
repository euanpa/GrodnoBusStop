package by.euanpa.gbs.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by google on 20.02.14.
 */
public class RoutesOnBusStopContract {
    public static final String AUTHORITY = "by.euanpa.gbs.database.GbsProvider";

    public RoutesOnBusStopContract() {
    }

    public static final class RoutesOnBusStopColumns implements BaseColumns {

        private RoutesOnBusStopColumns() {
        }
        public static final String ROBS_PATH = "robs";

        public static final Uri ROBS_URI = Uri.parse("content://" + AUTHORITY + "/" + ROBS_PATH);


        public static final String ROBS_TYPE = "vnd.android.cursor.dir/" + ROBS_PATH;

        public static final String ROUTE_NUMBER = "ROUTE_NUMBER";
        public static final String ROUTE_NAME = "ROUTE_NUMBER";

    }
}
