/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.scene.control.Alert;

/**
 *
 * @author Lei
 */
public class EIS_Response {
    
    public static void SuccessResponse(String window_title, String title, String message) {
        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, window_title, title, message);
        alert.open();
    }
    
    public static void SuccessResponse(String title, String message) {
        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Success", title, message);
        alert.open();
    }
    
    public static void ErrorResponse(String title, String message) {
        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Error", title, message);
        alert.open();
    }
    
    
    public static void ErrorResponse() {
        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "Error", "Halt!", "An error occured");
        alert.open();
    }
    
}
