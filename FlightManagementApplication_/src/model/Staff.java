
package model;

public class Staff {
    private String sId;
    private String sName;
    private String sRole;

    public Staff(String sId, String sName, String sRole) {
        this.sId = sId;
        this.sName = sName;
        this.sRole = sRole;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return sName;
    }

    public void setName(String sName) {
        this.sName = sName;
    }

    public String getRole() {
        return sRole;
    }

    public void setRole(String sRole) {
        this.sRole = sRole;
    }

    @Override
    public String toString() {
        return "ID: " + sId + "| Name: " + sName + "| Role: " + sRole;
    }
    
}
