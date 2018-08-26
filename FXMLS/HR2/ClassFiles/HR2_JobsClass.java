/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLS.HR2.ClassFiles;

/**
 * 
 * @author Eden Ramoneda
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_JobsClass {
    
                      
        public SimpleStringProperty Job_ID;
         public SimpleStringProperty Title;
          public SimpleStringProperty Description;
          
          public HR2_JobsClass(String Job_ID, String Title, String Description)
          {
              this.Job_ID = new SimpleStringProperty(Job_ID);
               this.Title = new SimpleStringProperty(Title);
                this.Description = new SimpleStringProperty(Description);
          }
          
}

