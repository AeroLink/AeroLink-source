/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementController implements Initializable {

    @FXML
    private JFXComboBox<String> item_type;

    @FXML
    private JFXButton btn_save;
    
    
    
    // Item Type ... items
      ObservableList<String> it = FXCollections.observableArrayList("Fan" , "Table" , "Chair");
    @FXML
    private TableView<?> im_tb;
    @FXML
    private JFXTextField txt_transacno;
    @FXML
    private JFXTextField txt_personnelincharge;
    @FXML
    private JFXDatePicker date_recieved;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // combobox
        item_type.setItems(it);
        item_type.setPromptText("Choose Item type");
        DisableComponents();
        
        btn_save.setOnMouseClicked(e -> Save());
       
        
    }
        
        public void DisableComponents(){
            
        
        
    }    
     
        public void Save(){
            
        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "Message", "Ginagawamue","");
            alert.open();    
       
        }
}
