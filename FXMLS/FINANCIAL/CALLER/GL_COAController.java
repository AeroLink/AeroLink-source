/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import FXMLS.FINANCIAL.CLASSFILES.coa_clasfile;
import Model.Financial.Financial_coa_model;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class GL_COAController implements Initializable {

    @FXML
    private JFXTextField code_no;
    @FXML
    private TableView<coa_clasfile> tbl_coa;
    @FXML
    private JFXTextField account_title;
    @FXML
    private JFXButton update_btn;
    @FXML
    private JFXButton save_btn;
    
    Financial_coa_model cm = new Financial_coa_model();
    ObservableList<coa_clasfile> coa = FXCollections.observableArrayList();
    @FXML
    private Label labelid;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
        
        
        addtable();
        loadTable();
        
        save_btn.setOnMouseClicked(e ->insertbtn());
        update_btn.setOnMouseClicked(e ->update_btn());
        
        
        tbl_coa.setOnMouseClicked(e -> {
        coa_clasfile cc = tbl_coa.getSelectionModel().getSelectedItem();
        labelid.setText(cc.getCoaid());
        code_no.setText(cc.getCode_no());
        account_title.setText(cc.getAccount_title());
        update_btn.setDisable(false);
        });
        
    }    
    
    public void update_btn(){
        try{
            if(cm.update(new Object[][]{
                {"code_no",code_no.getText()},
                {"acc_title",account_title.getText()}
            }).where("coa_id", "=", labelid.getText()).executeUpdate()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Updated");
                alert.setContentText("Updated");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("ERROR");
                alert.setContentText("FAILED");
                alert.showAndWait();
            }
                
            
        }catch(Exception e){
            
        }
        
    }
    
    public void insertbtn(){
        
         String[][] insertcoa
                        = {
                            {"code_no", code_no.getText()},
                            {"acc_title", account_title.getText()}
                        };
                if(cm.insert(insertcoa)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("Inserted");
                alert.setContentText("Saved");
                alert.showAndWait();
                }else{
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.setTitle("ERROR");
                alert.setContentText("FAILED");
                alert.showAndWait();
                }
    }
    
    
    public void addtable(){
          tbl_coa.getItems().clear();
          tbl_coa.getColumns().removeAll(tbl_coa.getColumns());
        
        TableColumn<coa_clasfile, String> cn = new TableColumn<>("Code No");
        TableColumn<coa_clasfile, String> at = new TableColumn<>("Account Title");
        
        cn.setCellValueFactory((TableColumn.CellDataFeatures<coa_clasfile,
                String> param) -> param.getValue().code);
        
        at.setCellValueFactory((TableColumn.CellDataFeatures<coa_clasfile,
                String> param) -> param.getValue().acc_title);
      
        tbl_coa.getColumns().addAll(cn,at);

    }
    
    
    
    
    
    long DummyCount = 0;
    long GlobalCount = 0;
    int Global_Count = 0;
    ExecutorService e = Executors.newFixedThreadPool(1);
    public void loadTable(){
            CompletableFuture.supplyAsync(() -> {
            while (Session.CurrentRoute.equals("id_gl")) {
                try {
                    cm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });
                           tbl_coa.getItems();
                    
                              List rs = cm.get("code_no",
                              "acc_title");
                               coa(rs);
                          
                               
                        tbl_coa.setItems(coa);
                        GlobalCount = DummyCount;
                    
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GL_COAController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
        System.err.println(tbl_coa.getItems().size());
       
 
    }
    
    private int coa(List c){
         tbl_coa.getItems().clear();
        try {
              for(Object d : c)
            {
              HashMap hm = (HashMap) d;   //exquisite casting
             coa.add(new coa_clasfile(
                            String.valueOf(hm.get("coa_id")),
                            String.valueOf(hm.get("code_no")),
                            String.valueOf(hm.get("acc_title"))
                ));   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        tbl_coa.setItems(coa);
        return 0;
        
    }
}
