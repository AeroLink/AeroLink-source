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
public class TableModel_Stages {
    
    public StringProperty stage_id;
    public StringProperty stage_name;
    public StringProperty stage_description;

    public TableModel_Stages(String stage_id, String stage_name, String stage_description) {
        this.stage_id = new SimpleStringProperty(stage_id);
        this.stage_name = new SimpleStringProperty(stage_name);
        this.stage_description = new SimpleStringProperty(stage_description);
    }
    
    
}
