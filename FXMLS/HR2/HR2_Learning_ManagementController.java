/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_Training_InfoClass;
import Model.HR2_CM_Pivot;
import Model.HR2_Courses;
import Model.HR2_Training_Info;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
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
public class HR2_Learning_ManagementController implements Initializable {

    @FXML
    private JFXTextField txt_search_course;
    @FXML
    private JFXButton btn_add_course;
    @FXML
    private JFXButton btn_refresh;
    @FXML
    private TableView<HR2_CoursesClass> tbl_courses;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_course_title;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_course_desc;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_created_by;
    
    long DummyCount = 0;
    long GlobalCount = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        DisplayDataInJTable();
    }

    @FXML
    public void loadData() {

        try {

            HR2_Courses c = new HR2_Courses();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("learning_management")) {
                    c.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List courses = c.join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "id", "emps", "=", "id")
                                .get("j.title as course_title", "course_description",
                                         "concat(emps.firstname, ' ',emps.middlename, ' ',emps.lastname)as Created_by");
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
                System.out.println(hm);
                obj.add(
                        new HR2_CoursesClass(
                                String.valueOf(hm.get("course_id")),
                                String.valueOf(hm.get("job_id")),
                                String.valueOf(hm.get("course_title")),
                                String.valueOf(hm.get("course_description")),
                                String.valueOf(hm.get("Created_by"))));

            }
            tbl_courses.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInJTable() {
        col_course_title.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().course_title);
        col_course_desc.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().course_description);
        col_created_by.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().created_by);
        TableColumn<HR2_CoursesClass, Void> addButton = new TableColumn("View List of Questions");

        Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>> cellFactory
                = new Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>>() {
            @Override
            public TableCell<HR2_CoursesClass, Void> call(final TableColumn<HR2_CoursesClass, Void> param) {

                final TableCell<HR2_CoursesClass, Void> cell = new TableCell<HR2_CoursesClass, Void>() {
                    private final Button btn = new Button("List of Questions");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {
                                ViewListOfQuestions();
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
        
         TableColumn<HR2_CoursesClass, Void> addQuestion = new TableColumn("Add Questions");

        Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>> cellFactory1
                = new Callback<TableColumn<HR2_CoursesClass, Void>, TableCell<HR2_CoursesClass, Void>>() {
            @Override
            public TableCell<HR2_CoursesClass, Void> call(final TableColumn<HR2_CoursesClass, Void> param) {

                final TableCell<HR2_CoursesClass, Void> cell1 = new TableCell<HR2_CoursesClass, Void>() {
                    private final Button btn1 = new Button("Add Question");

                    {
                        try {
                            btn1.setOnAction(e
                                    -> {
                                ViewListOfQuestions();
                            });
                            btn1.setStyle("-fx-text-fill: #fff; -fx-background-color:#00cc66");
                            btn1.setCursor(javafx.scene.Cursor.HAND);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                    }

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn1);
                        }
                    }
                };
                return cell1;
            }
            
            

        };

        addQuestion.setCellFactory(cellFactory1);
        tbl_courses.getColumns().add(addQuestion);
    }

    public void ViewListOfQuestions() {
        Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/List_Of_Questions.fxml").getParent());
        lq.open();
    }

    @FXML
    public void AddCourseModal() {
        Modal lq = Modal.getInstance(new Form("/FXMLS/HR2/Modals/Add_Courses.fxml").getParent());
        lq.open();
    }

}
