/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.core1;

import Model.Core1_booking_table;
import Synapse.Form;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Core1_bookingController implements Initializable {
    
    
    
    @FXML
    private TableColumn<Core1_booking_table, String> customer_id;
    @FXML
    private TableColumn<Core1_booking_table, String> fn;
    @FXML
    private TableColumn<Core1_booking_table, String> mid;
    @FXML
    private TableColumn<Core1_booking_table, String> ln;
    @FXML
    private TableColumn<Core1_booking_table, String> email;
    @FXML
    private TableColumn<Core1_booking_table, String> contact;
    @FXML
    private TableView<Core1_booking_table> customer_table;
    
    
    ObservableList<Core1_booking_table> oblist  = FXCollections.observableArrayList();
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    private JFXButton btn_Save;
    @FXML
    private JFXButton btn_new;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXTextField txt_search;
    
    @FXML
    private TableColumn<Core1_booking_table, String> company;
    @FXML
    private TableColumn<Core1_booking_table, String> house;
    @FXML
    private TableColumn<Core1_booking_table, String> street;
    @FXML
    private TableColumn<Core1_booking_table, String> brgy;
    @FXML
    private TableColumn<Core1_booking_table, String> city;
    @FXML
    private TableColumn<Core1_booking_table, String> province;
    @FXML
    private TableColumn<Core1_booking_table, String> zip;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton btn_search;
    @FXML
    private JFXButton btn_refresh;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customer_table.setDisable(true);
             
      try{  
        Connection conn = DBConnector.getConnection();
      
        
        ResultSet rs = conn.createStatement().executeQuery("select * from tbl_core1_customer_details");
        
        while (rs.next()){
            oblist.add(new Core1_booking_table(rs.getString("customer_id"), rs.getString("firstname"), rs.getString("middlename") ,rs.getString("lastname") ,rs.getString("email") ,rs.getString("contact_no."),rs.getString("company"),rs.getString("house"),rs.getString("street"),rs.getString("brgy"),rs.getString("city"),rs.getString("province"),rs.getString("zip")));
            
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Core1_bookingController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
      
      
        
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        fn.setCellValueFactory(new PropertyValueFactory<>("fn"));
        mid.setCellValueFactory(new PropertyValueFactory<>("mid"));
        ln.setCellValueFactory(new PropertyValueFactory<>("ln"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        house.setCellValueFactory(new PropertyValueFactory<>("house"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        brgy.setCellValueFactory(new PropertyValueFactory<>("brgy"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        province.setCellValueFactory(new PropertyValueFactory<>("province"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        
        
        customer_table.setItems(oblist);
        
        displayTable();
    }   
    
    @FXML
    public void newButton() throws IOException{
     AnchorPane newForm = FXMLLoader.load(getClass().getResource("Core1_booking_form_new.fxml"));
          rootPane.getChildren().setAll(newForm);  
          
    }
    
    
    @FXML
    public void editButton(){
        
      customer_table.setDisable(false);
        
        
    }
    
    @FXML
    public void deleteButton(){
        
        try{
            conn = DBConnector.getConnection();
            
           
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmatiion Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete all information ?");
            Optional <ButtonType> action = alert.showAndWait();
            
            
            if(action.get() == ButtonType.OK){
                    String sql = "DELETE * FROM `tbl_core1_customer_details` where customer_id = ?";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();    
            }
            
            loadData();
            
        }catch(Exception e){
            System.err.println(e);
            
        }
   
        
    }
    
    @FXML
    public void displayTable(){
        customer_table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                Core1_booking_table cbt = customer_table.getItems().get(customer_table.getSelectionModel().getSelectedIndex());
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("Core1_booking_form.fxml"));
                  
                                 
                
                try {
                    
                 AnchorPane newForm =  Loader.load(); 
                 rootPane.getChildren().setAll(newForm);
                                   
                }catch(IOException ex){
                    
                     Logger.getLogger(Core1_bookingController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Core1_booking_formController formControl = Loader.getController();
                formControl.setData(cbt.getCustomer_id(),cbt.getFn(),cbt.getMid(),cbt.getLn(),cbt.getEmail(),cbt.getContact(),cbt.getCompany(),cbt.getHouse(),cbt.getStreet(),cbt.getBrgy(),cbt.getCity(),cbt.getProvince(),cbt.getZip());  
               
            
            }
                    
                    
            
            
            
        });
        
        
   
        
    }
    @FXML
    public void loadData(){
        
        
        oblist.clear();
        
        try{  
        Connection conn = DBConnector.getConnection();
       
        
        ResultSet rs = conn.createStatement().executeQuery("select * from tbl_core1_customer_details");
        
        while (rs.next()){
            oblist.add(new Core1_booking_table(rs.getString("customer_id") ,rs.getString("firstname"), rs.getString("middlename") ,rs.getString("lastname") ,rs.getString("email") ,rs.getString("contact_no."),rs.getString("company"),rs.getString("house"),rs.getString("street"),rs.getString("brgy"),rs.getString("city"),rs.getString("province"),rs.getString("zip")));
            
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Core1_bookingController.class.getName()).log(Level.SEVERE, null, ex);
          
      }

    }
    
        
    @FXML
        public void searchData(){
            
                 try{
                     conn = DBConnector.getConnection();
                        String sql = "SELECT * FROM `tbl_core1_customer_details` WHERE `customer_id` = '"+txt_search.getText()+"' or `firstname` = '"+txt_search.getText()+"'";
                        pst=conn.prepareStatement(sql);
                        rs = pst.executeQuery();
                        
                        oblist.clear();
                         while (rs.next()){
                            oblist.add(new Core1_booking_table(rs.getString("customer_id") ,rs.getString("firstname"), rs.getString("middlename") ,rs.getString("lastname") ,rs.getString("email") ,rs.getString("contact_no."),rs.getString("company"),rs.getString("house"),rs.getString("street"),rs.getString("brgy"),rs.getString("city"),rs.getString("province"),rs.getString("zip")));
            
                              }
          
        
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
            
            
        }
    
}