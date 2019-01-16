/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1;

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
 * @author Crenz
 */
public class MaintenanceRepairOHController implements Initializable {

    @FXML
    private TableView<?> building_tbl;
    @FXML
    private TableColumn<?, ?> building_col;
    @FXML
    private TableColumn<?, ?> Type_col;
    @FXML
    private TableColumn<?, ?> address_col;
    @FXML
    private TableColumn<?, ?> contact_col;
    @FXML
    private TableColumn<?, ?> Status_col;
    @FXML
    private TableColumn<?, ?> Status_col1;
    @FXML
    private TableColumn<?, ?> Status_col11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddBuildingAction(ActionEvent event) {
    }

    @FXML
    private void updateBuildingAction(ActionEvent event) {
    }
    
}
