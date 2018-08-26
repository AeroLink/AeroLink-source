/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.icons.icon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class CustomerRelationshipManagementController implements Initializable {

    ObservableList<String> listInsight = FXCollections.observableArrayList("weekly","monthly","Yearly");
    ObservableList<String> listc1 = FXCollections.observableArrayList("Singapore","Philippines");
    ObservableList<String> listc2 = FXCollections.observableArrayList("Philippines","Singapore");
    ObservableList<String> listt1 = FXCollections.observableArrayList("Small Box","Large Box","Envelope");
    ObservableList<String> lists1 = FXCollections.observableArrayList("Pending","Delivered","Delay");
    
    @FXML
    private ComboBox insight;
    @FXML
    private ComboBox c1;
    @FXML
    private ComboBox c2;
    @FXML
    private ComboBox t1;
    @FXML
    private ComboBox s1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        insight.setItems(listInsight);
        c1.setItems(listc1);
        c2.setItems(listc2);
        t1.setItems(listt1);
        s1.setItems(lists1);
        //insight.setValue("jpx");
    }    

    @FXML
    private void another(ActionEvent event) {
        loadWindow("/FXMLS/Core2/crm/CRMtbLocal.fxml","Local");
    }
    
    void loadWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            icon.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(CustomerRelationshipManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
