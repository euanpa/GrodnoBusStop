package by.euanpa.gbs.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtil {

    public static final String LINE_SEPARATOR = "line.separator";

    public static void close(Closeable closeable) throws IOException{
        if(closeable != null){
            closeable.close();
        }
    }
}
