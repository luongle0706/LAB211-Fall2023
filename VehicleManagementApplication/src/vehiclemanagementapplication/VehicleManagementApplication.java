package vehiclemanagementapplication;

import control.VehicleManager;
import java.util.Scanner;
import utility.Inputter;
import view.Menu;

public class VehicleManagementApplication {
    private static Scanner sc = new Scanner(System.in);
    private static final VehicleManager VM = new VehicleManager();
    public static void main(String[] args) {
        int choice = 0;
        int choice2 = 0;
        do {
            Menu.mainMenu();
            choice = Inputter.getInt("Enter your choice (1-9): ", "Error! Input must be between 1 and 9!");
            switch (choice) {
                case 1:
                    VM.addVehicle();
                    break;

                case 2:
                    VM.checkExistVehicle("vehicle.dat");
                    break;
                case 3:
                    VM.updateVehicle();
                    break;
                case 4:
                    VM.deleteVehicle();
                    break;
                case 5:
                    do {
                        Menu.searchMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-3): ", "Error! Input must be between 1 and 3!");
                        switch (choice2) {
                            case 1:
                                VM.searchVehicleByID();
                                break;
                            case 2:
                                VM.searchVehicleByName();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 3!");
                        }
                    } while (choice2 != 3);
                    break;
                case 6:
                    do {
                        Menu.displayMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-3): ", "Error! Input must be between 1 and 3!");
                        switch (choice2) {
                            case 1:
                                VM.displayAll();
                                break;
                            case 2:
                                VM.displayByPrice();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 3!");
                        }
                    } while (choice2 != 3);
                    break;
                case 7:
                    VM.saveDataToFile("vehicle.dat");
                    break;
                case 8:
                    do {
                        Menu.printFromFileMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-3): ", "Error! Input must be between 1 and 3!");
                        switch (choice2) {
                            case 1:
                                VM.printAllInputFile("vehicle.dat");
                                break;
                            case 2:
                                VM.printByYearInputFile("vehicle.dat");
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 3!");
                        }
                    } while (choice2 != 3);
                    break;
                case 9:
                    VM.readDataInputFile("inputdata.dat");
                    break;
                case 10:
                    break;
            }
        } while (choice != 10);
        System.out.println("\nCLOSING THE PROGRAM! SEE YOU NEXT TIME!\n");
    }
    
}
