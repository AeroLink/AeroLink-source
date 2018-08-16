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
public class Training_ManagementClass {
    
        private SimpleStringProperty emp_id;
        private SimpleStringProperty emp_name; 
         private SimpleStringProperty job_position;
        private SimpleStringProperty title; 
         private SimpleStringProperty trainor;
        private SimpleStringProperty type_of_training; 
         private SimpleStringProperty location;
        private SimpleStringProperty date_start; 
         private SimpleStringProperty date_end;
        private SimpleStringProperty time_start; 
         private SimpleStringProperty time_end;
        private SimpleStringProperty budget_cost; 
         private SimpleStringProperty organizer;
         
        public Training_ManagementClass(String emp_id,String emp_name,String job_position,
                 String title,String trainor,String type_of_training,String location,String date_start,String date_end,
                    String time_start,String time_end,String budget_cost,String organizer)
         {
             this.emp_id = new SimpleStringProperty(emp_id);
             this.emp_name = new SimpleStringProperty(emp_name);
             this.job_position = new SimpleStringProperty(job_position);
             this.title = new SimpleStringProperty(title);
             this.trainor = new SimpleStringProperty(trainor);
             this.type_of_training = new SimpleStringProperty(type_of_training);
             this.location = new SimpleStringProperty(location);
             this.date_start = new SimpleStringProperty(date_start);
             this.date_end = new SimpleStringProperty(date_end);
             this.time_start = new SimpleStringProperty(time_start);
             this.time_end = new SimpleStringProperty(time_end);
             this.budget_cost = new SimpleStringProperty(budget_cost);
             this.organizer = new SimpleStringProperty(organizer);
         }
        
                    
             public String getEmp_Id()
             {
                 return emp_id.get();
             }
             
             public String getEmp_Name()
             {
                 return emp_name.get();
             }
             
             public String getJobPosition()
             {
                 return job_position.get();
             }
             
             public String getTitle()
             {
                 return title.get();
             }
             
             public String getTrainor()
             {
                 return trainor.get();
             }
             
             public String getType_of_training()
             {
                 return type_of_training.get();
             }
             
             public String getLocation()
             {
                 return location.get();
             }
             
             public String getDate_Start()
             {
                 return date_start.get();
             }
             
             public String getDate_End()
             {
                 return date_end.get();
             }
             
             public String getTime_Start()
             {
                 return time_start.get();
             }
             
             public String getTime_End()
             {
                 return time_end.get();
             }
             
             public String getBudget_Cost()
             {
                 return budget_cost.get();
             }
             
             public String getOrganizer()
             {
                 return organizer.get();
             }
}
