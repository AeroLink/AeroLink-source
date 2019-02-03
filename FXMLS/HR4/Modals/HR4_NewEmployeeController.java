/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import Synapse.RAW;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BlackMoon
 */
public class HR4_NewEmployeeController implements Initializable {

    @FXML
    private JFXButton btnUpload;

    FileChooser fileChooser = new FileChooser();
    @FXML
    private ImageView employee_image;
    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtlname;
    @FXML
    private TextField txtmname;
    @FXML
    private ComboBox<String> cboSuffix;
    @FXML
    private DatePicker dtpBirth;
    @FXML
    private TextField txtPlaceOfBirth;
    @FXML
    private ComboBox<String> cboGender;
    @FXML
    private ComboBox<String> cboCivilStatus;
    @FXML
    private TextField txtHeight;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtAddress;
    @FXML
    private ComboBox<String> cboJob;
    @FXML
    private TextField txtSalary;
    @FXML
    private ComboBox<String> cboType;
    @FXML
    private ComboBox<String> cboStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        cboGender.getItems().addAll("Male", "Female");
        cboGender.getSelectionModel().selectFirst();
        
        new RAW("tbl_hr1_suffix").get().stream().forEach(action -> {
            cboSuffix.getItems().add( "SFX0" + ( (HashMap) action ).get("id").toString() + " - " + ( (HashMap) action ).get("suffix_name").toString() );
        });
        
        cboSuffix.getSelectionModel().selectFirst();
        
        new RAW("tbl_hr1_civil_status").get().stream().forEach(action -> {
            cboCivilStatus.getItems().add( "CV0" + ( (HashMap) action ).get("id").toString() + " - " + ( (HashMap) action ).get("civil_status").toString() );
        });
        
        cboCivilStatus.getSelectionModel().selectFirst();
        
        new RAW("tbl_hr4_employeeTypes").get().stream().forEach(action -> {
            cboType.getItems().add( "TY0" + ( (HashMap) action ).get("type_id").toString() + " - " + ( (HashMap) action ).get("type_name").toString() );
        });
        
        cboType.getSelectionModel().selectFirst();
        
        new RAW("tbl_hr4_employeeStatus").get().stream().forEach(action -> {
            cboStatus.getItems().add( "STATS0" + ( (HashMap) action ).get("status_id").toString() + " - " + ( (HashMap) action ).get("status_name").toString() );
        });
        
        cboStatus.getSelectionModel().selectFirst();
        
        new Model.HR4_Jobs().get().stream().forEach(action -> {
            cboJob.getItems().add( "JO" + ( (HashMap) action ).get("job_id").toString() + " - " + ( (HashMap) action ).get("title").toString() );
        });
        
        cboJob.getSelectionModel().selectFirst();
        
        btnUpload.setOnAction(e -> {
            setExtFilters(fileChooser);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                employee_image.setFitWidth(168);
                employee_image.setFitHeight(134);
                employee_image.setPreserveRatio(true);
                employee_image.setImage(image);
                employee_image.setSmooth(true);
                employee_image.setCache(true);
            }
        });
    }

    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

}
