/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_Job_VacancyClass;
import FXMLS.HR2.ClassFiles.HR2_Training_InfoClass;
import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import Model.HR2_Jobs;
import Model.HR2_Training_Info;
import Synapse.Model;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class ViewJobVacancyController implements Initializable {

    @FXML
    private TableView<HR2_Job_VacancyClass> tbl_job_vacancy;
    @FXML
    private TableColumn<HR2_Job_VacancyClass, String> col_job_vacancy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        DisplayDataInTable();
    }    
    
    public void loadData() {

        HR2_Jobs jv = new HR2_Jobs();

        List jv_data = jv.join(Model.JOIN.INNER, "aerolink.tbl_hr4_job_limit", "job_id", "j_limit", "=", "job_id")
                .where(new Object[][]{{"j_limit.jobOpen", "!=", "0"}})
                .get("title");
        Data(jv_data);

    }

    public void Data(List b) {
        ObservableList<HR2_Job_VacancyClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b) {
                HashMap hm = (HashMap) d;
                obj.add(
                        new HR2_Job_VacancyClass(
                                String.valueOf(hm.get("title"))
                        ));

            }
            tbl_job_vacancy.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     public void DisplayDataInTable() {

        col_job_vacancy.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Job_VacancyClass, String> param) -> param.getValue().title);
       

    }
}
