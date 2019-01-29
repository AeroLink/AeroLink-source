/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Shipment_filesController implements Initializable {

    @FXML
    private TableView<?> tableShipment;
    @FXML
    private TableColumn<?, ?> columnTracking;
    @FXML
    private TableColumn<?, ?> columnShipper;
    @FXML
    private TableColumn<?, ?> columnConsign;
    @FXML
    private TableColumn<?, ?> columnPack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
}
