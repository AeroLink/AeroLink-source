/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import org.opencv.core.Core;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import FXMLS.Administrative.u;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * FXML Controller class
 *
 * @author Onodera
 */
public class Visitor_ManagementController implements Initializable {

    ByteArrayOutputStream BAOS;
    FileOutputStream FOS;
    private String directory;
    private static final String dir = "src/FXMLS/Administrative/QRdir/";
    private String capturedirectory = "src/FXMLS/Administrative/CaptureImage/";
   private ScheduledExecutorService timer;
   private VideoCapture capture = new VideoCapture();
   private boolean camactive = false;
   private static int camid = 0;
   private Connection con = DBconnection.con();
   private ResultSet rs  = null;
   private PreparedStatement pst = null;
   private FileInputStream fis;
   Mat frame = new Mat();
   AlertBox alert = new AlertBox();
    
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton edit1;
    @FXML
    private JFXButton edit212;
    @FXML
    private JFXButton edit2111;
    @FXML
    private JFXTextField purpose11111111;
    @FXML
    private JFXTextField purpose111111111;
    @FXML
    private JFXTextField purpose1111111111;
    @FXML
    private JFXButton edit22;

    
    
    @FXML
    private JFXTextField txtdestinationvr;
    private Pane pane3;
    private ImageView image4;
    @FXML
    private JFXTextField txtvisitorfn;
    @FXML
    private JFXTextField txtvisitormi;
    @FXML
    private JFXTextField txtvisitorsn;
    @FXML
    private JFXTextField txtvisitorptm;
    @FXML
    private JFXTextField txtvisitorpov;
    @FXML
    private JFXButton btntakephoto;
    @FXML
    private JFXButton capturebtn;
    @FXML
    private AnchorPane visitoranchor;
    @FXML
    private JFXTextField txtvisitorfn1;
    @FXML
    private TableColumn<?, ?> employeeid;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> office;
    @FXML
    private TableColumn<?, ?> floor;
    @FXML
    private TableView<VisitorTable_Employee> tablevisitoremployee;
    
    private ObservableList <VisitorTable_Employee> tblvisitor = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txtptm;
    @FXML
    private ImageView capturephoto;
    @FXML
    private TableColumn<?, ?> visitorid;
    @FXML
    private TableColumn<?, ?> visitorname;
    @FXML
    private TableColumn<?, ?> visitormeet;
    @FXML
    private TableColumn<?, ?> visitorpurpose;
    @FXML
    private TableView<Visitor_TableRegistration> visitorregtbl;
    private ObservableList<Visitor_TableRegistration> fortblreg = FXCollections.observableArrayList();
    @FXML
    private JFXButton edit11;
    @FXML
    private TableColumn<?, ?> monitorid;
    @FXML
    private TableColumn<?, ?> monitorname;
    @FXML
    private TableColumn<?, ?> monitorptm;
    @FXML
    private TableColumn<?, ?> monitortimein;
    @FXML
    private TableColumn<?, ?> monitortimeout;
    @FXML
    private TableColumn<?, ?> monitorstatus;
    @FXML
    private TableView<Visitor_Monitortbl> monitortable;
    private ObservableList<Visitor_Monitortbl> monitortbl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> monitordate;
    @FXML
    private JFXTextField schedmn;
    @FXML
    private JFXTextField schedsn;
    @FXML
    private JFXTextField schedfn;
    @FXML
    private JFXDatePicker scheddate;
    @FXML
    private JFXTimePicker schedtime;
    @FXML
    private JFXTextField schedptm;
    @FXML
    private JFXTextField schedpurpose;
    @FXML
    private JFXTextField scheddes;
    @FXML
    private JFXButton schedsave;
    @FXML
    private JFXButton schedclear;
    @FXML
    private TableView<VisitorTable_Employee> tablevisitoremployee1;
    private ObservableList<VisitorTable_Employee> tblvisitoremployee1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> employeeid1;
    @FXML
    private TableColumn<?, ?> name1;
    @FXML
    private TableColumn<?, ?> office1;
    @FXML
    private TableColumn<?, ?> floor1;
    @FXML
    private JFXTextField txtvisitorfn11;
    @FXML
    private JFXTextField schedempid;
    @FXML
    private TableColumn<?, ?> appointmentid;
    @FXML
    private TableColumn<?, ?> appointmentname;
    private TableColumn<?, ?> appointmenttimedate;
    @FXML
    private TableColumn<?, ?> appointmentptm;
    @FXML
    private TableColumn<?, ?> appointmentstatus;
    @FXML
    private TableView<Visitor_Appointments> appointmenttable;
    private ObservableList<Visitor_Appointments> aptbl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> appointmenttime;
    @FXML
    private TableColumn<?, ?> appointmentdate;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      directory = new File("").getAbsolutePath();
      directory += File.separator + dir;
      File file = new File(directory);
      if(!file.isDirectory()){
          file.mkdir();
          
      }
      
