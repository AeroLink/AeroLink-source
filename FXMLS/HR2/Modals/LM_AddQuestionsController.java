/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.HR2_LMClass_For_AddQuestion_Modal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lbl_course_title.setText(HR2_LMClass_For_AddQuestion_Modal.lm_course_title);

        ToggleGroup c = new ToggleGroup();
        rb1.setToggleGroup(c);
        rb2.setToggleGroup(c);
        rb3.setToggleGroup(c);
        rb4.setToggleGroup(c);

        addImage = new FileChooser();
        addImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*"),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jpeg"),
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );

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
