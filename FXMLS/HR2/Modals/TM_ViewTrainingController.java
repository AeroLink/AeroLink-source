/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class_for_Modal;
import FXMLS.HR2.ClassFiles.HR2_TM_ViewTrainingInfo_Modal;
import FXMLS.HR2.ClassFiles.HR2_Training_InfoClass;
import FXMLS.HR2.ClassFiles.TM_ViewParticipantsClass;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR2_Temp_Vehicles;
import Model.HR2_Training_Info;
import Model.HR2_Type_of_Training;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class TM_ViewTrainingController implements Initializable {

    private JFXTextField txt_edit_training_title;
    @FXML
    private JFXComboBox cbox_edit_trainor;
    @FXML
    private JFXComboBox cbox_edit_type;
    @FXML
    private JFXComboBox cbox_edit_v;
    @FXML
    private JFXTextField txt_edit_budget;
    @FXML
    private JFXComboBox cbox_edit_title;
    @FXML
    private JFXButton btn_view_participants;
    @FXML
    private JFXComboBox cbox_edit_dept;
    @FXML
    private JFXTextField txt_date_requested;
    @FXML
    private JFXDatePicker txt_from_day;
    @FXML
    private JFXDatePicker txt_to_day;
    @FXML
    private JFXButton btn_submit;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextField txt_hrs;
    @FXML
    private JFXTextArea txt_reason;
    @FXML
    private JFXTextField txt_req_by;
    @FXML
    private JFXTimePicker txt_from_time;
    @FXML
    private JFXTimePicker txt_to_time;
    @FXML
    private JFXComboBox cbox_edit_venue;
    @FXML
    private JFXTextField txt_participants;
    @FXML
    private JFXComboBox cbox_edit_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbox_edit_title.getItems().add(HR2_TM_ViewTrainingInfo_Modal.job_position);
        cbox_edit_title.getSelectionModel().selectFirst();        
        txt_from_day.setValue(LocalDate.parse(HR2_TM_ViewTrainingInfo_Modal.from_day));
        txt_to_day.setValue(LocalDate.parse(HR2_TM_ViewTrainingInfo_Modal.to_day));
        txt_participants.setText(HR2_TM_ViewTrainingInfo_Modal.participants);
        txt_hrs.setText(HR2_TM_ViewTrainingInfo_Modal.total_hours);
        cbox_edit_status.getItems().add("S00" + HR2_TM_ViewTrainingInfo_Modal.status_id + " - " + HR2_TM_ViewTrainingInfo_Modal.status);
        cbox_edit_status.getSelectionModel().selectFirst();  
        loadDataInComboBoxes();
        // loadData();
    }

    @FXML
    public void viewParticipants() {
        TM_ViewParticipantsClass.getJobPosition(cbox_edit_title.getSelectionModel().getSelectedItem().toString());
        Modal viewParticipants = Modal.getInstance(new Form("/FXMLS/HR2/Modals/TM_ViewParticipants.fxml").getParent());
        viewParticipants.open();
    }

    public void loadDataInComboBoxes() {

        HR2_Temp_Employee_Profiles trainors = new HR2_Temp_Employee_Profiles();
        HR2_Type_of_Training type_of_training = new HR2_Type_of_Training();
        HR2_Temp_Vehicles vehicles = new HR2_Temp_Vehicles();

        List set_trainors = trainors.get();

        for (Object e : set_trainors) {
            HashMap hm2 = (HashMap) e;
            //RS
            cbox_edit_trainor.getItems().add("T" + hm2.get("id") + " - " + hm2.get("firstname") + " " + hm2.get("middlename") + " " + hm2.get("lastname"));
        }

        List set_type_of_training = type_of_training.get();

        for (Object f : set_type_of_training) {
            HashMap hm3 = (HashMap) f;
            //RS
            cbox_edit_type.getItems().add("TM" + hm3.get("type_of_training_id") + " - " + hm3.get("type_of_training"));
        }

        List set_vehicles = vehicles.get();

        for (Object g : set_vehicles) {
            HashMap hm4 = (HashMap) g;
            //RS
            cbox_edit_v.getItems().add("V" + hm4.get("VehicleID") + " - " + hm4.get("VehicleModel"));
        }
    }

    /*   public void loadData() {

        HR2_Training_Info tm = new HR2_Training_Info();

        List training_data = tm.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "id", "employees", "=", "id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_type_of_training ", "type_of_training_id", "t_type", "=", "type_of_training_id")
                 .join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetVehicles ", "vehicle_id", "v", "=", "VehicleID")
                .where(new Object[][]{{"aerolink.tbl_hr2_training_info.job_position", "=", cbox_edit_title.getValue().toString()}})
                .get("training_title", "training_description", "CONCAT('T', employees.id , ' - ',employees.firstname, ' ' ,employees.middlename, ' ',"
                        + "employees.lastname)as trainor", "start_time", "end_time", "CONCAT('TM',t_type.type_of_training_id,' - ',t_type.type_of_training) as type_of_training",
                        "location","CONCAT('V',v.VehicleID,' - ',v.VehicleModel) as vehicle", "budget_cost");
        Data(training_data);

    }

    public void Data(List b) {
        b.stream().forEach(row -> {
            txt_edit_training_title.setText(((HashMap) row).get("training_title").toString());
            txt_edit_desc.setText(((HashMap) row).get("training_description").toString());
            cbox_edit_trainor.setValue(((HashMap) row).get("trainor").toString());
            txt_edit_st.setText(((HashMap) row).get("start_time").toString());
            txt_edit_et.setText(((HashMap) row).get("end_time").toString());
            cbox_edit_type.setValue(((HashMap) row).get("type_of_training").toString());
            txt_edit_loc.setText(((HashMap) row).get("location").toString());
            cbox_edit_v.setValue(((HashMap) row).get("vehicle").toString());
            txt_edit_budget.setText(((HashMap) row).get("budget_cost").toString());
        });
    }*/
    @FXML
    public void UpdateData() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();
        /*
        if (rs.get() == ButtonType.OK) {
            HR2_Training_Info tm = new HR2_Training_Info();

            Boolean a = tm.where(new Object[][]{
                {"job_position", "=", cbox_edit_title.getValue().toString()}
            }).update(new Object[][]{
                {"training_title", txt_edit_training_title.getText()},
                {"id", cbox_edit_trainor.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]},
                {"start_date", txt_edit_sd.getValue().toString()},
                {"end_date", txt_edit_ed.getValue().toString()},
                {"start_time", txt_edit_st.getText()},
                {"end_time", txt_edit_et.getText()},
                {"type_of_training_id", cbox_edit_type.getSelectionModel().getSelectedItem().toString().substring(2).toString().split(" - ")[0]},
                {"location", txt_edit_loc.getText()},
                {"vehicle_id", cbox_edit_v.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]},
                {"budget_cost", txt_edit_budget.getText()}
            }).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText(cbox_edit_title.getSelectionModel().getSelectedItem().toString() + " Successfully Updated");
            dropnotif.showAndWait();
            //loadData();
        }*/
    }
}
