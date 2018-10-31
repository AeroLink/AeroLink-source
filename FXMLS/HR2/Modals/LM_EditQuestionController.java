/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_LM_EditQuestion_for_Modal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class LM_EditQuestionController implements Initializable {

    @FXML
    private AnchorPane AnchorPane1;
    @FXML
    private Label lbl_course_title;
    @FXML
    private JFXTextArea txt_edit_question;
    @FXML
    private JFXButton btn_update_question;
    @FXML
    private JFXRadioButton edit_rb1;
    @FXML
    private JFXTextField edit_txt_option1;
    @FXML
    private JFXRadioButton edit_rb2;
    @FXML
    private JFXTextField edit_txt_option2;
    @FXML
    private JFXRadioButton edit_rb3;
    @FXML
    private JFXTextField edit_txt_option3;
    @FXML
    private JFXRadioButton edit_rb4;
    @FXML
    private JFXTextField edit_txt_option4;
    @FXML
    private JFXButton edit_browse1;
    @FXML
    private JFXButton edit_browse2;
    @FXML
    private JFXButton edit_browse3;
    @FXML
    private JFXButton edit_browse4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_course_title.setText(HR2_LM_EditQuestion_for_Modal.c);
        txt_edit_question.setText(HR2_LM_EditQuestion_for_Modal.q);

        ToggleGroup c = new ToggleGroup();
        edit_rb1.setToggleGroup(c);
        edit_rb2.setToggleGroup(c);
        edit_rb3.setToggleGroup(c);
        edit_rb4.setToggleGroup(c);
        edit_txt_option1.setText(String.valueOf(HR2_LM_EditQuestion_for_Modal.choice_description.get("a")));
        edit_txt_option2.setText(String.valueOf(HR2_LM_EditQuestion_for_Modal.choice_description.get("b")));
        edit_txt_option3.setText(String.valueOf(HR2_LM_EditQuestion_for_Modal.choice_description.get("c")));
        edit_txt_option4.setText(String.valueOf(HR2_LM_EditQuestion_for_Modal.choice_description.get("d")));
        
        
        if(HR2_LM_EditQuestion_for_Modal.choice_description.get("d") == null)
        {
            edit_txt_option4.setText("");
        }

    }

    @FXML
    private void OpenFile1(ActionEvent event) {
    }

    @FXML
    private void OpenFile2(ActionEvent event) {
    }

    @FXML
    private void OpenFile3(ActionEvent event) {
    }

    @FXML
    private void OpenFile4(ActionEvent event) {
    }

}
