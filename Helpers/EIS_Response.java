/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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

    public static void SuccessResponseSB(String title, String message) {
        Notifications nBuilder = Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(2))
                .position(Pos.BOTTOM_RIGHT);

        nBuilder.showConfirm();
    }

    public static void ErrorResponseSB(String title, String message) {
        Notifications nBuilder = Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(2))
                .position(Pos.BOTTOM_RIGHT);

        nBuilder.showError();
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
