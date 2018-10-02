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
import Synapse.DB.MYSQL;
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
import FXMLS.HR2.Modals.HR2_Edit_TrainingController;
import FXMLS.HR4.ClassFiles.HR4_MIZ;
import FXMLS.USM.Controllers.IUsers;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.Cursor;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

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
        AddButtonToTraining_ManagementTable();
        DeleteButton();
    }

    private void loadData() {

        HR2_Training_Management tm = new HR2_Training_Management();

        ObservableList<Training_ManagementClass> dv = FXCollections.observableArrayList();
        List b = tm.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("training_id");
            hm.get("default_id");
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
                            String.valueOf(hm.get("default_id") + "" + hm.get("training_id")),
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
                    m2.setValue(null);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(false);
                    m3.setValue(null);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void SearchTrainings() {

        HR2_Training_Management tm = new HR2_Training_Management();

        try {

            HR2_Training_Management tm1 = tm;
            List listTrainings;
            if (txt_search_trainings.equals("")) {
                listTrainings = tm1.get();
            } else {
                listTrainings = tm1.where(new Object[][]{
                    {"job_position", "like", "%" + txt_search_trainings.getText() + "%"}
                }).get();

                //  {"job_id", "like", " (SELECT id from tbl_jobs WHERE id = 1)"}
                training_management_data.getItems().clear();

                ObservableList<Training_ManagementClass> trainings = FXCollections.observableArrayList();

                for (Object d : listTrainings) {
                    HashMap hm1 = (HashMap) d;
                    //RS
                    trainings.add(
                            new Training_ManagementClass(
                                    String.valueOf(hm1.get("default_id") + "" + hm1.get("training_id")),
                                    String.valueOf(hm1.get("job_position")),
                                    String.valueOf(hm1.get("training_title")),
                                    String.valueOf(hm1.get("training_description")),
                                    String.valueOf(hm1.get("trainor")),
                                    String.valueOf(hm1.get("start_date")),
                                    String.valueOf(hm1.get("end_date")),
                                    String.valueOf(hm1.get("start_time")),
                                    String.valueOf(hm1.get("end_time")),
                                    String.valueOf(hm1.get("type_of_training")),
                                    String.valueOf(hm1.get("location")),
                                    String.valueOf(hm1.get("vehicle")),
                                    String.valueOf(hm1.get("budget_cost"))
                            ));

                }
                training_management_data.setItems(trainings);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Save() {
        HR2_Training_Management tm = new HR2_Training_Management();

        if (txt_job_position.getText().isEmpty() || txt_training_title.getText().isEmpty()
                || txt_training_description.getText().isEmpty() || txt_trainor.getText().isEmpty()
                || txt_start_date.getValue().toString().isEmpty() || txt_end_date.getValue().toString().isEmpty()
                || txt_start_time.getText().isEmpty() || txt_end_time.getText().isEmpty()
                || txt_type_of_training.getValue().toString().isEmpty() || txt_location.getText().isEmpty()
                || txt_vehicle.getText().isEmpty() || txt_budget_cost.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One or More Fields are empty");
            alert.showAndWait();
        } else {
            try {

                String[][] tm_data
                        = {
                            {"default_id", "000"},
                            {"job_position", txt_job_position.getText()},
                            {"training_title", txt_training_title.getText()},
                            {"training_description", txt_training_description.getText()},
                            {"trainor", txt_trainor.getText()},
                            {"start_date", txt_start_date.getValue().toString()},
                            {"end_date", txt_end_date.getValue().toString()},
                            {"start_time", txt_start_time.getText()},
                            {"end_time", txt_end_time.getText()},
                            {"type_of_training", txt_type_of_training.getValue().toString()},
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

    public void AddButtonToTraining_ManagementTable() {
        TableColumn<Training_ManagementClass, Void> addButton = new TableColumn("Edit Action");

        Callback<TableColumn<Training_ManagementClass, Void>, TableCell<Training_ManagementClass, Void>> cellFactory
                = new Callback<TableColumn<Training_ManagementClass, Void>, TableCell<Training_ManagementClass, Void>>() {
            @Override
            public TableCell<Training_ManagementClass, Void> call(final TableColumn<Training_ManagementClass, Void> param) {

                final TableCell<Training_ManagementClass, Void> cell = new TableCell<Training_ManagementClass, Void>() {
                    private final Button btn = new Button("Edit");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                viewTraining();
                            });
                            btn.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            printStackTrace();
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
        training_management_data.getColumns().add(addButton);
    }

    public void DeleteButton() {

        // Delete Button
        TableColumn<Training_ManagementClass, Void> addButton1 = new TableColumn("Delete Action");

        Callback<TableColumn<Training_ManagementClass, Void>, TableCell<Training_ManagementClass, Void>> cellFactory1
                = new Callback<TableColumn<Training_ManagementClass, Void>, TableCell<Training_ManagementClass, Void>>() {
            @Override
            public TableCell<Training_ManagementClass, Void> call(final TableColumn<Training_ManagementClass, Void> param) {

                final TableCell<Training_ManagementClass, Void> cell = new TableCell<Training_ManagementClass, Void>() {
                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event)
                                -> {
                            // Training_ManagementClass data = getTableView().getItems().get(getIndex());
                            DeleteTraining();

                        });
                        btn.setCursor(javafx.scene.Cursor.HAND);
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

        addButton1.setCellFactory(cellFactory1);
        training_management_data.getColumns().add(addButton1);

    }

    public void viewTraining() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Update Information");

        // Set the button types.
        Training_ManagementClass tmc = training_management_data.getSelectionModel().getSelectedItem();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        JFXTextField trainingID = new JFXTextField(tmc.training_id.getValue());
        trainingID.setPromptText("Training Id");

        JFXTextField jobPosition = new JFXTextField(tmc.job_position.getValue());
        jobPosition.setPromptText("Job Position");
        JFXTextField trainingTitle = new JFXTextField(tmc.training_title.getValue());
        trainingTitle.setPromptText("Training Title");
        JFXTextArea trainingDescription = new JFXTextArea(tmc.training_description.getValue());
        trainingDescription.setPromptText("Training Description");
        trainingDescription.setStyle("-fx-text-fill: #0f0f0a; -fx-font-size: 16px; -fx-border-color: #999966");
        trainingDescription.setPrefHeight(100);
        JFXTextField trainor = new JFXTextField(tmc.trainor.getValue());
        trainor.setPromptText("Trainor");
        JFXDatePicker startDate = new JFXDatePicker(LocalDate.parse(tmc.start_date.getValue()));
        startDate.setPromptText("Start Date");
        JFXDatePicker endDate = new JFXDatePicker(LocalDate.parse(tmc.end_date.getValue()));
        endDate.setPromptText("End Date");
        JFXTextField startTime = new JFXTextField(tmc.start_time.getValue());
        startTime.setPromptText("Start Time");
        JFXTextField endTime = new JFXTextField(tmc.end_time.getValue());
        endTime.setPromptText("End Time");
        JFXComboBox type_ofTraining = new JFXComboBox();
        type_ofTraining.getItems().add(tmc.type_of_training.getValue());
        type_ofTraining.setPromptText("Type of Training");
        JFXTextField location = new JFXTextField(tmc.location.getValue());
        location.setPromptText("Location");
        JFXTextField vehicle = new JFXTextField(tmc.vehicle.getValue());
        vehicle.setPromptText("Vehicle");
        JFXTextField budgetCost = new JFXTextField(tmc.budget_cost.getValue());
        budgetCost.setPromptText("Budget Cost");
        JFXButton d = new JFXButton("Update");
        d.setOnAction(e
                -> {
            Alert update = new Alert(Alert.AlertType.CONFIRMATION);
            update.setContentText("Are you sure you want to update this data?");
            Optional<ButtonType> rs = update.showAndWait();

            if (rs.get() == ButtonType.OK) {
                //   System.out.println(tbl_Skills.getSelectionModel().getSelectedItem().Skill_ID.getValue());
                HR2_Training_Management tm = new HR2_Training_Management();

                Boolean a = tm.update(new Object[][]{
                    {"job_position", jobPosition.getText()},
                    {"training_title", trainingTitle.getText()},
                    {"training_description", trainingDescription.getText()},
                    {"trainor", trainor.getText()},
                    {"start_date", startDate.getValue()},
                    {"end_date", endDate.getValue()},
                    {"start_time", startTime.getText()},
                    {"end_time", endTime.getText()},
                    {"type_of_training", type_ofTraining.getValue()},
                    {"location", location.getText()},
                    {"vehicle", vehicle.getText()},
                    {"budget_cost", budgetCost.getText()}

                }).where(new Object[][]{
                    {"training_id", "=", trainingID.getText()}
                }).executeUpdate();

                System.out.println(a);

                loadData();
            }
        });
        d.setStyle("-fx-text-fill: #fff; -fx-background-color: #00cc66; -fx-font-size: 15px; -fx-font-family: Lato-Medium;  ");
        d.setCursor(javafx.scene.Cursor.HAND);
        grid.add(new Label("Training Code:"), 0, 0);
        grid.add(trainingID, 1, 0);
        grid.add(new Label("Job Position:"), 0, 0);
        grid.add(jobPosition, 1, 0);
        grid.add(new Label("Training Title:"), 0, 1);
        grid.add(trainingTitle, 1, 1);
        grid.add(new Label("Training Description:"), 0, 2);
        grid.add(trainingDescription, 1, 2);
        grid.add(new Label("Trainor:"), 0, 3);
        grid.add(trainor, 1, 3);
        grid.add(new Label("Start Date:"), 0, 4);
        grid.add(startDate, 1, 4);
        grid.add(new Label("End Date:"), 0, 5);
        grid.add(endDate, 1, 5);
        grid.add(new Label("Start Time:"), 0, 6);
        grid.add(startTime, 1, 6);
        grid.add(new Label("End Time:"), 0, 7);
        grid.add(endTime, 1, 7);
        grid.add(new Label("Type of Training:"), 0, 8);
        grid.add(type_ofTraining, 1, 8);
        grid.add(new Label("Location:"), 0, 9);
        grid.add(location, 1, 9);
        grid.add(new Label("Vehicle:"), 0, 10);
        grid.add(vehicle, 1, 10);
        grid.add(new Label("Budget Cost:"), 0, 11);
        grid.add(budgetCost, 1, 11);
        grid.add(d, 1, 12);
        trainingID.setVisible(true);
// Enable/Disable login button depending on whether a username was entered.

        // loginButton.setDisable(true);
// Do some validation (using the Java 8 lambda syntax).
        /*  username.textProperty().addListener((observable, oldValue, newValue) -> {
                                loginButton.setDisable(newValue.trim().isEmpty());
                            });*/
        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        //    Platform.runLater(() -> username.requestFocus());
// Convert the result to a username-password-pair when the login button is clicked.
        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });

    }

    public void DeleteTraining() {
        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
        delete.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> rs = delete.showAndWait();

        System.out.println(rs.get());
        if (rs.get() == ButtonType.OK) {
            System.out.println(training_management_data.getSelectionModel().getSelectedItem().training_id.getValue());
            HR2_Training_Management tm = new HR2_Training_Management();

            tm.delete().where(new Object[][]{
                {"training_id", "=", training_management_data.getSelectionModel().getSelectedItem().training_id.getValue()}
            }).executeUpdate();
            loadData();

        }

    }

}
