package by.euanpa.gbs.database.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by google on 30.01.14.
 */
public class TimeContract {
    public static final String AUTHORITY = "by.euanpa.gbs.database.GbsProvider";

    public TimeContract() {
    }

    public static final class TimeColumns implements BaseColumns {

        private TimeColumns() {
        }
        public static final String TIME_PATH = "time";

        public static final Uri TIME_URI = Uri.parse("content://" + AUTHORITY + "/" + TIME_PATH);


        public static final String TIME_TYPE = "vnd.android.cursor.dir/" + TIME_PATH;

        public static final String TIME_ID = _ID;
        public static final String HOUR = "HOUR";
        public static final String MINUTE = "MINUTE";
        public static final String DAY_TYPE = "DAY_TYPE";
        public static final String TIME_BIND_ID = "TIME_BIND_ID";

    }
}