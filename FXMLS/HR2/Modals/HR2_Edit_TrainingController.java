/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_Edit_TrainingController implements Initializable {

    @FXML
    public JFXTextField job_position;
    @FXML
    public JFXTextField training_title;
    @FXML
    public JFXTextArea training_desc;
    @FXML
    public JFXTextField trainor;
    @FXML
    public JFXDatePicker start_date;
    @FXML
    public JFXDatePicker end_date;
    @FXML
    public JFXTextField start_time;
    @FXML
    public JFXTextField end_time;
    @FXML
    public JFXComboBox<String> type_of_training;
    @FXML
    public JFXTextField location;
    @FXML
    public JFXTextField vehicle;
    @FXML
    public JFXTextField budget_cost;
    @FXML
    public JFXButton btn_update;
    @FXML
    private JFXTextField training_code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setData(String _training_id, String _job_position, String _training_title,
            String _training_description, String _trainor, String _start_date, String _end_date, String _start_time, String _end_time,
            String _type_of_training, String _location, String _vehicle, String _budget_cost) {
        try {
            training_code.setText(_training_id);
            job_position.setText(_job_position);
            training_title.setText(_training_title);
            training_desc.setText(_training_description);
            trainor.setText(_trainor);
            start_date.getEditor().setText(_start_date);
            end_date.getEditor().setText(_end_date);
            trainor.setText(_trainor);
            start_time.setText(_start_time);
            end_time.setText(_end_time);
            type_of_training.setValue(_type_of_training);
            location.setText(_location);
            vehicle.setText(_vehicle);
            budget_cost.setText(_budget_cost);

        } catch (Exception e) {
            System.out.println(e);
            printStackTrace();
        }

    }

}
