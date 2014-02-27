package by.euanpa.gbs.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by google on 30.01.14.
 */
public class RouteContract {
    public static final String AUTHORITY = "by.euanpa.gbs.database.GbsProvider";

    public RouteContract() {
    }

    public static final class RouteColumns implements BaseColumns {

        private RouteColumns() {
        }
        public static final String ROUTE_PATH = "route";

        public static final Uri ROUTE_URI = Uri.parse("content://" + AUTHORITY + "/" + ROUTE_PATH);

        public static final String ROUTE_TYPE = "vnd.android.cursor.dir/" + ROUTE_PATH;

        public static final String ROUTE_ID = _ID;
        public static final String NUMBER_ROUTE = "NUMBER_ROUTE";
        public static final String NAME_ROUTE = "NAME_ROUTE";

    }
}
