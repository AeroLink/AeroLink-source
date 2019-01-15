/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.util;

import FXMLS.Log1.WarehouseManagementController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Crenz
 */
public class Log1Util {
    public static void loadWindow(URL loc, String title, Stage parentStage){
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if(parentStage != null){
                stage = parentStage;
            }else{
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarehouseManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
