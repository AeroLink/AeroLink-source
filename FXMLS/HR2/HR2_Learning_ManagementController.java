/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_ExaminationClass;
import FXMLS.HR2.ClassFiles.HR2_LMClass_For_AddQuestion_Modal;
import FXMLS.HR2.ClassFiles.HR2_LM_CourseOutlineModal;
import FXMLS.HR2.ClassFiles.HR2_LM_ViewCourseModal;
import Model.HR2_Courses;
import Model.HR2_Examination;
import Model.HR4_Jobs;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Learning_ManagementController implements Initializable {

    @FXML
    private JFXTextField txt_search_course;
    @FXML
    private JFXButton btn_refresh;
    @FXML
    private TableView<HR2_CoursesClass> tbl_courses;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_course_title;

    TableColumn<HR2_CoursesClass, String> courses_idx = new TableColumn<>();

    long DummyCount = 0;
    long GlobalCount = 0;
    @FXML
    private ContextMenu contextmenu_lm;
    @FXML
    private MenuItem c_item_modify;
    @FXML
    private MenuItem c_item_drop;
    @FXML
    private TableView<HR2_ExaminationClass> tbl_exam;
    @FXML
    private TableColumn<HR2_ExaminationClass, String> col_exam_name;
    @FXML
    private JFXComboBox cbox_job_title;
    @FXML
    private JFXButton btn_add_job;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        DisplayDataInJTable();
        // populateExam();
        DisplayExamInTable();
        tbl_courses.getSelectionModel().selectedItemProperty().addListener(listener -> {
            populateExam();
        });
        loadJobsInComboBox();

    }

    public void loadJobsInComboBox() {
        HR4_Jobs jobs = new HR4_Jobs();
        try {
            List c = jobs.get();

            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                String j_id = String.valueOf(hm1.get("job_id"));
                String sjobs = (String) hm1.get("title");

                cbox_job_title.getItems().add("J" + j_id + " - " + sjobs);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void loadData() {

        try {
            //tbl courses
            HR2_Courses c = new HR2_Courses();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("learning_management")) {
                    c.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List courses = c.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                                .where(new Object[][]{{"aerolink.tbl_hr2_courses.isDeleted", "<>", "1"}})
                                .get("aerolink.tbl_hr2_courses.course_id", "j.title as job_title");
                        Data(courses);

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

    }

    public void Data(List b) {
        ObservableList<HR2_CoursesClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b) {

                HashMap hm = (HashMap) d;
                System.out.println(String.valueOf(hm.get("course_id")));
                obj.add(
                        new HR2_CoursesClass(
                                String.valueOf("course_id"),
                                String.valueOf(hm.get("job_title"))));

            }
            tbl_courses.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void populateExam() {

        try {
            //tbl courses
            HR2_Examination exam = new HR2_Examination();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("learning_management")) {
                    exam.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List e = exam.join(Model.JOIN.INNER, "aerolink.tbl_hr2_courses", "course_id", "c", "=", "course_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "c", "job_id", true)
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "id", "ep", "=", "id")
                                .where(new Object[][]{{"aerolink.tbl_hr2_examination.isDeleted", "<>", "1"}})
                                .get("aerolink.tbl_hr2_examination.exam_name", "aerolink.tbl_hr2_examination.exam_desc",
                                        "CONCAT(ep.firstname,' ',ep.middlename,' ',ep.lastname)as Created_by");
                        Exam(e);

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

    }

    public void Exam(List b1) {
        ObservableList<HR2_ExaminationClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b1) {

                HashMap hm = (HashMap) d;
                obj.add(
                        new HR2_ExaminationClass(
                                String.valueOf(hm.get("exam_id")),
                                String.valueOf(hm.get("exam_name")),
                                String.valueOf(hm.get("exam_desc")),
                                String.valueOf(hm.get("Created_by"))));

            }
            tbl_exam.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void searchCourses() {
        HR2_Courses c = new HR2_Courses();
        tbl_courses.getItems().clear();
        try {
            List courses = c.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                    .where(new Object[][]{
                {"j.title", "like", "%" + txt_search_course.getText() + "%"},
                {"aerolink.tbl_hr2_courses.isDeleted", "<>", "1"}
            })
                    .get("aerolink.tbl_hr2_courses.course_id", "j.title as job_title");
            Data(courses);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInJTable() {

        courses_idx.setCellValueFactory(param -> param.getValue().course_id);
        col_course_title.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().job_title);
        TableColumn<HR2_CoursesClass, Void> addButton = new TableColumn("View Action");

        Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>> cellFactory
                = new Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>>() {
            @Override
            public TableCell<HR2_CoursesClass, Void> call(final TableColumn<HR2_CoursesClass, Void> param) {

                final TableCell<HR2_CoursesClass, Void> cell = new TableCell<HR2_CoursesClass, Void>() {
                    private final Button btn = new Button("Course Outline");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                HR2_LM_CourseOutlineModal.courseOutline(tbl_courses.getSelectionModel().getSelectedItem().job_title.get());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/LM_CourseOutline.fxml").getParent());
                                lq.open();
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

        addButton.setCellFactory(cellFactory);
        tbl_courses.getColumns().add(addButton);

        TableColumn<HR2_CoursesClass, Void> addExam = new TableColumn("Add Exam");

        Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>> cellFactory12
                = new Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>>() {
            @Override
            public TableCell<HR2_CoursesClass, Void> call(final TableColumn<HR2_CoursesClass, Void> param) {

                final TableCell<HR2_CoursesClass, Void> cell22 = new TableCell<HR2_CoursesClass, Void>() {
                    private final Button btn_add_exam = new Button("Add Exam");

                    {
                        try {
                            btn_add_exam.setOnAction(e
                                    -> {
                                HR2_LM_CourseOutlineModal.courseOutline(tbl_courses.getSelectionModel().getSelectedItem().job_title.get());
                                Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/AddExam.fxml").getParent());
                                lq.open();
                            });
                            btn_add_exam.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_add_exam.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_add_exam);
                        }
                    }
                };
                return cell22;
            }

        };

        addExam.setCellFactory(cellFactory12);
        tbl_courses.getColumns().add(addExam);
    }

    public void DisplayExamInTable() {
        col_exam_name.setCellValueFactory((TableColumn.CellDataFeatures<HR2_ExaminationClass, String> param) -> param.getValue().exam_name);

        TableColumn<HR2_ExaminationClass, Void> listOfQuestions = new TableColumn("List of Questions");

        Callback<TableColumn<HR2_ExaminationClass, Void>, TableCell<HR2_ExaminationClass, Void>> cellFactory
                = new Callback<TableColumn<HR2_ExaminationClass, Void>, TableCell<HR2_ExaminationClass, Void>>() {
            @Override
            public TableCell<HR2_ExaminationClass, Void> call(final TableColumn<HR2_ExaminationClass, Void> param) {

                final TableCell<HR2_ExaminationClass, Void> cell = new TableCell<HR2_ExaminationClass, Void>() {
                    private final Button btn_q_list = new Button("List of Questions");

                    {
                        try {
                            btn_q_list.setOnAction(e
                                    -> {
                                HR2_LMClass_For_AddQuestion_Modal.initCourseTitle(
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_name.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_desc.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().id.get());
                                Modal md = Modal.getInstance(new Form("/FXMLS/HR2/Modals/List_Of_Questions.fxml").getParent());
                                md.open();
                            });
                            btn_q_list.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_q_list.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_q_list);
                        }
                    }
                };
                return cell;
            }

        };

        listOfQuestions.setCellFactory(cellFactory);
        tbl_exam.getColumns().add(listOfQuestions);

        TableColumn<HR2_ExaminationClass, Void> addQuestion = new TableColumn("Add Questions");

        Callback<TableColumn<HR2_ExaminationClass, Void>, TableCell<HR2_ExaminationClass, Void>> cellFactory1
                = new Callback<TableColumn<HR2_ExaminationClass, Void>, TableCell<HR2_ExaminationClass, Void>>() {
            @Override
            public TableCell<HR2_ExaminationClass, Void> call(final TableColumn<HR2_ExaminationClass, Void> param) {

                final TableCell<HR2_ExaminationClass, Void> cell1 = new TableCell<HR2_ExaminationClass, Void>() {
                    private final Button btn_add_q = new Button("Add Question");

                    {
                        try {
                            btn_add_q.setOnAction(e
                                    -> {
                                HR2_LMClass_For_AddQuestion_Modal.initCourseTitle(
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_name.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().exam_desc.get(),
                                        tbl_exam.getSelectionModel().getSelectedItem().id.get());
                                Modal md = Modal.getInstance(new Form("/FXMLS/HR2/Modals/LM_AddQuestions.fxml").getParent());
                                md.open();
                            });
                            btn_add_q.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn_add_q.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn_add_q);
                        }
                    }
                };
                return cell1;
            }

        };

        addQuestion.setCellFactory(cellFactory1);
        tbl_exam.getColumns().add(addQuestion);

    }

    public void AddCourseModal() {
        Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/Add_Courses.fxml").getParent());
        lq.open();
    }

    @FXML
    public void viewRow(MouseEvent event) {

        if (event.getButton() == MouseButton.SECONDARY) {
            contextmenu_lm.show(tbl_courses, event.getX(), event.getSceneY());
            c_item_modify.setOnAction(e -> OpenModalForEdit());
            c_item_drop.setOnAction(e -> DropExam());
        }

    }

    @FXML
    public void addJob() {

        if (cbox_job_title.getValue().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please Select Jobs");
            alert.showAndWait();
        } else {
            try {
                HR2_Courses c = new HR2_Courses();
                String[][] cm_data
                        = {
                            {"job_id", cbox_job_title.getSelectionModel().getSelectedItem().toString().substring(1).toString().split(" - ")[0]},
                            {"isDeleted", "0"},};
                //int id = model.insert(vals, true);
                btn_add_job.setDisable(false);
                c.insert(cm_data);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
                cbox_job_title.setValue(null);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Error" + e);
                alert.showAndWait();
            }
        }

    }

    public void OpenModalForEdit() {
        HR2_LM_ViewCourseModal.EditCourse(tbl_courses.getSelectionModel().getSelectedItem().job_title.get());
        Modal viewCourse = Modal.getInstance(new Form("/FXMLS/HR2/Modals/LM_ViewCourse.fxml").getParent());
        viewCourse.open();

    }

    public void DropExam() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to drop this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_Courses c = new HR2_Courses();

            Boolean a = c.where(new Object[][]{
                {"course_id", "=", tbl_courses.getSelectionModel().getSelectedItem().course_id.get()}
            }).update(new Object[][]{
                {"isDeleted", "1"},}).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText(tbl_courses.getSelectionModel().getSelectedItem().job_title.get() + " Successfully Dropped");
            dropnotif.showAndWait();
            loadData();
        }
    }

}
