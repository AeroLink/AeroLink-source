/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.TM_ViewParticipantsClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class TM_ViewParticipantsController implements Initializable {

    @FXML
    private TableView<?> tbl_name_of_participants;
    @FXML
    private TableColumn<?, ?> col_name_of_participants;
    @FXML
    private Label lbl_position;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_position.setText(TM_ViewParticipantsClass.job_position);
    }    
    
}
