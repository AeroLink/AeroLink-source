/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class TM_Request_BudgetClass {

    public SimpleStringProperty Request_Title, Priority_Level, Amount, Status;

    public TM_Request_BudgetClass(String Request_Title, String Priority_Level, String Amount, String Status) {
        this.Request_Title = new SimpleStringProperty(Request_Title);
        this.Priority_Level = new SimpleStringProperty(Priority_Level);
        this.Amount = new SimpleStringProperty(Amount);
        this.Status = new SimpleStringProperty(Status);
    }
}
