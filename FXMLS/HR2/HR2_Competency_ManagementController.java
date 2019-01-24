/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.CM_Skill_RequisitionClass;
import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class;
import FXMLS.HR2.ClassFiles.HR2_CM_Skills_Class_for_Modal;
import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_LM_AddExamModalClass;
import FXMLS.HR2.ClassFiles.HR2_LM_CourseOutlineModal;
import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import FXMLS.HR4.ClassFiles.HR4_MIZ;
import Model.HR2_CM_Pivot;
import Model.HR2_CM_Skill_Requisition;
import Model.HR2_CM_Skills;
import Model.HR2_Jobs;
import Model.HR2_Training_Info;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXComboBox;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Competency_ManagementController implements Initializable {

    @FXML
    private JFXButton btn_set_skills;
    @FXML
    private JFXTextField txt_search_job;
    @FXML
    public TableView<HR4_Jobs_Class> tbl_jobs;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_job_desc;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_skills;
    @FXML
    private TableColumn<HR4_Jobs_Class, String> col_skill_desc;

    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private ContextMenu contextmenu_skills;
    @FXML
    private MenuItem contextmenu_item_modify;
    @FXML
    private MenuItem contextmenu_item_delete;
    @FXML
    private TableView<CM_Skill_RequisitionClass> tbl_req_skill;
    @FXML
    private TableColumn<CM_Skill_RequisitionClass, String> col_dept;
    @FXML
    private TableColumn<CM_Skill_RequisitionClass, String> col_jp;
    @FXML
    private TableColumn<CM_Skill_RequisitionClass, String> col_req_status;
    @FXML
    private JFXComboBox cbox_filter_status;
    @FXML
    private JFXComboBox cbox_filter_dept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        DisplayDataInTable();
        //     btn_refresh.setOnAction(e -> loadJob());
        // diplaycols();
        btn_set_skills.setOnAction(e -> {
            Modal set_skill_modal = Modal.getInstance(new Form("/FXMLS/HR2/Modals/Modal_SetSkills.fxml").getParent());
            set_skill_modal.open();
        });

        txt_search_job.setOnKeyReleased(e -> SearchJob());

    }

    public void loadData() {

        try {

            HR2_CM_Pivot cm_pivot = new HR2_CM_Pivot();
            HR2_CM_Skills skills_tbl = new HR2_CM_Skills();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("competency_management")) {
                    skills_tbl.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List c = cm_pivot.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr2_skillset", "skill_id", "s", "=", "skill_id")
                                .where(new Object[][]{{"s.isDeleted", "<>", "1"}})
                                .orderBy("jobs.title", Model.Sort.ASC)
                                .get("jobs.title, jobs.description, s.skill, s.skill_description , s.skill_id");

                        Data(c);

                        GlobalCount = DummyCount;
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(HR2_Competency_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                return 0;
            }, Session.SessionThreads);

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            HR2_CM_Skill_Requisition skill_req = new HR2_CM_Skill_Requisition();
            List sr = skill_req.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_department", "id", "dept", "=", "dept_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "ep", "=", "requested_by")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_request_status", "req_status_id", "rs", "=", "req_status_id")
                    .where(new Object[][]{{"aerolink.tbl_hr2_skill_requisition.isDeleted", "<>", "1"}})
                    .orderBy("aerolink.tbl_hr2_skill_requisition.date_requested", Model.Sort.ASC)
                    .get("aerolink.tbl_hr2_skill_requisition.sr_id,dept.dept_name, jobs.title, rs.req_status");

            Req_Skill_Data(sr);

        } catch (Exception req_skill) {
            System.out.println(req_skill);
        }
    }

    public void SearchJob() {
        HR2_CM_Pivot cm_pivot = new HR2_CM_Pivot();
        tbl_jobs.getItems().clear();
        try {

            List c = cm_pivot.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "jobs", "=", "job_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_skillset", "skill_id", "s", "=", "skill_id")
                    .where(new Object[][]{{"jobs.title", "like", "%" + txt_search_job.getText() + "%"}, {"s.isDeleted", "<>", "1"}})
                    .get("jobs.title", "jobs.description", "s.skill_id", "s.skill", "s.skill_description");

            Data(c);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Data(List cmgmt) {
        ObservableList<HR4_Jobs_Class> hr4_jobs = FXCollections.observableArrayList();
        hr4_jobs.clear();
        try {

            for (Object d : cmgmt) {
                HashMap hm1 = (HashMap) d;

                hr4_jobs.add(
                        new HR4_Jobs_Class(
                                String.valueOf(hm1.get("job_id")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("description")),
                                String.valueOf(hm1.get("skill_id")),
                                String.valueOf(hm1.get("skill")),
                                String.valueOf(hm1.get("skill_description"))
                        ));
            }

            tbl_jobs.setItems(hr4_jobs);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_jobs.getItems().size());
        tbl_jobs.getSelectionModel().selectFirst();
    }

    public void Req_Skill_Data(List rsd) {
        ObservableList<CM_Skill_RequisitionClass> s_req = FXCollections.observableArrayList();
        s_req.clear();
        try {

            for (Object d : rsd) {
                HashMap hm1 = (HashMap) d;

                s_req.add(
                        new CM_Skill_RequisitionClass(
                                String.valueOf(hm1.get("sr_id")),
                                String.valueOf(hm1.get("dept_name")),
                                String.valueOf(hm1.get("title")),
                                String.valueOf(hm1.get("req_status"))
                        ));
            }

            tbl_req_skill.setItems(s_req);

        } catch (Exception e) {
            System.out.println(e);
        }
        System.err.println(tbl_req_skill.getItems().size());
        tbl_req_skill.getSelectionModel().selectFirst();
    }

    public void DisplayDataInTable() {

        col_job.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Title);
        col_job_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Description);
        col_skills.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Skill);
        col_skill_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Jobs_Class, String> param) -> param.getValue().Skill_Description);
        //tbl_req_skill_Req
        col_dept.setCellValueFactory((TableColumn.CellDataFeatures<CM_Skill_RequisitionClass, String> param) -> param.getValue().dept_name);
        col_jp.setCellValueFactory((TableColumn.CellDataFeatures<CM_Skill_RequisitionClass, String> param) -> param.getValue().title);
        col_req_status.setCellValueFactory((TableColumn.CellDataFeatures<CM_Skill_RequisitionClass, String> param) -> param.getValue().req_status);
        TableColumn<CM_Skill_RequisitionClass, Void> ViewDetails = new TableColumn("View Action");

        Callback<TableColumn<CM_Skill_RequisitionClass, Void>, TableCell<CM_Skill_RequisitionClass, Void>> cellFactory
                = new Callback<TableColumn<CM_Skill_RequisitionClass, Void>, TableCell<CM_Skill_RequisitionClass, Void>>() {
            @Override
            public TableCell<CM_Skill_RequisitionClass, Void> call(final TableColumn<CM_Skill_RequisitionClass, Void> param) {

                final TableCell<CM_Skill_RequisitionClass, Void> cell = new TableCell<CM_Skill_RequisitionClass, Void>() {
                    private final Button btn = new Button("View Details");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {

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

        ViewDetails.setCellFactory(cellFactory);
        tbl_req_skill.getColumns().add(ViewDetails);

        TableColumn<CM_Skill_RequisitionClass, Void> Archivebtn = new TableColumn("Archive Action");

        Callback<TableColumn<CM_Skill_RequisitionClass, Void>, TableCell<CM_Skill_RequisitionClass, Void>> cellFactory12
                = new Callback<TableColumn<CM_Skill_RequisitionClass, Void>, TableCell<CM_Skill_RequisitionClass, Void>>() {
            @Override
            public TableCell<CM_Skill_RequisitionClass, Void> call(final TableColumn<CM_Skill_RequisitionClass, Void> param) {

                final TableCell<CM_Skill_RequisitionClass, Void> cell22 = new TableCell<CM_Skill_RequisitionClass, Void>() {
                    private final Button btn_a = new Button("Archive");

                    {
                        try {

                            btn_a.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_a.setCursor(javafx.scene.Cursor.HAND);

                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_a);
                        }
                    }
                };
                return cell22;
            }

        };

        Archivebtn.setCellFactory(cellFactory12);
        tbl_req_skill.getColumns().add(Archivebtn);
    }

    public void EditData() {
        HR2_CM_Skills_Class_for_Modal.init_JClass(
                tbl_jobs.getSelectionModel().getSelectedItem().Skill_id.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().Title.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().Description.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().Skill.get(),
                tbl_jobs.getSelectionModel().getSelectedItem().Skill_Description.get()
        );

        Modal md = Modal.getInstance(new Form("/FXMLS/HR2/Modals/Modal_EditSkills.fxml").getParent());
        md.open();
    }

    @FXML
    public void viewRow(MouseEvent event) {

        if (event.getButton() == MouseButton.SECONDARY) {
            contextmenu_skills.show(tbl_jobs, event.getX(), event.getSceneY());
            contextmenu_item_modify.setOnAction(e -> EditData());
            contextmenu_item_delete.setOnAction(e -> DropData());
        }

    }

    public void DropData() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_CM_Skills s = new HR2_CM_Skills();

            Boolean a = s.where(new Object[][]{
                {"skill_id", "=", tbl_jobs.getSelectionModel().getSelectedItem().Skill_id.get()}
            }).update(new Object[][]{
                {"isDeleted", "1"},}).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText(tbl_jobs.getSelectionModel().getSelectedItem().Skill.get() + " Successfully Dropped");
            dropnotif.showAndWait();
        }
    }
}
