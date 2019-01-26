/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import   javafx.application.*;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Document_ManagementController implements Initializable {
    
    
    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    private ResultSet rs  = null;
    private String directory = "src/FXMLS/Administrative/PDFFiles/";
    private String directory2 = "";
   
    @FXML
    private JFXTextField locationfield111111;
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
    private TableView<admin_doc_request> tabledocrequest;
    private ObservableList<admin_doc_request> tbldocrequest = FXCollections.observableArrayList();
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
    public TableView<fortblreqstoring> tblforreqstoring;
    private ObservableList<fortblreqstoring> tblreqstoring = FXCollections.observableArrayList();
   
    @FXML
    private JFXTextField txtdoctitle;
    @FXML
    private JFXTextField txtdepartment;
    @FXML
    private JFXTextField txtdocno;
    @FXML
    private JFXTextField txtrequestor;
    @FXML
    private JFXButton btnsend;
    @FXML
    private JFXButton btncancel;
    @FXML
    private JFXButton btndispose;
    @FXML
    private JFXTextField txtdocumentno;
    @FXML
    private JFXButton btnretrieved;
    @FXML
    private TableColumn<?, ?> docno;
    @FXML
    private TableColumn<?, ?> docname;
    @FXML
    private TableColumn<?, ?> doccat;
    @FXML
    private TableColumn<?, ?> doctime;
    @FXML
    private TableColumn<?, ?> docstat;
    @FXML
    private TableView<fortblreqstoring2> tbl2;
    ObservableList<fortblreqstoring2> tbl02 = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtdocumentno1;
    @FXML
    private TableView<docfiles> tbldocfiles;
    @FXML
    private TableColumn<docfiles, String> docfiles;
    @FXML
    private TableView<tblSentItems> tblsentitems;
    ObservableList<tblSentItems>tablesentitems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> sentreq;
    @FXML
    private TableColumn<?, ?> sentdoc;
    @FXML
    private TableColumn<?, ?> senttime;
    @FXML
    private TableColumn<?, ?> sentdate;
    @FXML
    private TableColumn<?, ?> sentstat;
    @FXML
    private AnchorPane anchortxt;
    @FXML
    private JFXButton btnadddocument;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblclick();
       
        requesttbl();
       loadrequesttbl();
       loadtblreqstoring();
        tblclick2();
        loadtblreqstoring2();
        tblclick3();
        
        tblsentitems();
        
        ObservableList<docfiles> l = doclist();
        tbldocfiles.setItems(l);
      
    }    

    
    @FXML
    public void opendialog(){
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        requestedtxt.setText(""+f);
    }
    
    
    
    public void requesttbl(){
        
        reqno.setCellValueFactory(new PropertyValueFactory<>("docreq"));
        doctitle.setCellValueFactory(new PropertyValueFactory<>("reqtitle"));
        docdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        docrequestor.setCellValueFactory(new PropertyValueFactory<>("reqby"));
        docdate.setCellValueFactory(new PropertyValueFactory<>("datereq"));
        
        documentno.setCellValueFactory(new PropertyValueFactory<>("docno"));
        documentname.setCellValueFactory(new PropertyValueFactory<>("docname"));
        docdepartment.setCellValueFactory(new PropertyValueFactory<>("docdep"));
        documentcategory.setCellValueFactory(new PropertyValueFactory<>("doccat"));
        documentstatus.setCellValueFactory(new PropertyValueFactory<>("docstat"));
        
        docno.setCellValueFactory(new PropertyValueFactory<>("docno"));
        docname.setCellValueFactory(new PropertyValueFactory<>("docname"));
        doccat.setCellValueFactory(new PropertyValueFactory<>("doccat"));
        doctime.setCellValueFactory(new PropertyValueFactory<>("doctime"));
        docdate.setCellValueFactory(new PropertyValueFactory<>("docdate"));
        docstat.setCellValueFactory(new PropertyValueFactory<>("docstat"));
        
        docfiles.setCellValueFactory(new PropertyValueFactory<>("docfile"));
        
        sentreq.setCellValueFactory(new PropertyValueFactory<>("reqno"));
        sentdoc.setCellValueFactory(new PropertyValueFactory<>("doctitle"));
        senttime.setCellValueFactory(new PropertyValueFactory<>("senttime"));
        sentdate.setCellValueFactory(new PropertyValueFactory<>("sentdate"));
        sentstat.setCellValueFactory(new PropertyValueFactory<>("status"));
        
    }
    
    private ObservableList<docfiles> doclist(){
        docfiles d1 = new docfiles("201 Files");
        docfiles d2 = new docfiles("Contracts");
        docfiles d3 = new docfiles("Shipment Files");
        docfiles d4 = new docfiles("Legal Records");
        docfiles d5 = new docfiles("Procurement Reports");
        docfiles d6 = new docfiles("WareHouse Reports");
        docfiles d7 = new docfiles("Other Files");
        
        ObservableList<docfiles> list = FXCollections.observableArrayList(d1,d2,d3,d4,d5,d6,d7);
        return list;
    }
    
    //Table Request Table
    private void loadrequesttbl(){
        tbldocrequest.clear();
        try{
            String query  = "select * from aerolink.admin_document_request where Status = 'Pending'";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
            tbldocrequest.add(new admin_doc_request(""+rs.getInt("Document Request No"),rs.getString("Request Title"),rs.getString("Description"),rs.getString("Request By"),""+rs.getDate("Date")));
            }
            tabledocrequest.setItems(tbldocrequest);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //Table ReqStoring Pending
    public void loadtblreqstoring(){
          tblreqstoring.clear();
        try{
            String query  = "SELECT * from aerolink.admin_document_reqstoring where Status = 'Pending' ";
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
    
    //Table ReqStoring Stored
    public void loadtblreqstoring2(){
        tbl02.clear();
        try{
            String query = "select * from aerolink.admin_document_reqstoring where Status = 'Stored' ";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                tbl02.add(new fortblreqstoring2(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Category"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status")));
            }
            tbl2.setItems(tbl02);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    //For Table Sent Items
    private void tblsentitems(){
        tablesentitems.clear();
        try{
            String query = " select * from aerolink.admin_document_approved as aada inner join aerolink.admin_document_request as aadr on aada.[Document Request No] = aadr.[Document Request No]";
            pst = con.prepareStatement(query);
            rs  = pst.executeQuery();
            while(rs.next()){
            tablesentitems.add(new tblSentItems(""+rs.getInt("Request No"),rs.getString("Request Title"),""+rs.getTime("Sent Time"),""+rs.getDate("Sent Date"),rs.getString("Status")));
            }
            tblsentitems.setItems(tablesentitems);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    @FXML
    public void adddocument(){
        AlertBox a =  new AlertBox();
        Add_DocumentController ad = new Add_DocumentController();
        a.loadfxml("Add_Document.fxml", ad);
        
    }
    
    //Table Click for Document Request
    public void tblclick(){
        tabledocrequest.setOnMouseClicked(e -> {
        admin_doc_request adr = tabledocrequest.getItems().get(tabledocrequest.getSelectionModel().getSelectedIndex());
        txtdocno.setText(adr.getDocreq());
        
        try{
           String query = "select * from aerolink.admin_document_request where [Document Request No] = '"+txtdocno.getText()+"'";
           pst = con.prepareStatement(query);
           rs = pst.executeQuery();
           while(rs.next()){
               txtdepartment.setText(rs.getString("Department"));
               txtdoctitle.setText(rs.getString("Request Title"));
               txtrequestor.setText(rs.getString("Request By"));
           }       
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        });
        
    }
    
    //Table Click for Document Storing
    public void tblclick2(){
        tblforreqstoring.setOnMouseClicked(e ->{ 
        fortblreqstoring fbs = tblforreqstoring.getItems().get(tblforreqstoring.getSelectionModel().getSelectedIndex());
        txtdocumentno.setText(fbs.getDocno());
        });
        
    }
    
    //Retrieving Document storing
    @FXML
   public void retrieving(){
       try{
            String query  = "update  aerolink.admin_document_reqstoring set Time = ?, Date = ?, Status = 'Stored' where [Documents No] = ? ";
            pst = con.prepareStatement(query);
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(1, st);
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(2, date);
            pst.setString(3, txtdocumentno.getText());
            pst.execute();
            AlertBox.display("Alert", "Document is now Stored");
            loadtblreqstoring();
            loadtblreqstoring2();
            
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
       
   }
   
   //Table Click for Reading File
   public void tblclick3(){
       tbl2.setOnMouseClicked(e -> {
          fortblreqstoring2 fbs2 = tbl2.getItems().get(tbl2.getSelectionModel().getSelectedIndex());
          txtdocumentno1.setText(fbs2.getDocno());
       });
   }
   
  
    @FXML
   public void readfile(){
       try{
           String query =  "select [Doc File],[Documents Name] from aerolink.admin_document_reqstoring where [Documents No] = '"+txtdocumentno1.getText()+"' ";
           pst = con.prepareStatement(query);
           rs = pst.executeQuery();
           
             
           while(rs.next()){
            
              File f = new File(directory + txtdocumentno1.getText()+" "+rs.getString("Documents Name")+ ".pdf");
              FileOutputStream out = new FileOutputStream(f);
               InputStream input = rs.getBinaryStream("Doc File");
               
               byte[] buffer = new byte[1024];
               while(input.read(buffer) > 0){
                   out.write(buffer);
               }
             if(f.exists()){
                 if(Desktop.isDesktopSupported()){
                     try{
                         Desktop.getDesktop().open(f);
                     }catch(Exception ex){
                         System.out.println(ex.getMessage());
                     }
                  }
              }else{
                 ;
                 AlertBox.display("Alert", "File is not Exist");
             }
            }
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
   
   }
   
   
    @FXML
   public void insertandupdate(){
       try{
           if(btnsend.isArmed()){
            String query = "update aerolink.admin_document_request set Status = 'Approved' where [Document Request No] = '"+txtdocno.getText()+"' ";
            pst = con.prepareStatement(query);
            pst.execute();
           }
           else if(btndispose.isArmed()){
            String query = "update aerolink.admin_document_request set Status = 'Disapproved' where [Document Request No] = '"+txtdocno.getText()+"' ";
            pst = con.prepareStatement(query);
            pst.execute();   
           }
            loadrequesttbl();
       try{
           String query2 = "insert into aerolink.admin_document_approved values(?,?,?,?,?)";
           pst = con.prepareStatement(query2);
           pst.setString(1, txtdocno.getText());
           
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(2, st);
            
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(3, date);
            if(btnsend.isArmed()){
            File f = new File(directory2+requestedtxt.getText());
            FileInputStream fs = new FileInputStream(f);
            pst.setBinaryStream(4, fs);
            pst.setString(5, "Approved");
            AlertBox.display("Alert", "Requested File is now Approved");
            }
            
            else if(btndispose.isArmed()){
            File f = new File("src/FXMLS/Administrative/PDFFiles/NOTAPPROVED.pdf");
            FileInputStream fs = new FileInputStream(f);    
            pst.setBinaryStream(4, fs);
            pst.setString(5, "Disapproved");
            AlertBox.display("Alert", "Requested File Disapproved");
            }
            pst.execute();
           
            tblsentitems();
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
        AlertBox.cleartext(anchortxt);
   }
   
    @FXML
     public void cancel(){
      AlertBox.cleartext(anchortxt);
   }
    
}
