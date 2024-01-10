package tool;

public class Checker {

    /**
     * Boolean to check whether input string code is numeric
     *
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
     *
     * @param date
     * @return
     */
    public static boolean isDateValid(String date) {
        if (date.matches("\\d{2}" + "/" + "\\d{2}" + "/" + "\\d{4}")) {
            String str[] = date.split("/");
            int day = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int year = Integer.parseInt(str[2]);
            if (month < 1 || month > 12) {
                return false;
            }
            if (year % 4 == 0) {
                if (month == 2 && day >= 0 && day <= 28) {
                    return true;
                }
            }
            if (month % 2 == 1) {
                if (day >= 0 && day <= 30) {
                    return true;
                }
            }
            if (month % 2 == 0) {
                if (month == 2 && day >= 0 && day <= 29) {
                    return true;
                } else if (day >= 0 && day <= 31) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Boolean to check expired products
     * @param manufacturingDate
     * @param expirationDate
     * @return 
     */
    public static boolean checkExpiredProducts(String manufacturingDate, String expirationDate) {
        boolean truth = false;
        String mDate[] = manufacturingDate.split("/");
        int mDay = Integer.parseInt(mDate[0]);
        int mMonth = Integer.parseInt(mDate[1]);
        int mYear = Integer.parseInt(mDate[2]);
        String eDate[] = expirationDate.split("/");
        int eDay = Integer.parseInt(eDate[0]);
        int eMonth = Integer.parseInt(eDate[1]);
        int eYear = Integer.parseInt(eDate[2]);
        
        if (mYear > eYear) truth = true;
        else if (mYear < eYear) truth = false;
        if (mYear == eYear) {
            if (mMonth > eMonth) truth = true;
            else if (mMonth < eMonth) truth = false;
            if (mMonth == eMonth){
                if (mDay > eDay) truth = true;
                else truth = false;
            }
        }
        return truth;
    }
}
