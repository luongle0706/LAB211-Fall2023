package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.CrewAssignments;
import model.Flight;
import model.Passenger;
import model.Reservation;
import model.Staff;
import tool.Inputter;

public class FlightManager {

    private final ArrayList<Flight> flightList = new ArrayList<>();
    private final HashMap<String, Passenger> passengerList = new HashMap<>();
    private final HashMap<String, Reservation> reservationList = new HashMap<>();
    private final HashMap<String, Staff> pilotList = new HashMap<>();
    private final HashMap<String, Staff> flightAttendantList = new HashMap<>();
    private final HashMap<String, Staff> groundStaffList = new HashMap<>();
    private final ArrayList<CrewAssignments> crewAssignmentList = new ArrayList<>();

    /**
     * This function allows you to add a new flight
     */
    public void addFlight() {
        String nFlightNumber, nDepartureCity, nDestinationCity, nDepartureTime;
        int nSeats, nDuration;
        String[] nAvailableSeats;

        do {
            nFlightNumber = Inputter.getFlightNumber("Enter flight number (Fxxxx - x are digits):",
                    "Invalid flight number", "F\\d{4}");
            if (nFlightNumber == null) {
                if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                    return;
                }
            }
        } while (nFlightNumber == null);

        nDepartureCity = Inputter.getString("Enter departure city:");
        do {
            nDestinationCity = Inputter.getString("Enter destination city:");
            if (nDepartureCity.equals(nDestinationCity)) {
                System.out.println("Invalid destination!");
                if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                    return;
                }
            }
        } while (nDepartureCity.equals(nDestinationCity));

        do {
            nDepartureTime = Inputter.getDateTime("Enter departure time (dd/MM/yyyy HH:mm): ", "Invalid input!");
            if (nDepartureTime == null) {
                if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                    return;
                }
            }
        } while (nDepartureTime == null);

        do {
            nDuration = Inputter.getInt("Enter flight duration (minutes): ", "Invalid input!");
            if (nDuration <= 0) {
                System.out.println("Input must be a positive number!");
                if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                    return;
                }
            }
        } while (nDuration <= 0);

        do {
            nSeats = Inputter.getInt("Enter number of seats", "Invalid input!");
            if (nSeats <= 0) {
                System.out.println("Input must be a positive number!");
                if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                    return;
                }
            }
        } while (nSeats <= 0);

        nAvailableSeats = getSeatList(nSeats);

        Flight fl = new Flight(nFlightNumber, nDepartureCity, nDestinationCity, nDepartureTime, nDuration, nSeats, nAvailableSeats);
        flightList.add(fl);
        System.out.println("\nAdded successfully!\n");
        if (Inputter.toContinue("Do you wish to add more flights? Type Y/N: ", "Invalid input!")) {
            addFlight();
        }
    }
    
    /**
     * This function creates a list of seat code base on your input amount of seats
     * @param seats
     * @return 
     */
    public String[] getSeatList(int seats) {
        int remainer = seats % 6;
        int row = seats / 6;
        String[] outputList = new String[seats];
        int c1 = 49;
        int c2 = 65;
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 6; j++) {
                outputList[index] = "" + (char) c1 + (char) c2;
                c2++;
                index++;
            }
            c1++;
            c2 = 65;
        }
        for (int i = 0; i < remainer; i++) {
            outputList[index] = "" + (char) c1 + (char) c2;
            c2++;
            index++;
        }
        return outputList;
    }
    
    /**
     * This function search available flight base on your input
     */
    public void searchAvailableFlight() {
        String departureCity, destinationCity, date;
        departureCity = Inputter.getString("Enter your departure location:");
        destinationCity = Inputter.getString("Enter your destination location:");
        date = Inputter.getDate("Enter your departure date:", "Invalid date");
        System.out.println("\n========== AVAILABLE FLIGHT LIST ==========\n");
        for (Flight fl : flightList) {
            if (fl.getDepartureCity().equals(departureCity) && fl.getDestinationCity().equals(destinationCity)
                    && fl.getDepartureTime().contains(date)) {
                fl.searchDisplay();
            }
        }
    }
    
    /**
     * This function allows you to update flight schedule
     */
    public void updateFlightSchedule() {
        String flightNumber, departureCity = null, destinationCity = null, departureTime = null;
        if (flightList.isEmpty()) {
            System.out.println("There is currently no flight!");
        } else {
            do {
                flightNumber = Inputter.getFlightNumber("Enter flight number that needs update (Fxxxx - x are digits):",
                        "Invalid flight number", "F\\d{4}");
                if (flightNumber == null) {
                    if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                        return;
                    }
                }
            } while (flightNumber == null);
            int index = -1;
            for (int i = 0; i < flightList.size(); i++) {
                if (flightNumber.equals(flightList.get(i).getFlightNumber())) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("No matched flight number!");
            } else {
                departureCity = Inputter.getString("Enter departure city:");
                do {
                    destinationCity = Inputter.getString("Enter destination city:");
                    if (departureCity.equals(destinationCity)) {
                        System.out.println("Invalid destination!");
                        if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                            return;
                        }
                    }
                } while (departureCity.equals(destinationCity));

                do {
                    departureTime = Inputter.getDateTime("Enter departure time (dd/MM/yyyy HH:mm): ", "Invalid input!");
                    if (departureTime == null) {
                        if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                            return;
                        }
                    }
                } while (departureTime == null);
            }
            flightList.get(index).setDepartureCity(departureCity);
            flightList.get(index).setDestinationCity(destinationCity);
            flightList.get(index).setDepartureTime(departureTime);
            System.out.println("\nUpdated successfully!\n");
        }
    }
    
    /**
     * This functions allows you to save flight list to file output
     * @param path 
     */
    public void saveFlightListToFileOutput(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (Flight flight : flightList) {
                writer.write(flight.toString() + "\n");
            }
            writer.close();
            System.out.println("Save flight list to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This function allows you to make a reservation
     */
    public void makeReservation() {
        String mFlightNumber;
        String mId, mName, mPhoneNum, mDateOfBirth;

        if (flightList.isEmpty()) {
            System.out.println("There is currently no flight!");
        } else {
            searchAvailableFlight();
            System.out.println("");
            do {
                mFlightNumber = Inputter.getFlightNumber("Enter flight number to choose your flight (Fxxxx - x are digits):",
                        "Invalid flight number", "F\\d{4}");
                if (mFlightNumber == null) {
                    if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                        return;
                    }
                }
            } while (mFlightNumber == null);
            int index = -1;
            for (int i = 0; i < flightList.size(); i++) {
                if (flightList.get(i).getFlightNumber().equals(mFlightNumber)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("Flight number does not exist!");
            } else {
                System.out.println("\n========== PASSENGER INFOMATION ==========\n");
                do {
                    mId = Inputter.getNoBlankString("Enter your citizen ID:", "Your citizen ID cannot be blank!");
                    if (mId.isEmpty()) {
                        if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                            return;
                        }
                    }
                } while (mId.isEmpty());
                mName = Inputter.getString("Enter your full name:");
                do {
                    mPhoneNum = Inputter.getStringWithConditions("Enter your phone number:", "Invalid phone number!", "0\\d{9}");
                    if (mPhoneNum.isEmpty()) {
                        if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                            return;
                        }
                    }
                } while (mPhoneNum.isEmpty());
                do {
                    mDateOfBirth = Inputter.getDate("Enter your date of birth (dd/MM/yyyy):", "Invalid date!");
                    if (mDateOfBirth.isEmpty()) {
                        if (!Inputter.toContinue("Do you want to type again? Type Y/N:", "Invalid input!")) {
                            return;
                        }
                    }
                } while (mDateOfBirth.isEmpty());

                Passenger p = new Passenger(mId, mName, mPhoneNum, mDateOfBirth);
                passengerList.put(mId, p);

                Reservation r = new Reservation(flightList.get(index), p, false, null);
                reservationList.put(r.getrId(), r);
                System.out.println("========== YOUR RESERVATION INFORMATION ==========");
                System.out.println(r.toString());
            }
        }
    }
    
    /**
     * This function is used for check-in and allocate your seat
     */
    public void checkInAndSeatAllocation() {
        String cId;
        if (reservationList.isEmpty()) {
            System.out.println("Reservation list is empty!");
        } else {
            cId = Inputter.getString("Enter your reservation ID:");
            if (reservationList.get(cId) == null) {
                System.out.println("Reservation ID does not exist!");
            } else {
                String selectedSeat;
                System.out.println("\n========== LIST OF AVAILABLE SEATS ==========\n");
                reservationList.get(cId).getFlightInfo().displaySeats();
                selectedSeat = Inputter.getString("\nEnter your seat location:");
                String[] seatList = reservationList.get(cId).getFlightInfo().getAvailableSeats();
                String[] afterSelected = new String[reservationList.get(cId).getFlightInfo().getAvailableSeats().length];
                for (int i = 0; i < reservationList.get(cId).getFlightInfo().getAvailableSeats().length; i++) {
                    if (seatList[i].equals(selectedSeat)) {
                        afterSelected[i] = "xx";
                        reservationList.get(cId).setSelectedSeat(selectedSeat);
                        reservationList.get(cId).getFlightInfo().setSeats(reservationList.get(cId).getFlightInfo().getSeats() - 1);
                    } else {
                        afterSelected[i] = seatList[i];
                    }
                }
                reservationList.get(cId).getFlightInfo().setAvailableSeats(afterSelected);
                reservationList.get(cId).setCheckInStatus(true);
                System.out.println("\nCheck-in successfully!\n");
            }
        }
    }
    
    /**
     * This functions allows you to save reservation list to file output
     * @param path 
     */
    public void saveReservationListToFileOutput(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (Map.Entry<String, Reservation> res : reservationList.entrySet()) {
                writer.write(res.toString());
            }
            writer.close();
            System.out.println("Save reservation list to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This function reads flight schedule input file
     * @param path
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readFlightFileInput(String path) throws FileNotFoundException, IOException {
        ArrayList<Flight> inputFlight = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = br.readLine()) != null) {
                String temp[] = str.split(",");
                String flightNumber = temp[1];
                String departureCity = temp[2];
                String destinationCity = temp[3];
                String departureTime = temp[4];
                String sduration = temp[5];
                String sseats = temp[6];
                int duration = Integer.parseInt(sduration);
                int seats = Integer.parseInt(sseats);
                String[] availableSeats = getSeatList(seats);
                Flight fl = new Flight(flightNumber, departureCity, destinationCity, departureTime, duration, seats, availableSeats);
                inputFlight.add(fl);
            }

            Collections.sort(inputFlight);

            for (Flight flight : inputFlight) {
                System.out.println(flight.toString());
            }
            flightList.addAll(inputFlight);
        }
    }

    /**
     * This function reads staff information input file
     * @param path
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readStaffFileInput(String path) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = br.readLine()) != null) {
                String temp[] = str.split(",");
                String sId = temp[1];
                String sName = temp[2];
                String sRole = temp[3];

                if (sRole.equalsIgnoreCase("Pilot")) {
                    Staff p = new Staff(sId, sName, sRole);
                    pilotList.put(sId, p);
                }
                if (sRole.equalsIgnoreCase("Flight attendant")) {
                    Staff fa = new Staff(sId, sName, sRole);
                    flightAttendantList.put(sId, fa);
                }
                if (sRole.equalsIgnoreCase("Ground staff")) {
                    Staff gs = new Staff(sId, sName, sRole);
                    groundStaffList.put(sId, gs);
                }
            }
        }
        displayAllStaffInfo();
    }

    /**
     * This function allows you to manage crew assignment for any available flight
     */
    public void manageCrewAssignmentsForFlight() {
        Flight forFlight = null;
        HashMap<String, Staff> pilots = new HashMap<>();
        HashMap<String, Staff> flightAttendants = new HashMap<>();
        HashMap<String, Staff> groundStaffs = new HashMap<>();
        String flightNum = Inputter.getFlightNumber("Enter flight number to manage (Fxxxx - x are digits):",
                "Invalid flight number", "F\\d{4}");
        boolean isFound = false;
        for (Flight fl : flightList) {
            if (fl.getFlightNumber().equals(flightNum)) {
                isFound = true;
                forFlight = fl;
                break;
            }
        }
        if (isFound) {
            System.out.println("\n========== STAFF LIST ==========\n");
            displayAllStaffInfo();
            System.out.println("");
            do {
                String id = getStaffId("Enter pilot ID:", "Pilot", "Invalid pilot ID!");
                if (id != null) {
                    Staff pilot = pilotList.get(id);
                    pilots.put(id, pilot);
                }
            } while (Inputter.toContinue("Do you want to add more pilot? Type Y/N:", "Invalid input!"));
            do {
                String id = getStaffId("Enter flight attendant ID:", "Flight attendant", "Invalid flight attendant ID!");
                if (id != null) {
                    Staff fa = flightAttendantList.get(id);
                    flightAttendants.put(id, fa);
                }
            } while (Inputter.toContinue("Do you want to add more flight attendant? Type Y/N:", "Invalid input!"));
            do {
                String id = getStaffId("Enter ground staff ID:", "Ground staff", "Invalid ground staff ID!");
                if (id != null) {
                    Staff gs = groundStaffList.get(id);
                    groundStaffs.put(id, gs);
                }
            } while (Inputter.toContinue("Do you want to add more ground staff? Type Y/N:", "Invalid input!"));

            CrewAssignments ca = new CrewAssignments(forFlight, pilots, flightAttendants, groundStaffs);
            crewAssignmentList.add(ca);
        } else {
            System.out.println("Flight number not found!");
        }
    }
    
    /**
     * This function displays all staff information
     */
    public void displayAllStaffInfo() {
        System.out.println("\nPILOTS");
        for (Map.Entry<String, Staff> pilot : pilotList.entrySet()) {
            Staff pilotInfo = pilot.getValue();
            System.out.println(pilotInfo.toString());
        }
        System.out.println("\nFLIGHT ATTENDANTS");
        for (Map.Entry<String, Staff> fa : flightAttendantList.entrySet()) {
            Staff faInfo = fa.getValue();
            System.out.println(faInfo.toString());
        }
        System.out.println("\nGROUND STAFF");
        for (Map.Entry<String, Staff> gs : groundStaffList.entrySet()) {
            Staff gsInfo = gs.getValue();
            System.out.println(gsInfo.toString());
        }
    }
    
    /**
     * This function is used for getting staff id
     * @param inputMsg
     * @param role
     * @param errMsg
     * @return 
     */
    public String getStaffId(String inputMsg, String role, String errMsg) {
        String id = Inputter.getString(inputMsg);
        boolean isFound = false;
        switch (role) {
            case "Pilot":
                for (Map.Entry<String, Staff> pilot : pilotList.entrySet()) {
                    if (id.equals(pilot.getKey())) {
                        isFound = true;
                        break;
                    }
                }
                break;
            case "Flight attendant":
                for (Map.Entry<String, Staff> fa : flightAttendantList.entrySet()) {
                    if (id.equals(fa.getKey())) {
                        isFound = true;
                        break;
                    }
                }
                break;
            case "Ground staff":
                for (Map.Entry<String, Staff> gs : groundStaffList.entrySet()) {
                    if (id.equals(gs.getKey())) {
                        isFound = true;
                        break;
                    }
                }
                break;
            default:
                System.out.println("Invalid staff role!");
        }

        if (!isFound) {
            System.out.println(errMsg);
            id = null;
        }
        return id;
    }
    
    /**
     * This function saves crew assignment list to file output
     * @param path 
     */
    public void saveCrewAssignmentListToFileOutput(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (CrewAssignments ca : crewAssignmentList) {
                writer.write(ca.toString());
            }
            writer.close();
            System.out.println("Save crew assignment list to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
