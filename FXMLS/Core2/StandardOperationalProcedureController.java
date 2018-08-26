/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.icons.icon;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class StandardOperationalProcedureController implements Initializable {

    @FXML
    private JFXButton btn_perishable;
    @FXML
    private JFXButton btn_nonperishable;
    @FXML
    private JFXButton btn_documents;
    @FXML
    private JFXButton btn_nondocuments;
    @FXML
    private JFXButton btn_fragile;
    @FXML
    private JFXButton btn_luxury;
    @FXML
    private JFXButton btn_perishable1;
    @FXML
    private JFXButton btn_perishable11;
    @FXML
    private JFXButton btn_perishable12;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void perishable(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/perishable.fxml","Perishable");
    }

    @FXML
    private void nonperishable(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/nonperishable.fxml","Non Perishable");
    }

    @FXML
    private void documents(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/documents.fxml","Documents");
    }

    @FXML
    private void nondocuments(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/nondocuments.fxml","Non Documents");
    }

    @FXML
    private void fragile(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/fragile.fxml","Fragile");
    }

    @FXML
    private void luxury(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/luxury.fxml","Luxury");
    }

    @FXML
    private void packageitems(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/packageitems.fxml","Package Items");
    }

    @FXML
    private void deliveryitems(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/deliveryitems.fxml","Delivery Items");
    }

    @FXML
    private void dangerousitems(ActionEvent event) {
        loadWindow("/FXMLS/Core2/sop/dangerousitems.fxml","Dangerous Items");
    }

    void loadWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            icon.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(StandardOperationalProcedureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
