package kurzy.men.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by honzapua on 13.11.2016.
 */
public final class DateUtils {

    public static String dateToISOString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private DateUtils() {
    }
}
