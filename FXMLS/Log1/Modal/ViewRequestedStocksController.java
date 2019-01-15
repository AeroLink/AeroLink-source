/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Modal;

import FXMLS.Log1.ClassFiles.Log1_ProcRequestsClassFile;
import Model.Log1.Log1_ProcurementRequestModel;
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
 * @author Crenz
 */
public class ViewRequestedStocksController implements Initializable {

    @FXML
    private TableView<Log1_ProcRequestsClassFile> RequestedStocks_tbl;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> item_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> Amount_col;
    @FXML
    private TableColumn<Log1_ProcRequestsClassFile, String> DateRequested_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadProcurementRequestData();
        displayProcurementRequestData();
    }    
    
    public void loadProcurementRequestData(){
        Log1_ProcurementRequestModel pr = new Log1_ProcurementRequestModel();
        ObservableList<Log1_ProcRequestsClassFile> ProcRequests = FXCollections.observableArrayList();
          
        List b = pr.get();
            
        for(Object d : b)
        {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                    ProcRequests.add(new Log1_ProcRequestsClassFile(
                
                String.valueOf(hm.get("RequestID")),
                String.valueOf(hm.get("RequestDescription")),
                String.valueOf(hm.get("Requestor")),
                String.valueOf(hm.get("RequestorPosition")),
                String.valueOf(hm.get("RequestDepartment")),
                String.valueOf(hm.get("RequestPriorityLevel")),
                String.valueOf(hm.get("RequestQuantity")),
                String.valueOf(hm.get("RequestPrice")),
                String.valueOf(hm.get("RequestStatus")),
                String.valueOf(hm.get("RequestDate"))
                
            ));       
        }
        RequestedStocks_tbl.setItems(ProcRequests);
    }
    
    public void displayProcurementRequestData(){   
        item_col.setCellValueFactory(new PropertyValueFactory<>("RequestDescription"));
        Amount_col.setCellValueFactory(new PropertyValueFactory<>("RequestQuantity"));
        DateRequested_col.setCellValueFactory(new PropertyValueFactory<>("RequestDate"));
    }
}
