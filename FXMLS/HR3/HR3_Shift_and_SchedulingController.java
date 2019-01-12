/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.HR3.ClassFiles.HR3_CreatingScheduleClass;
import FXMLS.HR3.ClassFiles.Hr3_ShiftingClass;
import FXMLS.HR3.ClassFiles.Shift_RequestClass;
import Model.Shift_and_Scheduling;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import FXMLS.HR3.ClassFiles.new_emplo;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.List;
import FXMLS.HR3.Controllers.DBConnector;
import FXMLS.HR3.Table.CStable;
import Model.Dummy_Data;
import Model.HR3_CreatingSchedule_Model;
import Model.ShiftRequestModel;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import Synapse.Model;
import Synapse.Session;
import java.util.concurrent.CompletableFuture;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Shift_and_SchedulingController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private BorderPane hr3sns;
    @FXML
    private JFXTextField csid;
    @FXML
    private JFXTextField sdate;
    @FXML
    private JFXTextField csname;
    @FXML
    private JFXTextField csposition;
    @FXML
    private Button csbtnsave;
    @FXML
    private Button csbtncancel;
    @FXML
    private ComboBox<String> csmin;
    @FXML
    private ComboBox<String> cstin;
    @FXML
    private ComboBox<String> cswin;
    @FXML
    private ComboBox<String> csthin;
    @FXML
    private ComboBox<String> csfin;
    @FXML
    private ComboBox<String> csmout;
    @FXML
    private ComboBox<String> cstout;
    @FXML
    private ComboBox<String> cswout;
    @FXML
    private ComboBox<String> csthout;
    @FXML
    private ComboBox<String> csfout;
    @FXML
    private ComboBox<String> csmbt;
    @FXML
    private ComboBox<String> cstbt;
    @FXML
    private ComboBox<String> cswbt;
    @FXML
    private ComboBox<String> csthbt;
    @FXML
    private ComboBox<String> csfbt;
    @FXML
    private TableView<HR3_CreatingScheduleClass> CStable;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_id;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_name;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_position;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_monday;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_tuesday;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_wednesday;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_thursday;
    @FXML
    private TableColumn<HR3_CreatingScheduleClass, String> cs_friday;
    
    @FXML
    private JFXTextField sid;
    @FXML
    private JFXTextField sname;
    @FXML
    private JFXTextField smon;
    @FXML
    private JFXTextField stue;
    @FXML
    private JFXTextField swed;
    @FXML
    private JFXTextField sthu;
    @FXML
    private JFXTextField sfri;
    @FXML
    private ComboBox<String> scmbstatus;
    @FXML
    private Button sbtnsave;

    @FXML
    private JFXTextField n_id;

    @FXML
    private Button btn_n_update;
    @FXML
    private Button btn_n_delete;
    @FXML
    private JFXTextField n_monday;
    @FXML
    private JFXTextField n_tuesday;
    @FXML
    private JFXTextField n_wednesday;
    @FXML
    private JFXTextField n_thursday;
    @FXML
    private JFXTextField n_friday;
    @FXML
    private Button btn_n_cancel;
    private TableColumn<Hr3_ShiftingClass, String> tabstatus;
    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private TableColumn<Shift_RequestClass, String> R_ID;
    @FXML
    private TableColumn<Shift_RequestClass, String> Employee;
    @FXML
    private TableColumn<Shift_RequestClass, String> Date;
    @FXML
    private TableColumn<Shift_RequestClass, String> Monday;
    @FXML
    private TableColumn<Shift_RequestClass, String> Tuesday;
    @FXML
    private TableColumn<Shift_RequestClass, String> Wednesday;
    @FXML
    private TableColumn<Shift_RequestClass, String> Thursday;
    @FXML
    private TableColumn<Shift_RequestClass, String> Friday;
    @FXML
    private TableView<Shift_RequestClass> newtable;
    @FXML
    private TableView<new_emplo> tableCSnew;
    @FXML
    private TableColumn<new_emplo, String> tb_id;
    @FXML
    private TableColumn<new_emplo, String> tb_name;
    @FXML
    private TableColumn<new_emplo, String> tb_position;
    @FXML
    private Button xbtndecline;
    @FXML
    private Button xbtnapprove;
    @FXML
    private Button xbtnpending;

    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> status = FXCollections.observableArrayList("Approve", "Decline");
        scmbstatus.setItems(status);

        csbtnsave.setOnMouseClicked(e -> SaveData());
        csbtncancel.setOnMouseClicked(e -> ClearData());
        sbtnsave.setOnMouseClicked(e -> Save());
        btn_n_cancel.setOnMouseClicked(e -> cancel());
        btn_n_update.setOnMouseClicked(e -> updateCShedule());

        displaynewemployee();
        ClearData();
        DisplaySchedule();
        loadDataSchedule();
        UpdateTableCS();
        UpdateTableCSS();
        NewRequest();
        ClearDataShift();
        ComboBox();
        archieve();
        UpdateTable();
    }

    public void Search_Skills() {

        Dummy_Data hr2hmc = new Dummy_Data();

        String SearchText = csid.getText().equals("") ? "[a-z]" : csid.getText();

        try {

            List listSkills = hr2hmc.where(new Object[][]{
                {"ID", "like", "%" + SearchText + "%"}
            }).get();

        } catch (Exception e) {
            System.out.println(e);
        }

        //  {"job_id", "like", " (SELECT id from tbl_jobs WHERE id = 1)"}
    }

    private void ComboBox() {
        ObservableList<String> mondayin = FXCollections.observableArrayList("8:00 AM", "9:00 AM", "10:00 AM", "Day-Off");
        csmin.setItems(mondayin);
        ObservableList<String> mondayout = FXCollections.observableArrayList("5:00 PM", "6:00 PM", "7:00 PM");
        csmout.setItems(mondayout);
        ObservableList<String> mondaybt = FXCollections.observableArrayList("12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM");
        csmbt.setItems(mondaybt);
        ObservableList<String> tuesdayin = FXCollections.observableArrayList("8:00 AM", "9:00 AM", "10:00 AM", "Day-Off");
        cstin.setItems(tuesdayin);
        ObservableList<String> tuesdayout = FXCollections.observableArrayList("5:00 PM", "6:00 PM", "7:00 PM");
        cstout.setItems(tuesdayout);
        ObservableList<String> tuesdaybt = FXCollections.observableArrayList("12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM");
        cstbt.setItems(tuesdaybt);
        ObservableList<String> wedin = FXCollections.observableArrayList("8:00 AM", "9:00 AM", "10:00 AM", "Day-Off");
        cswin.setItems(wedin);
        ObservableList<String> wedout = FXCollections.observableArrayList("5:00 PM", "6:00 PM", "7:00 PM");
        cswout.setItems(wedout);
        ObservableList<String> wedbt = FXCollections.observableArrayList("12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM");
        cswbt.setItems(wedbt);
        ObservableList<String> thurin = FXCollections.observableArrayList("8:00 AM", "9:00 AM", "10:00 AM", "Day-Off");
        csthin.setItems(thurin);
        ObservableList<String> thurout = FXCollections.observableArrayList("5:00 PM", "6:00 PM", "7:00 PM");
        csthout.setItems(thurout);
        ObservableList<String> thurbt = FXCollections.observableArrayList("12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM");
        csthbt.setItems(thurbt);
        ObservableList<String> friin = FXCollections.observableArrayList("8:00 AM", "9:00 AM", "10:00 AM", "Day-Off");
        csfin.setItems(friin);
        ObservableList<String> friout = FXCollections.observableArrayList("5:00 PM", "6:00 PM", "7:00 PM");
        csfout.setItems(friout);
        ObservableList<String> fribt = FXCollections.observableArrayList("12:00 PM - 1:00 PM", "1:00 PM - 2:00 PM");
        csfbt.setItems(fribt);
    }

    private void DisplaySchedule() {
        ObservableList<HR3_CreatingScheduleClass> dv = FXCollections.observableArrayList();
        try {
            Connection conn = DBConnector.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("Select * from aerolink.tbl_hr3_CreateSchedule");

            while (rs.next()) {

                dv.add(new HR3_CreatingScheduleClass(rs.getString("ID"), rs.getString("Name"), rs.getString("Position"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HR3_Shift_and_SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cs_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cs_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        cs_position.setCellValueFactory(new PropertyValueFactory<>("Position"));
        cs_monday.setCellValueFactory(new PropertyValueFactory<>("Monday"));
        cs_tuesday.setCellValueFactory(new PropertyValueFactory<>("Tuesday"));
        cs_wednesday.setCellValueFactory(new PropertyValueFactory<>("Wednesday"));
        cs_thursday.setCellValueFactory(new PropertyValueFactory<>("Thursday"));
        cs_friday.setCellValueFactory(new PropertyValueFactory<>("Friday"));

        CStable.setItems(dv);

    }

    


    private void loadDataSchedule() {

        HR3_CreatingSchedule_Model tm = new HR3_CreatingSchedule_Model();

        ObservableList<HR3_CreatingScheduleClass> dv = FXCollections.observableArrayList();
        List b = tm.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("ID");
            hm.get("Name");
            hm.get("Position");
            hm.get("Monday");
            hm.get("Tuesday");
            hm.get("Wednesday");
            hm.get("Thursday");
            hm.get("Friday");
            dv.add(
                    new HR3_CreatingScheduleClass(
                            String.valueOf(hm.get("ID")),
                            String.valueOf(hm.get("Name")),
                            String.valueOf(hm.get("Position")),
                            String.valueOf(hm.get("Monday")),
                            String.valueOf(hm.get("Tuesday")),
                            String.valueOf(hm.get("Wednesday")),
                            String.valueOf(hm.get("Thursday")),
                            String.valueOf(hm.get("Friday"))
                    ));

        }
        CStable.setItems(dv);
        DisplaySchedule();
    }

    public void GenerateTable() {

        this.CStable.getColumns().clear();

        TableColumn<CStable, String> ID = new TableColumn<>("ID");
        TableColumn<CStable, String> Name = new TableColumn<>("Name");
        TableColumn<CStable, String> Position = new TableColumn<>("Position");
        TableColumn<CStable, String> Monday = new TableColumn<>("Monday");
        TableColumn<CStable, String> Tuesday = new TableColumn<>("Tuesday");
        TableColumn<CStable, String> Wednesday = new TableColumn<>("Wednesday");
        TableColumn<CStable, String> Thursday = new TableColumn<>("Thursday");
        TableColumn<CStable, String> Friday = new TableColumn<>("Friday");

        ID.setCellValueFactory(value -> value.getValue().ID);
        Name.setCellValueFactory(value -> value.getValue().Name);
        Position.setCellValueFactory(value -> value.getValue().Position);
        Monday.setCellValueFactory(value -> value.getValue().Monday);
        Tuesday.setCellValueFactory(value -> value.getValue().Tuesday);
        Wednesday.setCellValueFactory(value -> value.getValue().Wednesday);
        Thursday.setCellValueFactory(value -> value.getValue().Thursday);
        Friday.setCellValueFactory(value -> value.getValue().Friday);

        this.CStable.getColumns().addAll(cs_id, cs_name, cs_position, cs_monday, cs_tuesday, cs_wednesday, cs_thursday, cs_friday);

    }

    private void UpdateTable() {
        newtable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Shift_RequestClass qwe = newtable.getItems().get(newtable.getSelectionModel().getSelectedIndex());
                sid.setText(qwe.getR_ID());
                sdate.setText(qwe.getDate());
                sname.setText(qwe.getEmployee());
                smon.setText(qwe.getMonday());
                stue.setText(qwe.getTuesday());
                swed.setText(qwe.getWednesday());
                sthu.setText(qwe.getThursday());
                sfri.setText(qwe.getFriday());

            }
        });

    }

    public void SaveData() {
        HR3_CreatingSchedule_Model sns1 = new HR3_CreatingSchedule_Model();
        String ID = csid.getText();
        String Name = csname.getText();
        String Position = csposition.getText();
        String Monday_In = csmin.getValue();
        String Monday_Out = csmout.getValue();
        String Monday_BreakTime = csmbt.getValue();
        String Tuesday_In = cstin.getValue();
        String Tuesday_Out = cstout.getValue();
        String Tuesday_BreakTime = cstbt.getValue();
        String Wednesday_In = cswin.getValue();
        String Wednesday_Out = cswout.getValue();
        String Wednesday_BreakTime = cswbt.getValue();
        String Thursday_In = csthin.getValue();
        String Thursday_Out = csthout.getValue();
        String Thursday_BreakTime = csthbt.getValue();
        String Friday_In = csfin.getValue();
        String Friday_Out = csfout.getValue();
        String Friday_BreakTime = csfbt.getValue();
        try {
            String[][] tbl_hr3_CreateSchedule
                    = {
                        {"ID", csid.getText()},
                        {"Name", csname.getText()},
                        {"Position", csposition.getText()},
                        {"Monday", csmin.getValue() + "-" + csmout.getValue() + "-" + csmbt.getValue()},
                        {"Tuesday", cstin.getValue() + "-" + cstout.getValue() + "-" + cstbt.getValue()},
                        {"Wednesday", cswin.getValue() + "-" + cswout.getValue() + "-" + cswbt.getValue()},
                        {"Thursday", csthin.getValue() + "-" + csthout.getValue() + "-" + csthbt.getValue()},
                        {"Friday", csfin.getValue() + "-" + csfout.getValue() + "-" + csfbt.getValue()},};

            if (sns1.insert(tbl_hr3_CreateSchedule)) {
                Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "INFORMATION", "Save", "Successfully");
                alert.open();
                loadDataSchedule();
                ClearData();

            } else {
                if ((ID.isEmpty() || Name.isEmpty() || Position.isEmpty() || Monday_In.isEmpty() || Monday_Out.isEmpty() || Monday_BreakTime.isEmpty() || Tuesday_In.isEmpty() || Tuesday_Out.isEmpty() || Tuesday_BreakTime.isEmpty() || Wednesday_In.isEmpty() || Wednesday_Out.isEmpty() || Wednesday_BreakTime.isEmpty() || Thursday_In.isEmpty() || Thursday_Out.isEmpty() || Thursday_BreakTime.isEmpty() || Friday_In.isEmpty() || Friday_Out.isEmpty() || Friday_BreakTime.isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Not Inserted");
                    alert.showAndWait();
                }

            }

        } catch (HeadlessException e) {

        }

    }

    private void ClearData() {
        csid.setText("");
        csname.setText("");
        csposition.setText("");
        csmin.setValue("");
        csmout.setValue("");
        csmbt.setValue("");
        cstin.setValue("");
        cstout.setValue("");
        cstbt.setValue("");
        cswin.setValue("");
        cswout.setValue("");
        cswbt.setValue("");
        csthin.setValue("");
        csthout.setValue("");
        csthbt.setValue("");
        csfin.setValue("");
        csfout.setValue("");
        csfbt.setValue("");

    }

    public void Save() {
        Shift_and_Scheduling sns1 = new Shift_and_Scheduling();

        try {
            String[][] tbl_hr3_ShiftingStatus
                    = {
                        {"R_ID", sid.getText()},
                        {"Date", sdate.getText()},
                        {"Name", sname.getText()},
                        {"Monday", smon.getText()},
                        {"Tuesday", stue.getText()},
                        {"Wednesday", swed.getText()},
                        {"Thursday", sthu.getText()},
                        {"Friday", sfri.getText()},
                        {"Status", scmbstatus.getValue()}

                    };

            sns1.insert(tbl_hr3_ShiftingStatus);

            ClearDataShift();
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "INFORMATION", "Save", "Successfully");
            alert.open();

        } catch (HeadlessException e) {

        }

    }

    private void ClearDataShift() {
        sid.setText("");
        sdate.setText("");
        sname.setText("");
        smon.setText("");
        stue.setText("");
        swed.setText("");
        sthu.setText("");
        sfri.setText("");
        scmbstatus.setValue("Status");
    }

    private void DeleteRecord() {
        String sql = " Delete from aerolink.tbl_hr3_Shifting where ID=?";
        try {

            pst = conn.prepareStatement(sql);
            pst.setString(1, sid.getText());
            pst.execute();

        } catch (SQLException ex) {

        }
    }

    @FXML
    private void viewRow(MouseEvent event) {
    }

    private void UpdateTableCS() {
        CStable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HR3_CreatingScheduleClass sns = CStable.getItems().get(CStable.getSelectionModel().getSelectedIndex());
                n_monday.setText(sns.getMonday());
                n_tuesday.setText(sns.getTuesday());
                n_wednesday.setText(sns.getWednesday());
                n_thursday.setText(sns.getThursday());
                n_friday.setText(sns.getFriday());
                n_id.setText(sns.getID());

            }
        });

    }

    private void updateCShedule() {
        Connection conn = DBConnector.getConnection();
        try {

            String sql = "UPDATE aerolink.tbl_hr3_CreateSchedule set Monday='" + n_monday.getText() + "',Tuesday='" + n_tuesday.getText() + "',Wednesday='" + n_wednesday.getText() + "',Thursday='" + n_thursday.getText() + "',Friday='" + n_friday.getText() + "' where ID='" + n_id.getText() + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Update Data Success");
            saved.showAndWait();
            loadDataSchedule();
            n_id.setText("");
            n_monday.setText("");
            n_tuesday.setText("");
            n_wednesday.setText("");
            n_thursday.setText("");
            n_friday.setText("");
        } catch (SQLException e) {

        }
    }

    private void cancel() {
        n_id.setText("");
        n_monday.setText("");
        n_tuesday.setText("");
        n_wednesday.setText("");
        n_thursday.setText("");
        n_friday.setText("");
    }

    @FXML
    private void deleterowfromtable(ActionEvent event) {
        CStable.getItems().removeAll(CStable.getSelectionModel().getSelectedItems());

        cancel();
    }

    private void archieve() {
        CStable.getItems().clear();
    }


    public void NewRequest() {
        ObservableList<Shift_RequestClass> dv = FXCollections.observableArrayList();
        try {
            Connection conn = DBConnector.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("Select * from aerolink.tbl_hr3_Shiftings");

            while (rs.next()) {
                dv.add(new Shift_RequestClass(rs.getString("R_ID"), rs.getString("Date"), rs.getString("Employee"), rs.getString("Monday"), rs.getString("Tuesday"), rs.getString("Wednesday"), rs.getString("Thursday"), rs.getString("Friday")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HR3_Shift_and_SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        R_ID.setCellValueFactory(new PropertyValueFactory<>("R_ID"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Employee.setCellValueFactory(new PropertyValueFactory<>("Employee"));
        Monday.setCellValueFactory(new PropertyValueFactory<>("Monday"));
        Tuesday.setCellValueFactory(new PropertyValueFactory<>("Tuesday"));
        Wednesday.setCellValueFactory(new PropertyValueFactory<>("Wednesday"));
        Thursday.setCellValueFactory(new PropertyValueFactory<>("Thursday"));
        Friday.setCellValueFactory(new PropertyValueFactory<>("Friday"));

        newtable.setItems(dv);

    }

    public void displaynew() {
        ShiftRequestModel tm = new ShiftRequestModel();

        ObservableList<Shift_RequestClass> dv = FXCollections.observableArrayList();
        List b = tm.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("R_ID");
            hm.get("Employee");
            hm.get("Date");
            hm.get("Monday");
            hm.get("Tuesday");
            hm.get("Wednesday");
            hm.get("Thursday");
            hm.get("Friday");
            dv.add(
                    new Shift_RequestClass(
                            String.valueOf(hm.get("R_ID")),
                            String.valueOf(hm.get("Employee")),
                            String.valueOf(hm.get("Date")),
                            String.valueOf(hm.get("Monday")),
                            String.valueOf(hm.get("Tuesday")),
                            String.valueOf(hm.get("Wednesday")),
                            String.valueOf(hm.get("Thursday")),
                            String.valueOf(hm.get("Friday"))
                    ));

        }
        newtable.setItems(dv);
        NewRequest();
    }

    private void UpdateTableCSS() {
        tableCSnew.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new_emplo sns = tableCSnew.getItems().get(tableCSnew.getSelectionModel().getSelectedIndex());
                csid.setText(sns.getId());
                csname.setText(sns.getName());
                csposition.setText(sns.getPosition());

            }
        });

    }

    /*
    public void new_employee()
    {
     this.tableCSnew.getColumns().clear();

        TableColumn<CStable, String> ID = new TableColumn<>("ID");
        TableColumn<CStable, String> Name = new TableColumn<>("Name");
        TableColumn<CStable, String> Position = new TableColumn<>("Position");
        
        ID.setCellValueFactory(value -> value.getValue().ID);
        Name.setCellValueFactory(value -> value.getValue().Name);
        Position.setCellValueFactory(value -> value.getValue().Position);
        
         this.tableCSnew.getColumns().addAll(tb_id, tb_name, tb_position );
    }*/

    private void displaynewemployee() {
        ObservableList<new_emplo> dv = FXCollections.observableArrayList();
        try {
            Connection conn = DBConnector.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("Select * from aerolink.tbl_hr1_applicants");

            while (rs.next()) {

                dv.add(new new_emplo(rs.getString("Id"), rs.getString("Name"), rs.getString("Position")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HR3_Shift_and_SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tb_position.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        tableCSnew.setItems(dv);

    }

}
