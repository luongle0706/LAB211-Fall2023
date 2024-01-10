
package view;

public class Menu {

    public static void mainMenu() {
        System.out.println("========== STORE MANAGEMENT APPLICATION ==========");
        System.out.println("| 1. Manage products                             |");
        System.out.println("| 2. Manage Warehouse                            |");
        System.out.println("| 3. Report                                      |");
        System.out.println("| 4. Store data to files                         |");
        System.out.println("| 5. Close the application                       |");
        System.out.println("==================================================");
    }
    
    public static void manageProductsMenu() {
        System.out.println("================= MANAGE PRODUCTS ================");
        System.out.println("| 1. Register a product                          |");
        System.out.println("| 2. Update registered product information       |");
        System.out.println("| 3. Update product information                  |");
        System.out.println("| 4. Delete product                              |");
        System.out.println("| 5. Show all registered product                 |");
        System.out.println("| 6. Show all product                            |");
        System.out.println("| 7. Exit to main menu                           |");
        System.out.println("==================================================");
    }
    
    public static void manageWarehouseMenu() {
        System.out.println("================ MANAGE WAREHOUSE ================");
        System.out.println("| 1. Create an import receipt                    |");
        System.out.println("| 2. Create an export receipt                    |");
        System.out.println("| 3. Exit to main menu                           |");
        System.out.println("==================================================");
    }
    
    public static void reportMenu() {
        System.out.println("====================== REPORT ====================");
        System.out.println("| 1. Products that have expired                  |");
        System.out.println("| 2. The products that the store is selling      |");
        System.out.println("| 3. Products that are running out of stock      |");
        System.out.println("| 4. Import/export receipt of a product          |");
        System.out.println("| 5. Exit to main menu                           |");
        System.out.println("==================================================");
    }
}
