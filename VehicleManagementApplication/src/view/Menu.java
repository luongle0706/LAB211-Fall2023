package view;

public class Menu {
    /**
     * This function displays main menu
     * 
     */
    public static void mainMenu() {
        System.out.println("============== VEHICLE MANAGEMENT APPLICATION ==============");
        System.out.println("|1. Add new vehicle.                                       |");
        System.out.println("|2. Check exits Vehicle.                                   |");
        System.out.println("|3. Update vehicle.                                        |");
        System.out.println("|4. Delete vehicle.                                        |");
        System.out.println("|5. Search vehicle.                                        |");
        System.out.println("|6. Display all Vehicle.                                   |");
        System.out.println("|7. Save all vehicles to file.                             |");
        System.out.println("|8. Print vehicles from the file.                          |");
        System.out.println("|9. Read input vehicle data file.                          |");
        System.out.println("|10. Exit the program.                                     |");
        System.out.println("============================================================");
    }
    
    /**
     * This function displays search menu
     * 
     */
    public static void searchMenu() {
        System.out.println("====================== SEARCH VEHICLE ======================");
        System.out.println("|1. Search by ID.                                          |");
        System.out.println("|2. Search by name.                                        |");
        System.out.println("|3. Exit to main menu.                                     |");
        System.out.println("============================================================");
    }
    
    /**
     * This function displays display menu
     * 
     */
    public static void displayMenu() {
        System.out.println("====================== DISPLAY VEHICLE =====================");
        System.out.println("|1. Show all.                                              |");
        System.out.println("|2. Show by price.                                         |");
        System.out.println("|3. Exit to main menu.                                     |");
        System.out.println("============================================================");
    }
    
    /**
     * This function displays print from file menu
     * 
     */
    public static void printFromFileMenu() {
        System.out.println("================= PRINT VEHICLES FROM FILE =================");
        System.out.println("|1. Print all.                                             |");
        System.out.println("|2. Print by year.                                         |");
        System.out.println("|3. Exit to main menu                                      |");
        System.out.println("============================================================");
    }
}