/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import Model.Financial.Financial_budget_request;
import Model.HR2_TM_DefaultTrainings;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR2_Temp_Facilities;
import Model.HR2_Temp_Vehicles;
import Model.HR2_Type_of_Training;
import Model.HR4_Jobs;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class TM_AddTrainingController implements Initializable {

    @FXML
    private JFXComboBox cbox_jp;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextArea txt_training_desc;
    @FXML
    private JFXComboBox cbox_trainor;
    @FXML
    private JFXDatePicker txt_date;
    @FXML
    private JFXDatePicker txt_date2;
    @FXML
    private JFXComboBox cbox_type_of_training;
    @FXML
    private JFXTextField txt_limit_participants;
    @FXML
    private JFXComboBox cbox_facility;
    @FXML
    private JFXComboBox cbox_vehicle;
    @FXML
    private JFXButton btn_submit;
    @FXML
    private JFXTimePicker txt_from_time;
    @FXML
    private JFXTimePicker txt_to_time;
    @FXML
    private JFXComboBox cbox_budget;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBoxes();
    }

    public void ComboBoxes() {
        try {
            HR2_Type_of_Training type_of = new HR2_Type_of_Training();
            HR2_Temp_Employee_Profiles emp = new HR2_Temp_Employee_Profiles();
            HR2_Temp_Vehicles vehicles = new HR2_Temp_Vehicles();
            HR4_Jobs jobs = new HR4_Jobs();
            HR2_Temp_Facilities facilities = new HR2_Temp_Facilities();
            Financial_budget_request fbr = new Financial_budget_request();
            List j = jobs.get();

            for (Object jtitle : j) {
                HashMap hm1 = (HashMap) jtitle;
                cbox_jp.getItems().add("J" + hm1.get("job_id") + " - " + hm1.get("title"));
            }
            List emp_prof = emp.get();

            for (Object ep : emp_prof) {
                HashMap hm2 = (HashMap) ep;
                cbox_trainor.getItems().add(hm2.get("employee_code") + " - " + hm2.get("firstname") + ' ' + hm2.get("middlename") + ' ' + hm2.get("lastname"));
            }
            List type_of_training = type_of.get();

            for (Object training_type : type_of_training) {
                HashMap hm3 = (HashMap) training_type;
                cbox_type_of_training.getItems().add("TM" + hm3.get("type_of_training_id") + " - " + hm3.get("type_of_training"));
            }

            List f = facilities.get();

            for (Object venue : f) {
                HashMap hm4 = (HashMap) venue;
                cbox_facility.getItems().add("F00" + hm4.get("FacilityID") + " - " + hm4.get("FacilityName"));
            }
            List v = vehicles.get();

            for (Object cars : v) {
                HashMap hm5 = (HashMap) cars;
                cbox_vehicle.getItems().add("V00" + hm5.get("VehicleID") + " - " + hm5.get("VehicleModel"));
            }
            List budget = fbr.where(new Object[][]{
                {"budget_department","=","Human Resource - Training Management"},
                {"budget_status","=","Approved"}
            }).get();

            for (Object training_budget : budget) {
                HashMap hm6 = (HashMap) training_budget;
                cbox_budget.getItems().add("B00" + hm6.get("budget_id") + " - " + hm6.get("budget_description"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void SubmitRequest() {
         Object[][] d_trainings = {};
        try {
            HR2_TM_DefaultTrainings sr = new HR2_TM_DefaultTrainings();
            d_trainings = new Object[][]{
                        {"job_id", cbox_jp.getSelectionModel().getSelectedItem().toString().replace("J", "").split(" - ")[0]},
                        {"training_title", txt_training_title.getText()},
                        {"training_desc", txt_training_desc.getText()},
                        {"trainor", cbox_trainor.getSelectionModel().getSelectedItem().toString().split(" - ")[0]},
                        {"start_date", txt_date.getValue().toString()},
                        {"end_date", txt_date2.getValue().toString()},
                        {"start_time", txt_from_time.getValue().toString()},
                        {"end_time", txt_to_time.getValue().toString()},
                        {"type_of_training_id", cbox_type_of_training.getSelectionModel().getSelectedItem().toString().replace("TM", "").split(" - ")[0]},
                        {"limit_of_participants", txt_limit_participants.getText()},
                        {"facility_id", cbox_facility.getSelectionModel().getSelectedItem().toString().replace("F00", "").split(" - ")[0]},
                        {"vehicle_id", cbox_vehicle.getSelectionModel().getSelectedItem().toString().replace("V00", "").split(" - ")[0]},
                        {"budget_id", cbox_budget.getSelectionModel().getSelectedItem().toString().replace("B00", "").split(" - ")[0]},
                        {"isDeleted", "0"}
                    };

            sr.insert(d_trainings);

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Training Successfully Submitted");
            saved.showAndWait();
        } catch (Exception e) {
            
            System.err.println("PANGIT NA ERROR : " + Arrays.asList(d_trainings));
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
