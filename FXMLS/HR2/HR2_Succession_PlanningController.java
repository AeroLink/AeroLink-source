/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Succession_PlanningController implements Initializable {

    @FXML
    private JFXButton btn_job_vacancy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void jobVacancyModal()
    {
        Modal m = Modal.getInstance(new Form("/FXMLS/HR2/Modals/ViewJobVacancy.fxml").getParent());
        m.open();
    }
}
