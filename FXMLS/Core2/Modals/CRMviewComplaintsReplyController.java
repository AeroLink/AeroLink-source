/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Modals;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class CRMviewComplaintsReplyController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static String name = "";
    public static String email = "";
    public static String comment = "";
    
    public static Boolean modalOpen = true;
    @FXML
    private StackPane sp;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private TextArea txtAquestion;
    @FXML
    private TextArea txtAReply;
    @FXML
    private JFXButton btnSubmit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblEmail.setText(email);
        lblName.setText(name);
        txtAquestion.setText(comment);
    }    

}
