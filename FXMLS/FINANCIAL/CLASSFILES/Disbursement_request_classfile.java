/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class Disbursement_request_classfile {
          public SimpleStringProperty drID;
          public SimpleStringProperty drReqno;
          public SimpleStringProperty drDatereq;
          public SimpleStringProperty drReqname;
          public SimpleStringProperty drDescription; 
          public SimpleStringProperty drDepartment; 
          public SimpleStringProperty drprioritylvl;
          public SimpleStringProperty drAmount;
          public SimpleStringProperty drdisbursementStatus;
        
public Disbursement_request_classfile(String dr_id,String Requestno,
        String datereq,
        String reqname,
        String department,
        String description,
        String priority_level,
        String amount,
        String disbursestatus){
            
          this.drID = new SimpleStringProperty(dr_id); 
          this.drReqno = new SimpleStringProperty(Requestno); 
          this.drDatereq = new SimpleStringProperty(datereq); 
          this.drReqname = new SimpleStringProperty(reqname); 
          this.drDepartment = new SimpleStringProperty(department);
          this.drDescription = new SimpleStringProperty(description);
          this.drprioritylvl = new SimpleStringProperty(priority_level);
          this.drAmount = new SimpleStringProperty(amount);
          this.drdisbursementStatus = new SimpleStringProperty(disbursestatus);
          
        }
            public String getDr_id(){
                return drID.get();
        }
            public String getRequestno(){
            return drReqno.get();
        }
            public String getDatereq(){
            return drDatereq.get();
        }
            public String getReqname(){
            return drReqname.get();
        }
            public String getDepartment(){
            return drDepartment.get();
        }
            public String getDescription(){
            return drDescription.get();
        }
            public String getPriority_level(){
            return drprioritylvl.get();
        }
            public String getAmount(){
            return drAmount.get();
        }
            public String getDisbursementStatus(){
            return drdisbursementStatus.get();
        }
}
