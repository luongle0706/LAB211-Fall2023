package tool;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Checker {

    /**
     * Boolean to check whether input string code is numeric
     * @param msg
     * @return
     */
    public static boolean isNumeric(String msg) {
        if (msg == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(msg);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Boolean to check whether input date is valid
     * @param date
     * @return
     */
    public static boolean isDateValid(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(date, dtf);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Boolean to check whether input time is valid
     * @param time
     * @return 
     */
    public static boolean isTimeValid(String time) {
        DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalTime.parse(time, strictTimeFormatter);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }
        /**
     * Boolean to compare two departure times
     * @param departureTime1
     * @param departureTime2
     * @return
     */
    public static boolean compareDepartureTime(String departureTime1, String departureTime2) {
        boolean truth = false;
        String mDate[] = departureTime1.split("/");
        int mDay = Integer.parseInt(mDate[0]);
        int mMonth = Integer.parseInt(mDate[1]);
        int mYear = Integer.parseInt(mDate[2]);
        String eDate[] = departureTime2.split("/");
        int eDay = Integer.parseInt(eDate[0]);
        int eMonth = Integer.parseInt(eDate[1]);
        int eYear = Integer.parseInt(eDate[2]);

        if (mYear > eYear) {
            truth = true;
        } else if (mYear < eYear) {
            truth = false;
        }
        if (mYear == eYear) {
            if (mMonth > eMonth) {
                truth = true;
            } else if (mMonth < eMonth) {
                truth = false;
            }
            if (mMonth == eMonth) {
                if (mDay > eDay) {
                    truth = true;
                } else {
                    truth = false;
                }
            }
        }
        return truth;
    }
}
