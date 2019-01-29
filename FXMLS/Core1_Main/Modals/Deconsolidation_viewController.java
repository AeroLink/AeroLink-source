/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.Consolidation_deconsolidationController;
import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.db_booking;
import FXMLS.Core1_Main.Po_managementController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Deconsolidation_viewController implements Initializable {
    
    public static Boolean modalOpen = true;
    
    ObservableList<db_booking> oblist  = FXCollections.observableArrayList();
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<db_booking, String> col_consol_no;
    @FXML
    private TableColumn<db_booking, String> col_ref_no;
    @FXML
    private TableColumn<db_booking, String> col_destination;
    @FXML
    private TextField txt_search;
    @FXML
    private TableView<db_booking> tbl_consol_view;
    @FXML
    private Label lbl_ref;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        setCellValue();
        searchData();
        lbl_ref.setVisible(false);
    }    
    
    public void initTable(){
        
        oblist.clear();
        
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking where status LIKE 'Consolidated%'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("book_date")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("insurance"), rs.getString("liability"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Deconsolidation_viewController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_consol_view.setItems(oblist);
        
    }
    
    public void setCellValue(){
        
       col_ref_no.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
       col_consol_no.setCellValueFactory(new PropertyValueFactory<>("status"));
       col_destination.setCellValueFactory(new PropertyValueFactory<>("rec_province"));
        
    }
    
    public void searchData(){
        
        FilteredList<db_booking> filteredData = new FilteredList<>(oblist, p -> true);
        
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(db_booking -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (db_booking.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    
                    return true; 
                }
                return false;
            });
        });

        SortedList<db_booking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_consol_view.comparatorProperty());
        tbl_consol_view.setItems(sortedData);
   
    }

  @FXML
    public void displayTable(){
        tbl_consol_view.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                db_booking cbt = tbl_consol_view.getItems().get(tbl_consol_view.getSelectionModel().getSelectedIndex());
                try{
                    lbl_ref.setText(cbt.getRef_no());
                    
                }catch(Exception ex){
                    Logger.getLogger(Deconsolidation_viewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    @FXML
    public void update_status(){
        if("".equals(lbl_ref.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "ERROR", "Please select information you want to deconsolidate !");
            alert.open();
        }else{
        try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_booking set status ='For Consolidation' where ref_no='"+lbl_ref.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            initTable();
            
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "", "Deconsolidation", "Successful !");
            alert.open();

                         
        } catch (SQLException ex) {
            Logger.getLogger(Deconsolidation_viewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
