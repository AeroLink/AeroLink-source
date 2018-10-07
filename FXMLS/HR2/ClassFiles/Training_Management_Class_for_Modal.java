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
public class Training_Management_Class_for_Modal {

    public static String edit_training_id;
    public static String edit_job_position;
    public static String edit_training_title;
    public static String edit_training_description;
    public static String edit_trainor;
    public static String edit_start_date;
    public static String edit_end_date;
    public static String edit_start_time;
    public static String edit_end_time;
    public static String edit_type_of_training;
    public static String edit_location;
    public static String edit_vehicle;
    public static String edit_budget_cost;

    public static void Training_Management_Modal(String training_id, String job_position, String training_title,
            String training_description, String trainor, String start_date, String end_date, String start_time, String end_time,
            String type_of_training, String location, String vehicle, String budget_cost) {
        //        
        edit_training_id = training_id;
        edit_job_position = job_position;
        edit_training_title = training_title;
        edit_training_description = training_description;
        edit_trainor = trainor;
        edit_start_date = start_date;
        edit_end_date = end_date;
        edit_start_time = start_time;
        edit_end_time = edit_end_time;
        edit_type_of_training = type_of_training;
        edit_location = location;
        edit_vehicle = vehicle;
        edit_budget_cost = budget_cost;
    }
}
