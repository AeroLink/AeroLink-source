/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_AssessmentClass;
import FXMLS.HR2.ClassFiles.HR2_EvaluationClass;
import Model.HR2_Assessment;
import Model.HR2_Evaluation;
import java.awt.Checkbox;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
public class HR2_View_ChoicesController implements Initializable {

    @FXML
    private TableView<HR2_EvaluationClass> tbl_choices;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice;
    @FXML
    private TableColumn<HR2_EvaluationClass, String> col_choice_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        DisplayDataInJTable();
    }

    public void loadData() {

        HR2_Evaluation c = new HR2_Evaluation();

        List choices = c.get();
        Data(choices);

    }

    public void Data(List b) {
        ObservableList<HR2_EvaluationClass> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b) {

                HashMap hm = (HashMap) d;
                System.out.println(hm);
                obj.add(
                        new HR2_EvaluationClass(
                                String.valueOf(hm.get("choice_id")),
                                String.valueOf(hm.get("question_id")),
                                String.valueOf(hm.get("choice")),
                                String.valueOf(hm.get("choice_description")),
                                String.valueOf(hm.get("ischecked"))));

            }
            tbl_choices.setItems(obj);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DisplayDataInJTable() {

        col_choice.setCellValueFactory(param -> param.getValue().choice);
        col_choice_description.setCellValueFactory(param -> param.getValue().choice_description);

    }
}
