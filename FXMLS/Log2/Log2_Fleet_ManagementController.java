/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.HR2.ClassFiles.Training_ManagementClass;
import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementClass;
import Model.HR2_Training_Management;
import Model.Log2_Fleet_Management;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Randelle
 */


public class Log2_Fleet_ManagementController implements Initializable {
Connection conn = null;
    
    @FXML
    private JFXComboBox<String> item_type;

    @FXML
    private JFXButton btn_save;
    
    
    
    // Item Type ... items
      ObservableList<String> it = FXCollections.observableArrayList("Fan" , "Table" , "Chair");
    @FXML
    private TableView<Log2_Fleet_ManagementClass> im_tb;
    @FXML
    private JFXTextField txt_transacno;
    @FXML
    private JFXTextField txt_personnelincharge;
    @FXML
    private JFXDatePicker date_recieved;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> transacno;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> itemtype;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> personnelincharge;
    @FXML
    private TableColumn<Log2_Fleet_ManagementClass, String> daterecieved;
    
    ObservableList<Table> tablelist = FXCollections.observableArrayList();
    
    
    
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
// combobox
        item_type.setItems(it);
        item_type.setPromptText("Choose Item type");
       
        
        btn_save.setOnMouseClicked(e -> Save());
        
        loadData();
        
  
       
       
 }
    
    
    private void loadData()
    {
           
        Log2_Fleet_Management fm = new Log2_Fleet_Management();
        
        ObservableList<Log2_Fleet_ManagementClass> tablelist = FXCollections.observableArrayList();
            List b = fm.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;
                
                hm.get("transaction_no");
                hm.get("item_type");
                hm.get("personnel_in_charge");
                hm.get("date_recieved");
               
                
               tablelist.add(
                    new Log2_Fleet_ManagementClass(
                            String.valueOf(hm.get("transaction_no")),
                            String.valueOf(hm.get("item_type")),
                            String.valueOf(hm.get("personnel_in_charge")),
                            String.valueOf(hm.get("date_recieved"))
                         

                    ) );   
            }
            im_tb.setItems(tablelist);
    }

           
    public void Save(){
        
   }
}
