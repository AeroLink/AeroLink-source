 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.USM.Controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class IUsers extends RecursiveTreeObject<IUsers>{
    
    public StringProperty id;
    public StringProperty username;
    public StringProperty created_at;
    public StringProperty update_at;

    public IUsers(String id, String username, String created_at, String update_at) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.created_at = new SimpleStringProperty(created_at);
        this.update_at = new SimpleStringProperty(update_at);
    }
    
    
}
