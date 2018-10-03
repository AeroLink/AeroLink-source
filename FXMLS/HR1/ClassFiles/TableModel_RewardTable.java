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
public class TableModel_RewardTable {
    
    public StringProperty reward_name;
    public StringProperty reward_remarks;
    public StringProperty date_added;
    
    public TableModel_RewardTable(String reward_name,String reward_remarks,String date_added){
        this.reward_name = new SimpleStringProperty(reward_name);
        this.reward_remarks = new SimpleStringProperty(reward_remarks);
        this.date_added = new SimpleStringProperty(date_added);
    
    }
    
    
}
