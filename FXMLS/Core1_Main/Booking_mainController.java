/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main;


import FXMLS.Core1_Main.Modals.Booking_selectionController;
import FXMLS.Core1_Main.Model.db_booking;
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

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Booking_mainController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    ObservableList<db_booking> oblist  = FXCollections.observableArrayList();
    

    @FXML
    private TableView<db_booking> tbl_booking;
    @FXML
    private TableColumn<db_booking, String> col_ref;
    @FXML
    private TableColumn<db_booking, String> col_ship_from;
    @FXML
    private TableColumn<db_booking, String> col_ship_to;
    @FXML
    private TableColumn<db_booking, String> col_contact;
    @FXML
    private TableColumn<db_booking, String> col_status;
    @FXML
    private TextField txt_search;
    @FXML
    private Label lbl_ref;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       initTable();
       setCellValue();
       searchData();
       lbl_ref.setText("");
       
    }    

    @FXML
    private void openNew(){
        
//        Modal md = Modal.getInstance(new Form("/FXMLS/Core1_Main/Modals/booking_information.fxml").getParent());
//        md.open();
//        md.getF().getStage().setOnCloseRequest(action -> {
//        FXMLS.Core1_Main.Modals.Booking_informationController.modalOpen = false;
//          
//        });
        AlertBox a =  new AlertBox();
        Booking_selectionController ad = new Booking_selectionController();
        a.loadfxml("/FXMLS/Core1_Main/Modals/booking_selection.fxml", ad);
    }
    
    public void initTable(){
        
        oblist.clear();
        
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking where status='Pending'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("book_date")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("item_type"), rs.getString("pick_update"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Booking_mainController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_booking.setItems(oblist);
        
    }
    
    public void setCellValue(){
        
       col_ref.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
       col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
       col_ship_from.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getShipper_address()));
       col_ship_to.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getReceiver_address()));
       col_contact.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getContact_person()));

    }
    
    @FXML
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
                if (db_booking.getShip_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<db_booking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_booking.comparatorProperty());
        tbl_booking.setItems(sortedData);
   
}

    @FXML
    private void deleteData(ActionEvent event) {
        if("".equals(lbl_ref.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "", "Please select information you want to delete. ");
            alert.open();
        }
         try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_booking set status ='Cancelled' where ref_no='"+lbl_ref.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            initTable();
            
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "", "", "Delete Successfully !");
            alert.open();
            
            lbl_ref.setText("");
                         
        } catch (SQLException ex) {
            Logger.getLogger(Booking_mainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refresh(ActionEvent event) {
        initTable();
    }
    
    
       @FXML
    public void displayTable(){
        tbl_booking.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                db_booking cbt = tbl_booking.getItems().get(tbl_booking.getSelectionModel().getSelectedIndex());
                try{
                    lbl_ref.setText(cbt.getRef_no());
                    
                }catch(Exception ex){
                    Logger.getLogger(Booking_mainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
   
    
  
}



