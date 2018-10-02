/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import FXMLS.HR2.ClassFiles.HR2_AssessmentClass;
import FXMLS.HR2.ClassFiles.HR2_Competency_ManagementClass;
import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_EvaluationClass;
import FXMLS.HR2.ClassFiles.HR2_JobsClass;
import Model.HR2_Assessment;
import Model.HR2_Competency_Management;
import Model.HR2_Courses;
import Model.HR2_Evaluation;
import Model.HR2_Jobs;
import FXMLS.HR2.Modals.*;
import Model.HR2_Training_Management;
import Synapse.Components.Modal.Modal;
import Synapse.Database;
import Synapse.DB.MYSQL;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Learning_ManagementController implements Initializable {

    @FXML
    private JFXTextField txt_course_id;
    @FXML
    private JFXTextField txt_course_title;
    @FXML
    private JFXTextArea txt_course_description;
    @FXML
    private JFXTextField txt_course_created_by;
    @FXML
    private JFXTextField txt_number_of_questions;
    @FXML
    private TableView<HR2_CoursesClass> tbl_course_data;
    @FXML
    private TableView<HR2_AssessmentClass> tbl_questions_data;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_course_id_pk;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_course_title;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_course_description;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_number_of_questions;
    @FXML
    private TableColumn<HR2_CoursesClass, String> col_created_by;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_question_id_pk;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_question;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_choice_id_fk;
    @FXML
    private JFXTextField txt_search_course;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXButton btn_refresh;
    @FXML
    private JFXTextField txt_search_assessment;
    @FXML
    private JFXButton btn_refresh_assessment;
    @FXML
    private JFXButton btn_add_assessment;
    @FXML
    private JFXButton btn_save;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_course_id_fk1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DisableComponents();
        //for course section
        btn_new.setOnAction(e -> {
            New();
            txt_course_id.setText("");
            txt_course_title.setText("");
            txt_course_description.setText("");
            txt_number_of_questions.setText("");
            txt_course_created_by.setText("");
            btn_edit.setDisable(true);
            btn_update.setDisable(true);
            btn_delete.setDisable(true);
            btn_save.setDisable(false);
        }
        );
        btn_update.setOnAction(e -> {
            UpdateCourse();
            DisableComponents();
        });
        btn_save.setOnAction(e -> SaveCourse());
        btn_edit.setOnAction(e -> {
            txt_course_title.setDisable(false);
            txt_course_description.setDisable(false);
            txt_course_created_by.setDisable(false);
            btn_update.setDisable(false);
        });
        btn_delete.setOnAction(e -> DeleteCourse());
        //for assessmenet section
        btn_add_assessment.setOnAction(e -> {
            Modal md = Modal.getInstance(new Form("/FXMLS/HR2/Modals/HR2_LM_Assessment.fxml").getParent());
            md.open();
        });
        tbl_course_data.setOnMouseClicked(e
                -> {
            HR2_CoursesClass cmc = tbl_course_data.getSelectionModel().getSelectedItem();
            btn_edit.setDisable(false);
            btn_delete.setDisable(false);
            txt_course_id.setText(cmc.course_id.getValue());
            txt_course_title.setText(cmc.course_title.getValue());
            txt_course_description.setText(cmc.course_description.getValue());
            txt_number_of_questions.setText(cmc.number_of_questions.getValue());
            txt_course_created_by.setText(cmc.created_by.getValue());
            btn_save.setDisable(true);
            txt_course_id.setDisable(true);
            txt_course_title.setDisable(true);
            txt_course_description.setDisable(true);
            txt_course_created_by.setDisable(true);
        });
        loadData();
        DisplayData();

    }

    //Display Data
    private void loadData() {
        try {

            //Courses
            HR2_Courses courses = new HR2_Courses();

            ObservableList<HR2_CoursesClass> coursesclass = FXCollections.observableArrayList();
            List b = courses.get();

            for (Object d : b) {
                HashMap hm = (HashMap) d;
                //RS
                hm.get("course_id");
                hm.get("course_title");
                hm.get("course_description");
                hm.get("number_of_questions");
                hm.get("created_by");

                coursesclass.add(
                        new HR2_CoursesClass(
                                String.valueOf(hm.get("course_id")),
                                String.valueOf(hm.get("course_title")),
                                String.valueOf(hm.get("course_description")),
                                String.valueOf(hm.get("number_of_questions")),
                                String.valueOf(hm.get("created_by"))
                        ));
            }
            tbl_course_data.setItems(coursesclass);

            //Questions                              
            HR2_Assessment assessment = new HR2_Assessment();

            ObservableList<HR2_AssessmentClass> assessmentclass = FXCollections.observableArrayList();
            List c = assessment.get();

            for (Object d : c) {
                HashMap hm1 = (HashMap) d;
                //RS
                hm1.get("question_id");
                hm1.get("question");
                hm1.get("choice_id");
                hm1.get("course_id");

                assessmentclass.add(
                        new HR2_AssessmentClass(
                                String.valueOf(hm1.get("question_id")),
                                String.valueOf(hm1.get("question")),
                                String.valueOf(hm1.get("choice_id")),
                                String.valueOf(hm1.get("course_id"))
                        ));

            }
            
            tbl_questions_data.setItems(assessmentclass);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //Initializing data
    private void DisplayData() {
        //Courses
        col_course_id_pk.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().course_id);
        col_course_title.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().course_title);
        col_course_description.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().course_description);
        col_number_of_questions.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().number_of_questions);
        col_created_by.setCellValueFactory((TableColumn.CellDataFeatures<HR2_CoursesClass, String> param) -> param.getValue().created_by);
        //Assessment
        col_question_id_pk.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().question_id);
        col_question.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().question);
        col_choice_id_fk.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().choice_id);
        col_course_id_fk1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().course_id);
    }

    public void DisableComponents() {

        Node[] d = {
            btn_save,
            btn_edit,
            btn_update,
            btn_delete,
            txt_course_id,
            txt_course_title,
            txt_course_description,
            txt_number_of_questions,
            txt_course_created_by

        };
        try {
            for (Node c : d) {
                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;

                    m.setDisable(true);
                    m.setText("");
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(true);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(true);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(true);
                }
                if (c instanceof JFXTextArea) {
                    JFXTextArea m4 = (JFXTextArea) c;
                    m4.setDisable(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //TEMPORARY CODE //WILL FIX THE REDUNDANCY
    public void New() {

        Node[] d = {
            btn_save,
            txt_course_title,
            txt_course_description,
            txt_course_created_by

        };
        try {
            for (Node c : d) {

                if (c instanceof JFXTextField) {
                    JFXTextField m = (JFXTextField) c;
                    m.setDisable(false);
                }
                if (c instanceof JFXButton) {
                    JFXButton m1 = (JFXButton) c;
                    m1.setDisable(false);
                }
                if (c instanceof JFXDatePicker) {
                    JFXDatePicker m2 = (JFXDatePicker) c;
                    m2.setDisable(false);
                }
                if (c instanceof JFXComboBox) {
                    JFXComboBox m3 = (JFXComboBox) c;
                    m3.setDisable(false);
                }
                if (c instanceof JFXTextArea) {
                    JFXTextArea m4 = (JFXTextArea) c;
                    m4.setDisable(false);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void SaveCourse() {
        HR2_Courses lm = new HR2_Courses();

        if (txt_course_title.getText().isEmpty() || txt_course_description.getText().isEmpty() || txt_course_created_by.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One or More Fields are empty");
            alert.showAndWait();
        } else {
            try {

                String[][] lm1
                        = {
                            {"course_title", txt_course_title.getText()},
                            {"course_description", txt_course_description.getText()},
                            {"number_of_questions", "0"},
                            {"created_by", txt_course_created_by.getText()}

                        };

                lm.insert(lm1);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Saved");
                saved.showAndWait();
                DisableComponents();
                loadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }

    public void DeleteCourse() {
        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
        delete.setContentText("Are you sure you want to delete this data?");
        Optional<ButtonType> rs = delete.showAndWait();

        System.out.println(rs.get());
        if (rs.get() == ButtonType.OK) {
            System.out.println(tbl_course_data.getSelectionModel().getSelectedItem().course_id.getValue());
            HR2_Courses courses = new HR2_Courses();

            courses.delete().where(new Object[][]{
                {"course_id", "=", tbl_course_data.getSelectionModel().getSelectedItem().course_id.getValue()}
            }).executeUpdate();
            loadData();
            DisableComponents();
        }
    }

    public void UpdateCourse() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to update this data?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            //   System.out.println(tbl_Skills.getSelectionModel().getSelectedItem().Skill_ID.getValue());
            HR2_Courses c = new HR2_Courses();

            Boolean a = c.update(new Object[][]{
                {"course_title", txt_course_title.getText()},
                {"course_description", txt_course_description.getText()},
                {"created_by", txt_course_created_by.getText()}

            }).where(new Object[][]{
                {"course_id", "=", txt_course_id.getText()}
            }).executeUpdate();

            System.out.println(a);
            DisableComponents();
            loadData();
        }
    }

    @FXML
    public void searchExam() {
        HR2_Courses courses = new HR2_Courses();

        try {

            HR2_Courses c = courses;
            List listExams;
            if (txt_search_course.equals("")) {
                listExams = c.get();
            } else {
                listExams = c.where(new Object[][]{
                    {"course_title", "like", "%" + txt_search_course.getText() + "%"}
                }).get();

                tbl_course_data.getItems().clear();

                ObservableList<HR2_CoursesClass> courses_class = FXCollections.observableArrayList();

                for (Object d : listExams) {
                    HashMap hm1 = (HashMap) d;
                    //RS
                    courses_class.add(
                            new HR2_CoursesClass(
                                    String.valueOf(hm1.get("course_id")),
                                    String.valueOf(hm1.get("course_title")),
                                    String.valueOf(hm1.get("course_description")),
                                    String.valueOf(hm1.get("number_of_questions")),
                                    String.valueOf(hm1.get("created_by"))
                            ));

                }
                tbl_course_data.setItems(courses_class);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
