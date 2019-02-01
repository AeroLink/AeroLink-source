/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import FXMLS.HR3.ClassFiles.HR3_table_shift_request;
import FXMLS.HR3.Table.hr3_tbl_timesheet_att_record;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author my
 */
public class HR3_Timesheet_ManagementController implements Initializable {

    @FXML
    private TableView<hr3_tbl_timesheet_att_record> tbl_attendance_records;
    @FXML
    private TableView<?> tbl_leave_records;
    @FXML
    private TableView<?> tbl_time_record;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.generatetable();
    }

    public void generatetable() {
        tbl_attendance_records.getColumns().removeAll(tbl_attendance_records.getColumns());

        TableColumn<hr3_tbl_timesheet_att_record, String> a = new TableColumn<>("Employee Code");
        TableColumn<hr3_tbl_timesheet_att_record, String> b = new TableColumn<>("Employee Name");
        TableColumn<hr3_tbl_timesheet_att_record, String> c = new TableColumn<>("Position");
        TableColumn<hr3_tbl_timesheet_att_record, String> d = new TableColumn<>("Date");
        TableColumn<hr3_tbl_timesheet_att_record, String> e = new TableColumn<>("Time-in");
        TableColumn<hr3_tbl_timesheet_att_record, String> f = new TableColumn<>("Time-out");
        TableColumn<hr3_tbl_timesheet_att_record, String> g = new TableColumn<>("Late");
        TableColumn<hr3_tbl_timesheet_att_record, String> h = new TableColumn<>("Overtime");
        TableColumn<hr3_tbl_timesheet_att_record, String> i = new TableColumn<>("Undertime");
        TableColumn<hr3_tbl_timesheet_att_record, String> j = new TableColumn<>("Daily Total Hours");

        a.setCellValueFactory(value -> value.getValue().employee_code);
        b.setCellValueFactory(value -> value.getValue().employee_name);
        c.setCellValueFactory(value -> value.getValue().position);
        d.setCellValueFactory(value -> value.getValue().date);
        e.setCellValueFactory(value -> value.getValue().time_in);
        f.setCellValueFactory(value -> value.getValue().time_out);
        g.setCellValueFactory(value -> value.getValue().late);
        h.setCellValueFactory(value -> value.getValue().overtime);
        i.setCellValueFactory(value -> value.getValue().undertime);
        j.setCellValueFactory(value -> value.getValue().daily_total_hours);

        tbl_attendance_records.getColumns().addAll(a, b, c, d, e, f, g, h, i ,j);
    }

}
