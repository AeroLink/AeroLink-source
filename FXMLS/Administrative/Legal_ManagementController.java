/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Legal_ManagementController implements Initializable {

    @FXML
    private JFXButton schedsave;
    @FXML
    private JFXButton schedsave3;
    @FXML
    private JFXButton schedsave2;
    @FXML
    private JFXButton schedsave21;
    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @FXML
    private JFXTextField cname;
    @FXML
    private TableColumn<?, ?> ctime;
    @FXML
    private TableColumn<?, ?> cdate;
    @FXML
    private JFXTextField cpathfile;
    @FXML
    private JFXTextField csearch;
    @FXML
    private JFXComboBox<String> cdepartment;
    ObservableList<String> cdepartment1 = FXCollections.observableArrayList("HR 1","HR 2","HR 3","HR 4", "Core 1", "Core 2", "Logistic 1", "Logistic 2");
    @FXML
    private TableColumn<?, ?> cdepart;
    @FXML
    private TableColumn<?, ?> contracname;
    @FXML
    private AnchorPane canchor;
    @FXML
    private TableView<tbl_admin_files> ctable;
    ObservableList<tbl_admin_files> ctable1 = FXCollections.observableArrayList();
    @FXML
    private TableView<tbl_admin_reqfiles> reqtable;
    ObservableList<tbl_admin_reqfiles> reqtable1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> reqtitle;
    @FXML
    private TableColumn<?, ?> reqtime;
    @FXML
    private TableColumn<?, ?> reqdate;
    @FXML
    private TableColumn<?, ?> reqdesc;
    @FXML
    private JFXTextField reqfilepath;
    @FXML
    private JFXDatePicker dateissued;
    @FXML
    private JFXDatePicker dateexpired;
    @FXML
    private JFXTextField txtreqtitle;
    @FXML
    private TableColumn<?, ?> requestno;
    @FXML
    private TableColumn<?, ?> requesttitle;
    @FXML
    private TableColumn<?, ?> requesttime;
    @FXML
    private TableColumn<?, ?> requestdate;
    @FXML
    private TableColumn<?, ?> requestdescription;
    @FXML
    private TableColumn<?, ?> requesetstatus;
    @FXML
    private TableView<tbl_admin_reqfiles> requesttablefiles;
    ObservableList<tbl_admin_reqfiles> requesttablefiles1 = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cdepartment.setItems(cdepartment1);
        displaycontracts();
        tbl_admin_reqfiles();
        click();
        displayrequest1();
    }    
    
    @FXML
    public void insert(){
    
        try{
            String insert = "insert into aerolink.admin_legal_files values(?,?,?,?,?)";
            pst = con.prepareStatement(insert);
            pst.setString(1,cname.getText());
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(2, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(3, date);
            pst.setString(4, cdepartment.getSelectionModel().getSelectedItem());
            File f = new File(""+cpathfile.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(5, fs);
                 
            pst.execute();
            AlertBox.display("Alert", "File is Added");
            AlertBox.cleartxt(canchor);
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    @FXML
    public void opendialog(){
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("DOC Files", "*.doc");
            fc.getExtensionFilters().add(exf1);
            File f = fc.showOpenDialog(null);
            if(f != null){
           
                cpathfile.setText(""+f);
            }
            else{
               AlertBox.display("Alert", "No File Selected");
            }

    }
    
    public void displaycontracts(){
        contracname.setCellValueFactory(new PropertyValueFactory<>("name"));
        ctime.setCellValueFactory(new PropertyValueFactory<>("timeadded"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("dateadded"));
        cdepart.setCellValueFactory(new PropertyValueFactory<>("department"));
        
        ctable1.clear();
        try{
            String select  = "select * from aerolink.admin_legal_files";
            pst = con.prepareStatement(select);
            rs = pst.executeQuery();
            while(rs.next()){
                ctable1.add(new tbl_admin_files(rs.getString("File Name"),""+rs.getString("Time Added"),""+rs.getString("Date Added"),rs.getString("Department")));
            }
         ctable.setItems(ctable1);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public void tbl_admin_reqfiles(){
        
        reqtitle.setCellValueFactory(new PropertyValueFactory<>("reqtitle"));
        reqtime.setCellValueFactory(new PropertyValueFactory<>("time"));
        reqdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        reqdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        reqtable1.clear();
            try{
                String query = "select * from aerolink.admin_legal_request1 where Status  = 'Pending'";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    reqtable1.add(new tbl_admin_reqfiles(""+rs.getString("Req No"),rs.getString("Req Title"),""+rs.getString("Time"),""+rs.getString("Date"),rs.getString("Description"),rs.getString("Status")));
                }
                reqtable.setItems(reqtable1);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        
    }
    
    
    public void click(){
        reqtable.setOnMouseClicked(e->{
            
            try{
            tbl_admin_reqfiles tar = reqtable.getItems().get(reqtable.getSelectionModel().getSelectedIndex());
                String select = "select * from aerolink.admin_legal_request1 where [Req No] = '"+tar.getReqno()+"' ";
                pst = con.prepareStatement(select);
                rs = pst.executeQuery();
                while(rs.next()){
                    txtreqtitle.setText(rs.getString("Req Title"));
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage()  );
            }
        });
    }
    
    public void displayrequest1(){
        requestno.setCellValueFactory(new PropertyValueFactory<>("reqno"));
        requesttitle.setCellValueFactory(new PropertyValueFactory<>("reqtitle"));
        requesttime.setCellValueFactory(new PropertyValueFactory<>("time"));
        requestdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        requestdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        requesetstatus.setCellValueFactory(new PropertyValueFactory<>("satatus"));
        
        
        requesttablefiles1.clear();
            try{
                String query = "select * from aerolink.admin_legal_request1 where Status  = 'Approved' or Status = 'DisApproved'";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    requesttablefiles1.add(new tbl_admin_reqfiles(""+rs.getString("Req No"),rs.getString("Req Title"),""+rs.getString("Time"),""+rs.getString("Date"),rs.getString("Description"),rs.getString("Status")));
                }
                requesttablefiles.setItems(requesttablefiles1);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void update(){
        try{
            tbl_admin_reqfiles tar = reqtable.getItems().get(reqtable.getSelectionModel().getSelectedIndex());
            if(schedsave3.isArmed()){
            String sql = "update aerolink.admin_legal_request1 set Time = ?, Date = ?, Status = 'Approved' where [Req No] = '"+tar.getReqno()+"' ";
            pst = con.prepareStatement(sql);
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(1, st);
            pst.setString(2, dateissued.getValue().toString());
            pst.execute();
            AlertBox.display("Alert", "Request Approved");
            } 
            else if(schedsave.isArmed()){
            String sql2 = "update aerolink.admin_legal_request1 set Time = ?, Date = ?, Status = 'DisApproved' where [Req No] = '"+tar.getReqno()+"' ";
            pst = con.prepareStatement(sql2);
             java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(1, st);
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(2, date);
            pst.execute();
            AlertBox.display("Alert", "Request is Disapproved");
            }
         displayrequest1();
         tbl_admin_reqfiles();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
}
