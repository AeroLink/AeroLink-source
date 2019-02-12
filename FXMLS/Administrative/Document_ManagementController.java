/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


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
    private String directory1 = "src/FXMLS/Administrative/RetrievedFiles/";
    
    @FXML
    private JFXButton btntakephoto11;
    @FXML
    private JFXButton btntakephoto;
    @FXML
    private JFXTextField requestedtxt;
    @FXML
    private JFXButton btntakephoto2;
    @FXML
    private JFXButton btntakephoto3;
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
    ObservableList<tbl_files_category> filescategory1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?,?> filecolumncat;
    @FXML
    private FontAwesomeIconView adddocumentes1;
    @FXML
    private FontAwesomeIconView adddocumentes;
    @FXML
    private JFXButton btntakephoto21;
    @FXML
    private TableColumn<?, ?> rdocid;
    @FXML
    private TableColumn<?, ?> rdocname;
    @FXML
    private TableColumn<?, ?> rcategory;
    @FXML
    private TableColumn<?, ?> rtimeadded;
    @FXML
    private TableColumn<?, ?> rdateadded;
    @FXML
    private TableColumn<?, ?> dtdocno;
    @FXML
    private TableColumn<?, ?> dtdocname;
    @FXML
    private TableColumn<?, ?> dtdepartment;
    @FXML
    private TableColumn<?, ?> dtcategory;
    @FXML
    private TableColumn<?, ?> dttimestore;
    @FXML
    private TableColumn<?, ?> dtdatestore;
    @FXML
    private TableView<tbl_files> rtbl_files;
    ObservableList<tbl_files> rtbl_files1 = FXCollections.observableArrayList();
    @FXML
    private TableView<tbl_documents> dt_documents_tbl;
    ObservableList<tbl_documents> dt_documents_tbl1 = FXCollections.observableArrayList();
    @FXML
    private JFXTextField filesearch1;
    @FXML
    private TextField txtdesc;
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
        sortcategory();
    
        rtbl_files.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    

    
    
    @FXML
    public void opendialog(){
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter exf1 = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
            fc.getExtensionFilters().add(exf1);
            File f = fc.showOpenDialog(null);
               
            if(f != null){
               requestedtxt.setText(""+f);
            }
            else{
               AlertBox.display("Alert", "No File Selected");
            }
        
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
       
       rdocid.setCellValueFactory(new PropertyValueFactory<>("documentid"));
       rdocname.setCellValueFactory(new PropertyValueFactory<>("documentname"));
       rcategory.setCellValueFactory(new PropertyValueFactory<>("documentcategory"));
       rtimeadded.setCellValueFactory(new PropertyValueFactory<>("timeadded"));
       rdateadded.setCellValueFactory(new PropertyValueFactory<>("dateadded"));
       
       dtdocno.setCellValueFactory(new PropertyValueFactory<>("dtdocumentno"));
       dtdocname.setCellValueFactory(new PropertyValueFactory<>("dtdocumentname"));
       dtdepartment.setCellValueFactory(new PropertyValueFactory<>("dtdepartment"));
       dtcategory.setCellValueFactory(new PropertyValueFactory<>("dtcategory"));
       dttimestore.setCellValueFactory(new PropertyValueFactory<>("dttimestored"));
       dtdatestore.setCellValueFactory(new PropertyValueFactory<>("dtdatestored"));
       
       
    }
    
    private void loadrequesttbl(){
        tbldocrequest.clear();
        try{
            String query  = "select * from aerolink.tbl_log2_document_tracking_request where Status = 'Pending'";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
            tbldocrequest.add(new tbladmin_doc_request(""+rs.getInt("request_no"),rs.getString("subject"),rs.getString("purpose"),rs.getString("requestor"),""+rs.getDate("requestdate"),rs.getString("status")));
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
           String query =  "select * from aerolink.admin_document_reqstoring where Status = 'Stored' or Status = 'Retrieved' ";
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
        filescategory1.clear();
            try{
                String select = "select * from aerolink.admin_document_category";
                pst = con.prepareStatement(select);
                rs = pst.executeQuery();
                while(rs.next()){
                    filescategory1.add(new tbl_files_category(rs.getString("Category Name")));
                }
                filescategory.setItems(filescategory1);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
    //Load Table Files Table
    @FXML
    public void tbl_files(){
        rtbl_files1.clear();
        try{
            String query = "select * from aerolink.admin_document_file";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    rtbl_files1.add(new tbl_files(""+rs.getInt("Document ID"),rs.getString("Document Name"),rs.getString("Category"),""+rs.getTime("Time Added"),""+rs.getDate("Date Added")));
                }
                rtbl_files.setItems(rtbl_files1);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        //Load Documents Table
         dt_documents_tbl1.clear();
         try{
            String query1 = "select * from aerolink.admin_document_reqstoring where Status = 'Stored' ";
            pst = con.prepareStatement(query1);
            rs = pst.executeQuery();
            while(rs.next()){
                dt_documents_tbl1.add(new tbl_documents(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Department"),rs.getString("Category"),""+rs.getTime("Time"),""+rs.getDate("Date")));
            }
            dt_documents_tbl.setItems(dt_documents_tbl1);
        
         }catch(Exception ex){
             System.out.println(ex.getMessage());
         }
        
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
       tbladmin_doc_request tdr = tabledocrequest.getItems().get(tabledocrequest.getSelectionModel().getSelectedIndex());
       if(txtreqno.getText().equals("")){
           AlertBox.display("Alert", "No Data Selected");
       }else{
       try{
           if(btnsend.isArmed()){
           String queryupdate = "update aerolink.admin_document_request set Status = 'Approved' where [Document Request No] = '"+tdr.getDocreq()+"' ";
           pst = con.prepareStatement(queryupdate);

           pst.execute();
           loadrequesttbl();
          
           }else if(btndispose.isArmed()){
           String queryupdate1 = "update aerolink.admin_document_request set Status = 'Disapproved' where [Document Request No] = '"+tdr.getDocreq()+"' ";
           pst = con.prepareStatement(queryupdate1);
           
           pst.execute();
           loadrequesttbl();
          
           }
        try{
            String query = "insert into aerolink.admin_document_approved values (?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, tdr.getDocreq());
          
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
       }}
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
           pst.execute();
            loadtblreqstoring();
            tbl_document_archive();
            }
        }catch(Exception ex){
           AlertBox.display("Alert", "No Value is Selected");
      }
   }
   
    @FXML
    public void openfile(){
        category c = new category();
        c.openfile(filestable);
   }
    @FXML
    public void openfiles2(){
        category c = new category();
        c.openfile1(documentstable);
    }
    
    
    @FXML
    public void open(){
        AlertBox a =  new AlertBox();
        AddfilesController ad = new AddfilesController();
        a.loadfxml("Add_Document.fxml", ad);
    }
    
    String query;
    public void sortcategory(){
     filescategory.setOnMouseClicked(e ->{
         try{
          documentstable1.clear();
          tbl_files_category tfc = filescategory.getItems().get(filescategory.getSelectionModel().getSelectedIndex());
              
                 if(tfc.getCategoryname().equals("All Files")){
                    String quer1 = "select * from aerolink.admin_document_reqstoring where Status = 'Stored' ";
                pst = con.prepareStatement(quer1);
                rs = pst.executeQuery();
                while(rs.next()){
                     documentstable1.add(new tbl_document_archieve(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Category"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status")));
                }
                documentstable.setItems(documentstable1);
                
                }else if(tfc.getCategoryname().equals("Add Category")){
                    AlertBox a = new AlertBox();
                    AddcategoryController ac = new AddcategoryController();
                    a.loadfxml("addcategory.fxml", ac);
                    
                String quer1 = "select * from aerolink.admin_document_reqstoring where Status = 'Stored' ";
                pst = con.prepareStatement(quer1);
                rs = pst.executeQuery();
                while(rs.next()){
                     documentstable1.add(new tbl_document_archieve(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Category"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status")));
                }
                documentstable.setItems(documentstable1);
                
                    
                }else{
                    String query = "select * from aerolink.admin_document_reqstoring where Category = '"+tfc.getCategoryname()+"' and Status = 'Stored' ";
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                     documentstable1.add(new tbl_document_archieve(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Category"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status")));
                }
                documentstable.setItems(documentstable1);
                 }
                        
                 
                
         }catch(Exception ex){
             System.out.println(ex.getMessage());
         }
      });
     
    }    
    
    @FXML
    public void retrieved(){
        tbl_document_archieve tda = documentstable.getItems().get(documentstable.getSelectionModel().getSelectedIndex());
        try{
            String query  = "update aerolink.admin_document_reqstoring set Status = 'Retrieved' where [Documents No] = '"+tda.getDocno()+"' ";
            pst = con.prepareStatement(query);
            pst.execute();
            AlertBox.display("Alert", "Document is now Retrieved");
        try{
                String sql = "select * from aerolink.admin_document_reqstoring where [Documents No] ='"+tda.getDocno()+"'";
                pst =con.prepareStatement(sql);
                rs = pst.executeQuery();
                File fn = new File(directory1+tda.getDocno()+"-"+tda.getDocname()+".pdf");
                FileOutputStream out = new FileOutputStream(fn);
                while(rs.next()){
                InputStream input = rs.getBinaryStream("Doc File");
                byte[] buffer = new byte[1024];
                while(input.read(buffer) > 0){
                     out.write(buffer);
                 }
            }
      }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        tbl_document_archive();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    // Table Search
    @FXML
    public void search(){
        filestable1.clear();
        try{
            String search = "select * from aerolink.admin_document_file where [Document ID] like '"+filesearch.getText()+"%' or [Document Name] like '"+filesearch.getText()+"%' ";
            pst = con.prepareStatement(search);
            rs = pst.executeQuery();
            while(rs.next()){
              filestable1.add(new tbl_retrieved_files(""+rs.getInt("Document ID"),rs.getString("Document Name"),rs.getString("Category"),rs.getString("Status")));
            }
        filestable.setItems(filestable1);
        
        }catch(Exception ex){
            System.out.println();
        }
    }
    
    @FXML
    public void search1(){
        rtbl_files1.clear();
        try{
            String search2 = "select * from aerolink.admin_document_file where [Document ID] like '"+filesearch1.getText()+"%' or [Document Name] like '"+filesearch1.getText()+"%' or [Date Added] like '"+filesearch1.getText()+"%' ";
            pst = con.prepareStatement(search2);
            rs = pst.executeQuery();
            while(rs.next()){
              rtbl_files1.add(new tbl_files(""+rs.getInt("Document ID"),rs.getString("Document Name"),rs.getString("Category"),rs.getString("Time Added"),""+rs.getDate("Date Added")));
            }
        rtbl_files.setItems(rtbl_files1);
        }catch(Exception ex){
            System.out.println();
        }
    }
    
    @FXML
    public void openreq(){
       AlertBox a =  new AlertBox();
        AddfilesController ad = new AddfilesController();
        a.loadfxml("RequestForm_Document_Management.fxml", ad);
    } 
    
    @FXML
    public void printfilesdocument(){
        
       
         String srcfile = "src/FXMLS/Administrative/files_table2_report.jrxml";
            try{
            JasperReport jpr = JasperCompileManager.compileReport(srcfile);
            HashMap<String, Object> hm = new HashMap<>();
          
            ArrayList<tbl_files> tfiles = new ArrayList<>();
            for(tbl_files tf  : rtbl_files1){
               
                tfiles.add(new tbl_files(tf.getDocumentid(),""+tf.getDocumentname(),""+tf.getDocumentcategory(),""+tf.getTimeadded(),""+tf.getDateadded()));
                
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(tfiles);
            JasperPrint jp = JasperFillManager.fillReport(jpr, hm, jcs);
            JasperViewer.viewReport(jp,false);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            
    }
    
    
    @FXML
    public void printtbl_documents(){
         String src = "src\\FXMLS\\Administrative\\tbl_documents_reports.jrxml";
            try{
            JasperReport jpr = JasperCompileManager.compileReport(src);
            HashMap<String, Object> hm1 = new HashMap<>();
          
            ArrayList<tbl_documents> docfiles = new ArrayList<>();
            for(tbl_documents tf : dt_documents_tbl1){
                docfiles.add(new tbl_documents(tf.getDtdocumentno(),""+tf.getDtdocumentname(),""+tf.getDtdepartment(),""+tf.getDtcategory(),""+tf.getDttimestored(),""+tf.getDtdatestored()));
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(docfiles);
            JasperPrint jp = JasperFillManager.fillReport(jpr, hm1, jcs);
            JasperViewer.viewReport(jp,false);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void trackingapproved(){
      try{
          String query = "insert into aerolink.admin_document_approved values(?,?,?,?,?,?)";
          pst = con.prepareStatement(query);
          rs = pst.executeQuery();
          pst.setString(1, txtreqno.getText());
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(2, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(3, date);
            pst.setString(4, txtdesc.getText());
            if(btnsend.isArmed()){
              File f = new File(directory+requestedtxt.getText());
              FileInputStream fs = new FileInputStream(f);
              pst.setBinaryStream(5, fs);
              pst.setString(6, "Approved");
              AlertBox.display("Alert", "Request has benn Approved");
            }else if(btndispose.isArmed()){
                File f = new File("src/FXMLS/Administrative/PDF/NOTAPPROVED.pdf");
                FileInputStream fs = new FileInputStream(f);
                pst.setBinaryStream(5, fs);
                pst.setString(6, "DisApproved");
                AlertBox.display("Alert", "Request has been decline");
            }
            pst.execute();
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
    }
}
