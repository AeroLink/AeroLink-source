/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_Competency_ManagementClass;
import FXMLS.HR2.ClassFiles.HR2_JobsClass;
import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import Model.HR2_Competency_Management;
import Model.HR2_Jobs;
import Model.HR4_Departments;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Succession_PlanningController implements Initializable {

    HR4_Departments dept = new HR4_Departments();
    @FXML
    private TableView<HR2_JobsClass> tbl_job_data;
    @FXML
    private TableView<HR2_Competency_ManagementClass> tbl_skills;
    @FXML
    private JFXTextField txt_search_jobs_with_skills;
    @FXML
    private TableView<Training_ManagementClass> tbl_ihs_empInfo;
    @FXML
    private JFXButton btn_ihs;
    @FXML
    private TableView<?> tbl_positions_ohs;
    @FXML
    private TableView<?> tbl_ohs_empInfo;
    @FXML
    private JFXButton btn_ohs;
    @FXML
    private JFXButton btn_add_successor;
    @FXML
    private TableColumn<HR2_JobsClass, String> col_job;
    @FXML
    private TableColumn<?, ?> col_job_position;
    @FXML
    private TableColumn<HR2_Competency_ManagementClass, String> col_skills;
    @FXML
    private TableView<?> tbl_position_ihs;
    @FXML
    private TableColumn<?, ?> col_ihs_position;
    @FXML
    private TableColumn<?, ?> col_ihs_emp_id;
    @FXML
    private TableColumn<?, ?> col_ihs_name;
    @FXML
    private TableColumn<?, ?> col_ihs_skills;
    @FXML
    private TableColumn<?, ?> col_ihs_dept;
    @FXML
    private TableColumn<?, ?> col_ihs_age;
    @FXML
    private TableColumn<?, ?> col_ihs_address;
    @FXML
    private TableColumn<?, ?> col_ohs_position;
    @FXML
    private TableColumn<?, ?> col_ohs_emp_id;
    @FXML
    private TableColumn<?, ?> col_ohs_name;
    @FXML
    private TableColumn<?, ?> col_ohs_skills;
    @FXML
    private TableColumn<?, ?> col_ohs_dept;
    @FXML
    private TableColumn<?, ?> col_ohs_age;
    @FXML
    private TableColumn<?, ?> col_ohs_address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DisplayData();
        LoadData();
    }

    public void DisplayData() {
        col_job.setCellValueFactory((TableColumn.CellDataFeatures<HR2_JobsClass, String> param) -> param.getValue().Title);
        col_skills.setCellValueFactory((TableColumn.CellDataFeatures<HR2_Competency_ManagementClass, String> param) -> param.getValue().Skill);
        
          TableColumn<Training_ManagementClass, Void> addButton = new TableColumn("Edit Action");

        Callback<TableColumn<Training_ManagementClass, Void>, TableCell<Training_ManagementClass, Void>> cellFactory
                = new Callback<TableColumn<Training_ManagementClass, Void>, TableCell<Training_ManagementClass, Void>>() {
            @Override
            public TableCell<Training_ManagementClass, Void> call(final TableColumn<Training_ManagementClass, Void> param) {

                final TableCell<Training_ManagementClass, Void> cell = new TableCell<Training_ManagementClass, Void>() {
                    private final Button btn = new Button("View Skills");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                LoadData();
                            });
                            btn.setCursor(javafx.scene.Cursor.HAND);
                        }catch(Exception ex)
                        {
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
        tbl_ihs_empInfo.getColumns().add(addButton);
    }

    public void LoadData() {
        //Job

        HR2_Jobs job = new HR2_Jobs();

        ObservableList<HR2_JobsClass> j = FXCollections.observableArrayList();
        List b = job.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            //RS
            hm.get("title");

            j.add(
                    new HR2_JobsClass(
                            String.valueOf("job_id"),
                            String.valueOf(hm.get("title")),
                            String.valueOf(hm.get("description"))
                    ));
        }
        tbl_job_data.setItems(j);
        
        
        //Skills
         HR2_Competency_Management cm = new HR2_Competency_Management();

            ObservableList<HR2_Competency_ManagementClass> hmc = FXCollections.observableArrayList();
            List c = cm.get();

            for (Object d : c) {
                HashMap hm = (HashMap) d;
                //RS
                hm.get("skill_id");
                hm.get("skill");
                hm.get("skill_description");

                hmc.add(
                        new HR2_Competency_ManagementClass(
                                String.valueOf(hm.get("skill_id")),
                                String.valueOf(hm.get("skill")),
                                String.valueOf(hm.get("skill_description")),
                                String.valueOf(hm.get("select_jobs"))
                        ));
            }
            tbl_skills.setItems(hmc);
    }
}
