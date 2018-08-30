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
public class ServiceNetworkTableController{
  
    public StringProperty branch_code;
    public StringProperty branch_location;
    public StringProperty branch_address;
    public StringProperty branch_email;
    public StringProperty branch_contact;
    public StringProperty branch_manager;

    public ServiceNetworkTableController(String branch_code, String branch_location, String branch_address, String branch_email, String branch_contact, String branch_manager){
        
        this.branch_code = new SimpleStringProperty(branch_code);
        this.branch_location = new SimpleStringProperty(branch_location);
        this.branch_address = new SimpleStringProperty(branch_address);
        this.branch_email = new SimpleStringProperty(branch_email);
        this.branch_contact = new SimpleStringProperty(branch_contact);
        this.branch_manager = new SimpleStringProperty(branch_manager);
    }
    
    public String getBranch_code(){
        return branch_code.get();
    }
    
    public String getBranch_location(){
        return branch_location.get();
    }
    
    public String getBranch_address(){
        return branch_address.get();
    }
    
    public String getBranch_email(){
        return branch_email.get();
    }
    
    public String getBranch_contact(){
        return branch_contact.get();
    }
    
    public String getBranch_manager(){
        return branch_manager.get();
    }
    
}
