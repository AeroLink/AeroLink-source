/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eden Ramoneda
 */
public class HR4_Jobs_Class {

    public SimpleStringProperty Job_ID;
    public SimpleStringProperty Title;
    public SimpleStringProperty Description;

    public HR4_Jobs_Class(String Job_ID, String Title, String Description) {
        this.Job_ID = new SimpleStringProperty(Job_ID);
        this.Title = new SimpleStringProperty(Title);
        this.Description = new SimpleStringProperty(Description);
    }

}
