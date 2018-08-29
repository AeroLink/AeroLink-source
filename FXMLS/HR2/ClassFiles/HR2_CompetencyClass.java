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
public class HR2_CompetencyClass {
    
         public SimpleStringProperty Job_ID;
         public SimpleStringProperty Skill_ID;
          
          public HR2_CompetencyClass(String Job_ID, String Skill_ID)
          {
              this.Job_ID = new SimpleStringProperty(Job_ID);
               this.Skill_ID = new SimpleStringProperty(Skill_ID);
          }
}
