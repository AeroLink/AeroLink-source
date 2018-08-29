/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIALS;

import FXMLS.FINANCIALS.CLASSFILES.FINANCIAL_AP;
import FXMLS.FINANCIALS.CLASSFILES.FINANCIAL_CLASS_AR;
import Model.FINANCIAL_APR;
import Model.FINANCIAL_AR;
import com.jfoenix.controls.JFXTextField;
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
 * @author JaeJae
 */
public class FINANCIAL_APRController implements Initializable {

    @FXML
    private JFXTextField search_field;
    @FXML
    private TableView<FINANCIAL_AP> ap_table;
    
    ObservableList<String> sdl = FXCollections.observableArrayList("Internal" , "External");
    @FXML
    private TableColumn<FINANCIAL_AP, String> ap_id;
    @FXML
    private TableColumn<FINANCIAL_AP, String> ap_date;
    @FXML
    private TableColumn<FINANCIAL_AP, String> ap_payee;
    @FXML
    private TableColumn<FINANCIAL_AP, String> ap_particular;
    @FXML
    private TableColumn<FINANCIAL_AP, String> ap_amount;
    @FXML
    private TableView<FINANCIAL_CLASS_AR> ar_table;
    @FXML
    private TableColumn<FINANCIAL_CLASS_AR, String> ar_id;
    @FXML
    private TableColumn<FINANCIAL_CLASS_AR, String> ar_invoice_no;
    @FXML
    private TableColumn<FINANCIAL_CLASS_AR, String> ar_date;
    @FXML
    private TableColumn<FINANCIAL_CLASS_AR, String> ar_payee;
    @FXML
    private TableColumn<FINANCIAL_CLASS_AR, String> ar_particular;
    @FXML
    private TableColumn<FINANCIAL_CLASS_AR, String> ar_amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
DisplayData();
loadDataAPayable();   
loadDataAReceivable();

// TODO
    }   
    
    
    
     private void DisplayData()
    {
        ap_id.setCellValueFactory(new PropertyValueFactory<>("ap_id"));
        ap_date.setCellValueFactory(new PropertyValueFactory<>("ap_date"));
        ap_payee.setCellValueFactory(new PropertyValueFactory<>("ap_payee"));
        ap_particular.setCellValueFactory(new PropertyValueFactory<>("ap_particular"));
        ap_amount.setCellValueFactory(new PropertyValueFactory<>("ap_amount"));
                
       
        
        
        ar_id.setCellValueFactory(new PropertyValueFactory<>("ar_id"));
        ar_invoice_no.setCellValueFactory(new PropertyValueFactory<>("invoice_no"));
        ar_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        ar_payee.setCellValueFactory(new PropertyValueFactory<>("payee"));
        ar_particular.setCellValueFactory(new PropertyValueFactory<>("particular"));
        ar_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
                                        
    }
     private void loadDataAPayable()
    {
           
        FINANCIAL_APR apr = new FINANCIAL_APR();
        
        ObservableList<FINANCIAL_AP> ap = FXCollections.observableArrayList();
            List b = apr.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;
                
                hm.get("ap_id");
                hm.get("ap_date");
                hm.get("ap_payee");
                hm.get("ap_particular");
                hm.get("ap_amount");
                
                
               ap.add(
                    new FINANCIAL_AP(
                            String.valueOf(hm.get("ap_id")),
                            String.valueOf(hm.get("ap_date")),
                            String.valueOf(hm.get("ap_payee")),
                            String.valueOf(hm.get("ap_particular")),
                            String.valueOf(hm.get("ap_amount"))
                            

                    ) );   
            }
            ap_table.setItems(ap);
                
    }
     
     
     
       private void loadDataAReceivable()
    {
           
        FINANCIAL_AR ar = new FINANCIAL_AR();
        
        ObservableList<FINANCIAL_CLASS_AR> car = FXCollections.observableArrayList();
            List b = ar.get();
            
            for(Object d : b)
            {
                HashMap hm = (HashMap) d;
                
                hm.get("ar_id");
                hm.get("invoice_no");
                hm.get("date");
                hm.get("payee");
                hm.get("particular");
                hm.get("amount");
                
                
               car.add(
                    new FINANCIAL_CLASS_AR(
                            String.valueOf(hm.get("ar_id")),
                            String.valueOf(hm.get("invoice_no")),
                            String.valueOf(hm.get("date")),
                            String.valueOf(hm.get("payee")),
                            String.valueOf(hm.get("particular")),
                             String.valueOf(hm.get("amount"))
                            

                    ) );   
            }
            ar_table.setItems(car);
    }
}
