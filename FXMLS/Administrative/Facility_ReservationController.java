/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import Model.ADMIN_Facility_Reservation;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.highgui.HighGui;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Facility_ReservationController implements Initializable {


    ADMIN_Facility_Reservation afr = new ADMIN_Facility_Reservation();
    ObservableList<String> faci = FXCollections.observableArrayList();
    ObservableList<String> urgent = FXCollections.observableArrayList("10", "20", "30");
    ObservableList<String> fmbox = FXCollections.observableArrayList();
    ObservableList<String> fbox= FXCollections.observableArrayList("Meeting","Event","Training","Seminar");
    
    
    
    private JFXComboBox<String> facilitybox;
    @FXML
    private TableColumn<?, ?> cfacility;
    @FXML
    private TableColumn<?, ?> cpurpose;
    @FXML
    private TableColumn<?, ?> cstartdate;
    @FXML
    private TableColumn<?, ?> cstarttime;
    @FXML
    private TableColumn<?, ?> crby;
    @FXML
    private TableColumn<?, ?> cstatus;
    
    private Connection con = DBconnection.con();
    private PreparedStatement pst= null;
    private ResultSet rs = null;
    private ObservableList<ADMINfacility> adminfrr = FXCollections.observableArrayList();
    private  ObservableList<ADMINfacility> adminfrapproved = FXCollections.observableArrayList();
    @FXML
    private TableView<ADMINfacility> tablefr;
    @FXML
    private JFXButton frapproved;
    private JFXComboBox<String> Fmanagerbox;
    private JFXTextField locationfield;
    private JFXTextField capacityfield;
    private ImageView frimageview;
    private Image image,image2;
    private OutputStream output;
    private InputStream input;
    private ImageView frreservationiview;
    @FXML
    private TableColumn<?, ?> FRreservationid;
    @FXML
    private JFXTextField frid;
    private JFXTextField locationfield1;
    private JFXTextField capacityfield1;
    @FXML
    private TableColumn<?, ?> FRreservationid1;
    @FXML
    private TableColumn<?, ?> cfacility1;
    @FXML
    private TableColumn<?, ?> cpurpose1;
    @FXML
    private TableColumn<?, ?> cstartdate1;
    @FXML
    private TableColumn<?, ?> cstarttime1;
    @FXML
    private TableColumn<?, ?> crby1;
    @FXML
    private TableColumn<?, ?> cstatus1;
    @FXML
    public  TableView<ADMINfacility> tablefrapproved;
    @FXML
    private CheckBox chk1;
    @FXML
    private CheckBox chk2;
    @FXML
    private CheckBox chk3;
    @FXML
    private CheckBox chk4;
    @FXML
    private CheckBox chk5;
    @FXML
    private CheckBox chk6;
    @FXML
    private CheckBox chk7;
    @FXML
    private CheckBox chk8;
    @FXML
    private CheckBox chk9;
    @FXML
    private CheckBox chk10;
    @FXML
    private CheckBox chk11;
    @FXML
    private CheckBox chk12;
    private JFXTextField facilityidtxt;
    @FXML
    private JFXTextField fordisplaytxt;
    @FXML
    private JFXButton viewbtn;
    @FXML
    private JFXButton refreshbtn;
    @FXML
    private AnchorPane registerbtn;
    @FXML
    private FontAwesomeIconView showvisitorreq;
    @FXML
    private TableColumn<?, ?> factid;
    @FXML
    private TableColumn<?, ?> factname;
    @FXML
    private TableColumn<?, ?> facttype;
    @FXML
    private TextField facilityidtext;
    @FXML
    private TextField facilitynametext;
    @FXML
    private TextField capacitytext;
    @FXML
    private JFXTextField imagetext;
    @FXML
    private JFXButton frapproved1;
    @FXML
    private TableView<tbl_facility> facilitytable;
    ObservableList<tbl_facility> facilitytable1 = FXCollections.observableArrayList();
    @FXML
    private TableView<tbl_registered_facility> registeredfacility;
    ObservableList<tbl_registered_facility> registeredfacility1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> factid1;
    @FXML
    private TableColumn<?, ?> factname1;
    @FXML
    private TableColumn<?, ?> facttype1;
    @FXML
    private TableColumn<?, ?> factstatus1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FRTable();
        loaddataFRpending();
        loaddataFRapproved();
     
        displayfacility();
        tableclick();
        displayinfo();
        factclick();
        facilityregistered();
    }    
    
    List b = afr.get();
    
    
   //For inserting reservation
   /* @FXML
    public void saveFR(){
    try{

     Object facility_request[][] = {
                {"Facility_ID",facilityidtxt.getText()},
                {"Purpose", purposebox.getSelectionModel().getSelectedItem()},
                {"Start_Date", ((TextField)startdate.getEditor()).getText()},
                {"End_Date", ((TextField)enddate.getEditor()).getText()},
                {"Start_Time", ((TextField)starttime.getEditor()).getText()},
                {"End_Time", ((TextField)endtime.getEditor()).getText()},
                {"Reserved_By",reservedby.getText()},
                {"Urgency_Level",urgencybox.getSelectionModel().getSelectedItem()},
                {"Status", "Pending"}
                }; if(afr.insert(facility_request)){
                    AlertBox.display("Alert", "Data Save Successfull");
                    FRTable();
                    loaddataFRpending();
                    }    
    }catch(Exception ex){ System.out.print(ex.getMessage());}
    
    }*/
    
    //for table retrieve
    private void FRTable(){
        //Pending
        FRreservationid.setCellValueFactory(new PropertyValueFactory<>("Facility_reservation_ID"));
        cfacility.setCellValueFactory(new PropertyValueFactory<>("Facility_ID"));
        cpurpose.setCellValueFactory(new PropertyValueFactory<>("Purpose"));
        cstartdate.setCellValueFactory(new PropertyValueFactory<>("Start_Date"));
        cstarttime.setCellValueFactory(new PropertyValueFactory<>("Start_Time"));
        crby.setCellValueFactory(new PropertyValueFactory<>("Reserved_By"));
        cstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        //Approved
         FRreservationid1.setCellValueFactory(new PropertyValueFactory<>("Facility_reservation_ID"));
        cfacility1.setCellValueFactory(new PropertyValueFactory<>("Facility_ID"));
        cpurpose1.setCellValueFactory(new PropertyValueFactory<>("Purpose"));
        cstartdate1.setCellValueFactory(new PropertyValueFactory<>("Start_Date"));
        cstarttime1.setCellValueFactory(new PropertyValueFactory<>("Start_Time"));
        crby1.setCellValueFactory(new PropertyValueFactory<>("Reserved_By"));
        cstatus1.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }
    
    //Load data from database
    private void loaddataFRpending(){
        adminfrr.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation as aafr inner join aerolink.tbl_log1_AssetFacility as atla on aafr.FacilityID = atla.FacilityID where Status = 'Pending' ");
            rs = pst.executeQuery();
            while(rs.next()){
            adminfrr.add(new ADMINfacility(""+rs.getString("Facility_Reservation_ID"),rs.getString("FacilityID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        } 
        } catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablefr.setItems(adminfrr);
    }
    
      //Load data from database
    @FXML
    public void loaddataFRapproved(){
        
       
        adminfrapproved.clear();
        try {
            pst = con.prepareStatement("Select * from aerolink.admin_facility_reservation where Status = 'Approved' or Status = 'Canceled' ");
            rs = pst.executeQuery();
            while(rs.next()){
            adminfrapproved.add(new ADMINfacility(""+rs.getInt("Facility_Reservation_ID"),""+rs.getInt("FacilityID"),rs.getString("Purpose"),""+rs.getDate("Start_Date"),""+rs.getTime("Start_Time"),rs.getString("Reserved_By"),rs.getString("Status")));
        } 
        } catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        tablefrapproved.setItems(adminfrapproved);
     
        }
    
    
    //For Facility Reservation box
     /* public void choosefacilityFR(){
          faci.clear();
        String query = "Select Facility_Name from aerolink.admin_facility where Status = 'Enable' ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
               faci.add(rs.getString("Facility_Name"));
           }
            
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        facilitybox.setItems(faci);
    }*/
      
    //For filling Combobox 
    /*public void choosefacility(){
        String query = "Select Facility_Name from aerolink.admin_facility ";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
               fmbox.add(rs.getString("Facility_Name"));
                }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }*/
    
    //Facility display the value in textfield
    /*public void facilityselection(){
        String query = "Select * from aerolink.admin_facility where Facility_Name = ? ";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, (String)Fmanagerbox.getSelectionModel().getSelectedItem());
          
            rs = pst.executeQuery();
            
            while(rs.next()){
                locationfield.setText(rs.getString("Location"));
                capacityfield.setText(rs.getString("Capacity"));
                
                input = rs.getBinaryStream("Image");
                output = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                int size = 0;
                while((size = input.read(contents))!= -1){
                    output.write(contents, 0 ,size);
                }
               image = new Image("file:photo.jpg",frimageview.getFitWidth(),frimageview.getFitHeight(), true,true);
               frimageview.setImage(image);
               
            }
            pst.close();
            rs.close();
            
        }catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }*/
    
    //Facility Resrvation Combobox
     /* public void facilityreservationselect(){
        String query = "Select * from aerolink.admin_facility where Facility_Name = ? ";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, (String)facilitybox.getSelectionModel().getSelectedItem());
          
            rs = pst.executeQuery();
           
            while(rs.next()){
                
                locationfield1.setText(rs.getString("Location"));
                capacityfield1.setText(rs.getString("Capacity"));
                facilityidtxt.setText(rs.getString("Facility_ID"));
                
                input = rs.getBinaryStream("Image");
                output = new FileOutputStream(new File("photo.jpg"));
                byte[] contents = new byte[1024];
                int size = 0;
                while((size = input.read(contents))!= -1){
                    output.write(contents, 0 ,size);
                }
               image = new Image("file:photo.jpg",frreservationiview.getFitWidth(),frreservationiview.getFitHeight(), true,true);
               frreservationiview.setImage(image);
               
            }
            pst.close();
            rs.close();
            
        }catch (SQLException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Facility_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }*/
    
    
   
    
    
    //update the button disable
   /* public void updateds(){
        try{
            String query = "update aerolink.admin_facility set Status = 'Disable' where Facility_Name = '"+Fmanagerbox.getSelectionModel().getSelectedItem()+"' ";
            pst = con.prepareStatement(query);
            AlertBox.display("Alert", "'"+Fmanagerbox.getSelectionModel().getSelectedItem()+"' is now Closed ");
            pst.execute();
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }  
      
    }*/
    
    //update the button enable
    /*public void updateen(){
        try{
            String query = "update aerolink.admin_facility set Status = 'Enable' where Facility_Name = '"+Fmanagerbox.getSelectionModel().getSelectedItem()+"' ";
            pst = con.prepareStatement(query);
            AlertBox.display("Alert", ""+Fmanagerbox.getSelectionModel().getSelectedItem()+" is now Open ");
            pst.execute();      
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
   
    }*/
    
    //button for approved reservation
    @FXML
    public void FRapproved(){
        try{    
            String query = "update aerolink.admin_facility_reservation set Status =  'Approved' where Facility_Reservation_ID = '"+frid.getText()+"' ";
            pst = con.prepareStatement(query);
             AlertBox.display("Alert",  "Facility Approved");
             pst.execute();
             
       }catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        loaddataFRpending();
        loaddataFRapproved();
    
    }
    
    //For Table Click
    public void tableclick(){
         tablefr.setOnMouseClicked(e ->{
         ADMINfacility afr = tablefr.getItems().get(tablefr.getSelectionModel().getSelectedIndex()); 
         frid.setText(afr.getFacility_reservation_ID());
      });
    }
    
    
    @FXML
    public void tblhistory(){
       checkbox.Arrangement(chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10,chk11,chk12, tablefrapproved);
    }
    
    public void displayinfo(){
        
        tablefrapproved.setOnMouseClicked(e ->{
         ADMINfacility afr = tablefrapproved.getItems().get(tablefrapproved.getSelectionModel().getSelectedIndex());
         fordisplaytxt.setText(afr.getFacility_reservation_ID());
        });
        
    }
    
    @FXML
    public void view(){
      
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("Facility_Reservation_info.fxml"));
        try{
         load.load();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        Facility_Reservation_infoController fri = load.getController();
      
        Parent p = load.getRoot();
        Stage st = new Stage();
        st.setScene(new Scene(p));
        st.show();
          fri.passtxt(fordisplaytxt.getText());
        fri.displayinfo();
    }
    
    @FXML
   public void showvisitorreq(){
       AlertBox ab = new AlertBox();
       
       RequestForm_FacilityController rf = new RequestForm_FacilityController();
       ab.loadfxml("RequestForm_Facility.fxml", rf);
   }
   
   
   private void displayfacility(){
        factid.setCellValueFactory(new PropertyValueFactory<>("fid"));
        factname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        facttype.setCellValueFactory(new PropertyValueFactory<>("ftype"));
        
        facilitytable1.clear();
                try{
                    String select = "select * from aerolink.tbl_log1_AssetFacility where FacilityStatus = 'Vacant'";
                    pst = con.prepareStatement(select);
                    rs = pst.executeQuery();
                    while(rs.next()){
                        facilitytable1.add(new tbl_facility(""+rs.getString("FacilityID"),rs.getString("FacilityName"),rs.getString("FacilityType")));
                    }
                    facilitytable.setItems(facilitytable1);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
   }
   
   
    private void factclick(){
        facilitytable.setOnMouseClicked(e->{
        
        tbl_facility tf = facilitytable.getItems().get(facilitytable.getSelectionModel().getSelectedIndex());
            try{
                String select  = "select * from aerolink.tbl_log1_AssetFacility where FacilityID = '"+tf.getFid()+"'";
                pst = con.prepareStatement(select);
                rs = pst.executeQuery();
                while(rs.next()){
                    facilityidtext.setText(rs.getString("FacilityID"));
                    facilitynametext.setText(rs.getString("FacilityName"));
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            
        });
        
    }
    
    
    public void registerfacility(){
        try{
            String insert = "insert into aerolink.admin_facility_reservation_facility values(?,?,?,?)";
            pst = con.prepareStatement(insert);
            pst.setString(1, facilityidtext.getText());
            pst.setString(2, capacitytext.getText());
            
            File f = new File(""+imagetext.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(3, fs);
            
            pst.setString(4, "Ready to Use");
            pst.execute();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        try{
                 tbl_facility tf = facilitytable.getItems().get(facilitytable.getSelectionModel().getSelectedIndex());
          
                String update = "update aerolink.tbl_log1_AssetFacility set FacilityStatus = 'Registered' where FacilityID = '"+tf.getFid()+"' ";
                pst = con.prepareStatement(update);
                pst.execute();
                AlertBox.display("Alert", "Facility is now Ready to Use");
                
            }catch(Exception ex){
                    System.out.println(ex.getMessage());
               }
        
            
        displayfacility();
        facilityregistered();
    }
    
    @FXML
    public void openfile(){
        FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("PNG Files", "*.PNG");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("JPG Files", "*.jpg");
            FileChooser.ExtensionFilter ext3 = new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg");
            fc.getExtensionFilters().addAll(exf1,ext2,ext3);
            File f = fc.showOpenDialog(null);
            
            if(f != null){
           
                imagetext.setText(""+f);
            }
            else{
               AlertBox.display("Alert", "No File Selected");
            }
    }
    
    
    private void facilityregistered(){
        factid1.setCellValueFactory(new PropertyValueFactory<>("facilitytd"));
        factname1.setCellValueFactory(new PropertyValueFactory<>("facilityname"));
        facttype1.setCellValueFactory(new PropertyValueFactory<>("facilitytype"));
        factstatus1.setCellValueFactory(new PropertyValueFactory<>("facilitystatus"));
        
        registeredfacility1.clear();
            try{
                String select = "select *,aafrf.FacilityID from aerolink.admin_facility_reservation_facility as aafrf inner join aerolink.tbl_log1_AssetFacility as atla on aafrf.FacilityID = atla.FacilityID ";
                pst = con.prepareStatement(select);
                rs = pst.executeQuery();
                while(rs.next()){
                    registeredfacility1.add(new tbl_registered_facility(""+rs.getString("FacilityID"),rs.getString("FacilityName"),rs.getString("FacilityType"),rs.getString("Status")));
                }
                registeredfacility.setItems(registeredfacility1);
            
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
}
