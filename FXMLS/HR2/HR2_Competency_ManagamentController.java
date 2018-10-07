/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class;
import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import com.jfoenix.controls.JFXButton;
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
 * @author Eden Ramoneda
 */
public class HR2_Competency_ManagamentController implements Initializable {

    @FXML
    private JFXButton btn_set_skills;
    @FXML
    private JFXTextField txt_search_job;
    @FXML
    private TableView<HR4_Jobs_Class> tbl_jobs;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job_desc;
    @FXML
    private TableColumn<HR2_CM_Skills_Class, String> col_skills;
    @FXML
    private TableColumn<HR2_CM_Skills_Class, String> col_skill_desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
