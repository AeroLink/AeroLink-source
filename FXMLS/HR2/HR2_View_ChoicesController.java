/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_EvaluationClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_View_ChoicesController implements Initializable {

    @FXML
    private TableView<HR2_EvaluationClass> tbl_choices;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
