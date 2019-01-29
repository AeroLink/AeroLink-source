/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.db_invoice;
import FXMLS.Core1_Main.Model.db_pom;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Invoice_viewController implements Initializable {
    
    public static Boolean modalOpen = true;
    
    ObservableList<db_invoice> oblist  = FXCollections.observableArrayList();
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<db_invoice> tbl_invoice_view;
    @FXML
    private TableColumn<db_invoice, String> col_invoice;
    @FXML
    private TableColumn<db_invoice, String> col_ref;
    @FXML
    private TableColumn<db_invoice, String> col_po;
    @FXML
    private TableColumn<db_invoice, String> col_date;
    @FXML
    private TextField txt_search;

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
        
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_invoices where invoice_no != 'sample_data'");
        
        while (rs.next()){
            oblist.add(new db_invoice(rs.getString("invoice_no"), rs.getString("invoice_date"), rs.getString("po_no") ,rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Invoice_viewController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_invoice_view.setItems(oblist);
        
    }
     
    public void setCellValue(){
        
       col_invoice.setCellValueFactory(new PropertyValueFactory<>("invoice_no"));
       col_date.setCellValueFactory(new PropertyValueFactory<>("invoice_date"));
       col_po.setCellValueFactory(new PropertyValueFactory<>("po_no"));
       col_ref.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
    }
    
    public void searchData(){
        
        FilteredList<db_invoice> filteredData = new FilteredList<>(oblist, p -> true);
        
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(db_pom -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (db_pom.getInvoice_no().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<db_invoice> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_invoice_view.comparatorProperty());
        tbl_invoice_view.setItems(sortedData);
   
    }
    
    
}
