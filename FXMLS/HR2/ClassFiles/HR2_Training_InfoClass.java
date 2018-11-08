/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Eden Ramoneda
 */
public class HR2_Training_InfoClass extends RecursiveTreeObject {

    public SimpleStringProperty training_id;
    public SimpleStringProperty job_position;
    public SimpleStringProperty training_title;
    public SimpleStringProperty start_date;
    public SimpleStringProperty end_date;
    
    public HR2_Training_InfoClass(String training_id, String job_position, String training_title,
            String start_date, String end_date) {

        this.training_id = new SimpleStringProperty(training_id);
        this.job_position = new SimpleStringProperty(job_position);
        this.training_title = new SimpleStringProperty(training_title);
        this.start_date = new SimpleStringProperty(start_date);
        this.end_date = new SimpleStringProperty(end_date);
    }

}
