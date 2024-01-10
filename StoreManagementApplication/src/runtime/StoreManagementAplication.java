package runtime;

import controller.ApplicationManager;
import java.util.Scanner;
import tool.Inputter;
import view.Menu;

public class StoreManagementAplication {

    private static Scanner sc = new Scanner(System.in);

    private static ApplicationManager AM = new ApplicationManager();

    public static void main(String[] args) {
        int choice = 0;
        int choice2 = 0;
        do {
            Menu.mainMenu();
            choice = Inputter.getInt("Enter your choice (1-5): ", "Error! Input must be between 1 and 5!");
            switch (choice) {
                case 1:
                    do {
                        Menu.manageProductsMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-7): ", "Error! Input must be between 1 and 7!");
                        switch (choice2) {
                            case 1:
                                AM.addProduct();
                                break;
                            case 2:
                                AM.updateRegisteredProductInformation();
                                break;
                            case 3:
                                AM.updateInformation();
                                break;
                            case 4:
                                AM.deleteProduct();
                                break;
                            case 5:
                                AM.showAllRegisteredProducts();
                                break;
                            case 6:
                                AM.showAllProducts();
                                break;
                            case 7:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 7!");
                        }
                    } while (choice2 != 7);
                    break;

                case 2:
                    do {
                        Menu.manageWarehouseMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-3): ", "Error! Input must be between 1 and 3!");
                        switch (choice2) {
                            case 1:
                                AM.createImportReceipt();
                                break;
                            case 2:
                                AM.createExportReceipt();
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 3!");
                        }
                    } while (choice2 != 3);
                    break;
                case 3:
                    do {
                        Menu.reportMenu();
                        choice2 = Inputter.getInt("Enter your choice (1-5): ", "Error! Input must be between 1 and 5!");
                        switch (choice2) {
                            case 1:
                                AM.reportExpiredProducts();
                                break;
                            case 2:
                                AM.reportSellingProducts();
                                break;
                            case 3:
                                AM.reportOutOfStockProducts();
                                break;
                            case 4:
                                AM.importExportReceiptOfAProduct();
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Error! Input must be between 1 and 5!");
                        }
                    } while (choice2 != 5);
                    break;
                case 4:
                    AM.storeDataToFiles();
                    break;
                case 5:
                    break;
            }

        } while (choice != 5);
        System.out.println("\nCLOSING THE PROGRAM! SEE YOU NEXT TIME!\n");
    }
}
