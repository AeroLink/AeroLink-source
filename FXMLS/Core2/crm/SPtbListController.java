/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.crm;

import FXMLS.Core2.Controllers.ServiceProviderTableController;
import Model.C2_Serviceprovider;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class SPtbListController implements Initializable {

    ObservableList<ServiceProviderTableController> list = FXCollections.observableArrayList();
    @FXML private TableView<ServiceProviderTableController> tableView;
    @FXML private TableColumn<ServiceProviderTableController, String> codeCol;
    @FXML private TableColumn<ServiceProviderTableController, String> companyCol;
    @FXML private TableColumn<ServiceProviderTableController, String> contactCol;
    @FXML private TableColumn<ServiceProviderTableController, String> houseCol;
    @FXML private TableColumn<ServiceProviderTableController, String> streetCol;
    @FXML private TableColumn<ServiceProviderTableController, String> barangayCol;
    @FXML private TableColumn<ServiceProviderTableController, String> monicipalCol;
    @FXML private TableColumn<ServiceProviderTableController, String> zipCol;
    @FXML private TableColumn<ServiceProviderTableController, String> emailCol;
    @FXML private TableColumn<ServiceProviderTableController, String> doorCol;
    @FXML private TableColumn<ServiceProviderTableController, String> pickupCol;
    @FXML private TableColumn<ServiceProviderTableController, String> expressCol;
    @FXML private TableColumn<ServiceProviderTableController, String> otherCol;
    @FXML private TableColumn<ServiceProviderTableController, String> regionCol;
    @FXML private TableColumn<ServiceProviderTableController, String> countryCol;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayData();
        loadData();
    }    
    
    private void DisplayData(){
        codeCol.setCellValueFactory(new PropertyValueFactory<>("apid"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        houseCol.setCellValueFactory(new PropertyValueFactory<>("house_number"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        barangayCol.setCellValueFactory(new PropertyValueFactory<>("barangay")); 
        monicipalCol.setCellValueFactory(new PropertyValueFactory<>("city_monicipality"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        doorCol.setCellValueFactory(new PropertyValueFactory<>("doortodoor"));
        pickupCol.setCellValueFactory(new PropertyValueFactory<>("pickup"));
        expressCol.setCellValueFactory(new PropertyValueFactory<>("express"));
        otherCol.setCellValueFactory(new PropertyValueFactory<>("other"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
    }
    // SELECT QUERY
    private void loadData(){
        C2_Serviceprovider sp = new C2_Serviceprovider();
        ObservableList<ServiceProviderTableController> sptl = FXCollections.observableArrayList();
        List b = sp.get();
            
            for(Object d : b){
                HashMap hm = (HashMap) d;
                
                hm.get("apid");
                hm.get("company_name");
                hm.get("contact_number");
                hm.get("house_number");
                hm.get("street");
                hm.get("barangay");
                hm.get("city_monicipality");
                hm.get("zipcode");
                hm.get("email");
                hm.get("doortodoor");
                hm.get("pickup");
                hm.get("express");
                hm.get("other");
                hm.get("region");
                hm.get("country");
                
               sptl.add(
               new ServiceProviderTableController(
                   String.valueOf(hm.get("apid")),
                   String.valueOf(hm.get("company_name")),
                   String.valueOf(hm.get("contact_number")),
                   String.valueOf(hm.get("house_number")),
                   String.valueOf(hm.get("street")),
                   String.valueOf(hm.get("barangay")),
                   String.valueOf(hm.get("city_monicipality")),
                   String.valueOf(hm.get("zipcode")),
                   String.valueOf(hm.get("email")),
                   String.valueOf(hm.get("doortodoor")),
                   String.valueOf(hm.get("pickup")),
                   String.valueOf(hm.get("express")),
                   String.valueOf(hm.get("other")),
                   String.valueOf(hm.get("region")),
                   String.valueOf(hm.get("country"))
                 ) );   
            }
            tableView.setItems(sptl);          
    }
    
}
