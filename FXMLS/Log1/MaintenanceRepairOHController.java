/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class MaintenanceRepairOHController implements Initializable {
    
    ObservableList<String> numberOfEmployeeNeeded = FXCollections.observableArrayList(
            "1",
            "2",
            "3",
            "4",
            "5");

    private ComboBox<String> howManyEmployees_combox;
    private ComboBox employee_1;
    private ComboBox employee_2;
    private ComboBox employee_3;
    private ComboBox employee_4;
    private ComboBox employee_5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
    }    

    private void numberOfEmployeeAction(ActionEvent event) {
        String num = howManyEmployees_combox.getValue();
        
        if(num=="1"){
            employee_1.setDisable(false);
            employee_2.setDisable(true);
            employee_3.setDisable(true);
            employee_4.setDisable(true);
            employee_5.setDisable(true);
        }else if(num=="2"){
            employee_1.setDisable(false);
            employee_2.setDisable(false);
            employee_3.setDisable(true);
            employee_4.setDisable(true);
            employee_5.setDisable(true);
        }else if(num=="3"){
            employee_1.setDisable(false);
            employee_2.setDisable(false);
            employee_3.setDisable(false);
            employee_4.setDisable(true);
            employee_5.setDisable(true);
        }else if(num=="4"){
            employee_1.setDisable(false);
            employee_2.setDisable(false);
            employee_3.setDisable(false);
            employee_4.setDisable(false);
            employee_5.setDisable(true);
        }else if(num=="5"){
            employee_1.setDisable(false);
            employee_2.setDisable(false);
            employee_3.setDisable(false);
            employee_4.setDisable(false);
            employee_5.setDisable(false);
        }
    }


    
}
