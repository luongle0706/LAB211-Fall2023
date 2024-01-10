package tool;

import java.util.Scanner;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Get a string with uppercase and no blank space
     *
     * @param inputMsg
     * @param errorMsg
     * @return
     */
    public static String getString(String inputMsg, String errorMsg) {
        String str = null;
        System.out.println(inputMsg);
        do {
            str = sc.nextLine().trim().toUpperCase();
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
        System.out.println(inpMsg);
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.matches(regex)) {
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    /**
     * Get an integer
     *
     * @param inpMsg
     * @param errMsg
     * @return
     */
    public static int getInt(String inpMsg, String errMsg) {
        int result = 0;
        boolean isInteger = false;
        System.out.println(inpMsg);
        do {
            try {
                result = Integer.parseInt(sc.nextLine());
                isInteger = true;
                break;
            } catch (NumberFormatException e) {
                System.out.println(errMsg);
            }
        } while (!isInteger);
        return result;
    }

    /**
     * Display the header of a list of products
     */
    public static void displayListHeader() {
        System.out.println("===============================================================================================");
        System.out.println("|Code   |Name                |Producer            |Manufacturing date|Expiration date|Quantity|");
        System.out.println("===============================================================================================");

    }

    public static void displayRegisteredProductListHeader() {
        System.out.println("===================================================");
        System.out.println("|Code   |Name                |Producer            |");
        System.out.println("===================================================");

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

}
