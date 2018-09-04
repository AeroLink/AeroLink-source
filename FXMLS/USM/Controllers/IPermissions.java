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
public class IPermissions extends RecursiveTreeObject{
    
    StringProperty permissions;

    public IPermissions(String permissions) {
        this.permissions = new SimpleStringProperty(permissions);
    }
    
}
