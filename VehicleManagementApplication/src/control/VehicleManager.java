package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Vehicle;
import utility.Inputter;

public class VehicleManager {

    private final HashMap<String, Vehicle> vehicleList = new HashMap<>();
    
    /**
     * This function is used for checking duplicated vehicle ID
     * 
     * @param vehicleList
     * @param vId
     * @return 
     */
    public boolean checkDuplicateID(HashMap<String, Vehicle> vehicleList, String vId) {
        boolean result = false;
            if (vehicleList.containsKey(vId)) {
                result = true;
            }
        return result;
    }
    
    /**
     * This function is used for getting unique vehicle ID
     * 
     * @param vehicleList
     * @return 
     */
    public String getUniqueVehicleID(HashMap<String, Vehicle> vehicleList) {
        String vId = null;
        boolean typeAgain = false;
        do {
            vId = Inputter.getNoBlankString("Enter vehicle ID: ", "Vehicle ID cannot be blank!");
            if (checkDuplicateID(vehicleList, vId)) {
                System.out.println("Vehicle ID is duplicated!");
                typeAgain = Inputter.checkYesOrNo("Do you want to type again? Type Y/N:", "Invalid input!");
            } else {
                break;
            }
        } while (typeAgain);
        return vId;
    }
    
    /**
     * This function is used for adding new vehicle
     * 
     */
    public void addVehicle() {
        String vId = getUniqueVehicleID(vehicleList);
        String vName = Inputter.getString("Enter vehicle name: ");
        String vColor = Inputter.getString("Enter vehicle color: ");
        String vBrand = Inputter.getString("Enter vehicle brand: ");
        String vType = Inputter.getString("Enter vehicle type: ");
        int vProductYear = Inputter.getPositiveInt("Enter vehicle product year: ", "Product year has to be a positive number!");
        int vPrice = Inputter.getPositiveInt("Enter vehicle price: ", "Price has to be a positive number!");
        
        Vehicle v = new Vehicle(vId, vName, vColor, vBrand, vType, vProductYear, vPrice);
        vehicleList.put(vId, v);
        System.out.println("Added successfully!");
    }
    
    /**
     * This function is used for checking whether input vehicle has existed in the dat file or not
     * 
     * @param path 
     */
    public void checkExistVehicle(String path) {
        boolean isFind = false;
        String inputId = Inputter.getNoBlankString("Enter vehicle ID: ", "Vehicle ID cannot be blank!");
        List<Vehicle> inputList = readVehicleInputFile(path);
        for (Vehicle vehicle : inputList) {
            if (vehicle.getvId().equalsIgnoreCase(inputId)) {
                System.out.println("Existed Vehicle");
                System.out.println(vehicle.toString());
                isFind = true;
                break;
            }
        }
        if (!isFind) {
            System.out.println("No Vehicle Found!");
        }
    }

    /**
     * This function is used for updating vehicle
     * 
     */
    public void updateVehicle() {
        String vName, vColor, vBrand, vType;
        int vProductYear, vPrice;
        String vId = searchVehicleByID();
        if (!vId.isEmpty()) {
            System.out.println("\n========== UPDATE INFORMATION ==========\n");
            vName = Inputter.getString("Enter vehicle name: ");
            if (vName.isEmpty()) {
                vName = vehicleList.get(vId).getvName();
            }
            vColor = Inputter.getString("Enter vehicle color: ");
            if (vColor.isEmpty()) {
                vColor = vehicleList.get(vId).getvColor();
            }
            vBrand = Inputter.getString("Enter vehicle brand: ");
            if (vBrand.isEmpty()) {
                vBrand = vehicleList.get(vId).getvBrand();
            }
            vType = Inputter.getString("Enter vehicle type: ");
            if (vType.isEmpty()) {
                vType = vehicleList.get(vId).getvType();
            }
            String tmpYear = Inputter.getString("Enter vehicle product year: ");
            if (tmpYear.isEmpty()) {
                vProductYear = vehicleList.get(vId).getvProductYear();
            } else {
                vProductYear = Inputter.parsePositiveInt(tmpYear, "Product year must be a positive number!");
            }
            String tmpPrice = Inputter.getString("Enter vehicle price: ");
            if (tmpPrice.isEmpty()) {
                vPrice = vehicleList.get(vId).getvPrice();
            } else {
                vPrice = Inputter.parsePositiveInt(tmpPrice, "Price must be a positive number!");
            }
            Vehicle v = new Vehicle(vId, vName, vColor, vBrand, vType, vProductYear, vPrice);
            vehicleList.put(vId, v);
            System.out.println("Updated successfully!");
        }
    }
    
    /**
     * This function is used for deleting vehicle
     * 
     */
    public void deleteVehicle() {
        String vId = searchVehicleByID();
        if (!vId.isEmpty()) {
            if (Inputter.checkYesOrNo("\nDo you want to delete this vehicle? Type Y/N:", "Invalid input!")) {
                vehicleList.remove(vId);
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("Canceled!");
            }
        }
    }
    
    /**
     * This function is used for searching vehicle by inputed vehicle ID
     * 
     * @return 
     */
    public String searchVehicleByID() {
        String vId = Inputter.getNoBlankString("Enter vehicle ID: ", "Vehicle ID cannot be blank!");
        if (vehicleList.get(vId) == null) {
            System.out.println("Vehicle does not exist!");
            vId = null;
        } else {
            System.out.println("\n========== MATCHED RESULT ==========\n");
            System.out.println(vehicleList.get(vId));
        }
        return vId;
    }
    
