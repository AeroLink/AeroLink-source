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
import Synapse.Database;
import Synapse.DB.MYSQL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
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
    private JFXTextField txt_course_description;
    @FXML
    private JFXTextField txt_course_created_by;
    @FXML
    private JFXTextField txt_number_of_questions;
    @FXML
    private TableView<HR2_CoursesClass> tbl_course_data;
    @FXML
    private TableView<HR2_AssessmentClass> tbl_questions_data;
    @FXML
    private TableView<HR2_EvaluationClass> tbl_choices_data;
    @FXML
    private MenuItem item_add_new_question;
    @FXML
    private MenuItem menu_add_new_course;
    @FXML
    private MenuItem menu_save_new_course;
    @FXML
    private MenuItem menu_edit_course;
    @FXML
    private MenuItem menu_update_course;
    @FXML
    private MenuItem menu_delete_course;
    @FXML
    private MenuItem item_save_new_question;
    @FXML
    private MenuItem item_edit_question;
    @FXML
    private MenuItem item_update_question;
    @FXML
    private MenuItem item_delete_question;
    @FXML
    private MenuItem menu_add_new_choice;
    @FXML
    private MenuItem menu_save_new_choice;
    @FXML
    private MenuItem menu_edit_choice;
    @FXML
    private MenuItem menu_update_choice;
    @FXML
    private MenuItem menu_delete_choice;
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
    private TableColumn<HR2_AssessmentClass, String> col_question_number;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_question;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_choice_id_fk;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_course_id_fk1;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice_id_pk;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_question_fk;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice_description;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_is_checked;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Database d = Database.getInstance();
        d.DB_INIT(new MYSQL());
        d.startConnection();

        DisableComponents();

        //for course section
        menu_add_new_course.setOnAction(e -> New());
        menu_save_new_course.setOnAction(e -> SaveCourse());
        //for assessmenet section
        item_add_new_question.setOnAction(e -> Open_LM_Questions());

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
                hm1.get("question_number");
                hm1.get("question");
                hm1.get("choice_id");
                hm1.get("course_id");

                assessmentclass.add(
                        new HR2_AssessmentClass(
                                String.valueOf(hm1.get("question_id")),
                                String.valueOf(hm1.get("question_number")),
                                String.valueOf(hm1.get("question")),
                                String.valueOf(hm1.get("choice_id")),
                                String.valueOf(hm1.get("course_id"))
                        ));

            }

            tbl_questions_data.setItems(assessmentclass);

            HR2_Evaluation evaluation = new HR2_Evaluation();

            ObservableList<HR2_EvaluationClass> evaluationclass = FXCollections.observableArrayList();
            List c1 = evaluation.get();

            for (Object d1 : c1) {
                HashMap hm2 = (HashMap) d1;
                //RS
                hm2.get("choice_id");
                hm2.get("question_id");
                hm2.get("choice");
                hm2.get("choice_description");
                hm2.get("ischecked");

                evaluationclass.add(
                        new HR2_EvaluationClass(
                                String.valueOf(hm2.get("choice_id")),
                                String.valueOf(hm2.get("question_id")),
                                String.valueOf(hm2.get("choice")),
                                String.valueOf(hm2.get("choice_description")),
                                String.valueOf(hm2.get("ischecked"))
                        ));

            }

            tbl_choices_data.setItems(evaluationclass);

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
        col_question_number.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().question_number);
        col_question.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().question);
        col_choice_id_fk.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().choice_id);
        col_course_id_fk1.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().course_id);
        //Evaluation
        col_choice_id_pk.setCellValueFactory((TableColumn.CellDataFeatures<HR2_EvaluationClass, String> param) -> param.getValue().choice_id);
        col_question_fk.setCellValueFactory((TableColumn.CellDataFeatures<HR2_EvaluationClass, String> param) -> param.getValue().question_id);
        col_choice.setCellValueFactory((TableColumn.CellDataFeatures<HR2_EvaluationClass, String> param) -> param.getValue().choice);
        col_choice_description.setCellValueFactory((TableColumn.CellDataFeatures<HR2_EvaluationClass, String> param) -> param.getValue().choice_description);
        col_is_checked.setCellValueFactory((TableColumn.CellDataFeatures<HR2_EvaluationClass, String> param) -> param.getValue().ischecked);

    }

    public void Open_LM_Questions() {

        try {
            Parent p = FXMLLoader.load(getClass().getResource("/FXMLS/HR2/Modals/HR2_LM_Questions.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void DisableComponents() {

        Node[] d = {
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
                    /*        validations v = new validations();
                                                         v.maximumChars(m, 25);
                                                         Alert alert = new Alert(Alert.AlertType.ERROR);
                                                                         alert.initStyle(StageStyle.UNDECORATED);
                                                                         alert.setTitle("Error");
                                                                         alert.setContentText("Maximum 25 Character only"); 
                                                                         alert.showAndWait();     */
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

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //TEMPORARY CODE //WILL FIX THE REDUNDANCY
    public void New() {

        Node[] d = {
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
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void SaveCourse() {
        HR2_Courses lm = new HR2_Courses();

        try {

            String[][] lm1
                    = {
                        {"course_title", txt_course_title.getText()},
                        {"course_description", txt_course_description.getText()},
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
