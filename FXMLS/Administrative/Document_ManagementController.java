/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Document_ManagementController implements Initializable {

    @FXML
    private JFXTextField locationfield111111;
    @FXML
    private TableView<?> tablevisitoremployee;
    @FXML
    private TableColumn<?, ?> employeeid;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> office;
    @FXML
    private TableColumn<?, ?> floor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
