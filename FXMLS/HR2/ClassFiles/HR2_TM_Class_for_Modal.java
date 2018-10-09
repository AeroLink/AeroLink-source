/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLS.HR2.ClassFiles;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_TM_Class_for_Modal {
    
    public static String t_id,jp,t_title,t_desc,
    t_trainor,t_start_date,t_end_date,t_start_time,t_meridiem1, t_end_time, t_meridiem2, t_type_of_training,t_location,t_vehicle,t_budget_cost,
    t_no_of_participants;
    
    public static void HR2_TM_Modal(String job_position, String training_title,
            String training_description, String trainor, String start_date, String end_date, String start_time, String meridiem1, String end_time,String meridiem2,
            String type_of_training, String location, String vehicle, String budget_cost, String number_of_participants) {

        jp = job_position;
        t_title = training_title;
        t_desc = training_description;
        t_trainor = trainor;
        t_start_date = start_date;
        t_end_date = end_date;
        t_start_time = start_time;
        t_meridiem1 = meridiem1;
        t_end_time = end_time;
        t_meridiem2 = meridiem2;
        t_type_of_training = type_of_training;
        t_location = location;
        t_vehicle = vehicle;
        t_budget_cost = budget_cost;
        t_no_of_participants = number_of_participants;
    }
}
