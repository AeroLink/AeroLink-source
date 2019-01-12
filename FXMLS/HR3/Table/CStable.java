/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author my
 */
public class CStable {
    public StringProperty ID;
    public StringProperty Name;
    public StringProperty Position;
    public StringProperty Monday;
    public StringProperty Tuesday;
    public StringProperty Wednesday;
    public StringProperty Thursday;
    public StringProperty Friday;
    
    public CStable(String ID, String Name, String Position, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday) {
        this.ID = new SimpleStringProperty(ID);
        this.Name = new SimpleStringProperty(Name);
        this.Position = new SimpleStringProperty(Position);
        this.Monday = new SimpleStringProperty(Monday);
        this.Tuesday = new SimpleStringProperty(Tuesday);
        this.Wednesday = new SimpleStringProperty(Wednesday);
        this.Thursday = new SimpleStringProperty(Thursday);
        this.Friday = new SimpleStringProperty(Friday);
    }
    
    
}
