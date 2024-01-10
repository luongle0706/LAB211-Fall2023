package model;

import tool.Checker;

public class Flight implements Comparable<Flight> {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private int duration;
    private int seats;
    private String[] availableSeats;

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime,  int duration, int seats, String[] availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.duration = duration;
        this.seats = seats;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String[] getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String[] availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight number: " + flightNumber + "| Departure city: " + departureCity + 
               "| Destination city: " + destinationCity + "| Departure time: " + departureTime +
               "| Duration: " + duration + "| Seats: " + seats;
    }
    
    /**
     * This function displays flight information
     */
    public void searchDisplay() {
        System.out.println("Flight number: " + flightNumber);
        System.out.println("Departure city: " + departureCity);
        System.out.println("Destination city: " + destinationCity);
        System.out.println("Departure time: " + departureTime);
        System.out.println("Duration: " + duration);
        System.out.println("Seats: " + seats);
    }
    
    /**
     * This function displays seat codes
     */
    public void displaySeats() {
        int index = 0;
        for (int i = 0; i < availableSeats.length / 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(availableSeats[index] + " ");
                index++;
            }
            System.out.println("");
        }
        for (int i = 0; i < availableSeats.length % 6; i++) {
            System.out.print(availableSeats[index] + " ");
            index++;
        }
    }
    
    /**
     * This function compares flight departure time
     * @param f
     * @return 
     */
    @Override
    public int compareTo(Flight f) {
        int result = 0;
        String split1[] = this.departureTime.split(" ");
        String date1 = split1[0];
        String split2[] = f.departureTime.split(" ");
        String date2 = split2[0];
        if (Checker.compareDepartureTime(date1, date2)) {
            result = 1;
        }
        else if (!Checker.compareDepartureTime(date1, date2)){
            result = -1;
        }
        if (date1.equals(date2)) {
            result = 0;
        }
        return result;
    } 
}
