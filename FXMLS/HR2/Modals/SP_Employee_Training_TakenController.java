/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.SP_Employee_Info_Modal;
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
public class SP_Employee_Training_TakenController implements Initializable {

    @FXML
    private Label lbl_count;
    @FXML
    private TableView<?> tbl_training_taken;
    @FXML
    private TableColumn<?, ?> col_training_taken;
    @FXML
    private Label lbl_fullname;
    @FXML
    private Label lbl_jp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_fullname.setText(SP_Employee_Info_Modal.fullname);
        lbl_jp.setText(SP_Employee_Info_Modal.title);

        int count_training_taken = tbl_training_taken.getItems().size();
        lbl_count.setText(String.valueOf(count_training_taken));
    }

}
