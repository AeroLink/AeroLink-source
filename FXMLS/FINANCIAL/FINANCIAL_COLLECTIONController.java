/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL;

import FXMLS.FINANCIAL.CLASSFILES.Collection_classfile;
import FXMLS.FINANCIAL.CLASSFILES.col_totalasset_classfile;
import FXMLS.FINANCIAL.CLASSFILES.coltotal_classfile;
import Model.Financial.Log_assetsales_model;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    @FXML
    private ContextMenu col_contextmenu;
    @FXML
    private MenuItem col_menuItem;
    @FXML
    private JFXButton denomination_btn;
    @FXML
    public Label totals;
    @FXML
    private TableView<coltotal_classfile> col_total_tbl;
    @FXML
    private Label totalAsset_label;
    @FXML
    private TableView<col_totalasset_classfile> col_asset_tbl;
    @FXML
    private DatePicker datepickersrch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AddTableColumn_collection();
       LoadTable_collection();
       denomination_btn.setOnMouseClicked(e -> openDenomination());  
      addcolamount();
      loadcolamount();
      
      
      addColTotalAst();
      loadColTotalAst();
      
      datepickersrch.setOnAction(e ->datesearch());
      
    }    
    
    
    private int srch(List sr){
         collection_tbl.getItems().clear();
        try {
              for(Object d : sr)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
               cc.add(new Collection_classfile(
                            String.valueOf(hm.get("ast_date")),
                            String.valueOf(hm.get("ast_id")),
                            String.valueOf(hm.get("ast_description")),
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_pom_ast_amount")),
                            String.valueOf(hm.get("ast_type")),
                            String.valueOf(hm.get("ast_status2"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        collection_tbl.setItems(cc);
        return 0;
       
    }
    public void datesearch(){
      Log_assetsales_model arm = new Log_assetsales_model();
      String SearchText = datepickersrch.getEditor().toString().equals("") ? "[a-z]" : datepickersrch.getEditor().toString();
        
        try {

            List src = arm.where(new Object[][]{
                {"ast_date", "=", "%" + SearchText +"%"}
            }).get();
            srch(src);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
    
    public int total =0;
    public void total(){
        for(int i  = 0; i<col_total_tbl.getItems().size(); i++){
         int amount = Integer.parseInt(col_total_tbl.getItems().get(i).colsamount.getValue());
         total += amount;
                    }
      totals.setText(String.valueOf(total));
    }
    public void openDenomination(){
        Modal md = Modal.getInstance(new Form("/FXMLS/FINANCIAL/CALLER/BUDGET_DENOMINATION.fxml").getParent());
        md.open();
    }
    
    
    public int total2 =0;
   ObservableList<col_totalasset_classfile> ctc = FXCollections.observableArrayList();
   public void addColTotalAst(){
        col_asset_tbl.getItems().clear();
        col_asset_tbl.getColumns().removeAll(col_asset_tbl.getColumns());
        
        TableColumn<col_totalasset_classfile, String> astamount = new TableColumn<>("astAmount");
        
        astamount.setCellValueFactory((TableColumn.CellDataFeatures<col_totalasset_classfile,
                String> param) -> param.getValue().colAstamount);
      
        col_asset_tbl.getColumns().addAll(astamount);
   } 
   public void loadColTotalAst(){
       try{
          List rs = arm.where("ast_status","=","Collected").andWhere("ast_status2","=","CollectedAsset").get();
                            colAssets(rs);
                                total2();
    }catch(Exception e){
        System.err.println(e);
    }
       
   }
   private int colAssets(List ca){
          col_asset_tbl.getItems().clear();
        try {
              for(Object d : ca)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
             ctc.add(new col_totalasset_classfile(
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_status2"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        col_asset_tbl.setItems(ctc);
        return 0;
   }
   public void total2(){
        for(int i  = 0; i<col_asset_tbl.getItems().size(); i++){
         int amount = Integer.parseInt(col_asset_tbl.getItems().get(i).colAstamount.getValue());
         total2 += amount;
                    }
      totalAsset_label.setText(String.valueOf(total2));
   }
    
    
    ObservableList<coltotal_classfile> sss = FXCollections.observableArrayList();
    public void addcolamount(){
        col_total_tbl.getItems().clear();
        col_total_tbl.getColumns().removeAll(col_total_tbl.getColumns());
        TableColumn<coltotal_classfile, String> amount = new TableColumn<>("Amount");
        
        amount.setCellValueFactory((TableColumn.CellDataFeatures<coltotal_classfile,
                String> param) -> param.getValue().colsamount);
      
        col_total_tbl.getColumns().addAll(amount);
    }
    public void loadcolamount(){
        try{
          List rs = arm.where("ast_status","=","Collected").andWhere("ast_status2","=","Collected").get();
                            col(rs);
                                total();
    }catch(Exception e){
        System.err.println(e);
    }
    }
    private int col(List cols){
         col_total_tbl.getItems().clear();
        try {
              for(Object d : cols)
            {
                HashMap hm = (HashMap) d;   //exquisite casting
             sss.add(new coltotal_classfile(
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_status2"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        col_total_tbl.setItems(sss);
        return 0;
    }
    
    
    
    
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
     ExecutorService e = Executors.newFixedThreadPool(1);   
    ObservableList<Collection_classfile> cc = FXCollections.observableArrayList();
      Log_assetsales_model arm = new Log_assetsales_model();
    public void AddTableColumn_collection() {

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
                hm.get("ast_pom_ast_amount");
                hm.get("ast_type");
               cc.add(new Collection_classfile(
                            String.valueOf(hm.get("ast_date")),
                            String.valueOf(hm.get("ast_id")),
                            String.valueOf(hm.get("ast_description")),
                            String.valueOf(hm.get("ast_amount")),
                            String.valueOf(hm.get("ast_pom_ast_amount")),
                            String.valueOf(hm.get("ast_type")),
                            String.valueOf(hm.get("ast_status2"))
                ) );   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        collection_tbl.setItems(cc);
        return 0;
       
     }
   
   public void LoadTable_collection(){
        
    try{
          List rs = arm.where("ast_status","=","Collected").get();
                            collection(rs);
                    
    }catch(Exception e){
        System.err.println(e);
    }

    }
    
    

 
}
