/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Legal_ManagementController implements Initializable {

    @FXML
    private TableView<?> appointmenttable;
    @FXML
    private TableColumn<?, ?> appointmentid;
    @FXML
    private TableColumn<?, ?> appointmentname;
    @FXML
    private TableColumn<?, ?> appointmenttime;
    @FXML
    private TableColumn<?, ?> appointmentdate;
    @FXML
    private TableColumn<?, ?> appointmentptm;
    @FXML
    private JFXTextField schedfn;
    @FXML
    private JFXDatePicker scheddate;
    @FXML
    private JFXDatePicker scheddate1;
    @FXML
    private JFXButton schedsave;
    @FXML
    private JFXButton schedsave1;
    @FXML
    private JFXTextField schedfn1;
    @FXML
    private JFXButton schedsave2;
    @FXML
    private JFXButton randg;
    @FXML
    private StackPane stackpane;
     AlertBox ab = new AlertBox();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AlertBox.tooltip("Rules and Regulations", randg);
    }    

   public void loadcomplainfxml() throws IOException{
       ab.fxmlloader("Admin_Complains.fxml", stackpane);
   }
   
   public void loadschedulingfxml() throws IOException{
      ab.fxmlloader("Admin_Scheduling.fxml", stackpane);
   }
   
   public void laodblacklistfxml() throws IOException{
      ab.fxmlloader("Admin_Blacklist.fxml", stackpane);
   }

   
    
}
