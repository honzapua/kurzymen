package kurzy.men.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 27.11.2016.
 */
public class DateToIsoStringTest {

    @Test(expected = NullPointerException.class)
    public void doesNotAcceptNull() throws Exception {
        DateUtils.dateToISOString(null);
    }

    @Test
    public void formats() throws Exception {
        Calendar cal = new GregorianCalendar(2016,11,31,12,34,56);
        String expected = "2016-12-31";
        String actual = DateUtils.dateToISOString(cal.getTime());
        Assert.assertEquals(expected, actual);
    }
}
