/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_TM_Class_for_Modal;
import FXMLS.HR2.ClassFiles.HR2_Temp_VehicleClass;
import FXMLS.HR2.ClassFiles.HR2_Training_InfoClass;
import FXMLS.HR2.ClassFiles.HR2_Type_of_TrainingClass;
import FXMLS.HR2.ClassFiles.HR4_Temp_Employee_Profiles_Class;
import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import Model.HR2_CM_Pivot;
import Model.HR2_Temp_Employee_Profiles;
import Model.HR2_Temp_Vehicles;
import Model.HR2_Training_Info;
import Model.HR2_Type_of_Training;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Synapse.Model;
import java.sql.Date;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Training_ManagementController implements Initializable {

    @FXML
    private JFXComboBox cbox_select_jobs;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextArea txt_training_desc;
    @FXML
    private JFXComboBox cbox_trainor;
    @FXML
    private JFXDatePicker txt_start_date;
    @FXML
    private JFXDatePicker txt_end_date;
    @FXML
    private JFXComboBox cbox_select_type_of_training;
    @FXML
    private JFXTextField txt_location;
    @FXML
    private JFXComboBox cbox_vehicle;
    @FXML
    private JFXTextField txt_budget_cost;
    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXTextField txt_search_training;
    @FXML
    private TableView<HR2_Training_InfoClass> tbl_trainings;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_job_position;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_training_title;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_training_desc;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_trainor;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_start_date;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_end_date;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_start_time;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_end_time;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_type_of_training;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_location;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_vehicle;
    @FXML
    private TableColumn<HR2_Training_InfoClass, String> col_budget_cost;
    @FXML
    private ContextMenu contextMenuTrainings;
    @FXML
    private MenuItem contextmenu_item_view_details;
    @FXML
    private MenuItem contextmenu_item_delete_trainings;
    @FXML
    private JFXTextField txt_start_time;
    @FXML
    private JFXTextField txt_end_time;
    @FXML
    private ContextMenu contextMenuTrainings1;
    @FXML
    private MenuItem contextmenu_item_view_details1;
    @FXML
    private MenuItem contextmenu_item_delete_trainings1;
    @FXML
    private JFXTextField txt_search_training1;
    @FXML
    private TableView<?> tbl_history_of_trainings;
    @FXML
    private TableColumn<?, ?> col_job_position1;
    @FXML
    private TableColumn<?, ?> col_training_title1;
    @FXML
    private TableColumn<?, ?> col_training_desc1;
    @FXML
    private TableColumn<?, ?> col_trainor1;
    @FXML
    private TableColumn<?, ?> col_start_date1;
    @FXML
    private TableColumn<?, ?> col_end_date1;
    @FXML
    private TableColumn<?, ?> col_start_time1;
    @FXML
    private TableColumn<?, ?> col_end_time1;
    @FXML
    private TableColumn<?, ?> col_type_of_training1;
    @FXML
    private TableColumn<?, ?> col_location1;
    @FXML
    private TableColumn<?, ?> col_vehicle1;
    @FXML
    private TableColumn<?, ?> col_budget_cost1;

    //for comboboxes
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btn_save.setDisable(true);
        btn_save.setOnAction(e -> Save());
        btn_new.setOnAction(e -> {

            cbox_select_jobs.setDisable(false);
            txt_training_title.setDisable(false);
            txt_training_desc.setDisable(false);
            cbox_trainor.setDisable(false);
            txt_start_time.setDisable(false);
            txt_end_time.setDisable(false);
            txt_start_date.setDisable(false);
            txt_end_date.setDisable(false);
            txt_location.setDisable(false);
            cbox_vehicle.setDisable(false);
            txt_budget_cost.setDisable(false);
            cbox_select_type_of_training.setDisable(false);

            cbox_select_jobs.setValue(null);
            txt_training_title.setText("");
            txt_training_desc.setText("");
            cbox_trainor.setValue(null);
            txt_start_time.setText("");
            txt_end_time.setText("");
            txt_start_date.setValue(null);
            txt_end_date.setValue(null);
            txt_location.setText("");
            cbox_vehicle.setValue(null);
            txt_budget_cost.setText("");
            cbox_select_type_of_training.setValue(null);
            btn_save.setDisable(true);
        });;
        loadDataInComboBoxes();
        DisplayDataInTable();
        loadData();
        DisableComponents();
        txt_budget_cost.setOnKeyReleased(e -> validate());
    }

    public void DisplayDataInTable() {

        col_job_position.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().job_position);
        col_training_title.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().training_title);
        col_training_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().training_description);
        col_trainor.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().trainor);
        col_start_date.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().start_date);
        col_end_date.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().end_date);
        col_start_time.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().start_time);
        col_end_time.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().end_time);
        col_type_of_training.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().type_of_training);
        col_location.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().location);
        col_vehicle.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().vehicle);
        col_budget_cost.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Training_InfoClass, String> param) -> param.getValue().budget_cost);
        TableColumn<HR2_Training_InfoClass, Void> addButton = new TableColumn("View Participants");

        Callback<TableColumn<HR2_Training_InfoClass, Void>, TableCell<HR2_Training_InfoClass, Void>> cellFactory
                = new Callback<TableColumn<HR2_Training_InfoClass, Void>, TableCell<HR2_Training_InfoClass, Void>>() {
            @Override
            public TableCell<HR2_Training_InfoClass, Void> call(final TableColumn<HR2_Training_InfoClass, Void> param) {

                final TableCell<HR2_Training_InfoClass, Void> cell = new TableCell<HR2_Training_InfoClass, Void>() {
                    private final Button btn = new Button("View Participants");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                loadData();
                            });
                            btn.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }

        };

        addButton.setCellFactory(cellFactory);
        tbl_trainings.getColumns().add(addButton);
    }

    public void loadData() {

        HR2_Training_Info tm = new HR2_Training_Info();

        List training_data = tm.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "id", "employees", "=", "id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_type_of_training ", "type_of_training_id", "t_type", "=", "type_of_training_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_log2_vehicle_status ", "vehicle_id", "v", "=", "vehicle_id")
                .get("job_position", "training_title", "training_description", "CONCAT(employees.firstname, ' ' ,employees.middlename, ' ',\n"
                        + "employees.lastname)as trainor", "start_date", "end_date", "start_time", "end_time", "t_type.type_of_training",
                        "location", "v.vehicle", "budget_cost");
        Data(training_data);

    }

    public void Data(List b) {
        ObservableList<HR2_Training_InfoClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b) {
                HashMap hm = (HashMap) d;
                System.out.println(hm);
                obj.add(
                        new HR2_Training_InfoClass(
                                String.valueOf(hm.get("training_id")),
                                String.valueOf(hm.get("job_position")),
                                String.valueOf(hm.get("training_title")),
                                String.valueOf(hm.get("training_description")),
                                String.valueOf(hm.get("trainor")),
                                String.valueOf(hm.get("start_date")),
                                String.valueOf(hm.get("end_date")),
                                String.valueOf(hm.get("start_time")),
                                String.valueOf(hm.get("end_time")),
                                String.valueOf(hm.get("type_of_training")),
                                String.valueOf(hm.get("location")),
                                String.valueOf(hm.get("vehicle")),
                                String.valueOf(hm.get("budget_cost"))
                        ));

            }
            tbl_trainings.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void searchTraining() {
        HR2_Training_Info tm = new HR2_Training_Info();
        tbl_trainings.getItems().clear();
        try {
            List training_data1 = tm.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "id", "employees", "=", "id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_type_of_training ", "type_of_training_id", "t_type", "=", "type_of_training_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_log2_vehicle_status ", "vehicle_id", "v", "=", "vehicle_id")
                    .where(new Object[][]{{"job_position", "like", "%" + txt_search_training.getText() + "%"}})
                    .get("job_position", "training_title", "training_description", "CONCAT(employees.firstname, ' ' ,employees.middlename, ' ',\n"
                            + "employees.lastname)as trainor", "start_date", "end_date", "start_time", "end_time", "t_type.type_of_training",
                            "location", "v.vehicle", "budget_cost");
            Data(training_data1);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadDataInComboBoxes() {
        HR4_Jobs jobs = new HR4_Jobs();
        HR2_Temp_Employee_Profiles trainors = new HR2_Temp_Employee_Profiles();
        HR2_Type_of_Training type_of_training = new HR2_Type_of_Training();
        HR2_Temp_Vehicles vehicles = new HR2_Temp_Vehicles();

        List c = jobs.get();

        for (Object d : c) {
            HashMap hm1 = (HashMap) d;

            cbox_select_jobs.getItems().add(hm1.get("title"));
        }

        List set_trainors = trainors.get();

        for (Object e : set_trainors) {
            HashMap hm2 = (HashMap) e;
            //RS
            cbox_trainor.getItems().add("T" + hm2.get("id") + " - " + hm2.get("firstname") + " " + hm2.get("middlename") + " " + hm2.get("lastname"));
        }

        List set_type_of_training = type_of_training.get();

        for (Object f : set_type_of_training) {
            HashMap hm3 = (HashMap) f;
            //RS
            cbox_select_type_of_training.getItems().add("TM" + hm3.get("type_of_training_id") + " - " + hm3.get("type_of_training"));
        }

        List set_vehicles = vehicles.get();

        for (Object g : set_vehicles) {
            HashMap hm4 = (HashMap) g;
            //RS
            cbox_vehicle.getItems().add("V" + hm4.get("vehicle_id") + " - " + hm4.get("vehicle"));
        }
    }

    public void DisableComponents() {

        Node[] d = {
            btn_save,
            cbox_select_jobs,
            txt_training_title,
            txt_training_desc,
            cbox_trainor,
            txt_start_time,
            txt_end_time,
            txt_start_date,
            txt_end_date,
            txt_location,
            cbox_vehicle,
            txt_budget_cost,
            cbox_select_type_of_training,};
        try {
            for (Node c : d) {
                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(true);
                    m.setText("");
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(true);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(true);
                    m2.setValue(null);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(true);
                    m3.setValue(null);
                }
                if (c instanceof JFXTextArea) {
                    JFXTextArea m4 = (JFXTextArea) c;
                    m4.setDisable(true);
                    m4.setText("");
                }

            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Warning" + e);
            alert.showAndWait();
        }
    }

    public void validate() {
        if (!cbox_select_jobs.getValue().toString().isEmpty() && !txt_training_title.getText().isEmpty()
                && !txt_training_desc.getText().isEmpty() && !cbox_trainor.getValue().toString().isEmpty()
                && !txt_start_date.getValue().toString().isEmpty() && !txt_end_date.getValue().toString().isEmpty()
                && !txt_start_time.getText().isEmpty() && !txt_end_time.getText().isEmpty()
                && !cbox_select_type_of_training.getValue().toString().isEmpty() && !txt_location.getText().isEmpty()
                && !cbox_vehicle.getValue().toString().isEmpty() && !txt_budget_cost.getText().isEmpty()) {
            btn_save.setDisable(false);
        } else {
            btn_save.setDisable(true);
        }
    }

    public void Save() {
        if (cbox_select_jobs.getValue().toString().isEmpty() || txt_training_title.getText().isEmpty()
                || txt_training_desc.getText().isEmpty() || cbox_trainor.getValue().toString().isEmpty()
                || txt_start_date.getValue().toString().isEmpty() || txt_end_date.getValue().toString().isEmpty()
                || txt_start_time.getText().isEmpty()
                || txt_end_time.getText().isEmpty()
                || cbox_select_type_of_training.getValue().toString().isEmpty() || txt_location.getText().isEmpty()
                || cbox_vehicle.getValue().toString().isEmpty() || txt_budget_cost.getText().toString().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("One or more fields are empty");
            alert.showAndWait();
        } else {
            try {
                HR2_Training_Info tm = new HR2_Training_Info();

                String[][] tm_data
                        = {
                            {"job_position", cbox_select_jobs.getValue().toString()},
                            {"training_title", txt_training_title.getText()},
                            {"training_description", txt_training_desc.getText()},
                            {"id", cbox_trainor.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]},
                            {"start_date", txt_start_date.getValue().toString()},
                            {"end_date", txt_end_date.getValue().toString()},
                            {"start_time", txt_start_time.getText()},
                            {"end_time", txt_end_time.getText()},
                            {"type_of_training_id", cbox_select_type_of_training.getSelectionModel().getSelectedItem().toString().substring(2).toString().split(" - ")[0]},
                            {"location", txt_location.getText()},
                            {"vehicle_id", cbox_vehicle.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]},
                            {"budget_cost", txt_budget_cost.getText()}
                        };

                tm.insert(tm_data);
                loadData();
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
                DisableComponents();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Error" + e);
                alert.showAndWait();
            }
        }
    }
}