      VisitorTableEmployee();
      loadvisitortable();
      visitortableclick();
      tblvisitoregistration();
      monitortable();
      appointmenttbl();
    }  
    
    //QR Code Generator
    public void generateqr() throws IOException{
        String fn = txtvisitorfn.getText();
        String mi = txtvisitormi.getText();
        String sn = txtvisitorsn.getText();
        if(!fn.isEmpty()){
            try{         
                
                String content = "";
                
                content += "First Name: " + txtvisitorfn.getText() + "\n";
                content += "Middle Initial: " + txtvisitormi.getText() + "\n";
                content += "Sur Name: " + txtvisitorsn.getText() + "\n";
                content += "Person to Meet: " + txtvisitorptm.getText() + "\n";
                content += "Purpose of Visit" + txtvisitorpov.getText() + "\n";
                content += "Destination" + txtdestinationvr.getText() + "\n";
                                
                FOS = new FileOutputStream(directory + File.separator + (fn + " " + mi + " "+sn) + ".png");
                BAOS = QRCode.from(content).withSize(150, 150).to(ImageType.PNG).stream();
              
                FOS.write(BAOS.toByteArray()); 
                BAOS.close();
                FOS.close();
                FOS.flush();
               
                
                
           } catch (FileNotFoundException ex) {
                 Logger.getLogger(Visitor_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
    }
    
    //Start Cam
    @FXML
    public void startcam(){    
        if(!this.camactive){
            this.capture.open(camid);
                
                if(this.capture.isOpened()){
                    this.camactive = true;
                      
                    Runnable framegrab =  new Runnable() {
                        @Override
                        public void run() {
                            
                            frame = grabFrame();
                            Image ima = u.mat2Image(frame);
                            updateimg(capturephoto,ima);
                        }
                    };
                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(framegrab, 0, 33, TimeUnit.MILLISECONDS);
                capturebtn.setDisable(false);
                this.btntakephoto.setText("NEW PHOTO");
                }
                else{
                    AlertBox.error("Alert", "There is no Camera Installed");
                }
        }
        else{
            this.camactive = false;
            
        }
    }
    //Mat Frame
    public Mat grabFrame(){
         frame = new Mat();  
            if(this.capture.isOpened()){
                try{                  
                    this.capture.read(frame);
                    if(!frame.empty()){
                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
                    MatOfByte mob = new MatOfByte();
                    Imgcodecs.imencode(".png", frame, mob);
                    new Image(new ByteArrayInputStream(mob.toArray()));
                    }                   
                }catch(Exception ex){
                    System.out.print(ex.getMessage());}        
            }
        return frame;    
    }
    
    public void updateimg(ImageView view, Image image){
      u.onFXThread(view.imageProperty(), image);
    }
    
    //Capture Image
    @FXML
    public void stopcam(){
        if(this.timer!=null && !this.timer.isShutdown()){
            try{
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            
            }catch(Exception ex){
                System.out.print(ex.getMessage());
            }
        }
        if(this.capture.isOpened()){
                this.capture.release();
             savecapture();
           
                
        }
    
    
    }
    BufferedImage bfm;
    
    //Insert Data
    @FXML
    public void savedregistration(){
        String sql = "insert into aerolink.admin_visitor_registration values(?,?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtvisitorfn.getText());
            pst.setString(2, txtvisitormi.getText());
            pst.setString(3, txtvisitorsn.getText());
            pst.setString(4, txtvisitorptm.getText());
            pst.setString(5, txtvisitorpov.getText());
            
            File f = new File(capturedirectory+txtvisitorfn.getText()+".png");
            bfm = ImageIO.read(f);
            Image image = SwingFXUtils.toFXImage(bfm, null);
            image4.setImage(image);
            FileInputStream fin = new FileInputStream(f);
            int len = (int)f.length();
            
            pst.setBinaryStream(6,fin ,len);
            
            pst.execute();
            AlertBox.display("Alert", "Data Succesfully Save");
            
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }
    
    //Capture Image
    public void savecapture(){      
          Imgcodecs.imwrite(capturedirectory+(txtvisitorfn.getText()+" "+txtvisitormi.getText()+" "+txtvisitorsn.getText())+".png", frame);
    }
    
    
    
    private void VisitorTableEmployee(){
        employeeid.setCellValueFactory(new PropertyValueFactory<>("Company_ID"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        office.setCellValueFactory(new PropertyValueFactory<>("Office"));
        floor.setCellValueFactory(new PropertyValueFactory<>("Floor"));
        
        employeeid1.setCellValueFactory(new PropertyValueFactory<>("Company_ID"));
        name1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        office1.setCellValueFactory(new PropertyValueFactory<>("Office"));
        floor1.setCellValueFactory(new PropertyValueFactory<>("Floor"));
        
        
        visitorid.setCellValueFactory(new PropertyValueFactory<>("Visitor_ID"));
        visitorname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        visitormeet.setCellValueFactory(new PropertyValueFactory<>("PTM"));
        visitorpurpose.setCellValueFactory(new PropertyValueFactory<>("Purpose"));
        
        
        
        monitorid.setCellValueFactory(new PropertyValueFactory<>("monitorid"));
        monitorname.setCellValueFactory(new PropertyValueFactory<>("monitorname"));
        monitorptm.setCellValueFactory(new PropertyValueFactory<>("monitorptm"));
        monitortimein.setCellValueFactory(new PropertyValueFactory<>("timein"));
        monitortimeout.setCellValueFactory(new PropertyValueFactory<>("timeout"));
        monitordate.setCellValueFactory(new PropertyValueFactory<>("date"));
        monitorstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
       appointmentid.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
       appointmentname.setCellValueFactory(new PropertyValueFactory<>("Name"));
       appointmenttime.setCellValueFactory(new PropertyValueFactory<>("time"));
       appointmentdate.setCellValueFactory(new PropertyValueFactory<>("date"));
       appointmentptm.setCellValueFactory(new PropertyValueFactory<>("empname"));
       appointmentstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
       
    }
    
    private void loadvisitortable(){
        tblvisitor.clear();
            try{
                String sql = "Select Company_ID, ([First Name] +' ' + [Middle Name] + ' ' + [Sur Name])as Name, Office, (Floor + '-' + Unit) as Floor from aerolink.admin_visitor_destination ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                tblvisitor.add(new VisitorTable_Employee(""+rs.getInt("Company_ID"), rs.getString("Name"), rs.getString("Office"), rs.getString("Floor")));
                
                }
            }
            catch(Exception ex){
                System.out.print(ex.getMessage());
            }
            
            tablevisitoremployee.setItems(tblvisitor);
            tablevisitoremployee1.setItems(tblvisitor);
           
           
    }
    
    @FXML
    public void tblvisitoregistration(){
        fortblreg.clear();
        try{
            String query = "Select Visitor_ID, Name, [Purpose of Visit], ([First Name] +' ' + [Middle Name] + ' ' + [Sur Name])as EmName from aerolink.admin_visitor_registration as aavr inner join aerolink.admin_visitor_destination as aavd on aavr.Company_ID = aavd.Company_ID";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                fortblreg.add(new Visitor_TableRegistration(""+rs.getInt("Visitor_ID"),rs.getString("Name"),rs.getString("EmName"),rs.getString("Purpose of Visit")));
            }
        visitorregtbl.setItems(fortblreg);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        monitortable();
    }
    
    public void monitortable(){
        monitortbl.clear();
        try{
            String query = "Select Visitor_ID, Name, [Time In], [Time Out],Status,Date, ([First Name] +' ' + [Middle Name] + ' ' + [Sur Name])as EmName from aerolink.admin_visitor_registration as aavr inner join aerolink.admin_visitor_destination as aavd on aavr.Company_ID = aavd.Company_ID";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                monitortbl.add(new Visitor_Monitortbl(""+rs.getInt("Visitor_ID"),rs.getString("Name"),rs.getString("EmName"),""+rs.getTime("Time In"),""+rs.getTime("Time Out"),""+rs.getDate("Date"),rs.getString("Status")));
            }
        monitortable.setItems(monitortbl);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    
    }
    
    public void appointmenttbl(){
        aptbl.clear();
            try{
             String sql = "Select [Appointment ID], (aava.[First Name]+ ' '+ aava.[Middle Name] + ' ' + aava.[Sur Name]) as Name, Time,Date, Status,(aavd.[First Name]+ ' '+ aavd.[Middle Name] + ' ' + aavd.[Sur Name]) as EmpName from aerolink.admin_visitor_appointments as aava inner join aerolink.admin_visitor_destination as aavd on aava.Company_ID = aavd.Company_ID ";
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery();
             while(rs.next()){
              aptbl.add(new Visitor_Appointments(""+rs.getInt("Appointment ID"),rs.getString("Name"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("EmpName"),rs.getString("Status")));
             }
            }catch(Exception ex){
                System.out.print(ex.getMessage());
            }
         appointmenttable.setItems(aptbl);
    }
    
    
    public void visitortableclick(){
        tablevisitoremployee.setOnMouseClicked(e -> {
            VisitorTable_Employee vte = tablevisitoremployee.getItems().get(tablevisitoremployee.getSelectionModel().getSelectedIndex());
            txtptm.setText(vte.getName());
            txtdestinationvr.setText(vte.getFloor());
            txtvisitorptm.setText(vte.getCompany_ID());
        
        });
        
         tablevisitoremployee1.setOnMouseClicked(e -> {
            VisitorTable_Employee vte = tablevisitoremployee1.getItems().get(tablevisitoremployee1.getSelectionModel().getSelectedIndex());
            schedptm.setText(vte.getName());
            scheddes.setText(vte.getFloor());
            schedempid.setText(vte.getCompany_ID());
        
        });
    }
    
    @FXML
    public void search(){
        tblvisitor.clear();
            String sql = "Select Company_ID, ([First Name] +' ' + [Middle Name] + ' ' + [Sur Name])as Name, Office, (Floor + '-' + Unit) as Floor from aerolink.admin_visitor_destination where [First Name] like '"+txtvisitorfn1.getText()+"%' or [Sur Name] like '"+txtvisitorfn1.getText()+"%' or Company_ID like '"+txtvisitorfn1.getText()+"%'";
        try{
            pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                tblvisitor.add(new VisitorTable_Employee(""+rs.getInt("Company_ID"), rs.getString("Name"), rs.getString("Office"), rs.getString("Floor")));
                }   
        
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        tablevisitoremployee.setItems(tblvisitor);
        tablevisitoremployee1.setItems(tblvisitor);
           
    }
   
    
    public void confirmationbtn(){
       Visitor_CardController o = new Visitor_CardController();
       alert.loadfxml("Visitor_Card.fxml", o);
    
    }
    
    @FXML
    public void information() throws IOException{
         
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("Visitor_Card.fxml"));
        try{
         load.load();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    
        Visitor_CardController vcc = load.getController();
        Parent p = load.getRoot();
        Stage st = new Stage();
        st.setScene(new Scene(p));
        st.setResizable(false);
        st.initStyle(StageStyle.UNDECORATED);
        st.show();
        generateqr();
        vcc.visitorinfo(txtvisitorfn.getText(), txtvisitormi.getText(), txtvisitorsn.getText(), txtptm.getText(), txtdestinationvr.getText(), txtvisitorpov.getText(),txtvisitorfn,txtvisitormi,txtvisitorsn,txtvisitorptm.getText());
    }
    
    
    @FXML
    public void insertappointment(){
        String sql = "insert into aerolink.admin_visitor_appointments values (?,?,?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, schedfn.getText());
            pst.setString(2, schedmn.getText());
            pst.setString(3, schedsn.getText());
            pst.setString(4, ((TextField)schedtime.getEditor()).getText());
            pst.setString(5, ((TextField)scheddate.getEditor()).getText());
            pst.setString(6, schedempid.getText());
            pst.setString(7,schedpurpose.getText());
            pst.setString(8, "Check In");
            pst.execute();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    
    }
    
}
