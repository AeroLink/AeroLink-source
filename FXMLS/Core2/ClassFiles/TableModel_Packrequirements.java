/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author JPEG
 */
public class TableModel_Packrequirements {
    public StringProperty tray;
    public StringProperty size;
    public StringProperty min_weight;
    public StringProperty amount;
    
    public TableModel_Packrequirements(String tray, String size, String min_weight, String amount){
        this.tray = new SimpleStringProperty(tray);
        this.size = new SimpleStringProperty(size);
        this.min_weight = new SimpleStringProperty(min_weight);
        this.amount = new SimpleStringProperty(amount);
    }

    public String getTray() {
        return tray.get();
    }

    public String getSize() {
        return size.get();
    }

    public String getMin_weight() {
        return min_weight.get();
    }

    public String getAmount() {
        return amount.get();
    }
    
    
    
}
