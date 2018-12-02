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
 * @author RAVEN
 */
public class TableModel_AwardTable {
    public StringProperty award_name;
    public StringProperty award_description;
    public StringProperty date_added;

    public TableModel_AwardTable(String award_name, String award_description, String date_added) {
        this.award_name = new SimpleStringProperty(award_name);
        this.award_description = new SimpleStringProperty(award_description);
        this.date_added = new SimpleStringProperty(date_added);
    }

    
}
