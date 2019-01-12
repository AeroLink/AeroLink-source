/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.HR3.Register_attendController;
import Model.Time_and_Attendance;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import FXMLS.HR3.ClassFiles.Time_and_AttendanceClass;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.List;
import FXMLS.HR3.Controllers.DBConnector;
import FXMLS.HR3.Controllers.sample;
import Synapse.Form;
import static Synapse.R2SL.convert;
import static io.reactivex.internal.schedulers.SchedulerPoolFactory.start;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.O;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javax.xml.ws.Action;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Time_and_AttendanceController implements Initializable {

    ObservableList<Time_and_AttendanceClass> oblist = FXCollections.observableArrayList();
    Connection con = null;
    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;

    @FXML
    private JFXTextField txtid;
    @FXML
    private JFXTextField txtname;
    private JFXTextField undertime;
    private JFXTextField overtime;

    @FXML
    private JFXTextField txtsearch;

    @FXML
    private Button btn_save;
    @FXML
    private TableView<Time_and_AttendanceClass> tablemonitoring;
    @FXML
    private Button btn_save1;

    @FXML
    private Label TimeLab;
    @FXML
    private Button btn_save2;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_id;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_name;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_date;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_works;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_in;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_out;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_time;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_over;
    @FXML
    private TableColumn<Time_and_AttendanceClass, String> b_under;

    Time_and_Attendance Time = new Time_and_Attendance();
    @FXML
    private Button regbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DBConnector.getConnection();
        con = DBConnector.getConnection();

        DisplayData();
        loadDataSchedule();
        btn_save.setOnMouseClicked(e -> TimeIn());
        btn_save2.setOnMouseClicked(e -> Out());
        regbtn.setOnMouseClicked(e -> button());
        UpdateTable();

    }

    private void DisplayData() {
        ObservableList<Time_and_AttendanceClass> dv = FXCollections.observableArrayList();
        try {
            Connection conn = DBConnector.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("Select * from aerolink.tbl_hr3_CountTime");

            while (rs.next()) {
                dv.add(new Time_and_AttendanceClass(rs.getString("ID"), rs.getString("Name"), rs.getString("Datein"), rs.getString("Timein"), rs.getString("Timeout"), rs.getString("Dateout"), rs.getString("ElapseTime"), rs.getString("Overtime"), rs.getString("Undertime")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HR3_Time_and_AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        b_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        b_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        b_date.setCellValueFactory(new PropertyValueFactory<>("Datein"));
        b_in.setCellValueFactory(new PropertyValueFactory<>("Timein"));
        b_out.setCellValueFactory(new PropertyValueFactory<>("Timeout"));
        b_works.setCellValueFactory(new PropertyValueFactory<>("Dateout"));
        b_time.setCellValueFactory(new PropertyValueFactory<>("ElapseTime"));
        b_over.setCellValueFactory(new PropertyValueFactory<>("Overtime"));
        b_under.setCellValueFactory(new PropertyValueFactory<>("Undertime"));

        tablemonitoring.setItems(dv);

    }

    private void loadDataSchedule() {

        Time_and_Attendance tm = new Time_and_Attendance();

        ObservableList<Time_and_AttendanceClass> dv = FXCollections.observableArrayList();
        List b = tm.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;

            hm.get("ID");
            hm.get("Name");
            hm.get("Datein");
            hm.get("Timein");
            hm.get("Timeout");
            hm.get("Dateout");
            hm.get("ElapseTime");
            hm.get("Overtime");
            hm.get("Undertime");
            dv.add(
                    new Time_and_AttendanceClass(
                            String.valueOf(hm.get("ID")),
                            String.valueOf(hm.get("Name")),
                            String.valueOf(hm.get("Datein")),
                            String.valueOf(hm.get("Timein")),
                            String.valueOf(hm.get("Timeout")),
                            String.valueOf(hm.get("Dateout")),
                            String.valueOf(hm.get("ElapseTime")),
                            String.valueOf(hm.get("Overtime")),
                            String.valueOf(hm.get("Undertime"))
                    ));

        }
        tablemonitoring.setItems(dv);
        DisplayData();
    }

    private void TimeIn() {

        Connection con = DBConnector.getConnection();

        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM/dd/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");
        try {
            int count = 0;
            String sql = " select * from aerolink.tbl_hr3_dummy_data where ID=? and Name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            pst.setString(2, txtname.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                count = count + 0;
                pst = con.prepareStatement(sql);
                pst.setString(1, txtid.getText());
                pst.setString(2, txtname.getText());
                pst.execute();
                pst.close();
                rs.close();
            }
            if (count == 0) {

                Time_and_Attendance sns1 = new Time_and_Attendance();

                String[][] tbl_hr3_CountTime
                        = {
                            {"ID", txtid.getText()},
                            {"Name", txtname.getText()},
                            {"Datein", sdf1.format(cal.getTime())},
                            {"Timein", sdf2.format(cal.getTime())},
                            {"Dateout", "Not Yet"},
                            {"Timeout", "Not Yet"},
                            {"ElapseTime", "Not Yet"},};

                sns1.insert(tbl_hr3_CountTime);

                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Login Success");
                saved.showAndWait();
                loadDataSchedule();
                txtid.setText("");
                txtname.setText("");
            } else {
                Alert saved = new Alert(Alert.AlertType.WARNING);
                saved.setContentText("No Data Has Been Save");
                saved.showAndWait();
            }

        } catch (SQLException e) {
            Alert saved = new Alert(Alert.AlertType.WARNING);
            saved.setContentText("Login Already");
            saved.showAndWait();
        }
    }
/*
    @FXML
    private void txtIDR(javafx.scene.input.KeyEvent event) {
        try {
            String sql = "Select * from aerolink.tbl_hr3_dummy_data where ID =?";
            pst = con.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            rs = pst.executeQuery();

        } catch (Exception e) {
            Alert saved = new Alert(Alert.AlertType.WARNING);
            saved.setContentText("No ID");
            saved.showAndWait();
        } finally {
            try {
                rs.close();
                pst.close();

            } catch (Exception e) {

            }

        }
        int count = 0;
        try {
            if (count == 0) {
                btn_save.setVisible(true);

            }
        } catch (Exception ex) {

        }
    }*/


    private void UpdateTable() {
        tablemonitoring.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Time_and_AttendanceClass sns = tablemonitoring.getItems().get(tablemonitoring.getSelectionModel().getSelectedIndex());
                txtid.setText(sns.getID());
                txtname.setText(sns.getName());

            }
        });

    }

    public void tm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are You Sure ?? ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Yes");

        } else {
            System.out.println("No");
        }
    }

    private void Out() {

        Connection conn = DBConnector.getConnection();

        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM/dd/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");
        try {
            int count = 0;
            String sql = " select * from aerolink.tbl_hr3_dummy_data where ID=? and Name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            pst.setString(2, txtname.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                count = count + 1;
                pst = con.prepareStatement(sql);
                pst.setString(1, txtid.getText());
                pst.setString(2, txtname.getText());
                pst.execute();
                pst.close();
                rs.close();
            }
            if (count == 1) {

                int tm = 0;
                

                String sql1 = "UPDATE aerolink.tbl_hr3_CountTime set Dateout='" + sdf1.format(cal.getTime()) + "',Timeout='" + sdf2.format(cal.getTime()) + "' where ID='" + txtid.getText() + "'";
                pst = con.prepareStatement(sql1);
                pst.execute();

                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Log-Out Success");
                saved.showAndWait();
                loadDataSchedule();

                txtid.setText("");
                txtname.setText("");

            } else {
                Alert saved = new Alert(Alert.AlertType.WARNING);
                saved.setContentText("No Data Has Been Save");
                saved.showAndWait();
            }

        } catch (SQLException e) {
            Alert saved = new Alert(Alert.AlertType.WARNING);
            saved.setContentText("Log-out Already");
            saved.showAndWait();
        } finally {
            try {
                pst.execute();
                pst.close();
                rs.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
/*
    private void countcount() {
        try {
            String sql = " select * from aerolink.tbl_hr3_dummy_data where ID=? and Name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            pst.setString(2, txtname.getText());
            rs = pst.executeQuery();

            int in = Integer.parseInt(sql);
            int out = Integer.parseInt(sql);
            int overtimes, undertimes = 0;
            int total = out - in;

            if (in > out) {
                int fintotal = 12 - in;
                int secondfintotal = fintotal + out;
                if (secondfintotal > 8) {

                    overtimes = fintotal - 8;

                    String one = txtid.getText();
                    String two = overtime.getText();
                    String tree = undertime.getText();

                    String sql1 = "UPDATE aerolink.tbl_hr3_CountTime set Overtime='" + overtime.getText() + "', Undertime='" + undertime.getText() + "' where ID='" + txtid.getText() + "'";
                    pst1 = conn.prepareStatement(sql1);
                    pst1.executeQuery();
                    pst.close();
                } else {
                    if (secondfintotal < 8) {
                        undertimes = 9 - secondfintotal;
                        String sql1 = "UPDATE aerolink.tbl_hr3_CountTime set Overtime='" + secondfintotal + "', Undertime='" + undertimes + "' where ID='" + txtid.getText() + "'";
                        pst1 = conn.prepareStatement(sql1);
                        pst1.executeQuery();
                        pst.close();
                    }
                }
            } else if (total > 8) {
                total = total - 1;
                overtimes = total - 8;
                String sql1 = "UPDATE aerolink.tbl_hr3_CountTime set Overtime='" + total + "', Undertime='" + undertimes + "' where ID='" + txtid.getText() + "'";
                pst1 = conn.prepareStatement(sql1);
                pst1.executeQuery();
                pst.close();
            }
            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Log-Out SuccessFully");
            saved.showAndWait();
        } catch (SQLException e) {
            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Log-Out j");
            saved.showAndWait();
        }

    }

    private void updateb() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM/dd/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");
        try {
            String sql1 = "UPDATE aerolink.tbl_hr3_CountTime set Dateout='" + sdf1.format(cal.getTime()) + "',Timeout='" + sdf2.format(cal.getTime()) + "' where ID='" + txtid.getText() + "'";
            pst = con.prepareStatement(sql1);
            pst.execute();

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Log-Out Success");
            saved.showAndWait();
        } catch (Exception e) {

        }
    }*/

    private void button() {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR3/register_attend.fxml").getParent());

        md.open();
    }

    @FXML
    private void register(MouseEvent event) {
       

    }

    @FXML
    private void txtIDR(javafx.scene.input.KeyEvent event) {
    }

    @FXML
    private void Password(javafx.scene.input.KeyEvent event) {
    }
    /* sample lng to
    private void TimeInsample() {

        Connection con = DBConnector.getConnection();

        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM/dd/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");
        try {
            int count = 0;
            String sql = " select * from aerolink.tbl_hr3_dummy_data where ID=? and Name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            pst.setString(2, txtname.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                count = count + 0;
                pst = con.prepareStatement(sql);
                pst.setString(1, txtid.getText());
                pst.setString(2, txtname.getText());
                pst.execute();
                pst.close();
                rs.close();
            }
            if (count == 0) {

                Time_and_Attendance sns1 = new Time_and_Attendance();

                String[][] tbl_hr3_CountTime
                        = {
                            {"ID", txtid.getText()},
                            {"Name", txtname.getText()},
                            {"Datein", sdf1.format(cal.getTime())},
                            {"Timein", sdf2.format(cal.getTime())},
                            {"Dateout", "Not Yet"},
                            {"Timeout", "Not Yet"},
                            {"ElapseTime", "Not Yet"},};

                sns1.insert(tbl_hr3_CountTime);

                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Login Success");
                saved.showAndWait();
                loadDataSchedule();
                txtid.setText("");
                txtname.setText("");
            } else {
                Alert saved = new Alert(Alert.AlertType.WARNING);
                saved.setContentText("No Data Has Been Save");
                saved.showAndWait();
            }

        } catch (SQLException e) {
            Alert saved = new Alert(Alert.AlertType.WARNING);
            saved.setContentText("Login Already");
            saved.showAndWait();
        }
    }*/

}
