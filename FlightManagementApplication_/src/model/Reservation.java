
package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Reservation {
    private static final AtomicInteger count = new AtomicInteger(0); 
    private int selfIncrementingNumber;
    private String rId;
    private Flight flightInfo;
    private Passenger passengerInfo;
    private boolean checkInStatus;
    private String selectedSeat;

    public Reservation(Flight flightInfo, Passenger passengerInfo, boolean checkInStatus, String selectedSeat) {
        this.selfIncrementingNumber = count.incrementAndGet();
        this.rId = String.format("%07d", selfIncrementingNumber);
        this.flightInfo = flightInfo;
        this.passengerInfo = passengerInfo;
        this.checkInStatus = checkInStatus;
        this.selectedSeat = selectedSeat;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public Flight getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }

    public Passenger getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(Passenger passengerInfo) {
        this.passengerInfo = passengerInfo;
    }

    public boolean isCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckInStatus(boolean checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public String getSelectedSeat() {
        return selectedSeat;
    }

    public void setSelectedSeat(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }
    

    @Override
    public String toString() {
        return "Reservation ID: " + rId + "\nFlight info:\n" + flightInfo.toString() + 
               "\nPassenger info:\n" + passengerInfo.toString() + "\nCheck-in status: " + checkInStatus + "\nSelected seat=" + selectedSeat;
    }
    
}
