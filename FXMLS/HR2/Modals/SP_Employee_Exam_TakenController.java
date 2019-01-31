/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR4_Jobs_Class;
import FXMLS.HR2.ClassFiles.SP_Employee_Info_Modal;
import FXMLS.HR2.ClassFiles.SP_ExamTakenClass;
import Model.HR2_SP_ExamTaken;
import Synapse.Model;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
public class SP_Employee_Exam_TakenController implements Initializable {

    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_position;
    @FXML
    private TableView<SP_ExamTakenClass> tbl_exam_taken;
    @FXML
    private TableColumn<SP_ExamTakenClass, String> col_exam_taken;
    @FXML
    private TableColumn<SP_ExamTakenClass, String> col_score;
    @FXML
    private Label lbl_count;
    @FXML
    private Label lbl_emp_code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_emp_code.setText(SP_Employee_Info_Modal.employee_code);
        lbl_name.setText(SP_Employee_Info_Modal.fullname);
        lbl_position.setText(SP_Employee_Info_Modal.title);

        int count_et = tbl_exam_taken.getItems().size();
        lbl_count.setText(String.valueOf(count_et));
        DisplayExamTaken();
    }

    public void DisplayExamTaken() {
        try {
            HR2_SP_ExamTaken exam_taken = new HR2_SP_ExamTaken();
            List et = exam_taken.join(Model.JOIN.INNER, "aerolink.tbl_hr4_employee_profiles", "employee_code", "emp", "=", "employee_code")
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr2_examination", "exam_id", "e", "=", "exam_id")
                    .where(new Object[][]{{"emp.employee_code","=",SP_Employee_Info_Modal.employee_code}})
                    .get();

            ObservableList<SP_ExamTakenClass> emp_exam = FXCollections.observableArrayList();
            emp_exam.clear();

            for (Object d : et) {
                HashMap hm1 = (HashMap) d;

                emp_exam.add(
                        new SP_ExamTakenClass(
                                String.valueOf(hm1.get("eer_id")),
                                String.valueOf(hm1.get("exam_name")),
                                String.valueOf(hm1.get("score"))
                        ));
            }

            tbl_exam_taken.setItems(emp_exam);

            col_exam_taken.setCellValueFactory((TableColumn.CellDataFeatures<SP_ExamTakenClass, String> param) -> param.getValue().exam_name);
            col_score.setCellValueFactory((TableColumn.CellDataFeatures<SP_ExamTakenClass, String> param) -> param.getValue().score);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
