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
public class Log2_Fleet_Management_outboundtable {

    public SimpleStringProperty Id;
    public SimpleStringProperty Vehicle_model;
    public SimpleStringProperty Plate_no;

    public Log2_Fleet_Management_outboundtable(String id, String vmodel, String no) {
        this.Id = new SimpleStringProperty(id);
        this.Vehicle_model = new SimpleStringProperty(vmodel);
        this.Plate_no = new SimpleStringProperty(no);

    }
}
