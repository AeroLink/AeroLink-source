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
public class TM_TrainingRequisition_Class {

    public SimpleStringProperty tr_id, dept_name, title, date_requested, status_id, status_name;

    public TM_TrainingRequisition_Class(String tr_id, String dept_name, String title, String date_requested,
            String status_id, String status_name) {

        this.tr_id = new SimpleStringProperty(tr_id);
        this.dept_name = new SimpleStringProperty(dept_name);
        this.title = new SimpleStringProperty(title);
        this.date_requested = new SimpleStringProperty(date_requested);
        this.status_id = new SimpleStringProperty(status_id);
        this.status_name = new SimpleStringProperty(status_name);

    }
}
