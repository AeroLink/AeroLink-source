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
public class TableModel_surcharges {
    public StringProperty surcharges;
    public StringProperty hwc;
    public StringProperty add_charge;
    
    public TableModel_surcharges(String surcharges, String hwc, String add_charge){
        this.surcharges = new SimpleStringProperty(surcharges);
        this.hwc = new SimpleStringProperty(hwc);
        this.add_charge = new SimpleStringProperty(add_charge);
    }

    public String getSurcharges() {
        return surcharges.get();
    }

    public String getHwc() {
        return hwc.get();
    }

    public String getAdd_charge() {
        return add_charge.get();
    }
    
    
}
