/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_Competency_ManagementClass;
import Model.HR2_Competency_Management;
import Model.HR2_Training_Management;
import Synapse.Database;
import Synapse.DB.MySql;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;
import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import FXMLS.USM.Controllers.IUsers;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Training_ManagementController implements Initializable {

    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXTextField txt_training_id;
    @FXML
    private JFXTextField txt_job_position;
    @FXML
    private JFXTextField txt_training_title;
    @FXML
    private JFXTextField txt_training_description;
    @FXML
    private JFXTextField txt_trainor;
    @FXML
    private JFXTextField txt_start_time;
    @FXML
    private JFXTextField txt_end_time;
    @FXML
    private JFXDatePicker txt_start_date;
    @FXML
    private JFXDatePicker txt_end_date;
    @FXML
    private JFXTextField txt_location;
    @FXML
    private JFXTextField txt_vehicle;
    @FXML
    private JFXTextField txt_budget_cost;
    @FXML
    private JFXComboBox<String> txt_type_of_training;
    @FXML
    private JFXTextField txt_search_trainings;
    @FXML
    private TableView<Training_ManagementClass> training_management_data;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_training_ID;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_job_position;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_training_title;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_training_description;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_trainor;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_start_date;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_end_date;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_start_time;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_end_time;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_type_of_training;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_location;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_vehicle;
    @FXML
    private TableColumn<Training_ManagementClass, String> col_budget_cost;

    ObservableList<String> sdl = FXCollections.observableArrayList("Internal", "External");
    @FXML
    private TableColumn<Training_ManagementClass, Boolean> col_edit;
    @FXML
    private TableColumn<Training_ManagementClass, Boolean> col_delete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txt_type_of_training.setItems(sdl);
        txt_type_of_training.setPromptText("Choose Type of Training");

        btn_new.setOnMouseClicked(e -> New());
        btn_save.setOnMouseClicked(e -> Save());
        DisableComponents();

        DisplayData();

        loadData();

        //   btn_save.setOnAction(e -> Save());
    }

    private void DisplayData() {
        col_training_ID.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().training_id);
        col_job_position.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().job_position);
        col_training_title.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().training_title);
        col_training_description.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().training_description);
        col_trainor.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().trainor);
        col_start_date.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().start_date);
        col_end_date.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().end_date);
        col_start_time.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().start_time);
        col_end_time.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().end_time);
        col_type_of_training.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().type_of_training);
        col_location.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().location);
        col_vehicle.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().vehicle);
        col_budget_cost.setCellValueFactory((TableColumn.CellDataFeatures<Training_ManagementClass, String> param) -> param.getValue().budget_cost);
        col_edit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Training_ManagementClass, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Training_ManagementClass, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });

        col_edit.setCellFactory(new Callback<TableColumn<Training_ManagementClass, Boolean>, TableCell<Training_ManagementClass, Boolean>>() {
            @Override
            public TableCell<Training_ManagementClass, Boolean> call(TableColumn<Training_ManagementClass, Boolean> param) {
                return new Synapse.Components.HR2_Edit_Training<Training_ManagementClass>().create("Edit", FXMLS.HR2.Handlers.HR2_Edit_Training_Handler.triggerPermissionsModal);
            }
        });

        col_delete.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Training_ManagementClass, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Training_ManagementClass, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });

        col_delete.setCellFactory(new Callback<TableColumn<Training_ManagementClass, Boolean>, TableCell<Training_ManagementClass, Boolean>>() {
            @Override
            public TableCell<Training_ManagementClass, Boolean> call(TableColumn<Training_ManagementClass, Boolean> param) {
                return new Synapse.Components.HR2_Delete_Training<Training_ManagementClass>().create("Delete", FXMLS.HR2.Handlers.HR2_Delete_Training_Handler.triggerPermissionsModal);
            }
        });

    }

    private void loadData() {

        HR2_Training_Management tm = new HR2_Training_Management();

        ObservableList<Training_ManagementClass> dv = FXCollections.observableArrayList();
        List b = tm.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("training_id");
            hm.get("job_position");
            hm.get("training_title");
            hm.get("training_description");
            hm.get("trainor");
            hm.get("start_date");
            hm.get("end_date");
            hm.get("start_time");
            hm.get("end_time");
            hm.get("type_of_training");
            hm.get("location");
            hm.get("vehicle");
            hm.get("budget_cost");

            dv.add(
                    new Training_ManagementClass(
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
        training_management_data.setItems(dv);

    }

    public void DisableComponents() {

        Node[] d = {
            btn_save,
            txt_training_id,
            txt_job_position,
            txt_training_title,
            txt_training_description,
            txt_trainor,
            txt_start_time,
            txt_end_time,
            txt_start_date,
            txt_end_date,
            txt_location,
            txt_vehicle,
            txt_budget_cost,
            txt_type_of_training

        };
        try {
            for (Node c : d) {
                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    /*  validations v = new validations();
                                                         v.maximumChars(m, 25);
                                                         Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                         alert.initStyle(StageStyle.UNDECORATED);
                                                                         alert.setTitle("Error");
                                                                         alert.setContentText("Maximum 25 Character only"); 
                                                                         alert.showAndWait();      */
                    m.setDisable(true);
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(true);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(true);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(true);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void New() {

        Node[] d = {
            btn_save,
            txt_job_position,
            txt_training_title,
            txt_training_description,
            txt_trainor,
            txt_start_time,
            txt_end_time,
            txt_start_date,
            txt_end_date,
            txt_location,
            txt_vehicle,
            txt_budget_cost,
            txt_type_of_training

        };

        try {
            for (Node c : d) {

                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(false);
                    m.setText("");
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(false);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(false);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(false);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Save() {
        HR2_Training_Management tm = new HR2_Training_Management();

        try {

            String[][] tm_data
                    = {
                        {"job_position", txt_job_position.getText()},
                        {"training_title", txt_training_title.getText()},
                        {"training_description", txt_training_description.getText()},
                        {"trainor", txt_trainor.getText()},
                        {"start_date", txt_start_date.getValue().toString()},
                        {"end_date", txt_end_date.getValue().toString()},
                        {"start_time", txt_start_time.getText()},
                        {"end_time", txt_end_time.getText()},
                        {"type_of_training", txt_type_of_training.getSelectionModel().toString()},
                        {"location", txt_location.getText()},
                        {"vehicle", txt_vehicle.getText()},
                        {"budget_cost", txt_budget_cost.getText()}
                    };

            tm.insert(tm_data);
            loadData();
            DisableComponents();
            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
