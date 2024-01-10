package runtime;

import controller.FlightManager;
import java.io.IOException;
import java.util.Scanner;
import tool.Inputter;
import view.Menu;
public class FlightManagementApplication {
    private static Scanner sc = new Scanner(System.in);
    private static final FlightManager fl = new FlightManager();

    public static void main(String[] args) throws IOException {
        int choice = 0;
        int choice2 = 0;
        do {
            Menu.mainMenu();
            choice = Inputter.getInt("Enter your choice (1-7): ", "Error! Input must be between 1 and 7!");
            switch (choice) {
                case 1:
                    do {
                        Menu.flightManagementMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-4): ", "Error! Input must be between 1 and 4!");
                        switch (choice2) {
                            case 1:
                                fl.addFlight();
                                break;
                            case 2:
                                fl.updateFlightSchedule();
                                break;
                            case 3:
                                fl.searchAvailableFlight();
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 4!");
                        }
                    } while (choice2 != 4);
                    break;

                case 2:
                    fl.makeReservation();
                    break;
                case 3:
                    fl.checkInAndSeatAllocation();
                    break;
                case 4:
                    fl.manageCrewAssignmentsForFlight();
                    break;
                case 5:
                    do {
                        Menu.systemManagementMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-3): ", "Error! Input must be between 1 and 3!");
                        switch (choice2) {
                            case 1:
                                fl.readFlightFileInput("flight.txt");
                                break;
                            case 2:
                                fl.readStaffFileInput("staff.txt");
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 4!");
                        }
                    } while (choice2 != 3);
                    break;
                case 6:
                    fl.saveFlightListToFileOutput("flightList.txt");
                    fl.saveReservationListToFileOutput("reservationList.txt");
                    fl.saveCrewAssignmentListToFileOutput("crewAssignmentList.txt");
            }

        } while (choice != 7);
        System.out.println("\nCLOSING THE PROGRAM! SEE YOU NEXT TIME!\n");
    }
    
}
