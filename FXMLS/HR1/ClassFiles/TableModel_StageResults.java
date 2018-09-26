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
public class TableModel_StageResults {
    
    public StringProperty id;
    public StringProperty Stage;
    public StringProperty result;
    public StringProperty remarks;

    public TableModel_StageResults(String id, String Stage, String result, String remarks) {
        this.id = new SimpleStringProperty(id);
        this.Stage = new SimpleStringProperty(Stage);
        this.result = new SimpleStringProperty(result);
        this.remarks = new SimpleStringProperty(remarks);
    }
    
    
}
