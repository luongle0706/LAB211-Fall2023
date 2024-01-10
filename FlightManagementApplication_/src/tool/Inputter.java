package tool;

import java.util.Scanner;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Get a string with uppercase letter
     *
     * @param inputMsg
     * @return
     */
    public static String getString(String inputMsg) {
        System.out.println(inputMsg);
        String str = sc.nextLine().trim().toUpperCase();
        return str;
    }
    
    /**
     * Get a string with no blank space
     * @param inputMsg
     * @param errorMsg
     * @return 
     */
    public static String getNoBlankString(String inputMsg, String errorMsg) {
        String str = null;
        do {
            str = getString(inputMsg);
            if (str.isEmpty()) {
                System.out.println(errorMsg);
            }
        } while (str.isEmpty());
        return str;
    }
    
    /**
     * Get a string with conditions
     *
     * @param inpMsg
     * @param errMsg
     * @param regex
     * @return
     */
    public static String getStringWithConditions(String inpMsg, String errMsg, String regex) {
        String str = getString(inpMsg);
        if (!str.matches(regex)) {
            System.out.println(errMsg);
            str = null;
        }
        return str;
    }

    /**
     * Get an integer
     *
     * @param inpMsg
     * @param errMsg
     * @return
     */
    public static int getInt(String inpMsg, String errMsg) {
        int result = -1;
        boolean isInteger = false;
        do {
            try {
                result = Integer.parseInt(getString(inpMsg));
                isInteger = true;
                break;
            } catch (NumberFormatException e) {
                System.out.println(errMsg);
            }
        } while (!isInteger);
        return result;
    }

    /**
     * Boolean to ask user whether they want to continue to do something
     *
     * @param inpMsg
     * @param errMsg
     * @return
     */
    public static boolean toContinue(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        while (true) {
            String input = sc.nextLine().trim().toUpperCase();
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println(errMsg);
            }
        }
    }
    
    /**
     * This function is used to get flight number from keyboard
     * @param inpMsg
     * @param errMsg
     * @param regex
     * @return 
     */
    public static String getFlightNumber(String inpMsg, String errMsg, String regex){
        String flightNumber = getStringWithConditions(inpMsg, errMsg, regex);
        return flightNumber;
    }
    
    /**
     * This function is used to get date from keyboard
     * @param inpMsg
     * @param errMsg
     * @return 
     */
    public static String getDate(String inpMsg, String errMsg){
        String date = getString(inpMsg);
        if (!Checker.isDateValid(date)) {
            System.out.println(errMsg);
            date = null;
        }
        return date;
    }
    
    /**
     * This function is used to get date and time from keyboard
     * @param inpMsg
     * @param errMsg
     * @return 
     */
    public static String getDateTime(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        String dateTime = sc.nextLine().trim();
        String[] str = dateTime.split(" ");
        if (Checker.isDateValid(str[0]) && Checker.isTimeValid(str[1])) {
            return dateTime;
        } else return errMsg;
    }
}
