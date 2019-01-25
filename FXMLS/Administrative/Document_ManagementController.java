/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.application.*;
import javafx.event.ActionEvent;

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
    @FXML
    private TableView<?> tablevisitoremployee1;
    @FXML
    private TableColumn<?, ?> employeeid1;
    @FXML
    private TableColumn<?, ?> name1;
    @FXML
    private TableColumn<?, ?> office1;
    @FXML
    private TableColumn<?, ?> floor1;
    @FXML
    private JFXButton btntakephoto1;
    @FXML
    private JFXTextField locationfield1111112;
    @FXML
    private JFXTextField locationfield11111121;
    @FXML
    private JFXDatePicker scheddate11;
    @FXML
    private JFXTextField locationfield111111211;
    @FXML
    private JFXTextField locationfield1111112111;
    @FXML
    private JFXButton btntakephoto11;
    @FXML
    private JFXButton btntakephoto;
    @FXML
    private JFXTextField locationfield1111111;
    @FXML
    private JFXTextField locationfield11111111;
    @FXML
    private JFXTextField locationfield111111111;
    @FXML
    private JFXTextField locationfield1111111111;
    @FXML
    private JFXDatePicker scheddate;
    @FXML
    private JFXDatePicker scheddate1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }    

    @FXML
    private void startcam(ActionEvent event) {
    }
    
}
