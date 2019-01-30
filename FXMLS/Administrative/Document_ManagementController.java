/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Document_ManagementController implements Initializable {

    
    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    private ResultSet rs  = null;
    private String directory = "";
    
    
    @FXML
    private JFXButton btntakephoto11;
    @FXML
    private JFXButton btntakephoto;
    @FXML
    private TableView<?> tablevisitoremployee11;
    @FXML
    private TableColumn<?, ?> employeeid11;
    @FXML
    private TableColumn<?, ?> name11;
    @FXML
    private TableColumn<?, ?> office11;
    @FXML
    private TableColumn<?, ?> floor11;
    @FXML
    private JFXTextField requestedtxt;
    @FXML
    private JFXButton btntakephoto2;
    @FXML
    private JFXButton btntakephoto3;
    @FXML
    private JFXButton btntakephoto13;
    @FXML
    private TableColumn<?, ?> reqno;
    @FXML
    private TableColumn<?, ?> doctitle;
    @FXML
    private TableColumn<?, ?> docdesc;
    @FXML
    private TableColumn<?, ?> docrequestor;
    @FXML
    private TableColumn<?, ?> docdate;
    @FXML
    private TableView<tbladmin_doc_request> tabledocrequest;
    ObservableList<tbladmin_doc_request> tbldocrequest = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> documentno;
    @FXML
    private TableColumn<?, ?> documentname;
    @FXML
    private TableColumn<?, ?> docdepartment;
    @FXML
    private TableColumn<?, ?> documentcategory;
    @FXML
    private TableColumn<?, ?> documentstatus;
    @FXML
    private TableView<fortblreqstoring> tblforreqstoring;
    private ObservableList<fortblreqstoring> tblreqstoring = FXCollections.observableArrayList();
    @FXML
    private JFXButton btntakephoto14;
    @FXML
    private JFXButton btntoarch;
    @FXML
    private TableColumn<?, ?> docstatus;
    @FXML
    private TextField txtreqno;
    @FXML
    private TextField txtdoctitle;
    @FXML
    private TextField txtreq;
    @FXML
    private AnchorPane anchortxt;
    @FXML
    private TableColumn<?, ?> requestno;
    @FXML
    private TableColumn<?, ?> documentt;
    @FXML
    private TableColumn<?, ?> requestby;
    @FXML
    private TableColumn<?, ?> senttime;
    @FXML
    private TableColumn<?, ?> sentdate;
    @FXML
    private TableColumn<?, ?> status1;
    @FXML
    private TableView<tbldo_approved> tbldocrequest1;
    ObservableList<tbldo_approved> tbldocrequest01 = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnsend;
    @FXML
    private JFXButton btndispose;
    @FXML
    private TableView<tbl_document_archieve> documentstable;
    ObservableList<tbl_document_archieve> documentstable1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> documentid;
    @FXML
    private TableColumn<?, ?> documentname1;
    @FXML
    private TableColumn<?, ?> documentcategory1;
    @FXML
    private TableColumn<?, ?> storetime;
    @FXML
    private TableColumn<?, ?> storedate;
    @FXML
    private TableColumn<?, ?> status2;
    @FXML
    private JFXTextField filesearch;
    @FXML
    private TableView<tbl_retrieved_files> filestable;
    ObservableList<tbl_retrieved_files> filestable1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> docid3;
    @FXML
    private TableColumn<?, ?> docname3;
    @FXML
    private TableColumn<?, ?> cat3;
    @FXML
    private TableColumn<?, ?> status3;
    @FXML
    private TableView<tbl_files_category> filescategory;
    @FXML
    private TableColumn<ObservableList,String> filecolumncat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        requesttbl();
        loadrequesttbl();
        loadtblreqstoring();
        tblclick1();
        tbldo_approved();
        tbl_document_archive();
        tbl_retrieved_files();
        tbl_files_category();
    }    

    
    
    @FXML
    public void opendialog(){
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        
        requestedtxt.setText(""+f);
    }
    
    private void requesttbl(){
        
        reqno.setCellValueFactory(new PropertyValueFactory<>("docreq"));
        doctitle.setCellValueFactory(new PropertyValueFactory<>("reqtitle"));
        docdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        docrequestor.setCellValueFactory(new PropertyValueFactory<>("reqby"));
        docdate.setCellValueFactory(new PropertyValueFactory<>("datereq"));
        docstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        documentno.setCellValueFactory(new PropertyValueFactory<>("docno"));
        documentname.setCellValueFactory(new PropertyValueFactory<>("docname"));
        docdepartment.setCellValueFactory(new PropertyValueFactory<>("docdep"));
        documentcategory.setCellValueFactory(new PropertyValueFactory<>("doccat"));
        documentstatus.setCellValueFactory(new PropertyValueFactory<>("docstat"));
        
        requestno.setCellValueFactory(new PropertyValueFactory<>("requestno"));
        documentt.setCellValueFactory(new PropertyValueFactory<>("documenttitle"));
        requestby.setCellValueFactory(new PropertyValueFactory<>("requestby"));
        senttime.setCellValueFactory(new PropertyValueFactory<>("senttime"));
        sentdate.setCellValueFactory(new PropertyValueFactory<>("sentdate"));
        status1.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        documentid.setCellValueFactory(new PropertyValueFactory<>("docno"));
        documentname1.setCellValueFactory(new PropertyValueFactory<>("docname"));
        documentcategory1.setCellValueFactory(new PropertyValueFactory<>("cate"));
        storetime.setCellValueFactory(new PropertyValueFactory<>("storetime"));
        storedate.setCellValueFactory(new PropertyValueFactory<>("storedate"));
        status2.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        docid3.setCellValueFactory(new PropertyValueFactory<>("docid"));
        docname3.setCellValueFactory(new PropertyValueFactory<>("docname"));
        cat3.setCellValueFactory(new PropertyValueFactory<>("catname"));
        status3.setCellValueFactory(new PropertyValueFactory<>("status"));
        
       filecolumncat.setCellValueFactory(new PropertyValueFactory<>("categoryname"));
    }
    
    private void loadrequesttbl(){
        tbldocrequest.clear();
        try{
            String query  = "select * from aerolink.admin_document_request where Status = 'Pending'";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
            tbldocrequest.add(new tbladmin_doc_request(""+rs.getInt("Document Request No"),rs.getString("Request Title"),rs.getString("Description"),rs.getString("Request By"),""+rs.getDate("Date"),rs.getString("Status")));
            }
            tabledocrequest.setItems(tbldocrequest);
            
        }catch(Exception ex){}
    }
    
    //Load Table for Storing
    public void loadtblreqstoring(){
            tblreqstoring.clear();
        try{
            String query = "select * from aerolink.admin_document_reqstoring where Status = 'Pending' ";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                tblreqstoring.add(new fortblreqstoring(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Department"),rs.getString("Category"),rs.getString("Status")));
            }
            tblforreqstoring.setItems(tblreqstoring);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
   
    }
    
    //Load Table tblddo_approved
    private void tbldo_approved(){
        tbldocrequest01.clear();
        try{
            String query = "select * from aerolink.admin_document_approved as aada inner join aerolink.admin_document_request as aadr on  aada.[Document Request No] = aadr.[Document Request No]";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                tbldocrequest01.add(new tbldo_approved(""+rs.getInt("Request No"),rs.getString("Request Title"),rs.getString("Request By"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status")));
            }
            tbldocrequest1.setItems(tbldocrequest01);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //Load Table Documents Archieved
    private void tbl_document_archive(){
        documentstable1.clear();
        try{
           String query =  "select * from aerolink.admin_document_reqstoring where Status = 'Stored' ";
           pst = con.prepareStatement(query);
           rs = pst.executeQuery();
           while(rs.next()){
           documentstable1.add(new tbl_document_archieve(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Category"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status")));
           }
        documentstable.setItems(documentstable1);   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        }
    
   
    
    //Load Table Retrieve Files
    @FXML
    public void tbl_retrieved_files(){
        filestable1.clear();
            try{
                String query = "select * from aerolink.admin_document_file";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    filestable1.add(new tbl_retrieved_files(""+rs.getInt("Document ID"),rs.getString("Document Name"),rs.getString("Category"),rs.getString("Status")));
                }
                filestable.setItems(filestable1);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
    //Load Table Category Files
    private void tbl_files_category(){
        ObservableList<tbl_files_category> filescategory1 = FXCollections.observableArrayList(
                new tbl_files_category("201 Files"),
                new tbl_files_category("Contracts"),
                new tbl_files_category("Shipment Files"),
                new tbl_files_category("Legal Records"),
                new tbl_files_category("Procurememt Reports"),
                new tbl_files_category("Warehouse Reports"),
                new tbl_files_category("Other Files")
        );
        
        filescategory.setItems(filescategory1);
    
    }
    
    
    @FXML
    public void adddocument(){
        AlertBox a =  new AlertBox();
        AddfilesController ad = new AddfilesController();
        a.loadfxml("addfiles.fxml", ad);
        
    }
    
   private void tblclick1(){
       
       tabledocrequest.setOnMouseClicked(e ->{ 
           
           try{
          tbladmin_doc_request tdr = tabledocrequest.getItems().get(tabledocrequest.getSelectionModel().getSelectedIndex());
         if(tdr.getDocreq() != null){
          txtreqno.setText(tdr.getDocreq());
          txtdoctitle.setText(tdr.getReqtitle());
          txtreq.setText(tdr.getReqby());
         }}catch(Exception ex){
             AlertBox.display("Alert", "No Value Selected");
         }
         });
       
       
   }
   
   
   
    @FXML
   public void cleartxt(){
       AlertBox.cleartxt(anchortxt);
   }
   
   //Button for Approved Request
    @FXML
   public void approvedrequest(){
       AlertBox.warning(anchortxt, txtreqno, "Alert", "No Data Selected");
       try{
           if(btnsend.isArmed()){
           String queryupdate = "update aerolink.admin_document_request set Status = 'Approved' where [Document Request No] = '"+txtreqno.getText()+"' ";
           pst = con.prepareStatement(queryupdate);

           pst.execute();
           loadrequesttbl();
          
           }else if(btndispose.isArmed()){
           String queryupdate1 = "update aerolink.admin_document_request set Status = 'Disapproved' where [Document Request No] = '"+txtreqno.getText()+"' ";
           pst = con.prepareStatement(queryupdate1);
           
           pst.execute();
           loadrequesttbl();
          
           }
        try{
            String query = "insert into aerolink.admin_document_approved values (?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, txtreqno.getText());
          
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(2, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(3, date);
            if(btnsend.isArmed()){
            File f = new File(directory+requestedtxt.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(4, fs);
            
            pst.setString(5, "Approved");
            pst.execute();
            AlertBox.display("Alert", "Request is now Approved");
            }
            else if (btndispose.isArmed()){
            
            File f = new File("src/FXMLS/Administrative/PDF/NOTAPPROVED.pdf");
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(4, fs);
            
            pst.setString(5, "Disapproved");
            pst.execute();
            AlertBox.display("Alert", "Request is Disapproved");
            }
            tbldo_approved();
            
            
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
   }
    
   //Button add to archive for update
    @FXML
   public void addtoarchieve(){
       try{
           fortblreqstoring frq = tblforreqstoring.getItems().get(tblforreqstoring.getSelectionModel().getSelectedIndex());
            if(frq.getDocno() != null){
           String query = "update aerolink.admin_document_reqstoring set Status = 'Stored' where [Documents No] = '"+frq.getDocno()+"' ";
           pst = con.prepareStatement(query);
           AlertBox.display("Alert", "Document is now Added to Archieve");
           pst.execute();}
        }catch(Exception ex){
           AlertBox.display("Alert", "No Value is Selected");
      }
   }
    
    
}
