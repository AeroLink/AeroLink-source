/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Modals;

import FXMLS.Core1_Main.DBConnector;
import FXMLS.Core1_Main.Model.db_pom;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Pom_viewController implements Initializable {
    
    public static Boolean modalOpen = true;
    
    ObservableList<db_pom> oblist  = FXCollections.observableArrayList();
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<db_pom, String> col_po_no;
    @FXML
    private TableColumn<db_pom, String> col_track_no;
    @FXML
    private TableColumn<db_pom, String> col_po_date;
    @FXML
    private TableColumn<db_pom, String> col_total;
    @FXML
    private TextField txt_search;
    @FXML
    private TableView<db_pom> tbl_pom_view;

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
      
        
        ResultSet rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_pom where po_no != 'sample_data'");
        
        while (rs.next()){
            oblist.add(new db_pom(rs.getString("po_no"), rs.getString("po_date"), rs.getString("track_no") ,rs.getString("sub") ,rs.getString("total") ,rs.getString("vat"),rs.getString("charge"),rs.getString("pom_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Pom_viewController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_pom_view.setItems(oblist);
        
    }
     
     public void setCellValue(){
        
       col_po_no.setCellValueFactory(new PropertyValueFactory<>("po_no"));
       col_po_date.setCellValueFactory(new PropertyValueFactory<>("po_date"));
       col_track_no.setCellValueFactory(new PropertyValueFactory<>("track_no"));
       col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
     
      public void searchData(){
        
        FilteredList<db_pom> filteredData = new FilteredList<>(oblist, p -> true);
        
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(db_pom -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (db_pom.getPo_no().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<db_pom> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_pom_view.comparatorProperty());
        tbl_pom_view.setItems(sortedData);
   
    }

    @FXML
    private void printReport(ActionEvent event) throws DocumentException, IOException, SQLException, ClassNotFoundException{
        try{
        String query = "select * from aerolink.tbl_core1_pom";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("pom.pdf"));
        document.open();
        PdfPTable tbl = new PdfPTable(4);
        PdfPCell tbl_cell;
        
        while(rs.next()){
            String po_no = rs.getString("po_no");
            tbl_cell=new PdfPCell(new Phrase(po_no));
            tbl.addCell(tbl_cell);
            String po_date = rs.getString("po_date");
            tbl_cell=new PdfPCell(new Phrase(po_date));
            tbl.addCell(tbl_cell);
            String track_no = rs.getString("track_no");
            tbl_cell=new PdfPCell(new Phrase(track_no));
            tbl.addCell(tbl_cell);
            String total = rs.getString("total");
            tbl_cell=new PdfPCell(new Phrase(total));
            tbl.addCell(tbl_cell);
        }
        document.add(tbl);
        document.close();
        rs.close();
        pst.close();
 
         }catch(FileNotFoundException e){
             e.printStackTrace();
         }
    } 
    
}
