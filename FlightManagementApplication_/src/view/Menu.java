package view;

public class Menu {
    public static void mainMenu() {
        System.out.println("=================== FLIGHT MANAGEMENT APPLICATION ==================");
        System.out.println("|1. Flight schedule management                                     |");
        System.out.println("|2. Passenger reservation and booking                              |");
        System.out.println("|3. Passenger check-in and seat allocation                         |");
        System.out.println("|4. Crew management and assignments                                |");
        System.out.println("|5. Administrator access for system management                     |");
        System.out.println("|6. Data storage for flight details, reservations, and assignments |");
        System.out.println("|7. Exit the program                                               |");
        System.out.println("====================================================================");
    }
    
    public static void flightManagementMenu() {
        System.out.println("=================== FLIGHT SCHEDULE MANAGEMENT =====================");
        System.out.println("|1. Add a new flight                                               |");
        System.out.println("|2. Update flight schedule                                         |");
        System.out.println("|3. Search available flights                                       |");
        System.out.println("|4. Exit to main menu                                              |");
        System.out.println("====================================================================");
    }
    
    public static void systemManagementMenu() {
        System.out.println("======================== SYSTEM MANAGEMENT =========================");
        System.out.println("|1. Load flight data from file                                     |");
        System.out.println("|2. Load staff data from file                                      |");
        System.out.println("|3. Exit to main menu                                              |");
        System.out.println("====================================================================");
    }
}
