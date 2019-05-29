package eu.profinit.publiccontracts.util;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Contains utils to work with calendar data.
 */
public class DateUtils {

    public static class FORMAT {
        public static String MMyyyy = "MMyyyy";
        public static String ddMMyyyy = "ddMMyyyy";
    }

    /**
     * Divides the calendar interval by months
     * @param fromCal start date
     * @param toCal end date
     * @return list of month intervals
     */
    public static List<Interval> createMonthlyIntervals(Calendar fromCal, Calendar toCal) {
        List<Interval> intervals = new ArrayList<>();
        Calendar curCal = fromCal;
        while(!curCal.after(toCal)) {
            fromCal.set(Calendar.DAY_OF_MONTH, curCal.getActualMinimum(Calendar.DAY_OF_MONTH));
            DateTime fromDate = new DateTime(curCal);
            fromCal.set(Calendar.DAY_OF_MONTH, curCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            DateTime toDate = new DateTime(curCal);
            intervals.add(new Interval(fromDate, toDate));
            curCal.add(Calendar.MONTH,1);
        }
        return intervals;
    }

    /**
     * Converts {@link DateTime} to a formatted string
     * @param date
     * @return
     */
    public static String convertDateTimeToString(DateTime date, String format) {
        if (format==null) {
            format = FORMAT.ddMMyyyy;
        }
        return DateTimeFormat.forPattern(format).print(date);
    }

}
