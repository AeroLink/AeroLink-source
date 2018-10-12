/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class LM_AddQuestionsController implements Initializable {

    private FileChooser addImage;
    private File file;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
        addImage = new FileChooser();
        addImage.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "* "),
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text File", "*.txt", "*.jpg", "*.gif")
        );

    }

}
