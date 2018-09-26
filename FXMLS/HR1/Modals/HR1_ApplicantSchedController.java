/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_Applicant;
import FXMLS.HR1.ClassFiles.TableModel_Schedules;
import Model.HR1.HR1_Applicants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_ApplicantSchedController implements Initializable {

    HR1_Applicants applicant_schedules = new HR1_Applicants("app_schedule");
    public static Boolean modalOpen = true;
    public long DummyCount = 0;
    public long GlobalCount = 0;
    public int lastrow = 0;

    @FXML
    private StackPane spane;
    @FXML
    private MenuItem menuCalendar;
    @FXML
    private TitledPane titlePane;
    @FXML
    private TableView<TableModel_Schedules> tblScheds;
    @FXML
    private TextField txtPurpose;
    @FXML
    private DatePicker txtDate;
    @FXML
    private AnchorPane acStatus;
    @FXML
    private JFXButton btnYes;
    @FXML
    private JFXButton btnNo;
    @FXML
    private TextArea txtRemarks;
    @FXML
    private Pane txtPaneStatus;
    @FXML
    private Label lblPaneStatus;
    @FXML
    private MenuItem menuNewSched;
    @FXML
    private JFXButton btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modalOpen = true;
        // TODO
        titlePane.setText("Schedule List | Applicant " + HR1_Applicant.fullname);
        this.generateTable();
        this.renderSchedules();

        tblScheds.setOnKeyReleased(value -> {
            selectingRow();
        });

        tblScheds.setOnMouseClicked(value -> {
            selectingRow();
        });
        
        tblScheds.itemsProperty().addListener(listener -> {
            tblScheds.getSelectionModel().select(lastrow);
            selectingRow();
        });

        menuNewSched.setOnAction(value -> NewSched());

        btnNo.setOnMouseClicked(value -> {
            ConfirmationNO();
        });
        btnYes.setOnMouseClicked(value -> {
            Confirmation();
        });
        btnCancel.setOnMouseClicked(value -> {
            Cancellation();
        });

    }

    public void selectingRow() {
        lastrow = tblScheds.getSelectionModel().getSelectedIndex();
        txtDate.setValue(LocalDate.parse(tblScheds.getSelectionModel().getSelectedItem().scheduled_date.getValue()));
        txtPurpose.setText(tblScheds.getSelectionModel().getSelectedItem().scheduled_purpose.getValue());
        txtRemarks.setText(tblScheds.getSelectionModel().getSelectedItem().remarks.getValue());

        switch (tblScheds.getSelectionModel().getSelectedItem().status.getValue()) {
            case "0":
                if (txtDate.getValue().equals(LocalDate.now()) || LocalDate.now().isAfter(txtDate.getValue())) {
                    acStatus.setVisible(true);
                    btnCancel.setVisible(false);
                    txtPaneStatus.getStyleClass().removeAll(txtPaneStatus.getStyleClass());
                    txtPaneStatus.getStyleClass().add("paneStatus-late");
                    lblPaneStatus.setText("No Confirmation");
                } else {
                    acStatus.setVisible(false);
                    btnCancel.setVisible(true);
                    txtPaneStatus.getStyleClass().removeAll(txtPaneStatus.getStyleClass());
                    txtPaneStatus.getStyleClass().add("paneStatus-upcoming");
                    lblPaneStatus.setText("Upcoming Schedule");
                }
                break;
            case "1":
                acStatus.setVisible(false);
                btnCancel.setVisible(false);
                txtPaneStatus.getStyleClass().removeAll(txtPaneStatus.getStyleClass());
                txtPaneStatus.getStyleClass().add("paneStatus-confirmed");
                lblPaneStatus.setText("Applicant Arrived");
                break;
            case "2":
                acStatus.setVisible(false);
                btnCancel.setVisible(false);
                txtPaneStatus.getStyleClass().removeAll(txtPaneStatus.getStyleClass());
                txtPaneStatus.getStyleClass().add("paneStatus-late");
                lblPaneStatus.setText("Applicant didn't come");
                break;
            case "3":
                acStatus.setVisible(false);
                btnCancel.setVisible(false);
                txtPaneStatus.getStyleClass().removeAll(txtPaneStatus.getStyleClass());
                txtPaneStatus.getStyleClass().add("paneStatus-late");
                lblPaneStatus.setText("Cancelled Schedule");
                break;
            default:
                break;
        }
    }

    public void generateTable() {

        tblScheds.getItems().clear();
        tblScheds.getColumns().removeAll(tblScheds.getColumns());

        TableColumn<TableModel_Schedules, String> combination = new TableColumn<>("Schedules");

        combination.setCellValueFactory(value -> value.getValue().combination);
        tblScheds.getColumns().add(combination);

    }

    public void renderSchedules() {

        CompletableFuture.supplyAsync(() -> {
            while (modalOpen) {
                try {
                    applicant_schedules.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (GlobalCount != DummyCount) {
                        ObservableList<TableModel_Schedules> scheds = FXCollections.observableArrayList();

                        applicant_schedules.where(new Object[][]{
                            {"app_id", "=", HR1_Applicant.app_id}
                        }).get().stream().forEach(callback -> {
                            HashMap row = (HashMap) callback;

                            scheds.add(new TableModel_Schedules(
                                    row.get("schedule_id").toString(),
                                    row.get("purpose").toString(),
                                    row.get("scheduled_date").toString(),
                                    (row.get("scheduled_date").toString() + " - " + row.get("purpose").toString()),
                                    row.get("status").toString(),
                                    row.get("remarks").toString()
                            ));
                        });

                        tblScheds.getItems().clear();
                        tblScheds.setItems(scheds);

                        GlobalCount = DummyCount;
                    }

                } catch (Exception ex) {
                    System.err.println("Exception -> " + ex.getMessage());
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(HR1_ApplicantSchedController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        });

    }

    public void NewSched() {

        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setHeading(new Text("New Schedule"));

        DatePicker dt = new DatePicker();
        TextArea textArea = new TextArea();

        VBox vbox = new VBox(
                new Label(""),
                new Label("Set the Date: "),
                dt,
                new Label(""),
                new Label("Set the Purpose: "),
                textArea
        );

        JFXButton btnSubmitInit = new JFXButton("Submit");
        JFXButton btnCancel = new JFXButton("Cancel");

        btnSubmitInit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btnSubmitInit.setOnMouseClicked(event -> {
            if (!dt.getValue().isBefore(LocalDate.now())) {
                LocalDate f = dt.getValue();
                f.format(DateTimeFormatter.ISO_LOCAL_DATE);
                if (applicant_schedules.insert(new Object[][]{
                    {"app_id", HR1_Applicant.app_id},
                    {"scheduled_date", f.toString()},
                    {"purpose", textArea.getText()}
                })) {
                    Helpers.EIS_Response.SuccessResponse("Success", "New Schedule added to the applicant");
                    dialog.close();
                }
            } else {
                Helpers.EIS_Response.ErrorResponse("Halt!", "You could not select date before the current date \n" + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
            }

        });

        btnCancel.setOnMouseClicked(value -> {
            dialog.close();
        });
        layout.setBody(vbox);
        layout.setActions(btnSubmitInit, new JFXButton(""), btnCancel);

        dialog.show();
    }

    public void Confirmation() {
        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setHeading(new Text("Confirmation of the applicant arrival to the said date"));

        TextArea textArea = new TextArea();

        VBox vbox = new VBox(
                new Label(""),
                new Label(""),
                new Label("Remarks: "),
                textArea
        );

        JFXButton btnSubmitInit = new JFXButton("Submit");
        JFXButton btnCancel = new JFXButton("Cancel");

        btnSubmitInit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btnSubmitInit.setOnMouseClicked(event -> {
            if (applicant_schedules.update(new Object[][]{
                {"remarks", textArea.getText()},
                {"status", 1}
            }).where(new Object[][]{
                {"schedule_id", "=", tblScheds.getSelectionModel().getSelectedItem().scheduled_id.getValue()}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Applicant Schedule successfully updated");
                dialog.close();

            }
        });

        btnCancel.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setBody(vbox);
        layout.setActions(btnSubmitInit, new JFXButton(""), btnCancel);

        dialog.show();
    }

    public void ConfirmationNO() {
        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setHeading(new Text("Put some remarks: "));

        TextArea textArea = new TextArea();

        VBox vbox = new VBox(
                new Label(""),
                new Label(""),
                new Label("Remarks: "),
                textArea
        );

        JFXButton btnSubmitInit = new JFXButton("Submit");
        JFXButton btnCancel = new JFXButton("Cancel");

        btnSubmitInit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btnSubmitInit.setOnMouseClicked(event -> {
            if (applicant_schedules.update(new Object[][]{
                {"remarks", textArea.getText()},
                {"status", 2}
            }).where(new Object[][]{
                {"schedule_id", "=", tblScheds.getSelectionModel().getSelectedItem().scheduled_id.getValue()}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Applicant Schedule successfully updated");
                dialog.close();

            }
        });

        btnCancel.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setBody(vbox);
        layout.setActions(btnSubmitInit, new JFXButton(""), btnCancel);

        dialog.show();
    }

    public void Cancellation() {
        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setHeading(new Text("Why do you want to cancel? "));

        TextArea textArea = new TextArea();

        VBox vbox = new VBox(
                new Label(""),
                new Label(""),
                new Label("Remarks: "),
                textArea
        );

        JFXButton btnSubmitInit = new JFXButton("Submit");
        JFXButton btnCancel = new JFXButton("Cancel");

        btnSubmitInit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);

        btnSubmitInit.setOnMouseClicked(event -> {
            if (applicant_schedules.update(new Object[][]{
                {"remarks", textArea.getText()},
                {"status", 3}
            }).where(new Object[][]{
                {"schedule_id", "=", tblScheds.getSelectionModel().getSelectedItem().scheduled_id.getValue()}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Applicant Schedule successfully updated");
                dialog.close();

            }
        });

        btnCancel.setOnMouseClicked(value -> {
            dialog.close();
        });

        layout.setBody(vbox);
        layout.setActions(btnSubmitInit, new JFXButton(""), btnCancel);

        dialog.show();
    }

}
