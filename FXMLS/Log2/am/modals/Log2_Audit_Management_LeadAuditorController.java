/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.am.modals;

import FXMLS.Log2.*;
import Model.Log2_Fleet_Management;
import Synapse.SysDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Audit_Management_LeadAuditorController implements Initializable {

    @FXML
    private JFXButton Close;
    @FXML
    private StackPane spane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_close(MouseEvent event) {
      
         SysDialog dialog = new SysDialog();

        VBox vbox = new VBox(
                new Label("Do you really want to close?")
        );

        JFXButton btnSubmitInit = new JFXButton("Yes");
        JFXButton btnCancel = new JFXButton("No");

        btnSubmitInit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        btnSubmitInit.setOnMouseClicked(value -> {
            // get a handle to the stage
            Stage stage = (Stage) btnSubmitInit.getScene().getWindow();
            // do what you have to do
            stage.close();
            
            
        });

        btnCancel.setOnMouseClicked(value -> {
            dialog.closeDialog();
        });

        dialog.createLayout(new Text("Warning!")).showDialog(spane, JFXDialog.DialogTransition.BOTTOM, vbox, btnSubmitInit, new JFXButton(""), btnCancel);

   
    }
    
}
