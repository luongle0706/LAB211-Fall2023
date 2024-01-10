
package model;

public class Passenger {
    private String pId;
    private String name;
    private String phoneNum;
    private String dateOfBirth;

    public Passenger(String pId, String name, String phoneNum, String dateOfBirth) {
        this.pId = pId;
        this.name = name;
        this.phoneNum = phoneNum;
        this.dateOfBirth = dateOfBirth;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + pId + "| Full name: " + name + "| Phone num: " + phoneNum + "| Date of birth:" + dateOfBirth;
    }
    
}
