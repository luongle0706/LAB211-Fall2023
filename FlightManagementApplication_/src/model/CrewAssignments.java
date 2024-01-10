
package model;

import java.util.HashMap;

public class CrewAssignments {
    private Flight forFlight;
    private HashMap<String, Staff> pilots;
    private HashMap<String, Staff> flightAttendants;
    private HashMap<String, Staff> groundStaffs;

    public CrewAssignments(Flight forFlight, HashMap<String, Staff> pilots, HashMap<String, Staff> flightAttendants, HashMap<String, Staff> groundStaffs) {
        this.forFlight = forFlight;
        this.pilots = pilots;
        this.flightAttendants = flightAttendants;
        this.groundStaffs = groundStaffs;
    }

    public Flight getForFlight() {
        return forFlight;
    }

    public void setForFlight(Flight forFlight) {
        this.forFlight = forFlight;
    }

    public HashMap<String, Staff> getPilots() {
        return pilots;
    }

    public void setPilots(HashMap<String, Staff> pilots) {
        this.pilots = pilots;
    }

    public HashMap<String, Staff> getFlightAttendants() {
        return flightAttendants;
    }

    public void setFlightAttendants(HashMap<String, Staff> flightAttendants) {
        this.flightAttendants = flightAttendants;
    }

    public HashMap<String, Staff> getGroundStaffs() {
        return groundStaffs;
    }

    public void setGroundStaffs(HashMap<String, Staff> groundStaffs) {
        this.groundStaffs = groundStaffs;
    }

    @Override
    public String toString() {
        return "For flight:\n" + forFlight + "\nPilots:\n" + pilots.toString() + "\nFlight attendants:\n" + 
                flightAttendants.toString() + "\nGround staffs:\n" + groundStaffs.toString();
    } 
}
