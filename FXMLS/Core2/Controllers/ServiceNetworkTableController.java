/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jpeg
 */
public class ServiceNetworkTableController extends RecursiveTreeObject<ServiceNetworkTableController>{
  
    public StringProperty branch_code;
    public StringProperty branch_location;
    public StringProperty branch_address;
    public StringProperty branch_email;
    public StringProperty branch_contact;
    public StringProperty branch_manager;

    public ServiceNetworkTableController(String code, String location, String address, String email, String contact, String manager){
        
        this.branch_code = new SimpleStringProperty(code);
        this.branch_location = new SimpleStringProperty(location);
        this.branch_address = new SimpleStringProperty(address);
        this.branch_email = new SimpleStringProperty(email);
        this.branch_contact = new SimpleStringProperty(contact);
        this.branch_manager = new SimpleStringProperty(manager);
    }
    
    public String getBranch_Code(){
        return branch_code.get();
    }
    
    public String getBranch_Location(){
        return branch_location.get();
    }
    
    public String getBranch_Address(){
        return branch_address.get();
    }
    
    public String getBranch_Email(){
        return branch_email.get();
    }
    
    public String getBranch_Contact(){
        return branch_contact.get();
    }
    
    public String getBranch_Manager(){
        return branch_manager.get();
    }
    
}
