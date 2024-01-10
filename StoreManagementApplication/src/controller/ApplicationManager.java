package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Product;
import model.Warehouse;
import tool.Checker;
import tool.Inputter;

public class ApplicationManager {

    public static Scanner sc = new Scanner(System.in);
    String code, name, producer, manufacturingDate, expirationDate;
    int quantity, price;

    private final ArrayList<Product> regPdList = new ArrayList<>();
    private final ArrayList<Product> pdList = new ArrayList<>();
    private final ArrayList<Warehouse> whList = new ArrayList<>();

    /**
     * Add a new product Function
     */
    public void addProduct() {
        boolean isCancel = false, isFind = true;
        System.out.println("\n========== REGISTER A NEW PRODUCT ==========\n");

        do {
            code = Inputter.getString("Enter product code:", "ERROR!!! Product code can not be blank!");
            for (Product pd : regPdList) {
                if (pd.getCode().equals(code)) {
                    System.out.println("ERROR!!! Product code is already exist!");
                    if (Inputter.toContinue("Would you like to type again? Type Y/N", "Please enter 'Y' or 'N'!") == false) {
                        isCancel = true;
                        isFind = true;
                        break;
                    } else {
                        isFind = false;
                    }
                }
            }
        } while (!isFind);

        if (isCancel == false) {

            name = Inputter.getString("Enter product name:", "ERROR!!! Product name can not be blank!");

            producer = Inputter.getString("Enter product producer:", "ERROR!!! Product producer can not be blank!");

            //Create an object product and add into the product list
            Product p = new Product(code, name, producer, null, null, 0, 0);
            regPdList.add(p);

            System.out.println("\nADDED SUCCESSFULLY!\n");
        }
    }

