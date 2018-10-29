/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_AssessmentClass;
import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_LMClass_For_AddQuestion_Modal;
import Model.HR2_Assessment;
import Model.HR2_Courses;
import Synapse.Components.Modal.Modal;
import Synapse.Model;
import Synapse.Form;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class List_Of_QuestionsController implements Initializable {

    @FXML
    private TableView<HR2_AssessmentClass> tbl_questions;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_questions;
    @FXML
    private Label lbl_course_title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_course_title.setText(HR2_LMClass_For_AddQuestion_Modal.lm_course_title);
        loadData();
        DisplayDataInJTable();
    }

    public void loadData() {
        HR2_Assessment q = new HR2_Assessment();
        List questions = q.join(Model.JOIN.INNER, "aerolink.tbl_hr2_courses", "course_id", "c", "=", "course_id")
                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "c", "job_id", true)
                .where(new Object[][]{{"aerolink.tbl_hr4_jobs.title", "=", lbl_course_title.getText()}})
                .get("aerolink.tbl_hr2_assessment.question");
        Data(questions);

    }

    public void Data(List b) {
        ObservableList<HR2_AssessmentClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b) {

                HashMap hm = (HashMap) d;
                System.out.println(hm);
                obj.add(
                        new HR2_AssessmentClass(
                                String.valueOf(hm.get("question_id")),
                                String.valueOf(hm.get("question"))));
                //     String.valueOf(hm.get("choice_id")),
                //     String.valueOf(hm.get("course_id"))));

            }
            tbl_questions.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInJTable() {

        col_questions.setCellValueFactory((TableColumn.CellDataFeatures<HR2_AssessmentClass, String> param) -> param.getValue().question);
        TableColumn<HR2_AssessmentClass, Void> addButton = new TableColumn("View Choices");

        Callback<TableColumn<HR2_AssessmentClass, Void>, TableCell<HR2_AssessmentClass, Void>> cellFactory
                = new Callback<TableColumn<HR2_AssessmentClass, Void>, TableCell<HR2_AssessmentClass, Void>>() {
            @Override
            public TableCell<HR2_AssessmentClass, Void> call(final TableColumn<HR2_AssessmentClass, Void> param) {

                final TableCell<HR2_AssessmentClass, Void> cell = new TableCell<HR2_AssessmentClass, Void>() {
                    private final Button btn = new Button("Choices");

                    {
                        try {
                            btn.setOnAction(e
                                    -> {

                                ViewChoices();
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
        tbl_questions.getColumns().add(addButton);
    }

    public void ViewChoices() {
        HR2_LMClass_For_AddQuestion_Modal.initCourseQuestion(tbl_questions.getSelectionModel().getSelectedItem().question.get());
        Modal choices = Modal.getInstance(new Form("/FXMLS/HR2/Modals/HR2_View_Choices.fxml").getParent());
        choices.open();
    }
}