    /**
     * This function is used for searching vehicle by inputed vehicle name and sort by name descending 
     * 
     */
    public void searchVehicleByName() {
        String vName = Inputter.getString("Enter vehicle name: ");
        List<Vehicle> resultList = new ArrayList<>();
        for (Map.Entry<String, Vehicle> entry : vehicleList.entrySet()) {
            String entryName = entry.getValue().getvName();
            if (entryName.contains(vName)) {
                resultList.add(entry.getValue());
            }
        }
        Collections.sort(resultList, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return t1.getvName().compareTo(t.getvName());
            }
        });
        System.out.println("\n========== MATCHED RESULT ==========\n");
        for (Vehicle vehicle : resultList) {
            System.out.println(vehicle.toString());
        }
    }
    
    /**
     * This function is used for displaying all vehicle information
     * 
     */
    public void displayAll() {
        for (Map.Entry<String, Vehicle> entry : vehicleList.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
    
    /**
     * This function is used for displaying all vehicle information that have price
     * less than inputed price in descending order
     * 
     */
    public void displayByPrice() {
        int vPrice = Inputter.getPositiveInt("Enter vehicle price: ", "Price has to be a positive number!");
        List<Vehicle> resultList = new ArrayList<>();
        for (Map.Entry<String, Vehicle> entry : vehicleList.entrySet()) {
            int entryPrice = entry.getValue().getvPrice();
            if (vPrice >= entryPrice) {
                resultList.add(entry.getValue());
            }
        }
        Collections.sort(resultList, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return Integer.compare(t1.getvPrice(), t.getvPrice());
            }
        });
        System.out.println("\n========== RESULT LIST ==========\n");
        for (Vehicle vehicle : resultList) {
            System.out.println(vehicle.toString());
        }
    }
    
    /**
     * This function is used for displaying all vehicle information that have
     * product year less than inputed product year in descending order
     * 
     */
    public void displayByYear() {
        List<Vehicle> resultList = new ArrayList<>();
        int vProductYear = Inputter.getPositiveInt("Enter vehicle product year: ", "Product year has to be a positive number!");
        for (Map.Entry<String, Vehicle> entry : vehicleList.entrySet()) {
            int entryPrice = entry.getValue().getvPrice();
            if (vProductYear <= entryPrice) {
                resultList.add(entry.getValue());
            }
        }
        System.out.println("\n========== RESULT LIST ==========\n");
        Collections.sort(resultList, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return Integer.compare(t1.getvProductYear(), t.getvProductYear());
            }
        });

    }
    
    /**
     * This function is used for saving all vehicle information to output file
     * 
     * @param path 
     */
    public void saveDataToFile(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (Map.Entry<String, Vehicle> entry : vehicleList.entrySet()) {
                writer.write(entry.getValue().displayOuputFile().toUpperCase() + "\n");
            }
            writer.close();
            System.out.println("Save data to file successfully!");
        } catch (IOException e) {
            System.out.println("IOException is caught");
        }
    }
    
    /**
     * This function is used for reading all vehicle information from input file
     * 
     * @param path
     * @return 
     */
    public List<Vehicle> readVehicleInputFile(String path) {
        List<Vehicle> inputList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String str;
            while ((str = reader.readLine()) != null) {
                String temp[] = str.split(",");
                String vId = temp[0];
                String vName = temp[1];
                String vColor = temp[2];
                String vBrand = temp[3];
                String vType = temp[4];
                int vProductYear = Integer.parseInt(temp[5]);
                int vPrice = Integer.parseInt(temp[6]);
                Vehicle v = new Vehicle(vId, vName, vColor, vBrand, vType, vProductYear, vPrice);
                inputList.add(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
    
    /**
     * This function is used for printing all vehicle information from input file to the console screen
     * 
     * @param path 
     */
    public void printAllInputFile(String path) {
        List<Vehicle> inputList;
        inputList = readVehicleInputFile(path);
        System.out.println("\n========== RESULT LIST ==========\n");
        for (Vehicle vehicle : inputList) {
            System.out.println(vehicle.toString());
        }
    }
    
    /**
     * This function is used for printing all vehicle information from input file to the console screen
     * by year in descending order
     * 
     * @param path 
     */
    public void printByYearInputFile(String path) {
        List<Vehicle> inputList;
        inputList = readVehicleInputFile(path);
        Collections.sort(inputList, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle t, Vehicle t1) {
                return Integer.compare(t.getvProductYear(), t1.getvProductYear());
            }
        });
        System.out.println("\n========== RESULT LIST ==========\n");
        for (Vehicle vehicle : inputList) {
            System.out.println(vehicle.toString());
        }
    }
    
    /**
     * This function is used for reading data from input file
     * 
     * @param path 
     */
    public void readDataInputFile(String path) {
        List<Vehicle> inputData = readVehicleInputFile(path);
        for (Vehicle vehicle : inputData) {
            vehicleList.put(vehicle.getvId(), vehicle);
        }
        System.out.println("\n========== INPUTED DATA ==========\n");
        for (Vehicle vehicle : inputData) {
            System.out.println(vehicle.toString());
        }
    }
}
