package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * This function is used for comparing input year to current year
     * @param inpYear
     * @return 
     */
    public static int compareToCurrentYear(Integer inpYear) {
        int result = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        int currentYear = Integer.parseInt(dtf.format(now));
        if (inpYear > currentYear) {
            result = 1;
        } else if (inpYear < currentYear) {
            result = -1;
        } else result = 0;
        return result;
    }
}
