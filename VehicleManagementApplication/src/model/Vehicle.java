package model;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String vId;
    private String vName;
    private String vColor;
    private String vBrand;
    private String vType;
    private int vProductYear;
    private int vPrice;

    public Vehicle(String vId, String vName, String vColor, String vBrand, String vType, int vProductYear, int vPrice) {
        this.vId = vId;
        this.vName = vName;
        this.vColor = vColor;
        this.vBrand = vBrand;
        this.vType = vType;
        this.vProductYear = vProductYear;
        this.vPrice = vPrice;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvColor() {
        return vColor;
    }

    public void setvColor(String vColor) {
        this.vColor = vColor;
    }

    public String getvBrand() {
        return vBrand;
    }

    public void setvBrand(String vBrand) {
        this.vBrand = vBrand;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public int getvProductYear() {
        return vProductYear;
    }

    public void setvProductYear(int vProductYear) {
        this.vProductYear = vProductYear;
    }
    
    public int getvPrice() {
        return vPrice;
    }

    public void setvPrice(int vPrice) {
        this.vPrice = vPrice;
    }
    
    /**
     * To-string function
     * @return 
     */
    @Override
    public String toString() {
        return "ID: " + vId + ", Name: " + vName + ", Color: " + vColor + 
               ", Brand: " + vBrand + ", Type: " + vType + ", Product year: " + vProductYear + ", Price: " + vPrice;
    }
    
    /**
     * This function is used when to-string to output file
     * @return 
     */
    public String displayOuputFile() {
        return vId + "," + vName + "," + vColor + "," + vBrand + "," + vType + "," + vProductYear + "," + vPrice;
    }
}
