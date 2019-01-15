/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Server_Error
 */
public class Admin_Legal_ManagementController {
    public StringProperty ID_No;
    public StringProperty Request_ID;
    public StringProperty Date_Issued;
    public StringProperty Date_Expired;
    public StringProperty Category;
    
    public Admin_Legal_ManagementController(String ID_No, String Request_ID, String Category,String Date_Issued,String Date_Expired){
    this.ID_No = new SimpleStringProperty(ID_No);
    this.Request_ID = new SimpleStringProperty(Request_ID);
    this.Category = new SimpleStringProperty(Category);
    this.Date_Issued = new SimpleStringProperty(Date_Issued);
    this.Date_Expired = new SimpleStringProperty(Date_Expired);
 
    
    }
    public String getID_No(){
        return ID_No.get();
    }
 
    public String getRequest_ID(){
        return Request_ID.get();
    }
    
   
    public String getDate_Issued(){
        return Date_Issued.get();
    }
    
    public String getDate_Expired(){
        return Date_Expired.get();
    }
    
    public String getCategory(){
        return Category.get();
    }
    
}
