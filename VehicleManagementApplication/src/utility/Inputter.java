package utility;

import java.util.Scanner;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    /**
     * This function is used for getting a string with uppercase letter
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
     * This function is used for getting a string with no blank space
     *
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
     * This function is used for getting a string with conditions
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
     * This function is used for getting an integer
     *
     * @param inpMsg
     * @param errMsg
     * @return
     */
    public static int getInt(String inpMsg, String errMsg) {
        int result = 0;
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
     * This function is used for getting a positive integer
     * 
     * @param inpMsg
     * @param errMsg
     * @return 
     */
    public static int getPositiveInt(String inpMsg, String errMsg) {
        int result = -1;
        do {
            result = getInt(inpMsg, "Invalid input");
            if (result < 0) {
                System.out.println(errMsg);
            }
        } while (result < 0);
        return result;
    }

    /**
     * This function is used for asking yes/no question
     * @param inpMsg
     * @param errMsg
     * @return
     */
    public static boolean checkYesOrNo(String inpMsg, String errMsg) {
        while (true) {
            String input = getString(inpMsg);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("errMsg");
            }
        }
    }
    
    /**
     * This function is used for parsing positive integer
     * 
     * @param inpStr
     * @param errMsg
     * @return 
     */
    public static int parsePositiveInt(String inpStr, String errMsg) {
        int result = -1;
        do {
            try {
                result = Integer.parseInt(inpStr);
                if (result < 0) {
                    System.out.println(errMsg);
                }
            } catch (NumberFormatException e ) {
                System.out.println(errMsg);
            }
        } while (result < 0);
        return result;
    }
}
