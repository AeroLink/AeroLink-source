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
public class HR2_Type_of_TrainingClass {
    
    public SimpleStringProperty type_of_training_id,type_of_training;
    
      public HR2_Type_of_TrainingClass(String type_of_training_id, String type_of_training)
      {
          this.type_of_training_id = new SimpleStringProperty(type_of_training_id);
          this.type_of_training = new SimpleStringProperty(type_of_training);
      }
}
