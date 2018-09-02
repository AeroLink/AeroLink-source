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
public class Training_ManagementClass extends RecursiveTreeObject{

    public SimpleStringProperty training_id;
    public SimpleStringProperty job_position;
    public SimpleStringProperty training_title;
    public SimpleStringProperty training_description;
    public SimpleStringProperty trainor;
    public SimpleStringProperty start_date;
    public SimpleStringProperty end_date;
    public SimpleStringProperty start_time;
    public SimpleStringProperty end_time;
    public SimpleStringProperty type_of_training;
    public SimpleStringProperty location;
    public Button Edit;
    public Button Delete;
    
    
    public SimpleStringProperty vehicle;
    public SimpleStringProperty budget_cost;

    public Training_ManagementClass(String training_id, String job_position, String training_title,
            String training_description, String trainor, String start_date, String end_date, String start_time, String end_time,
            String type_of_training, String location, String vehicle, String budget_cost) {
        this.training_id = new SimpleStringProperty(training_id);
        this.job_position = new SimpleStringProperty(job_position);
        this.training_title = new SimpleStringProperty(training_title);
        this.training_description = new SimpleStringProperty(training_description);
        this.trainor = new SimpleStringProperty(trainor);
        this.start_date = new SimpleStringProperty(start_date);
        this.end_date = new SimpleStringProperty(end_date);
        this.start_time = new SimpleStringProperty(start_time);
        this.end_time = new SimpleStringProperty(end_time);
        this.type_of_training = new SimpleStringProperty(type_of_training);
        this.location = new SimpleStringProperty(location);
        this.vehicle = new SimpleStringProperty(vehicle);
        this.budget_cost = new SimpleStringProperty(budget_cost);
        this.Edit = new Button("Edit");
        this.Delete = new Button("Delete");
    }

  
}
