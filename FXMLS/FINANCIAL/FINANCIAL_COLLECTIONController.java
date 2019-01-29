/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.Collection_classfile;
import Model.Financial.Log_assetsales_model;
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
       LoadTable_collection();
    }    
    
    
    
    
    
    
    public void AddTableColumn_collection() 
    {

        collection_tbl.getItems().clear();
        collection_tbl.getColumns().removeAll(collection_tbl.getColumns());
        TableColumn<Collection_classfile, String> date = new TableColumn<>("Date");
        TableColumn<Collection_classfile, String> invoice = new TableColumn<>("Invoice No.");
        TableColumn<Collection_classfile, String> description = new TableColumn<>("Description");
        TableColumn<Collection_classfile, String> amount = new TableColumn<>("Amount");
        TableColumn<Collection_classfile, String> type = new TableColumn<>("Type");
       
    
        date.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().coldate);
        invoice.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().colinvoice);
        description.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().coldescription);
        amount.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().colamount);
        type.setCellValueFactory((TableColumn.CellDataFeatures<Collection_classfile, String> param) -> param.getValue().coltype);
      

        
        collection_tbl.getColumns().addAll(date,invoice,description,amount,type);
       
    } 
    
    
    
    
     private int collection(List col){
        collection_tbl.getItems().clear();
        try {
             
              for(Object d : col)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
                
                hm.get("ast_date");
                hm.get("ast_id");
                hm.get("ast_description");
                hm.get("ast_amount");
                hm.get("ast_type");
               cc.add(new Collection_classfile(
                            String.valueOf(hm.get("ast_date")),
                            String.valueOf(hm.get("ast_id")),
                            String.valueOf(hm.get("ast_description")),
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_type"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        collection_tbl.setItems(cc);
        return 0;
       
     }
    
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
     ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<Collection_classfile> cc = FXCollections.observableArrayList();
      Log_assetsales_model arm = new Log_assetsales_model();
   public void LoadTable_collection(){
        
         CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("id_collection")) {
                
                try {
                    arm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {

                        collection_tbl.getItems();
                            List rs = arm.where("ast_status","=","Collected").get();
                            collection(rs);
                            
                            
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FINANCIAL_BUDGET_MANAGEMENTController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }
    
    

 
}
