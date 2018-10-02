/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_NewHireClass;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_viewNewHireController implements Initializable {

    private String empCode;

    @FXML
    private FontAwesomeIconView jobID;
    @FXML
    private Label lblAppFull;
    @FXML
    private MenuItem menuHiring;
    @FXML
    private MenuItem menuDeny;
    @FXML
    private TitledPane jobTitle;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPlace;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtCivilStatus;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private JFXButton btn0;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn6;
    @FXML
    private JFXButton btn5;
    @FXML
    private JFXButton btn4;
    @FXML
    private JFXButton btn1;
    @FXML
    private JFXButton btn2;
    @FXML
    private StackPane spane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(HR1_NewHireClass.jobtitle);
        this.jobTitle.setText("Job :" + HR1_NewHireClass.jobtitle);
        this.txtDate.setText(HR1_NewHireClass.DateOfBirth);
        this.txtPlace.setText(HR1_NewHireClass.PlaceOfBirth);
        this.txtGender.setText(HR1_NewHireClass.Gender);
        this.txtCivilStatus.setText(HR1_NewHireClass.CivilStatus);
        this.txtEmail.setText(HR1_NewHireClass.Email);
        this.txtHeight.setText(HR1_NewHireClass.Height);
        this.txtWeight.setText(HR1_NewHireClass.Weight);
        this.txtContactNumber.setText(HR1_NewHireClass.ContactNumber);
    }

    public JFXDialog viewDialog(String title, VBox Content, Node[] buttons) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(title));
        layout.setBody(Content);
        
        layout.setActions(buttons);
        
        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);
        return dialog;
    }

    @FXML
    private void btnDownloadCV(ActionEvent event) {
    }

    @FXML
    private void viewFamilyBackGround(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewFB.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewTrainingSem(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewTSM.fxml").getParent());
        md.open();
    }

    @FXML
    private void viewAcademicAwards(ActionEvent event) {
    }

    @FXML
    private void viewCertification(ActionEvent event) {
    }

    @FXML
    private void viewWorkExp(ActionEvent event) {
    }

    @FXML
    private void viewEducAttain(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR1/Modals/HR1_viewEducAttain.fxml").getParent());
        md.open();
    }

}
