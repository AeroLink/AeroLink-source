/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.util.HashMap;
import javafx.scene.control.Alert;

/**
 *
 * @author Lei
 */
public class Response {
   
    public static String ORM_ERR_01 = "Where values must 3 values.. \nExample \n { { \"column\", \"=\", \"'example'\"}, { \"column2\", \"=\", \"'example2'\"} }";
    public static String ORM_ERR_02 = "Insert values must 2 values.. \nExample \n { { \"column\", \"'example'\"}, { \"column2\", \"'example2'\"} }";
   
    public static void ErrorResponse() {
        AL alert = new AL(Alert.AlertType.ERROR, "Error", "Halt!", "An error occured");
        alert.open();
    }
}