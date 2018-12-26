/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_CoursesClass;
import FXMLS.HR2.ClassFiles.HR2_LM_CourseOutlineModal;
import Model.HR2_CourseOutline;
import Model.HR2_Courses;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class LM_CourseOutlineController implements Initializable {

    @FXML
    private Label lbl_job_title;
    @FXML
    private JFXButton btn_browse;
    @FXML
    private JFXTextField txt_path;
    @FXML
    private TableView<HR2_LM_CourseOutlineModal> tbl_files;
    @FXML
    private TableColumn<HR2_LM_CourseOutlineModal, String> col_file;

    long DummyCount = 0;
    long GlobalCount = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_job_title.setText(HR2_LM_CourseOutlineModal.jid);
        DisplayFilesInTable();
        FilesTable();
    }

    public void DisplayFilesInTable() {

           try {
            //tbl courses
            HR2_Courses c = new HR2_Courses();

            CompletableFuture.supplyAsync(() -> {
                while (Session.CurrentRoute.equals("learning_management")) {
                    c.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        List courses = c.join(Model.JOIN.INNER, "aerolink.tbl_hr2_course_outline", "course_id", "co", "=", "course_id")
                                .join(Model.JOIN.INNER, "aerolink.tbl_hr4_jobs", "job_id", "j", "=", "job_id")
                                .where(new Object[][]{{"j.title", "=", lbl_job_title.getText()},{"aerolink.tbl_hr2_courses.isDeleted", "<>", "1"}})
                                .get("co.uploaded_file");
                        CourseOutline(courses);

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

    public void CourseOutline(List co) {
        ObservableList<HR2_LM_CourseOutlineModal> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : co) {

                HashMap hm = (HashMap) d;
                obj.add(
                        new HR2_LM_CourseOutlineModal(
                                String.valueOf(hm.get("uploaded_file"))));

            }
            tbl_files.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void FilesTable(){
          col_file.setCellValueFactory((TableColumn.CellDataFeatures<HR2_LM_CourseOutlineModal, String> param) -> param.getValue().files);
    }
}
