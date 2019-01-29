/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.Consolidation_deconsolidationController;
import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.db_booking;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Consol_holdController implements Initializable {
    
    public static Boolean modalOpen = true;
    ObservableList<db_booking> oblist  = FXCollections.observableArrayList();
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<db_booking, String> col_ref;
    @FXML
    private TableColumn<db_booking, String> col_des;
    @FXML
    private TableColumn<db_booking, String> col_remarks;
    @FXML
    private TextField txt_search;
    @FXML
    private Label lbl_ref;
    @FXML
    private TableView<db_booking> tbl_hold;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        setCellValue();
        searchData();
    } 
    
    public void initTable(){
        
        oblist.clear();
        
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking inner join aerolink.tbl_core1_hold on aerolink.tbl_core1_booking.ref_no = aerolink.tbl_core1_hold.ref_no where status='Hold'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("remarks")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("insurance"), rs.getString("liability"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_hold.setItems(oblist);
        
    }
    
        public void setCellValue(){
        
       col_ref.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
       col_remarks.setCellValueFactory(new PropertyValueFactory<>("book_date"));
       col_des.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getReceiver_address()));
        
    }
    

    @FXML
    private void update_status(ActionEvent event) {
       if("".equals(txt_search.getText())){
            Helpers.AlertResponse alert_success = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "", "Select information you want to unhold ");
            alert_success.open();
       }else{
        try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_booking set status ='For Consolidation' where ref_no='"+txt_search.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            initTable();
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "", "Unhold", "Successful !");
            alert.open();
            delete();

                         
        } catch (SQLException ex) {
            Logger.getLogger(Consol_remarksController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }

    @FXML
    private void displayTable(MouseEvent event) {
        
        tbl_hold.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                db_booking cbt = tbl_hold.getItems().get(tbl_hold.getSelectionModel().getSelectedIndex());
                    txt_search.setText(cbt.getRef_no());
            } 
        });
        
    }
    
    public void delete(){
 
        try{
            conn = DBConnector.getConnection();

                    String sql = "DELETE FROM aerolink.tbl_core1_hold where ref_no = '"+txt_search.getText()+"'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate(); 
                    
                    initTable();
                    txt_search.clear();
                    
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public void searchData(){
        
        FilteredList<db_booking> filteredData = new FilteredList<>(oblist, p -> true);
        
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(db_booking -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (db_booking.getRef_no().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<db_booking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_hold.comparatorProperty());
        tbl_hold.setItems(sortedData);
   
    }
    
}
