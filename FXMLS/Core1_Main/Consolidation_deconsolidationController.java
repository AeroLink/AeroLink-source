/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main;

import FXMLS.Core1_Main.Modals.Consol_holdController;
import FXMLS.Core1_Main.Modals.Consol_remarksController;
import FXMLS.Core1_Main.Modals.Deconsolidation_viewController;
import FXMLS.Core1_Main.Modals.Pom_chargesController;
import FXMLS.Core1_Main.Model.combo_provinces;
import FXMLS.Core1_Main.Model.db_booking;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Consolidation_deconsolidationController implements Initializable {
    
    ObservableList<db_booking> oblist  = FXCollections.observableArrayList();
    ObservableList search_city  = FXCollections.observableArrayList();
    ObservableList search_province  = FXCollections.observableArrayList();
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    Stage newStage = new Stage();

    @FXML
    private TableView<db_booking> tbl_consol;
    @FXML
    private TableColumn<db_booking, String> col_ref_no;
    @FXML
    private TableColumn<db_booking, String> col_rec_add;
    @FXML
    private TableColumn<db_booking, String> col_status;
    @FXML
    private Label lbl_consol_no;
    @FXML
    private TableColumn<db_booking, String> col_weight;
    @FXML
    private Label lbl_total_weight;
    private TextField txt_search_zip;
    @FXML
    private Label lbl_ref;
    @FXML
    private ComboBox<String> combo_province;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        setCellValue();
        setConsolidationNo();
        setTotalWeight();
        fillProvince();
        lbl_ref.setText("");
    }   
    
    public void initTable(){
        
        oblist.clear();
        
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking inner join tbl_core2_sop on aerolink.tbl_core1_booking.ref_no = tbl_core2_sop.ref_no inner join aerolink.tbl_core1_hawb on aerolink.tbl_core1_booking.ref_no = aerolink.tbl_core1_hawb.ref_no where status='For Consolidation' and remarks='printed'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("weight")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("insurance"), rs.getString("liability"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_consol.setItems(oblist);
        
    }
    
     public void setCellValue(){
        
       col_ref_no.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
       col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
       col_weight.setCellValueFactory(new PropertyValueFactory<>("book_date"));
       col_rec_add.setCellValueFactory(new PropertyValueFactory<>("rec_province"));
        
    }
     
    public void setConsolidationNo(){
        String consol_no;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select max(consol_count) as consol_count from aerolink.tbl_core1_consolidation";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
               
               consol_no = String.valueOf(rs.getInt("consol_count") + 1);
               lbl_consol_no.setText("CO0000" + consol_no);
               
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTotalWeight(){
        String total_weight;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select sum(cast(weight as float)) as total_weight from tbl_core2_sop inner join aerolink.tbl_core1_booking on tbl_core2_sop.ref_no = aerolink.tbl_core1_booking.ref_no where status = 'For Consolidation' and rec_province='"+combo_province.getValue()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
               
               total_weight = String.valueOf(rs.getDouble("total_weight"));
               lbl_total_weight.setText(total_weight);
               
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void searchData(){
        
        oblist.clear();
        
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking inner join tbl_core2_sop on aerolink.tbl_core1_booking.ref_no = tbl_core2_sop.ref_no inner join aerolink.tbl_core1_hawb on aerolink.tbl_core1_booking.ref_no = aerolink.tbl_core1_hawb.ref_no where status='For Consolidation' and remarks='printed' and rec_province = '"+combo_province.getValue()+"'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("weight")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("insurance"), rs.getString("liability"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_consol.setItems(oblist);
        setTotalWeight();
   
    }

    @FXML
    private void hold(ActionEvent event) throws IOException {
        
//         try {
//            conn = DBConnector.getConnection();
//            
//            String sql = "Update tbl_core1_booking set status ='Hold' where ref_no='"+lbl_ref.getText()+"'";
//            pst = conn.prepareStatement(sql);
//            pst.execute();
//
//                         
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
//        }

            if("".equals(lbl_ref.getText())){
                Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "ERROR", "Please select information you want to hold !");
                alert.open();
            }else{
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLS/Core1_Main/Modals/consol_remarks.fxml")); 
//            
//            try {  
//
//               Parent root = (Parent)fxmlLoader.load();          
//               Consol_remarksController controller = fxmlLoader.<Consol_remarksController>getController();
//               controller.setData(lbl_ref.getText());
//               Scene scene = new Scene(root); 
//
//               newStage.setScene(scene);    
//               newStage.show(); 
//               searchData();
//
//        } catch (IOException ex) {
//            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
//            }

            FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("/FXMLS/Core1_Main/Modals/consol_remarks.fxml"));
        try{
            load.load();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
            
                Consol_remarksController vcc = load.getController();
                Parent p = load.getRoot();
                Stage st = new Stage();
                st.setScene(new Scene(p));
                st.setResizable(false);
                st.initStyle(StageStyle.DECORATED);
                st.show();
                vcc.setData(lbl_ref.getText());

        }
    }

    @FXML
    private void consolidate(ActionEvent event) {
        if("".equals(txt_search_zip.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "ERROR", "Please enter package destination !");
            alert.open();
        }else{
            try {
                conn = DBConnector.getConnection();
                
                String sql = "INSERT INTO aerolink.tbl_core1_consolidation(consol_no , rec_zip, total_weight) Values (?,?,?);";
                
                pst = conn.prepareStatement(sql);

                pst.setString(1, lbl_consol_no.getText());
                pst.setString(2, txt_search_zip.getText());
                pst.setString(3, lbl_total_weight.getText());
                
                pst.execute();
                update_status();
                initTable();
                txt_search_zip.clear();
                setTotalWeight();
                searchData();
                setConsolidationNo();
                
                Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "", "", "Consolidation Successful !");
                alert.open();
                
            } catch (SQLException ex) {
                Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    public void update_status(){
        
        try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_booking set status ='Consolidated  #"+lbl_consol_no.getText()+"' where status = 'For Consolidation' and rec_province='"+txt_search_zip.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();

                         
        } catch (SQLException ex) {
            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void viewConsol(ActionEvent event) {
        
//        Modal md = Modal.getInstance(new Form("/FXMLS/Core1_Main/Modals/deconsolidation_view.fxml").getParent());
//        md.open();
//        md.getF().getStage().setOnCloseRequest(action -> {
//        FXMLS.Core1_Main.Modals.Deconsolidation_viewController.modalOpen = false;
//        searchData();
//        }); 
        
        AlertBox a =  new AlertBox();
        Deconsolidation_viewController ad = new Deconsolidation_viewController();
        a.loadfxml("/FXMLS/Core1_Main/Modals/deconsolidation_view.fxml", ad);
    }
    
    @FXML
    public void displayTable(){
        tbl_consol.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                db_booking cbt = tbl_consol.getItems().get(tbl_consol.getSelectionModel().getSelectedIndex());
                    combo_province.setValue(cbt.getRec_province());
                    lbl_ref.setText(cbt.getRef_no());
                    setTotalWeight();
            } 
        });
    }

    @FXML
    private void viewHold(ActionEvent event) {
            
//        Modal md = Modal.getInstance(new Form("/FXMLS/Core1_Main/Modals/consol_hold.fxml").getParent());
//        md.open();
//        md.getF().getStage().setOnCloseRequest(action -> {
//        FXMLS.Core1_Main.Modals.Consol_holdController.modalOpen = false;
//        searchData();
//        });

        AlertBox a =  new AlertBox();
        Consol_holdController ad = new Consol_holdController();
        a.loadfxml("/FXMLS/Core1_Main/Modals/consol_hold.fxml", ad);
    }

    @FXML
    private void refresh_table(ActionEvent event) {
        initTable();
        lbl_ref.setText("");
    }
    
    public void fillProvince(){
    
    search_province.clear();

        try {   
            conn = DBConnector.getConnection();
            
            String query = "select DISTINCT(rec_province) as rec_province from aerolink.tbl_core1_booking where status = 'For Consolidation' and rec_province != 'sample_data' order by rec_province asc";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                search_province.add(new db_booking(rs.getString("rec_province"), rs.getString("rec_province")
                                             , rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province"),rs.getString("rec_province"),rs.getString("rec_province"),rs.getString("rec_province"),rs.getString("rec_province")
                                             , rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province") ,rs.getString("rec_province")
                                             , rs.getString("rec_province"), rs.getString("rec_province"), rs.getString("rec_province"), rs.getString("rec_province"), rs.getString("rec_province"), rs.getString("rec_province"), rs.getString("rec_province")).getRec_province());
                
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_province.setItems(search_province);
        
    }
  
}