    /**
     * Update information of a product Function
     */
    public void updateRegisteredProductInformation() {

        if (regPdList.isEmpty()) {
            System.out.println("\nREGISTERED PRODUCT LIST IS EMPTY!\n");
        } else {
            System.out.println("\n========== UPDATE REGISTERED PRODUCT INFORMATION ==========\n");
            code = Inputter.getString("Enter product code that needs update:", "ERROR!!! Product code can not be blank!");

            int index = 0;
            int check = 0;
            for (int i = 0; i < regPdList.size(); i++) {
                if (regPdList.get(i).getCode().matches(code)) {
                    index = i;
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                System.out.println("Product does not exist!");
            } else {
                System.out.println("========== MATCHED RESULT ==========");
                Inputter.displayRegisteredProductListHeader();
                regPdList.get(index).displayRegisteredProducts();

                System.out.println("\n========== UPDATE ==========");

                System.out.println("Enter product name:");
                name = sc.nextLine().trim().toUpperCase();
                if (name.isEmpty()) {
                    name = regPdList.get(index).getName();
                }

                System.out.println("Enter product producer:");
                producer = sc.nextLine().trim().toUpperCase();
                if (producer.isEmpty()) {
                    producer = regPdList.get(index).getProducer();
                }

                //Create an object product and update directly in the product list
                Product p = new Product(code, name, producer, null, null, 0, 0);
                regPdList.set(index, p);

                //The corresponding information are also adjusted on the import/export receipts
                for (Warehouse wh : whList) {
                    for (Product pd : wh.getProductList()) {
                        if (pd.getCode().equals(code)) {
                            pd.setName(name);
                            pd.setProducer(producer);
                            break;
                        }
                    }
                }
                System.out.println("\nUPDATED SUCCESSFULLY!\n");
            }
        }
    }

    /**
     * Update information of a product Function
     */
    public void updateInformation() {

        if (pdList.isEmpty()) {
            System.out.println("\nPRODUCT LIST IS EMPTY!\n");
        } else {
            System.out.println("\n========== UPDATE PRODUCT INFORMATION ==========\n");

            code = Inputter.getString("Enter product code that needs update:", "ERROR!!! Product code can not be blank!");

            int index = 0;
            int check = 0;
            for (int i = 0; i < pdList.size(); i++) {
                if (pdList.get(i).getCode().matches(code)) {
                    index = i;
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                System.out.println("Product does not exist!");
            } else {
                System.out.println("========== MATCHED RESULT ==========");
                Inputter.displayListHeader();
                pdList.get(index).displayProduct();

                System.out.println("\n========== UPDATE ==========");

                System.out.println("Enter product name:");
                name = sc.nextLine().trim().toUpperCase();
                if (name.isEmpty()) {
                    name = pdList.get(index).getName();
                }
                System.out.println("Enter product producer:");
                producer = sc.nextLine().trim().toUpperCase();
                if (producer.isEmpty()) {
                    producer = pdList.get(index).getProducer();
                }

                do {
                    System.out.println("Enter product manufacturing date (DD/MM/YYYY):");
                    manufacturingDate = sc.nextLine();
                    if (manufacturingDate.isEmpty()) {
                        manufacturingDate = pdList.get(index).getManufacturingDate();
                    }
                    if (Checker.isDateValid(manufacturingDate) == false) {
                        System.out.println("ERROR!!! Invalid date!");
                    }
                } while (!Checker.isDateValid(manufacturingDate) || manufacturingDate.isEmpty());

                do {
                    System.out.println("Enter product expiration date (DD/MM/YYYY):");
                    expirationDate = sc.nextLine();
                    if (expirationDate.isEmpty()) {
                        expirationDate = pdList.get(index).getExpirationDate();
                    }
                    if (Checker.isDateValid(expirationDate) == false) {
                        System.out.println("ERROR!!! Invalid date!");
                    }
                } while (!Checker.isDateValid(expirationDate) || expirationDate.isEmpty());

                boolean isValid = false;
                String input;
                do {
                    System.out.println("Enter product quantity");
                    input = sc.nextLine().trim();
                    if (input.isEmpty()) {
                        quantity = pdList.get(index).getQuantity();
                    } else {
                        try {
                            quantity = Integer.parseInt(input);
                            isValid = true;
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR!!! Product quantity must be a number!");
                        }
                    }
                } while (isValid || input.isEmpty());

                //Create an object product and update directly in the product list
                Product p = new Product(code, name, producer, manufacturingDate, expirationDate, quantity, 0);
                pdList.set(index, p);

                //The corresponding information are also adjusted on the import/export receipts
                for (Warehouse wh : whList) {
                    for (Product pd : wh.getProductList()) {
                        if (pd.getCode().equals(code)) {
                            pd.setName(name);
                            pd.setProducer(producer);
                            pd.setManufacturingDate(manufacturingDate);
                            pd.setExpirationDate(expirationDate);
                            break;
                        }
                    }
                }
                System.out.println("\nUPDATED SUCCESSFULLY!\n");
            }
        }
    }

    /**
     * Delete a product Function
     */
    public void deleteProduct() {
        if (regPdList.isEmpty()) {
            System.out.println("\nREGISTERED PRODUCT LIST IS EMPTY!\n");
        } else {
            int check = 0, index = 0, find = 0;

            System.out.println("\n========== DELETE A PRODUCT ==========\n");

            code = Inputter.getString("Enter product code that needs to be deleted:", "ERROR!!! Product code can not be blank!");

            for (int i = 0; i < regPdList.size(); i++) {
                if (regPdList.get(i).getCode().matches(code)) {
                    find = 1;
                    index = i;
                    break;
                }
            }
            if (find == 0) {
                check = 0;
            } else {
                for (Product pd : pdList) {
                    if (pd.getCode().equals(regPdList.get(index).getCode())) {
                        check = 1;
                        break;
                    }
                }
                if (check != 1) {
                    check = 2;
                }
            }

            switch (check) {
                case 0:
                    System.out.println("\nProduct does not exist!\n");
                    break;
                case 1:
                    System.out.println("\nThe import/export information for this product has been generated. Cannot delete this product!\n");
                    break;
                default:
                    System.out.println("\n========== MATCHED RESULT ==========");
                    Inputter.displayRegisteredProductListHeader();
                    regPdList.get(index).displayRegisteredProducts();
                    //Confirm message
                    if (Inputter.toContinue("\nDo you want to add more products? Type Y/N:\n", "ERROR! Invalid input! Try again\n") == true) {
                        regPdList.remove(index);
                        System.out.println("\nDELETED!\n");
                    } else {
                        System.out.println("\nPRODUCT DELETION HAS BEEN CANCELLED!\n");
                    }
                    break;
            }
        }
    }

    /**
     * Show all registered product information Function
     */
    public void showAllRegisteredProducts() {
        if (regPdList.isEmpty()) {
            System.out.println("\n REGISTERED PRODUCT LIST IS EMPTY!\n");
        } else {
            System.out.println("\n========== LIST OF REGISTERED PRODUCTS ==========\n");
            Inputter.displayRegisteredProductListHeader();
            for (Product product : regPdList) {
                product.displayRegisteredProducts();
            }
            System.out.println("\n");
        }
    }

    /**
     * Show all product information Function
     */
    public void showAllProducts() {
        if (pdList.isEmpty()) {
            System.out.println("\n PRODUCT LIST IS EMPTY!\n");
        } else {
            System.out.println("\n========== LIST OF PRODUCTS IN THE WAREHOUSE ==========\n");
            Inputter.displayListHeader();
            for (Product product : pdList) {
                product.displayProduct();
            }
            System.out.println("\n");
        }
    }

    /**
     * Create an import receipt Function
     */
    public void createImportReceipt() {
        ArrayList<Product> importList = new ArrayList<>();

        if (regPdList.isEmpty()) {
            System.out.println("You don't have any registered product. Cannot import product!");
        } else {
            boolean isValid, isCancel, isRegistered, isFinished = true;
            
            showAllRegisteredProducts();
            System.out.println("\n========== CREATE AN IMPORT RECEIPT ==========\n");
            System.out.println("Add new product to the import receipt");
            do {
                do {
                    isCancel = false;
                    isRegistered = false;
                    isValid = true;
                    code = Inputter.getString("Enter product code:", "ERROR!!! Product code can not be blank!");
                    for (Product pd : importList) {
                        if (pd.getCode().equals(code)) {
                            System.out.println("ERROR!!! Product code is already exist!");
                            boolean confirm = Inputter.toContinue("Would you like to type again? Type Y/N", "Please enter 'Y' or 'N'!");
                            if (confirm == false) {
                                isValid = true;
                                isCancel = true;
                                isFinished = true;
                                System.out.println("\nCANCELLED IMPORTING PROGRESS!!!");
                                break;
                            } else {
                                isValid = false;
                                isCancel = true;
                                break;
                            }
                        }
                    }
                    if (!isCancel) {
                        for (Product regPd : regPdList) {
                            if (regPd.getCode().equals(code)) {
                                isRegistered = true;
                                isValid = true;
                                break;
                            }
                        }
                        if (!isRegistered) {
                            System.out.println("ERROR! Unregisted product! Cannot import this product!\n");
                            isValid = true;
                            isCancel = true;
                        }
                    }

                } while (isValid == false);

                if (!isCancel) {
                    name = Inputter.getString("Enter product name:", "ERROR!!! Product name can not be blank!");
                    producer = Inputter.getString("Enter product producer:", "ERROR!!! Product producer can not be blank!");
                    //Get system day
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime today = LocalDateTime.now();
                    do {
                        manufacturingDate = Inputter.getString("Enter product manufacturing date (DD/MM/YYYY):", "ERROR!!! Date can not be blank!");
                        if (Checker.isDateValid(manufacturingDate) == false || Checker.checkExpiredProducts(manufacturingDate, df.format(today)) == true) {
                            System.out.println("ERROR!!! Invalid date!");
                        }
                    } while (Checker.isDateValid(manufacturingDate) == false || Checker.checkExpiredProducts(manufacturingDate, df.format(today)) == true);

                    do {
                        expirationDate = Inputter.getString("Enter product expiration date (DD/MM/YYYY):", "ERROR!!! Date can not be blank!");
                        if (Checker.isDateValid(expirationDate) == false || Checker.checkExpiredProducts(df.format(today), expirationDate) == true) {
                            System.out.println("ERROR!!! Invalid date!");
                        }
                    } while (Checker.isDateValid(expirationDate) == false || Checker.checkExpiredProducts(df.format(today), expirationDate) == true);

                    do {
                        quantity = Inputter.getInt("Enter product quantity", "ERROR!!! Product quantity must be a number!");
                        if (quantity <= 0) {
                            System.out.println("ERROR!!! Product quantity must be bigger than 0!");
                        }
                    } while (quantity <= 0);
                    do {
                        price = Inputter.getInt("Enter product price", "ERROR!!! Product quantity must be a number!");
                        if (quantity <= 0) {
                            System.out.println("ERROR!!! Product price must be bigger than 0!");
                        }
                    } while (price <= 0);
                    //Create an object product and add into the import list
                    Product p = new Product(code, name, producer, manufacturingDate, expirationDate, quantity, price);
                    importList.add(p);
                    System.out.println("\nADDED TO THE IMPORT RECEIPT SUCCESSFULLY!\n");

                    //Ask user whether they want to add more products
                    if (Inputter.toContinue("\nDo you want to add more products? Type Y/N:\n", "ERROR! Invalid input! Try again\n") == true) {
                        isFinished = false;

                    } else {
                        isFinished = true;
                    }
                }
            } while (!isFinished);
            if (!isCancel) {
                /**
                 * Add import products into the product list. If the product is
                 * already in the product list, then increase its quantity. Else
                 * add as a new product
                 */
                ArrayList<Product> toAdd = new ArrayList<>();
                for (Product pdImport : importList) {
                    int check = 0;
                    for (Product pd : pdList) {
                        if (pdImport.getCode().equals(pd.getCode())) {
                            pd.setQuantity(pd.getQuantity() + pdImport.getQuantity());
                            check = 1;
                            break;
                        }
                    }
                    if (check == 0) {
                        toAdd.add(pdImport);
                    }
                }

                //Get system time when the receipt is created
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                pdList.addAll(toAdd);
                //Create an object import receipt and add to the import/export list
                Warehouse importReceipt = new Warehouse("Import Receipt", dtf.format(now), importList);
                whList.add(importReceipt);

                System.out.println("\nCREATE IMPORT RECEIPT SUCCESSFULLY!!!\n");
            }

        }
    }

    /**
     * Create an export receipt function
     */
    public void createExportReceipt() {
        ArrayList<Product> exportList = new ArrayList<>();

        if (pdList.isEmpty()) {
            System.out.println("\nPRODUCT LIST IS EMPTY!\n");
        } else {
            boolean isValid, isCancel, isFinished = true;
            showAllProducts();
            System.out.println("\n========== CREATE AN EXPORT RECEIPT ==========\n");
            System.out.println("Add new product to the export receipt");

            do {
                int index = 0;

                do {
                    isValid = false;
                    isCancel = false;
                    code = Inputter.getString("Enter product code:", "ERROR!!! Product code can not be blank!");
                    for (Product product : exportList) {
                        if (code.equals(product.getCode())) {
                            System.out.println("ERROR!!! Code is duplicated!");
                            boolean confirm = Inputter.toContinue("Would you like to type again? Type Y/N", "Please enter 'Y' or 'N'!");
                            if (confirm == false) {
                                isValid = true;
                                isCancel = true;
                                isFinished = true;
                                System.out.println("\nCANCELLED EXPORTING PROGRESS!!!");
                            } else {
                                isValid = false;
                                isCancel = true;
                            }
                        }
                    }
                    if (!isValid) {
                        int check = 0;
                        for (int i = 0; i < pdList.size(); i++) {
                            if (pdList.get(i).getCode().matches(code)) {
                                if (pdList.get(i).getQuantity() == 0) {
                                    System.out.println("Product is out of stock!");
                                    isValid = true;
                                    isCancel = true;
                                    isFinished = true;
                                    break;
                                } else {
                                    index = i;
                                    check = 1;
                                    isValid = true;
                                    break;
                                }
                            }
                        }
                        if (check != 1) {
                            System.out.println("Product does not exist!");
                            isValid = true;
                            isCancel = true;
                        }
                    }
                } while (!isValid);

                if (!isCancel) {
                    name = regPdList.get(index).getName();
                    producer = regPdList.get(index).getProducer();
                    boolean isEnough;
                    /**
                     * Get export quantity that must be bigger than 0. If there
                     * is not enough stock to export, report to the user.
                     */
                    do {
                        isEnough = true;
                        quantity = Inputter.getInt("Enter product quantity", "ERROR!!! Product quantity must be a number!");
                        if (quantity <= 0) {
                            System.out.println("ERROR!!! Product quantity must be bigger than 0!");
                        } else {
                            for (Product pd : pdList) {
                                if (code.equals(pd.getCode())) {
                                    if (quantity > pd.getQuantity()) {
                                        System.out.println("ERROR!!! There is not enough stock to export!");
                                        isEnough = false;
                                        break;
                                    }
                                }
                            }
                        }
                    } while (quantity <= 0 || !isEnough);

                    do {
                        price = Inputter.getInt("Enter product price", "ERROR!!! Product quantity must be a number!");
                        if (price <= 0) {
                            System.out.println("ERROR!!! Product price must be bigger than 0!");
                        }
                    } while (price <= 0);
                    //Create an object product and add into the export list
                    Product p = new Product(code, name, producer, manufacturingDate, expirationDate, quantity, price);
                    exportList.add(p);
                    System.out.println("\nADDED TO THE EXPORT RECEIPT SUCCESSFULLY!\n");

                    //Ask user whether they want to add more products
                    if (Inputter.toContinue("\nDo you want to add more products? Type Y/N:", "ERROR! Invalid input! Try again\n") == true) {
                        isFinished = false;

                    } else {
                        isFinished = true;
                    }
                }
            } while (!isFinished);
            if (!isCancel) {
                /**
                 * Export products from the Warehouse, decrease the quantity of
                 * products in the Warehouse
                 */
                for (Product pdExport : exportList) {
                    for (Product pd : pdList) {
                        if (pdExport.getCode().equals(pd.getCode())) {
                            pd.setQuantity(pd.getQuantity() - pdExport.getQuantity());
                        }
                    }
                }

                //Get system time when the receipt is created
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                //Create an object export receipt and add to the import/export list
                Warehouse exportReceipt = new Warehouse("Export Receipt", dtf.format(now), exportList);
                whList.add(exportReceipt);

                System.out.println("\nCREATE EXPORT RECEIPT SUCCESSFULLY!!!\n");
            }
        }
    }

    /**
     * Print list of expired products Function
     */
    public void reportExpiredProducts() {
        if (pdList.isEmpty()) {
            System.out.println("\nPRODUCT LIST IS EMPTY!\n");
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("\n========== LIST OF EXPIRED PRODUCTS ==========\n");
            Inputter.displayListHeader();
            for (Product pd : pdList) {
                if (Checker.checkExpiredProducts(dtf.format(now), pd.getExpirationDate()) == true) {
                    pd.displayProduct();
                }
            }
            System.out.println("\n");
        }
    }

    /**
     * Print list of currently selling products Function
     */
    public void reportSellingProducts() {
        if (regPdList.isEmpty()) {
            System.out.println("\nSELLING PRODUCT LIST IS EMPTY!\n");
        } else {
            System.out.println("\n========== LIST OF SELLING PRODUCTS ==========\n");
            Inputter.displayListHeader();
            for (Product pd : pdList) {
                if (pd.getQuantity() > 0) {
                    pd.displayProduct();
                }
            }
            System.out.println("\n");
        }
    }

    /**
     * Print list of products that are running out of stock Function
     */
    public void reportOutOfStockProducts() {
        ArrayList<Product> list = new ArrayList<>();
        if (pdList.isEmpty()) {
            System.out.println("\nPRODUCT LIST IS EMPTY!\n");
        } else {
            System.out.println("\n========== LIST OF PRODUCTS THAT ARE RUNNING OUT OF STOCK ==========\n");
            for (Product pd : pdList) {
                if (pd.getQuantity() <= 3) {
                    list.add(pd);
                }
            }
            Collections.sort(list);
            Inputter.displayListHeader();
            for (Product pd : list) {
                pd.displayProduct();
            }
            System.out.println("\n");
        }
    }

    /**
     * Print import/export receipt of a product Function
     */
    public void importExportReceiptOfAProduct() {
        boolean isFind = false;
        if (whList.isEmpty()) {
            System.out.println("\nRECEIPT LIST IS EMPTY!\n");
        } else {

            code = Inputter.getString("Enter product code that needs to report import/export receipt:", "ERROR!!! Product code can not be blank!");
            for (int i = 0; i < pdList.size(); i++) {
                if (pdList.get(i).getCode().matches(code)) {
                    isFind = true;
                    break;
                }
            }
            if (!isFind) {
                System.out.println("Product does not exist!");
            } else {
                for (Warehouse wh : whList) {
                    for (Product pd : wh.getProductList()) {
                        if (pd.getCode().equals(code)) {
                            System.out.println(wh.toString());
                        }
                    }
                }
            }

        }
    }

    /**
     * Store data to files function
     */
    public void storeDataToFiles() {

        try {
            BufferedWriter writer1 = new BufferedWriter(new FileWriter("productList.dat"));
            writer1.write("===============================================================================================\n");
            writer1.write("|Code   |Name                |Producer            |Manufacturing date|Expiration date|Quantity|\n");
            writer1.write("===============================================================================================\n");
            for (Product pd : pdList) {
                writer1.write(pd.displayProductString());
            }
            writer1.close();

            BufferedWriter writer2 = new BufferedWriter(new FileWriter("warehouseList.dat"));
            for (Warehouse wh : whList) {
                writer2.write(wh.toString());
            }
            writer2.close();
            System.out.println("\nSTORED DATA TO FILES SUCCESSFULLY!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
