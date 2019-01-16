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
public class TableModel_Rate {
    public StringProperty dec_items;
    public StringProperty percentage;
    public StringProperty quantity_desc;
    public StringProperty hwc;
    public StringProperty vat_gst;
    
    public TableModel_Rate(String dec_items, String percentage, String quantity_desc, String hwc, String vat_gst){
        this.dec_items = new SimpleStringProperty(dec_items);
        this.percentage = new SimpleStringProperty(percentage);
        this.quantity_desc = new SimpleStringProperty(quantity_desc);
        this.hwc = new SimpleStringProperty(hwc);
        this.vat_gst = new SimpleStringProperty(vat_gst);
    }
    
    public String getDec_items(){
        return dec_items.get();
    }

    public String getPercentage() {
        return percentage.get();
    }

    public String getQuantity_desc() {
        return quantity_desc.get();
    }

    public String getHwc() {
        return hwc.get();
    }

    public String getVat_gst() {
        return vat_gst.get();
    }
    
    
}
