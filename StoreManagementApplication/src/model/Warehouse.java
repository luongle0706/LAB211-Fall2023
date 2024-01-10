
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse implements Serializable{
    private static final AtomicInteger count = new AtomicInteger(0); 
    private String type;
    private int selfIncrementingNumber;
    private String importExportCode;
    private String time;
    private ArrayList<Product> productList;

    public Warehouse() {}

    public Warehouse(String type, String time, ArrayList<Product> productList) {
        this.type = type;
        this.selfIncrementingNumber = count.incrementAndGet();
        this.importExportCode = String.format("%07d", selfIncrementingNumber);
        this.time = time;
        this.productList = productList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImportExportCode() {
        return importExportCode;
    }

    public void setImportExportCode(String importExportCode) {
        this.importExportCode = importExportCode;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return String.format("%s\n"
                + "Receipt code: %7s          Time created: %-20s\n"
                + "==========================================================================================================\n"
                + "|Code   |Name                |Producer            |Manufacturing date|Expiration date|Quantity|Unit Price|\n"
                + "=========================================================================================================="
                + "%s\n", type, importExportCode, time, productList.toString().replaceAll("^\\[","\n").replaceAll("\\s*,\\s*", "\n").replaceAll("\\]$", ""));
    }
    
}
