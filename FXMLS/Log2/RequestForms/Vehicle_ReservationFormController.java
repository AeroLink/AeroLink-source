/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.RequestForms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Vehicle_ReservationFormController implements Initializable {

    @FXML
    private JFXTextField purpose;
    @FXML
    private JFXTextField location;
    @FXML
    private JFXButton btnsubmitreservation;
    @FXML
    private JFXTextField vmodel;
    @FXML
    private JFXTextField vtype;
    @FXML
    private JFXTextField specs;
    @FXML
    private JFXTextField stype;
    @FXML
    private JFXTextField plateno;
    @FXML
    private JFXTextField cap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_selectmodelform(MouseEvent event) throws IOException {
       vmodel.setDisable(true);
       vtype.setDisable(true);
       specs.setDisable(true);
       stype.setDisable(true);
       plateno.setDisable(true);
       cap.setDisable(true);
        
        
         Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent parent = loader.load(getClass().getResource("/FXMLS/Log2/vr/modals/Log2_Vehicle_Reservation_SelectV.fxml"));

        Scene scene = new Scene(parent);

        stage.setFullScreen(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();
    }
    
}
