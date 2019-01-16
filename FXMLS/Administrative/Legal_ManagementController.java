/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Legal_ManagementController implements Initializable {

    @FXML
    private TableView<?> request_table;
    @FXML
    private TableColumn<?, ?> request_id;
    @FXML
    private TableColumn<?, ?> requestor_id;
    @FXML
    private TableColumn<?, ?> datei;
    @FXML
    private TableColumn<?, ?> datee;
    @FXML
    private JFXListView<?> reqfilepath;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXComboBox<?> casecategory;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void open(MouseEvent event) {
    }
    
}
