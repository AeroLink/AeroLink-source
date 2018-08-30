/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.Controllers.ServiceNetworkTableController;
import Model.C2_Servicenetwork;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class ServiceNetworkController implements Initializable {

    @FXML private JFXTextField bcode;
    @FXML private JFXTextField blocation;
    @FXML private JFXTextField baddress;
    @FXML private JFXTextField bemail;
    @FXML private JFXTextField bcontact;
    @FXML private JFXTextField bmanager;
    @FXML private JFXButton badd;
    
    //FOR SELECT DATA IN tbl_core2_add_branch
    ObservableList<ServiceNetworkTableController> list = FXCollections.observableArrayList();
    @FXML private TableView<ServiceNetworkTableController> tableView;
    @FXML private TableColumn<ServiceNetworkTableController, String> codeCol;
    @FXML private TableColumn<ServiceNetworkTableController, String> locationCol;
    @FXML private TableColumn<ServiceNetworkTableController, String> addressCol;
    @FXML private TableColumn<ServiceNetworkTableController, String> emailCol;
    @FXML private TableColumn<ServiceNetworkTableController, String> contactCol;
    @FXML private TableColumn<ServiceNetworkTableController, String> managerCol;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initCol();
        badd.setOnMouseClicked(e -> Save());
        DisplayData();
        loadData();
        
    }  
    // fxid and 
    private void DisplayData(){
        codeCol.setCellValueFactory(new PropertyValueFactory<>("branch_code"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("branch_location"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("branch_address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("branch_email"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("branch_contact"));
        managerCol.setCellValueFactory(new PropertyValueFactory<>("branch_manager"));                                        
    }
    // SELECT QUERY
    private void loadData(){
        C2_Servicenetwork sv = new C2_Servicenetwork();
        ObservableList<ServiceNetworkTableController> svtl = FXCollections.observableArrayList();
        List b = sv.get();
            
            for(Object d : b){
                HashMap hm = (HashMap) d;
                
                hm.get("branch_code");
                hm.get("branch_location");
                hm.get("branch_address");
                hm.get("branch_email");
                hm.get("branch_contact");
                hm.get("branch_manager");
                
               svtl.add(
               new ServiceNetworkTableController(
                   String.valueOf(hm.get("branch_code")),
                   String.valueOf(hm.get("branch_location")),
                   String.valueOf(hm.get("branch_address")),
                   String.valueOf(hm.get("branch_email")),
                   String.valueOf(hm.get("branch_contact")),
                   String.valueOf(hm.get("branch_manager"))
                 ) );   
            }
            tableView.setItems(svtl);          
    }
    // INSERT QUERY
    public void Save(){
      C2_Servicenetwork sv = new C2_Servicenetwork();
      // ginawa ko to para mag popup yung else sa loob ng try
      String code = bcode.getText();
      String location = blocation.getText();
      String address = baddress.getText();
      String email = bemail.getText();
      String contact = bcontact.getText();
      String manager = bmanager.getText();
      
        try{
            String[][] sn_data = {
                {"branch_code" , bcode.getText()},
                {"branch_location" , blocation.getText()},
                {"branch_address" , baddress.getText()},
                {"branch_email" , bemail.getText()},
                {"branch_contact" , bcontact.getText()},
                {"branch_manager" , bmanager.getText()}
            }; 
                // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
                if(sv.insert(sn_data)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Data Saved");
                    alert.showAndWait();
                    
                    bcode.setText("");
                    blocation.setText("");
                    baddress.setText("");
                    bemail.setText("");
                    bcontact.setText("");
                    bmanager.setText("");
                }else{
                    // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                    if((code.isEmpty() || location.isEmpty() || address.isEmpty() || email.isEmpty() || contact.isEmpty() || manager.isEmpty())){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Not Inserted");
                        alert.showAndWait();
              }
            } 
                
        }catch(Exception e){
            e.printStackTrace();
        }
        // para sa realty update ng data
        DisplayData();
        loadData();
    }
}
