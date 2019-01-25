/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
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

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Document_ManagementController implements Initializable {
    
    
    private Connection con = DBconnection.con();
    private PreparedStatement pst = null;
    private ResultSet rs  = null;
    int Global_Count = 0;
    
   
    @FXML
    private JFXTextField locationfield111111;
    @FXML
    private JFXButton btntakephoto1;
    @FXML
    private JFXTextField locationfield11111121;
    @FXML
    private JFXTextField locationfield111111211;
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
    private TableView<?> tablevisitoremployee12;
    @FXML
    private TableColumn<?, ?> employeeid12;
    @FXML
    private TableColumn<?, ?> name12;
    @FXML
    private TableColumn<?, ?> office12;
    @FXML
    private TableColumn<?, ?> floor12;
    @FXML
    private TableColumn<?, ?> floor121;
    @FXML
    private JFXTextField locationfield11111122;
    @FXML
    private JFXTextField requestedtxt;
    @FXML
    private TableColumn<?, ?> floor2;
    @FXML
    private TableColumn<?, ?> floor21;
    @FXML
    private TableView<?> tablevisitoremployee2;
    @FXML
    private TableColumn<?, ?> employeeid2;
    @FXML
    private TableColumn<?, ?> name2;
    @FXML
    private TableColumn<?, ?> floor211;
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
    private JFXTextField locationfield1111112111;
    @FXML
    private TableView<admin_doc_request> tabledocrequest;
    private ObservableList<admin_doc_request> tbldocrequest = FXCollections.observableArrayList();
    @FXML
    private JFXButton btntakephoto12;
    @FXML
    private TableColumn<?, ?> floor2111;
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
    final a2 A2 = new a2();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tblreqstoring.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblforreqstoring.setItems(tblreqstoring);
        });
        this.requesttbl();
        this.loadrequesttbl();
        this.loadtblreqstoring();
    }    

     public a2 newclass(){
        return new a2();
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
        
    }
    
    private void loadrequesttbl(){
        tbldocrequest.clear();
        try{
            String query  = "select * from aerolink.admin_document_request";
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
    
    public void loadtblreqstoring(){
        tblforreqstoring.itemsProperty().bind(A2.valueProperty());
        A2.start();
   }
    
    
    @FXML
    public void adddocument(){
        AlertBox a =  new AlertBox();
        Add_DocumentController ad = new Add_DocumentController();
        a.loadfxml("Add_Document.fxml", ad);
        
    }
    
    
}
