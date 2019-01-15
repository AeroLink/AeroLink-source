/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Gilbert
 */
public class FINANCIAL_BR_CLASSFILE extends RecursiveTreeObject{
        public SimpleStringProperty id;
        public SimpleStringProperty budgetno;
        public SimpleStringProperty dateRequest;
        public SimpleStringProperty department;
        public SimpleStringProperty requestor;
        public SimpleStringProperty description;
        public SimpleStringProperty prioritylvl;
        public SimpleStringProperty amount;
        public SimpleStringProperty status;
      //  private CheckBox select;
       // private Button More;
        
        
        
        public FINANCIAL_BR_CLASSFILE(String budget_id,
                String budget_no,
                String budget_date_request,
                String budget_department,
                String budget_requestor, 
                String budget_description,
                String budget_priority_lvl,
                String budget_amount,
                String budget_status){
            
            this.id = new SimpleStringProperty(budget_id); 
            this.budgetno = new SimpleStringProperty(budget_no); 
            this.dateRequest = new SimpleStringProperty(budget_date_request);
            this.department = new SimpleStringProperty(budget_department);
            this.requestor = new SimpleStringProperty(budget_requestor);
            this.description = new SimpleStringProperty(budget_description);  
            this.prioritylvl = new SimpleStringProperty(budget_priority_lvl);
            this.amount = new SimpleStringProperty(budget_amount);  
            this.status = new SimpleStringProperty(budget_status);
           // this.select = new CheckBox();
          // this.More = new Button("More");
        }
        
             public String getBudget_id(){
            return id.get();
             }
             public String getBudget_no(){
            return budgetno.get();
             }
             public String getBudget_date_request(){
            return dateRequest.get();
             }
             public String getBudget_department(){
            return department.get();
             }
              public String getBudget_requestor(){
                  
            return requestor.get();
             }
           
              public String getBudget_description(){
            return description.get();
             }
              public String getBudget_priority_lvl(){
                  
            return prioritylvl.get();
             }
              public String getBudget_amount(){
            return amount.get();
             }
               public String getBudget_status(){
            return status.get();
             }
          
           /* public Button getMore(){
            return More;
                }
            public void setMore(Button More){
            this.More = More;
}*/
}
