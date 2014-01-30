package by.euanpa.gbs.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by google on 30.01.14.
 */
public class Contract {

    public static final String AUTHORITY = "by.euanpa.gbs.database.GbsProvider";

    public Contract() {
    }

    public static final class GbsColumns implements BaseColumns {

        private GbsColumns() {
        }
        public static final String ROUTE_PATH = "route";
        public static final String BUS_STOP_PATH = "bus_stop";
        public static final String TIME_PATH = "time";
        public static final String BIND_PATH = "bind";



        public static final Uri ROUTE_URI = Uri.parse("content://" + AUTHORITY + "/" + ROUTE_PATH);
        public static final Uri BUS_STOP_URI = Uri.parse("content://" + AUTHORITY + "/" + BUS_STOP_PATH);
        public static final Uri TIME_URI = Uri.parse("content://" + AUTHORITY + "/" + TIME_PATH);
        public static final Uri BIND_URI = Uri.parse("content://" + AUTHORITY + "/" + BIND_PATH);


        public static final String ROUTE_TYPE = "vnd.android.cursor.dir/" + ROUTE_PATH;
        public static final String BUS_STOP_TYPE = "vnd.android.cursor.dir/" + ROUTE_PATH;



        public static final String ROUTE_ID = _ID;
        public static final String NUMBER_ROUTE = "NUMBER_ROUTE";
        public static final String NAME_ROUTE = "NAME_ROUTE";

        public static final String BUS_STOP_ID = _ID;
        public static final String BUS_STOP_NAME = "BUS_STOP_NAME";
        public static final String LATITUDE = "LATITUDE";
        public static final String LONGITUDE = "LONGITUDE";

        public static final String TIME_ID = _ID;
        public static final String HOUR = "HOUR";
        public static final String MINUTE = "MINUTE";
        public static final String DAY_TYPE = "DAY_TYPE";
        public static final String BS_PLUS_ROUTE = "BS_PLUS_ROUTE";


        public static final String BIND_ID = _ID;
        public static final String BIND_ROUTE_ID = "ROUTE_ID";
        public static final String BIND_BUS_STOP_ID = "BUS_STOP_ID";




    }


}
