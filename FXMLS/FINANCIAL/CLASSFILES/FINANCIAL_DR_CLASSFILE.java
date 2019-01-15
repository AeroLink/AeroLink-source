/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class FINANCIAL_DR_CLASSFILE extends RecursiveTreeObject{
    
        public SimpleStringProperty disburseID; 
        public SimpleStringProperty disburseDateSend; 
        public SimpleStringProperty disburseDateRequest;  
        public SimpleStringProperty disburseDepartment;
        public SimpleStringProperty disburseDescription;
        public SimpleStringProperty disburseRequestor;  
        public SimpleStringProperty disbursePrioritylvl;  
        public SimpleStringProperty disburseAmount;
        public SimpleStringProperty disburseStatus;  
        
       
    
        
     public FINANCIAL_DR_CLASSFILE(String disburse_id,
             String disburse_datesend,
             String disburse_date_request,
             String disburse_department,
             String disburse_description,
             String disburse_requestor,
             String disburse_prioritylvl,
             String disburse_amount,
             String disburse_status){
         
            this.disburseID = new SimpleStringProperty(disburse_id);
            this.disburseDateSend = new SimpleStringProperty(disburse_datesend);
            this.disburseDateRequest = new SimpleStringProperty(disburse_date_request);
            this.disburseDepartment = new SimpleStringProperty(disburse_department);
            this.disburseDescription = new SimpleStringProperty(disburse_description);
            this.disburseRequestor = new SimpleStringProperty(disburse_requestor);
            this.disbursePrioritylvl = new SimpleStringProperty(disburse_prioritylvl);
            this.disburseAmount = new SimpleStringProperty(disburse_amount);
            this.disburseStatus = new SimpleStringProperty(disburse_status);
     }
        public String getDisburse_id(){
            return disburseID.get();
        }
         public String getDisburse_datesend(){
            return disburseDateSend.get();
        }
        public String getDisburse_date_request(){
            return disburseDateRequest.get();
        }
        public String getDisburse_department(){
            return disburseDepartment.get();
        }
        public String getDisburse_description(){
            return disburseDescription.get();
        }
        public String getDisburse_requestor(){
            return disburseRequestor.get();
        }
        public String getDisburse_prioritylvl(){
            return disbursePrioritylvl.get();
        }
        public String getDisburse_amount(){
            return disburseAmount.get();
        }
        public String getDisburse_status(){
            return disburseStatus.get();
        }
    
}
