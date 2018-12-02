/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_GenerateEC;
import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import FXMLS.HR1.ClassFiles.TableModel_NHTaskList;
import Model.HR1.HR1_NewHire_Task;
import Model.HR4.HR4_Employee;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewNewHireController implements Initializable {

    HR4_Employee profile = new HR4_Employee("profile");
    HR4_Employee emp = new HR4_Employee();

    @FXML
    private StackPane spane;
    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private Label lblAppFull;
    @FXML
    private MenuItem menuHiring;
    @FXML
    private MenuItem menuDeny;
    @FXML
    private TitledPane jobTitle;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtCivilStatus;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private JFXButton btn0;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn6;
    @FXML
    private JFXButton btn5;
    @FXML
    private JFXButton btn4;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;
    @FXML
    private TableView<TableModel_NHTaskList> tblTaskList;
    @FXML
    private JFXButton btn61;
    @FXML
    private TextField txtTaskName;
    @FXML
    private TextArea txtTaskDesc;
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private JFXButton SubmitTask;
    @FXML
    private TextField txtTaskName1;
    @FXML
    private TextArea txtTaskDesc1;
    @FXML
    private DatePicker txtStartDate1;
    @FXML
    private DatePicker txtEndDate1;
    @FXML
    private JFXButton updateTaskYes;
    @FXML
    private Label txtStatus;
    @FXML
    private AnchorPane paneUpdate;
    @FXML
    private AnchorPane paneButtons;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(HR1_NewHireClass.jobtitle);

        this.jobTitle.setText("Job :" + HR1_NewHireClass.jobtitle);
        this.txtDate.setText(HR1_NewHireClass.DateOfBirth);
        this.txtPlace.setText(HR1_NewHireClass.PlaceOfBirth);
        this.txtGender.setText(HR1_NewHireClass.Gender);
        this.txtCivilStatus.setText(HR1_NewHireClass.CivilStatus);
        this.txtEmail.setText(HR1_NewHireClass.Email);
        this.txtHeight.setText(HR1_NewHireClass.Height);
        this.txtWeight.setText(HR1_NewHireClass.Weight);
        this.txtContactNumber.setText(HR1_NewHireClass.ContactNumber);
        this.lblAppFull.setText(HR1_NewHireClass.name);

        this.generateTable();
        this.renderTable();

        tblTaskList.setOnMouseClicked(value -> {
            paneUpdate.setDisable(false);
            txtStatus.setText("Update Task: [" + tblTaskList.getSelectionModel().getSelectedItem().status.getValue() + "]");
            txtStatus.getStyleClass().removeAll();

            if (tblTaskList.getSelectionModel().getSelectedItem().status.getValue().equals("Pending")) {
                txtStatus.getStyleClass().add("paneStatus-late");
                paneButtons.setVisible(true);
            } else {
                txtStatus.getStyleClass().add("paneStatus-confirmed");
                paneButtons.setVisible(false);
            }

            txtTaskName1.setText(tblTaskList.getSelectionModel().getSelectedItem().taskname.getValue());
            txtTaskDesc1.setText(tblTaskList.getSelectionModel().getSelectedItem().taskdesc.getValue());
            txtStartDate1.setValue(LocalDate.parse(tblTaskList.getSelectionModel().getSelectedItem().startdate.getValue()));
            txtEndDate1.setValue(LocalDate.parse(tblTaskList.getSelectionModel().getSelectedItem().enddate.getValue()));

        });

        updateTaskYes.setOnAction(value -> {
            
            tasks.update(new Object[][]{
                {"status", 1}
            }).where(new Object[][]{
                {"id", "=", tblTaskList.getSelectionModel().getSelectedItem().task_id.getValue()}
            }).executeUpdate();
            
            Helpers.EIS_Response.SuccessResponse("Success", "Task was successfully Completed");
        });
    }

    public void generateTable() {
        tblTaskList.getColumns().removeAll(tblTaskList.getColumns());

        TableColumn<TableModel_NHTaskList, String> taskname = new TableColumn<>("Task Name");
        TableColumn<TableModel_NHTaskList, String> taskstart = new TableColumn<>("Task Start");
        TableColumn<TableModel_NHTaskList, String> taskend = new TableColumn<>("Task End");
        TableColumn<TableModel_NHTaskList, String> taskstatus = new TableColumn<>("Task Status");

        taskname.setCellValueFactory(value -> value.getValue().taskname);
        taskstart.setCellValueFactory(value -> value.getValue().startdate);
        taskend.setCellValueFactory(value -> value.getValue().enddate);
        taskstatus.setCellValueFactory(value -> value.getValue().status);

        tblTaskList.getColumns().addAll(taskname, taskstart, taskend, taskstatus);
    }

    HR1_NewHire_Task tasks = new HR1_NewHire_Task();

    public void renderTable() {

        ObservableList ob = FXCollections.observableArrayList();

        tasks.where(new Object[][]{
            {"employee_code", "=", HR1_NewHireClass.employee_code}
        }).get().stream().forEach(action -> {
            HashMap row = (HashMap) action;

            ob.add(new TableModel_NHTaskList(
                    row.get("taskname").toString(),
                    row.get("taskdesc").toString(),
                    row.get("id").toString(),
                    row.get("end_date").toString(),
                    row.get("start_date").toString(),
                    row.get("status").toString().equals("0") ? "Pending" : "Completed"));

        });

        tblTaskList.getItems().clear();
        tblTaskList.setItems(ob);
    }

    public JFXDialog viewDialog(String title, VBox Content, Node[] buttons) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(title));
        layout.setBody(Content);

        layout.setActions(buttons);

        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);
        return dialog;
    }

    @FXML
    private void viewFamilyBackGround(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewFB.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewTrainingSem(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewTSM.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewAcademicAwards(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewAW.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewCertification(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewCE.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewWorkExp(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewWE.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewEducAttain(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewEducAttain.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewGovIDs(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewGovID.fxml").getParent());
        md.open();
    }

    @FXML
    private void intoRegular(ActionEvent event) {

        DatePicker dtp = new DatePicker(LocalDate.now());
        TextField txt = new TextField("Address");
        TextField txtSalary = new TextField("0");

        VBox vbox = new VBox(
                new Label("Set the Effective Date of regular employment"),
                dtp,
                new Label(""),
                new Label("Set the employee address "),
                txt,
                new Label(""),
                new Label("Set the employee Salary "),
                txtSalary
        );

        JFXButton send = new JFXButton("Submit");

        send.getStyleClass().add("btn-primary");

        JFXButton cancel = new JFXButton("Cancel");

        cancel.getStyleClass().add("btn-danger");

        Node[] b = {send, new JFXButton(""), cancel};

        JFXDialog d = viewDialog("Transitioning to Regular Employee", vbox, b);

        txtSalary.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });

        txtSalary.setOnKeyReleased(value -> {
            if (txtSalary.getText().isEmpty()) {
                txtSalary.setText("0");
            } else {

                txtSalary.setText(NumberFormat.getInstance().format(Double.parseDouble(txtSalary.getText().replace(",", ""))));
                txtSalary.end();
            }
        });

        send.setOnAction(value -> {

            emp.update(new Object[][]{
                {"type_id", 1},
                {"salary", Double.parseDouble(txtSalary.getText().replace(",", ""))}
            }).where(new Object[][]{
                {"employee_code", "=", HR1_NewHireClass.employee_code}
            }).executeUpdate();

            String a = HR1_GenerateEC.generateRegEC(HR1_NewHireClass.name, HR1_NewHireClass.jobtitle, dtp.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)), txtSalary.getText(), txt.getText(), HR1_NewHireClass.employee_code);

            profile.update(new Object[][]{
                {"address", txt.getText()},
                {"employement_contract", a}
            }).where(new Object[][]{
                {"employee_code", "=", HR1_NewHireClass.employee_code}
            }).executeUpdate();

            Helpers.EIS_Response.SuccessResponse("Success", "Successfully Transitioned to regular employee and generate an employment contract " + a);
            d.close();

        });

        cancel.setOnAction(value -> {
            d.close();
        });

        d.show();

    }

    @FXML
    private void btnDownloadCV(ActionEvent event) {
    }

    @FXML
    private void SubmitNewTask(ActionEvent event) {
        tasks.insert(new Object[][]{
            {"employee_code", HR1_NewHireClass.employee_code},
            {"taskname", txtTaskName.getText()},
            {"taskdesc", txtTaskDesc.getText()},
            {"start_date", txtStartDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)},
            {"end_date", txtEndDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)}
        });

        Helpers.EIS_Response.SuccessResponse("Success", "New task was added to the employee");
        this.renderTable();
    }
}
