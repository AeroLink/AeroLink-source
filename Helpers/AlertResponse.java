/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Lei
 */
public class AlertResponse {
    
    private Alert alert;
    
    public AlertResponse(AlertType a, String title, String HeaderText, String ContextText) {
        
        alert = new Alert(a);
        alert.setTitle(title);
        alert.setHeaderText(HeaderText);
        alert.setContentText(ContextText);
    
    }
    
    public void open(){    
      alert.showAndWait();
    }
}
