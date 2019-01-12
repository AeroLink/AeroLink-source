/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_LMClass_For_AddQuestion_Modal;
import Model.HR2_Assessment;
import Model.HR2_Evaluation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class LM_AddQuestionsController implements Initializable {

    private FileChooser addImage;
    private File file;
    private Stage stage;
    @FXML
    private JFXTextArea txt_add_question;
    @FXML
    private JFXTextField txt_option1;
    @FXML
    private JFXTextField txt_option2;
    @FXML
    private JFXTextField txt_option3;
    @FXML
    private JFXTextField txt_option4;
    @FXML
    private JFXButton browse1;
    @FXML
    private JFXButton browse2;
    @FXML
    private JFXButton browse3;
    @FXML
    private JFXButton browse4;
    @FXML
    private JFXButton btn_add_question;
    @FXML
    private AnchorPane AnchorPane1;
    @FXML
    private JFXRadioButton rb1;
    @FXML
    private JFXRadioButton rb2;
    @FXML
    private JFXRadioButton rb3;
    @FXML
    private JFXRadioButton rb4;
    @FXML
    private Label lbl_course_title;
    ToggleGroup c = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lbl_course_title.setText("EXAM00" + HR2_LMClass_For_AddQuestion_Modal.exam_id + " - " + HR2_LMClass_For_AddQuestion_Modal.exam_name);
        rb1.setToggleGroup(c);
        rb2.setToggleGroup(c);
        rb3.setToggleGroup(c);
        rb4.setToggleGroup(c);

        addImage = new FileChooser();
        addImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jpeg")
        //    new FileChooser.ExtensionFilter("Text File", "*.txt")
        );

    }

    @FXML
    public void AddQuestion() {

        Node[] co = {txt_option1, txt_option2, txt_option3, txt_option4};
        JFXRadioButton[] rdo = {rb1, rb2, rb3, rb4};
        HR2_Evaluation courses = new HR2_Evaluation();
        String[] lm = {"a", "b", "c", "d"};

        JFXRadioButton rx = (JFXRadioButton) c.getSelectedToggle();
        List id_list = new ArrayList<>();
        int QuestID = 0;
        try {
            for (int i = 0; i < lm.length; i++) {
                String[][] cm_data
                        = {
                            {"choice", lm[i]},
                            {"choice_description", ((JFXTextField) co[i]).getText()},
                            {"ischecked", (rdo[i].isSelected() ? "1" : "0")}
                        };

                int al = courses.insert(cm_data, true);
                id_list.add(al);
                if (rdo[i].isSelected()) {
                    QuestID = new HR2_Assessment().insert(new String[][]{
                        {"question", txt_add_question.getText()},
                        {"exam_id", lbl_course_title.getText().toString().substring(6).toString().split(" - ")[0]},
                        {"choice_id", String.valueOf(al)},
                        {"isDeleted", "0"}
                }, true);
            }
            //     System.err.println(String.valueOf(rdo[i].isSelected()));

            for (Object obj : id_list) {
                courses.update(new Object[][]{
                    {"question_id", QuestID}
                }).where(new Object[][]{
                    {"choice_id", "=", obj}
                }).executeUpdate();
            }

            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Question Added");
            saved.showAndWait();

            /*  if (rdo[i].isSelected()) {

                    Alert saved = new Alert(Alert.AlertType.INFORMATION);
                    saved.setContentText("Question Added");
                    saved.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please Select correct answer for this question");
                    alert.showAndWait();
                }*/
        }
    }
    catch (Exception e

    
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("ERROR" + e);
        alert.showAndWait();
    }
}

@FXML
        public void OpenFile1() {
        stage = (Stage) AnchorPane1.getScene().getWindow();
        file = addImage.showOpenDialog(stage);
        addImage.setInitialDirectory(new File("C:\\Users\\EdenRamoneda\\Documents\\NetBeansProjects\\Staging\\src\\FXMLS\\HR2\\Images"));
        if (file != null) {
            txt_option1.setText(file.getAbsolutePath());
        } else {
            Alert n = new Alert(Alert.AlertType.ERROR);
            n.setContentText("File not valid! Please Select .png .jpg .gif .jpeg or .txt file");
            n.showAndWait();
        }
    }

    @FXML
        public void OpenFile2() {
        stage = (Stage) AnchorPane1.getScene().getWindow();
        file = addImage.showOpenDialog(stage);
        addImage.setInitialDirectory(new File("C:\\Users\\EdenRamoneda\\Documents\\NetBeansProjects\\Staging\\src\\FXMLS\\HR2\\Images"));
        if (file != null) {
            txt_option2.setText(file.getAbsolutePath());
        } else {
            Alert n = new Alert(Alert.AlertType.ERROR);
            n.setContentText("File not valid! Please Select .png .jpg .gif .jpeg or .txt file");
            n.showAndWait();
        }
    }

    @FXML
        public void OpenFile3() {
        stage = (Stage) AnchorPane1.getScene().getWindow();
        file = addImage.showOpenDialog(stage);
        addImage.setInitialDirectory(new File("C:\\Users\\EdenRamoneda\\Documents\\NetBeansProjects\\Staging\\src\\FXMLS\\HR2\\Images"));
        if (file != null) {
            txt_option3.setText(file.getAbsolutePath());
        } else {
            Alert n = new Alert(Alert.AlertType.ERROR);
            n.setContentText("File not valid! Please Select .png .jpg .gif .jpeg or .txt file");
            n.showAndWait();
        }
    }

    @FXML
        public void OpenFile4() {
        stage = (Stage) AnchorPane1.getScene().getWindow();
        file = addImage.showOpenDialog(stage);
        addImage.setInitialDirectory(new File("C:\\Users\\EdenRamoneda\\Documents\\NetBeansProjects\\Staging\\src\\FXMLS\\HR2\\Images"));
        if (file != null) {
            txt_option4.setText(file.getAbsolutePath());
        } else {
            Alert n = new Alert(Alert.AlertType.ERROR);
            n.setContentText("File not valid! Please Select .png .jpg .gif .jpeg or .txt file");
            n.showAndWait();
        }
    }

}
