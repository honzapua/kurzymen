package kurzy.men.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Obecne utility pro praci s datem.
 */
public final class DateUtils {

    /**
     * Formatuje datum datum do ISO formatu ovsem bez casu
     * @param date datum k naformatovani
     * @return textova reprezentace datumu v ISO formatu
     */
    public static String dateToISOString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private DateUtils() {
    }
}
