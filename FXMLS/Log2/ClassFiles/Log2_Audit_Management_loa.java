/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randelle
 */
public class Log2_Audit_Management_loa {

    public SimpleStringProperty Auditor;
    public SimpleStringProperty Area_of_exp;

    public Log2_Audit_Management_loa(String auditor, String aoe) {
        this.Auditor = new SimpleStringProperty(auditor);
        this.Area_of_exp = new SimpleStringProperty(aoe);

    }
}
