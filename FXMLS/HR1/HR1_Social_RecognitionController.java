/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.TableModel_AwardTable;
import FXMLS.HR1.ClassFiles.TableModel_EmployeeList;
import FXMLS.HR1.ClassFiles.TableModel_RewardTable;
import FXMLS.HR1.ClassFiles.TableModel_table1;
import FXMLS.HR1.ClassFiles.TableModel_table2;
import FXMLS.HR1.ClassFiles.TableModel_tableUE;
import Model.HR1.HR1_PerformanceGrade;
import Model.HR1.HR1_SC_Award;
import Model.HR1.HR1_SC_Reward;
import Model.HR1.HR1_SC_Update;
import Model.HR4.HR4_Employee;
import Model.HR4_Departments;
import Model.HR4_Jobs;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javafx.scene.control.TableColumn;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_Social_RecognitionController implements Initializable {

    HR1_PerformanceGrade grd = new HR1_PerformanceGrade();
    HR4_Departments dept = new HR4_Departments();
    HR4_Employee employees = new HR4_Employee();
    HR4_Employee empJob = new HR4_Employee("job");
    HR4_Jobs jobs = new HR4_Jobs();
      
    HR1_SC_Update update = new HR1_SC_Update();
    
    HR1_SC_Award award = new HR1_SC_Award();

    HR1_SC_Reward reward = new HR1_SC_Reward();
    @FXML
    private TableView<TableModel_AwardTable> tbl_award;
    @FXML
    private TableView<TableModel_RewardTable> tbl_reward;
    @FXML
    private JFXTextField txtAwardname;
    @FXML
    private JFXTextField txtAwarddes;
    @FXML
    private JFXDatePicker dateAdded;
    @FXML
    private JFXTextField txtRewardname;
    @FXML
    private JFXTextField txtRemarks;
    @FXML
    private JFXDatePicker dateadded;
    @FXML
    private JFXComboBox awardcategory;
    @FXML
    private JFXTextField Dept1;
    @FXML
    private JFXTextField empid;
    @FXML
    private JFXTextField empname;
    @FXML
    private JFXDatePicker dateposted;
    @FXML
    private JFXComboBox rewardcategory;
    @FXML
    private JFXComboBox Department1;
    @FXML
    private JFXTextField remarks;
    @FXML
    private JFXComboBox Department11;
    @FXML
    private TableView<TableModel_table1> table1;
    @FXML
    private TableView<TableModel_table2> table2;
    @FXML
    private JFXTextField empname1;
    @FXML
    private TableView<TableModel_tableUE> tblUE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dept.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            Department1.getItems().add(row.get("dept_name").toString());
        });

        Object[] x = {"productivity", "qualityofwork", "Initiative", "teamwork", "problemsolving", "attendance"};

         award.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
        awardcategory.getItems().add(row.get("award_name").toString());
         });
         
         reward.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
        rewardcategory.getItems().add(row.get("reward_name").toString());
         });
         
        
        this.generateTable();
        this.renderTable();
        this.renderRewardTable();
        
        ObservableList<String> status = FXCollections.observableArrayList("Name", "Declined");
        awardcategory.setItems(status);
    }
    

    public void generateTable() {
       
        //

        tbl_award.getColumns().removeAll(tbl_award.getColumns());

        TableColumn<TableModel_AwardTable, String> award_name = new TableColumn<>("Award Name");
        TableColumn<TableModel_AwardTable, String> award_description = new TableColumn<>("Award Description");
        TableColumn<TableModel_AwardTable, String> date_added = new TableColumn<>("Date Added");

        award_name.setCellValueFactory(value -> value.getValue().award_name);
        award_description.setCellValueFactory(value -> value.getValue().award_description);
        date_added.setCellValueFactory(value -> value.getValue().date_added);

        tbl_award.getColumns().addAll(award_name, award_description, date_added);

        //
        tbl_reward.getColumns().removeAll(tbl_reward.getColumns());

        TableColumn<TableModel_RewardTable, String> reward_name = new TableColumn<>("Reward Name");
        TableColumn<TableModel_RewardTable, String> reward_remarks = new TableColumn<>("Remarks");
        TableColumn<TableModel_RewardTable, String> added_date = new TableColumn<>("Date Added");

        reward_name.setCellValueFactory(value -> value.getValue().reward_name);
        reward_remarks.setCellValueFactory(value -> value.getValue().reward_remarks);
        added_date.setCellValueFactory(value -> value.getValue().date_added);

        tbl_reward.getColumns().addAll(reward_name, reward_remarks, added_date);

        //
        table1.getColumns().removeAll(table1.getColumns());

        TableColumn<TableModel_table1, String> Department = new TableColumn<>("Department");
        TableColumn<TableModel_table1, String> Employee_Code = new TableColumn<>("Employee_Code");
        TableColumn<TableModel_table1, String> Employee_Name = new TableColumn<>("Employee_Name");
        TableColumn<TableModel_table1, String> Ratings = new TableColumn<>("Employee_Ratings");

        Department.setCellValueFactory(value -> value.getValue().Department);
        Employee_Code.setCellValueFactory(value -> value.getValue().Employee_Code);
        Employee_Name.setCellValueFactory(value -> value.getValue().Employee_Name);
        Ratings.setCellValueFactory(value -> value.getValue().Ratings);
        
        table1.getColumns().addAll(Department, Employee_Code, Employee_Name, Ratings);
     //
        table2.getColumns().removeAll(table2.getColumns());

        TableColumn<TableModel_table2, String> id = new TableColumn<>("Department");
        TableColumn<TableModel_table2, String> employee_code = new TableColumn<>("Employee_code");
        TableColumn<TableModel_table2, String> employee_name = new TableColumn<>("Employee_Name");
        TableColumn<TableModel_table2, String> Ratings1 = new TableColumn<>("Ratings");
        TableColumn<TableModel_table2, String> award_id = new TableColumn<>("Employee_Award");
        TableColumn<TableModel_table2, String> reward_id = new TableColumn<>("Employee_Reward");
        TableColumn<TableModel_table2, String> remarks = new TableColumn<>("Employee_Remarks");
        TableColumn<TableModel_table2, String> date_Posted = new TableColumn<>("Date_Posted");

        id.setCellValueFactory(value -> value.getValue().id);
        employee_code.setCellValueFactory(value -> value.getValue().employee_code);
        employee_name.setCellValueFactory(value -> value.getValue().employee_name);
        Ratings1.setCellValueFactory(value -> value.getValue().Ratings);
        award_id.setCellValueFactory(value -> value.getValue().award_id);
        reward_id.setCellValueFactory(value -> value.getValue().reward_id);
        remarks.setCellValueFactory(value -> value.getValue().remarks);
        date_Posted.setCellValueFactory(value -> value.getValue().date_Posted);

        table2.getColumns().addAll(id,employee_code, employee_name, Ratings1, award_id, reward_id, remarks, date_Posted);
        
        //
       
        tblUE.getColumns().removeAll(tblUE.getColumns());
        
        TableColumn<TableModel_tableUE, String> xDepartment = new TableColumn<>("Department");
        TableColumn<TableModel_tableUE, String> xEmployee_Code = new TableColumn<>("Employee_Code");
        TableColumn<TableModel_tableUE, String> xEmployee_Name = new TableColumn<>("Employee_Name");
        TableColumn<TableModel_tableUE, String> xRatings = new TableColumn<>("Employee_Ratings");

        xDepartment.setCellValueFactory(value -> value.getValue().Department);
        xEmployee_Code.setCellValueFactory(value -> value.getValue().Employee_Code);
        xEmployee_Name.setCellValueFactory(value -> value.getValue().Employee_Name);
        xRatings.setCellValueFactory(value -> value.getValue().Ratings);

        
        tblUE.getColumns().addAll(xDepartment, xEmployee_Code, xEmployee_Name, xRatings);

    }

    public void renderTable() {
        ObservableList<TableModel_AwardTable> list = FXCollections.observableArrayList();
        award.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            list.add(new TableModel_AwardTable(row.get("award_name").toString(),
                    row.get("award_description").toString(), 
                    row.get("date_added").toString()));
        });

        tbl_award.getItems().clear();
        tbl_award.setItems(list);
    }

    public void renderRewardTable() {
        ObservableList<TableModel_RewardTable> list = FXCollections.observableArrayList();
        reward.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            list.add(new TableModel_RewardTable(row.get("reward_name").toString(), row.get("reward_remarks").toString(), row.get("date_added").toString()));
        });

        tbl_reward.getItems().clear();
        tbl_reward.setItems(list);
    }

    @FXML
    public void rendertable1() {
        ObservableList<TableModel_table1> list = FXCollections.observableArrayList();

        empJob
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_perfGrading", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true).where(new Object[][]{
            {"aerolink.tbl_hr4_department.dept_name", "=", Department1.getSelectionModel().getSelectedItem().toString()},
            {"aerolink.tbl_hr1_perfGrading." + Department11.getSelectionModel().getSelectedItem().toString(), "=", 5},
            {"aerolink.tbl_hr4_employees.type_id", "<>", 5},
            {"aerolink.tbl_hr4_employees.status_id", "=", 1}

        }).get()
                .stream().forEach(action -> {

                    HashMap row = (HashMap) action;

                    list.add(new TableModel_table1(row.get("dept_name").toString(), row.get("employee_code").toString(), row.get("lastname").toString(), row.get(Department11.getSelectionModel().getSelectedItem().toString()).toString()));

                });

        table1.getItems().clear();
        table1.setItems(list);
        rendertblUE();
    }

    public void rendertblUE() {
        ObservableList<TableModel_tableUE> list = FXCollections.observableArrayList();

        empJob
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employees", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr1_perfGrading", "employee_code", "=", "employee_code")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "job_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "=", "aerolink.tbl_hr4_jobs", "dept_id", true).where(new Object[][]{
            {"aerolink.tbl_hr4_department.dept_name", "=", Department1.getSelectionModel().getSelectedItem().toString()},
            {"aerolink.tbl_hr1_perfGrading." + Department11.getSelectionModel().getSelectedItem().toString(), "<", 3},
            {"aerolink.tbl_hr4_employees.type_id", "<>", 5},
            {"aerolink.tbl_hr4_employees.status_id", "=", 1}

        }).get()
                .stream().forEach(action -> {

                    HashMap row = (HashMap) action;

                    list.add(new TableModel_tableUE(row.get("dept_name").toString(), row.get("employee_code").toString(), row.get("lastname").toString(), row.get(Department11.getSelectionModel().getSelectedItem().toString()).toString()));

                });

        tblUE.getItems().clear();
        tblUE.setItems(list);
    }
    
    public void rendertable2() {
        ObservableList<TableModel_table2> list = FXCollections.observableArrayList();
        reward.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            //list.add(new TableModel_table2(row.get("reward_name").toString(), row.get("reward_remarks").toString(), row.get("date_added").toString()));
        });

        tbl_reward.getItems().clear();
        //tbl_reward.setItems(list);
    }

    @FXML
    private void btnSaveAward(ActionEvent event) {

        if (award.insert(new Object[][]{
            {"award_name", txtAwardname.getText()},
            {"award_description", txtAwarddes.getText()},
            {"date_added", dateAdded.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)}
        })) {

            Helpers.EIS_Response.SuccessResponse("Success", "Adding New Award Successfuly");

            this.renderTable();
            txtAwardname.setText("");
            txtAwarddes.setText("");
            dateAdded.setValue(null);

        }

    }

    @FXML
    private void btnSaveReward(ActionEvent event) {

        if (reward.insert(new Object[][]{
            {"reward_name", txtRewardname.getText()},
            {"reward_remarks", txtRemarks.getText()},
            {"Date_added", dateadded.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)}
        })) {

            Helpers.EIS_Response.SuccessResponse("Success", "Adding New Reward Successfuly");

            this.renderRewardTable();

            txtRewardname.setText("");
            txtRemarks.setText("");
            dateadded.setValue(null);
        }

    }

    @FXML
    private void btnawardclear(ActionEvent event) {
        txtAwardname.setText("");
        txtAwarddes.setText("");
        dateAdded.setValue(null);

    }

    @FXML
    private void btnrewardclear(ActionEvent event) {
        txtRewardname.setText("");
        txtRemarks.setText("");
        dateadded.setValue(null);
    }

    @FXML
    private void btnUpdateClear(ActionEvent event) {
         
            Dept1.setText("");
            empid.setText("");
            empname.setText("");
            empname1.setText("");
            remarks.setText("");
            dateadded.setValue(null);
    }
    //public void renderTableDepartment

    @FXML
    private void btnUpdate(ActionEvent event) {
        if (update.insert(new Object[][]{
            {"id",Dept1.getText()},
            {"employee_code", empid.getText()},
            {"employee_name", empname.getText()},
            {"ratings", empname1.getText()},
            {"award_id", awardcategory.getSelectionModel().getSelectedItem().toString()},
            {"reward_id", rewardcategory.getSelectionModel().getSelectedItem().toString()},
            {"remarks", remarks.getText()},
            {"Date_added", dateadded.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)}
        })) {

            Helpers.EIS_Response.SuccessResponse("Success", "Update Successfuly");

            this.renderRewardTable();
            
            Dept1.setText("");
            empid.setText("");
            empname.setText("");
            empname1.setText("");
            awardcategory.setValue("");
            rewardcategory.setValue("");
            remarks.setText("");
            dateadded.setValue(null);
        }
    }
    
   
    
    
    
}
