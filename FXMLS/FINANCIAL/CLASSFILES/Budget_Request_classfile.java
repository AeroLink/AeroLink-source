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
public class Budget_Request_classfile {
          public SimpleStringProperty brrequestno;
          public SimpleStringProperty brdate;
          public SimpleStringProperty brdepartment;
          public SimpleStringProperty brrequestorname;
          public SimpleStringProperty brdescription;
          public SimpleStringProperty brprioritylevel;
          public SimpleStringProperty bramount;
          public SimpleStringProperty brstatus;
          public SimpleStringProperty brApprovedBy;
          public SimpleStringProperty brReason;
        
        
        
        
        public Budget_Request_classfile(String requestno,String date,String department,String requestor_name,String description,
                                        String prioritylevel,String amount,String status,String approvedby,String reason){
            
            this.brrequestno = new SimpleStringProperty(requestno); 
            this.brdate = new SimpleStringProperty(date);
            this.brdepartment = new SimpleStringProperty(department);
            this.brrequestorname = new SimpleStringProperty(requestor_name);
            this.brdescription = new SimpleStringProperty(description); 
            this.brprioritylevel = new SimpleStringProperty(prioritylevel);
            this.bramount = new SimpleStringProperty(amount); 
            this.brstatus = new SimpleStringProperty(status);
            this.brApprovedBy = new SimpleStringProperty(approvedby);
            this.brReason = new SimpleStringProperty(reason);
        }
            
            public String getRequestNo(){
            return brrequestno.get();
             }
            public String getDate(){
            return brdate.get();
             }
            public String getDepartment(){
            return brdepartment.get();
             }
            public String getRequestor_name(){
            return brrequestorname.get();
             }
             public String getDescription(){
            return brdescription.get();
             }
              public String getPrioritylevel(){
            return brprioritylevel.get();
             }
             public String getAmount(){
            return bramount.get();
             }
            public String getStatus(){
            return brstatus.get();
             }
}
