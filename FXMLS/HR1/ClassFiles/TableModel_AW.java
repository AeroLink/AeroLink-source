/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class TableModel_AW {
    
    public StringProperty aTitle;
    public StringProperty date_awarded;

    public TableModel_AW(String aTitle, String date_awarded) {
        this.aTitle = new SimpleStringProperty(aTitle);
        this.date_awarded = new SimpleStringProperty(date_awarded);
    }
    
    
}
