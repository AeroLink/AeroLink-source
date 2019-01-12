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
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Cell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    //excel class
    private static Workbook wb;
    private static Sheet s;
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static Row r;

    private static String[] columns = {"Name", "Email", "Date Of Birth", "Salary"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_course_title.setText("EXAM00" + HR2_LMClass_For_AddQuestion_Modal.exam_id + " - " + HR2_LMClass_For_AddQuestion_Modal.exam_name);
        loadData();
        DisplayDataInJTable();

        //For Excel File;
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

                        List questions = q.join(Model.JOIN.INNER, "aerolink.tbl_hr2_examination", "exam_id", "e", "=", "exam_id")
                            //    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "=", "c", "job_id", true)
                                .where(new Object[][]{{"e.exam_name", "=", HR2_LMClass_For_AddQuestion_Modal.exam_name},
                        {"aerolink.tbl_hr2_assessment.isDeleted", "=", "0"}})
                                .get("aerolink.tbl_hr2_assessment.question_id,aerolink.tbl_hr2_assessment.question");
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
                                tbl_questions.getItems().get(getIndex());
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
                {"question_id", "=", tbl_questions.getSelectionModel().getSelectedItem().question_id.get()}
            }).update(new Object[][]{
                {"isDeleted", "1"},}).executeUpdate();
            Alert dropnotif = new Alert(Alert.AlertType.INFORMATION);
            dropnotif.setContentText("Question Successfully Dropped");
            dropnotif.showAndWait();
            loadData();
        }
    }

    public void importCSV() {

        List<String> importCSV = new ArrayList<>();

        try {
            importCSV = Files.readAllLines(Paths.get("C:\\Users\\EdenRamoneda\\Desktop\\sample.csv"));
            for (String ls : importCSV) {
                ls = ls.replace("\'", "");

                String[] r = ls.split(",");
                for (String s : r) {
                    System.out.print(s + " - ");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(List_Of_QuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void exportCSV() {

        try {
            /*   fis = new FileInputStream("./testdata.xlsx");
            wb = WorkbookFactory.create(fis);
            s = wb.getSheet("Sheet1");
            int noOfRows = s.getLastRowNum();
            System.out.println(noOfRows);
            
            r = s.createRow(1);
            c = r.createCell(0);
            c.setCellValue("CAV");
            System.out.println(c.getStringCellValue());
            fos = new FileOutputStream("./testdata.xlsx");
            wb.write(fos);
            fos.flush();
            fos.close();
            System.out.println("Done");*/

            // Formatter f = new Formatter("C:\\Users\\EdenRamoneda\\Desktop\\template.xlsx");
            /*   XSSFWorkbook workbook =pp new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Java Books");
            fos = new FileOutputStream("C:\\\\Users\\\\EdenRamoneda\\\\Desktop\\\\ss.xlsx");
            workbook.write(fos);
            Alert n = new Alert(Alert.AlertType.INFORMATION);
            n.setContentText("TEMPLATE DOWNLOADED! Go to your Desktop you will see the name template.xlsx");
            n.showAndWait();*/
            XSSFWorkbook w = new XSSFWorkbook();
            FileOutputStream out = new FileOutputStream(new File("c:/demo.xlsx"));
            XSSFSheet sp = w.createSheet("D");
            w.write(out);
            out.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
