/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.Collection_classfile;
import Synapse.Model;
import Synapse.Session;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class FINANCIAL_COLLECTIONController implements Initializable {

    @FXML
    private TableView<Collection_classfile> collection_tbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AddTableColumn_collection();
       
       //LoadTable_collection();
    }    
    
    
    
    public void AddTableColumn_collection() {

        collection_tbl.getItems().clear();
        collection_tbl.getColumns().removeAll(collection_tbl.getColumns());
        TableColumn<Collection_classfile, String> date = new TableColumn<>("Date");
        TableColumn<Collection_classfile, String> invoice = new TableColumn<>("Invoice No.");
        TableColumn<Collection_classfile, String> description = new TableColumn<>("Description");
        TableColumn<Collection_classfile, String> amount = new TableColumn<>("Amount");
        TableColumn<Collection_classfile, String> type = new TableColumn<>("Type");
       
    
        date.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().podate);
        invoice.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().poinvoice);
        description.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().podescription);
        amount.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().poamount);
        type.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().potype);
      

        
        collection_tbl.getColumns().addAll(date,invoice,description,amount,type);
       
    } 
    
    
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
     ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<Collection_classfile> pom = FXCollections.observableArrayList();
    
   /* public void LoadTable_collection(){
        
         CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_collection")) {
                
                try {
                    fpm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        collection_tbl.getItems().removeAll(pom);
                            List rs = fpm
                                    .join(Model.JOIN.LEFT, "aerolink.tbl_finance_to_asset", "asset_id", "tblA", "=", "po_id")
                                    .get( "po_invoice"
                                          );
                        AddSalesToTable(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }*/
    
    
    public void AddSalesToTable(List rs) {
        collection_tbl.refresh();
        pom.clear();
        
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            
            
            String id = String.valueOf(crow.get("poid"));
            String no = (String) crow.get("pono");
            String date = (String) crow.get("date");
            String inv = (String) crow.get("invoice");
            String desc = (String) crow.get("description");
            String amnt = (String) crow.get("amount");
            String tpe = (String) crow.get("type");
            
            pom.add(new Collection_classfile(id,no,date,inv,desc,amnt,tpe));
        }
         collection_tbl.setItems(pom);
    }
 
}
