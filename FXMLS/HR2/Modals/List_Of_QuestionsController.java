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
import Synapse.Session;
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
import javafx.scene.control.Label;
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
public class List_Of_QuestionsController implements Initializable {

    @FXML
    private TableView<HR2_AssessmentClass> tbl_questions;
    @FXML
    private TableColumn<HR2_AssessmentClass, String> col_questions;
    @FXML
    private Label lbl_course_title;
    @FXML
    private ContextMenu contextmenu;
    @FXML
    private MenuItem contextmenu_item_drop;

    long DummyCount = 0;
    long GlobalCount = 0;

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

        try {
            HR2_Assessment q = new HR2_Assessment();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("learning_management")) {
                    q.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List questions = q.join(Model.JOIN.INNER, "aerolink.tbl_hr2_courses", "course_id", "c", "=", "course_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "c", "job_id", true)
                                .where(new Object[][]{{"aerolink.tbl_hr4_jobs.title", "=", lbl_course_title.getText(), "AND",
                            "aerolink.tbl_hr2_assessment.isDeleted", "=", "0"}})
                                .get("aerolink.tbl_hr2_assessment.question");
                        Data(questions);

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

    @FXML
    public void ContextMenuDrop(MouseEvent event) {

        if (event.getButton() == MouseButton.SECONDARY) {
            contextmenu.show(tbl_questions, event.getX(), event.getSceneY());
            contextmenu_item_drop.setOnAction(e -> DropQuestion());
        }
    }

    public void DropQuestion() {
        Alert update = new Alert(Alert.AlertType.CONFIRMATION);
        update.setContentText("Are you sure you want to drop this question?");
        Optional<ButtonType> rs = update.showAndWait();

        if (rs.get() == ButtonType.OK) {
            HR2_Assessment c = new HR2_Assessment();

            Boolean a = c.where(new Object[][]{
                {"question", "=", tbl_questions.getSelectionModel().getSelectedItem().question.get()}
            }).update(new Object[][]{
                {"isDeleted", "1"},}).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText("Question Successfully Dropped");
            dropnotif.showAndWait();
            loadData();
        }
    }
}
