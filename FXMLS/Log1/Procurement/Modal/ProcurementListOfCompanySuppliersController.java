/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Procurement.Modal;

import FXMLS.Log1.ClassFiles.Log1_ProcurementPurchaseListClassfiles;
import FXMLS.Log1.ClassFiles.Log1_ProcurementSuppliersClassfiles;
import Model.Log1.Log1_ProcurementSupplierModel;
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

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class ProcurementListOfCompanySuppliersController implements Initializable {

    @FXML
    private TableView<Log1_ProcurementSuppliersClassfiles> suppliers_tbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setPurchaseList();
        fetchPurchList();
    }    
    
    public void setPurchaseList(){
        suppliers_tbl.getItems().clear();
        suppliers_tbl.getColumns().removeAll(suppliers_tbl.getColumns());

        TableColumn<Log1_ProcurementSuppliersClassfiles, String> supplierName = new TableColumn<>("Supplier Name");
        TableColumn<Log1_ProcurementSuppliersClassfiles, String> ContractStarted = new TableColumn<>("Contract Start");
        TableColumn<Log1_ProcurementSuppliersClassfiles, String> ContractEnd = new TableColumn<>("Contract End");
        TableColumn<Log1_ProcurementSuppliersClassfiles, String> AwardedBy = new TableColumn<>("Awarded by");

        supplierName.setCellValueFactory((param) -> param.getValue().SupplierName);
        ContractStarted.setCellValueFactory(param -> param.getValue().ContractStarted);
        ContractEnd.setCellValueFactory(param -> param.getValue().ContractEnd);
        AwardedBy.setCellValueFactory(param -> param.getValue().AwardedBy);

        suppliers_tbl.getColumns().addAll(supplierName, ContractStarted, ContractEnd, AwardedBy);
    }
    
    public void fetchPurchList(){
    Log1_ProcurementSupplierModel suppDB = new Log1_ProcurementSupplierModel();
    ObservableList<Log1_ProcurementSuppliersClassfiles> suppCF = FXCollections.observableArrayList(); 
        
    List b = suppDB.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                suppCF.add(new Log1_ProcurementSuppliersClassfiles(
                
                String.valueOf(hm.get("SupplierID")),
                String.valueOf(hm.get("SupplierName")),
                String.valueOf(hm.get("SupplierRepresentative")),
                String.valueOf(hm.get("SupplierContact")),
                String.valueOf(hm.get("SupplierEmail")),
                String.valueOf(hm.get("SupplierLocation")),
                String.valueOf(hm.get("ContractStarted")),
                String.valueOf(hm.get("ContractEnd")),
                String.valueOf(hm.get("AwardedBy")),
                String.valueOf(hm.get("Remarks")),
                String.valueOf(hm.get("SupplierStatus"))
                ));       
        }
        suppliers_tbl.setItems(suppCF);
    }
    
}
