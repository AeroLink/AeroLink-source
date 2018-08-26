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
        GenerateTable();
    }    

        /*private void initCol(){
        codeCol.setCellValueFactory(new PropertyValueFactory<>("branch_code"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("branch_location"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("branch_address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("branch_email"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("branch_contact"));
        managerCol.setCellValueFactory(new PropertyValueFactory<>("branch_manager"));
    }*/   

    
    public void GenerateTable(){
        tableView.getColumns().removeAll(tableView.getColumns());
        tableView.getItems().clear();
        //System.out.println("anjmhgvjhynvjyuh");
        
        TableColumn<ServiceNetworkTableController, String> Bcode = new TableColumn<>("code");
        TableColumn<ServiceNetworkTableController, String> Blocation = new TableColumn<>("location");
        TableColumn<ServiceNetworkTableController, String> Baddress = new TableColumn<>("address");
        TableColumn<ServiceNetworkTableController, String> Bemail = new TableColumn<>("email");
        TableColumn<ServiceNetworkTableController, String> Bcontact = new TableColumn<>("contact");
        TableColumn<ServiceNetworkTableController, String> Bmanager = new TableColumn<>("manager");
        
        Bcode.setCellValueFactory((TableColumn.CellDataFeatures<ServiceNetworkTableController, String> param) -> param.getValue().branch_code);
        Blocation.setCellValueFactory((TableColumn.CellDataFeatures<ServiceNetworkTableController, String> param) -> param.getValue().branch_location);
        Baddress.setCellValueFactory((TableColumn.CellDataFeatures<ServiceNetworkTableController, String> param) -> param.getValue().branch_address);
        Bemail.setCellValueFactory((TableColumn.CellDataFeatures<ServiceNetworkTableController, String> param) -> param.getValue().branch_email);
        Bcontact.setCellValueFactory((TableColumn.CellDataFeatures<ServiceNetworkTableController, String> param) -> param.getValue().branch_contact);
        Bmanager.setCellValueFactory((TableColumn.CellDataFeatures<ServiceNetworkTableController, String> param) -> param.getValue().branch_manager);
    
        C2_Servicenetwork sn = new C2_Servicenetwork();
        List row = sn.get();
        
        for(Object m : row){
            HashMap map = (HashMap) m;
            
            String code = String.valueOf(map.get("branch_code"));
            String location = String.valueOf(map.get("branch_location"));
            String address = String.valueOf(map.get("branch_address"));
            String email = String.valueOf(map.get("branch_email"));
            String contact = String.valueOf(map.get("branch_contact"));
            String manager = String.valueOf(map.get("branch_manager"));
            
            this.list.add(new ServiceNetworkTableController(code,location,address,email,contact,manager));
        }
    
        tableView.setItems(this.list);
        tableView.getColumns().addAll(Bcode,Blocation,Baddress,Bemail,Bcontact,Bmanager);
        
    }
    
    /*private void loadData(){
        C2_Servicenetwork database = new C2_Servicenetwork();
        List list = database.get();
        for(int i = 0; i < list.size(); i++){
            System.out.println();
        }
    }*/
    
    @FXML
    private void addBranch(ActionEvent event) {
        //insert
        
    }
}
