/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    private String capturedirectory2 = "src/FXMLS/Administrative/CaptureImageAppointments/";
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
    private FontAwesomeIconView btntakephoto;
    @FXML
    private FontAwesomeIconView capturebtn;
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
    private TableView<tbl_visitor_appointments> appointmenttable;
    ObservableList<tbl_visitor_appointments> aptbl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> appointmenttime;
    @FXML
    private TableColumn<?, ?> appointmentdate;
    @FXML
    private AnchorPane anchorvinfo;
    @FXML
    private ImageView imagetimeout;
    @FXML
    private JFXTextField txtvisitorfn2;
    @FXML
    private JFXTextField txtvisitormi1;
    @FXML
    private JFXTextField txtvisitorsn1;
    @FXML
    private JFXTextField txtdestinationvr1;
    @FXML
    private JFXTextField txtptm1;
    @FXML
    private JFXTextField txtvisitorpov1;
    @FXML
    private JFXButton edit2;
    @FXML
    private ImageView capturephoto1;
    @FXML
    private JFXTextField txtvisitorfn1111;
    @FXML
    private FontAwesomeIconView cameraclick;
    @FXML
    private FontAwesomeIconView savebtn;
    @FXML
    private FontAwesomeIconView stopcam;
    @FXML
    private FontAwesomeIconView savebtn1;
    @FXML
    private JFXTextField serachapp;
    @FXML
    private JFXButton btngo;
    @FXML
    private JFXButton btnvapp;
    @FXML
    private JFXButton edit221;
    @FXML
    private JFXTextField searchblist;
    @FXML
    private JFXTextField blistname;
    @FXML
    private JFXTextField vid;
    @FXML
    private JFXTextField reasontxt;
    @FXML
    private JFXTextField txtlvloffense;
    @FXML
    private TableView<tbl_blacklist> tblblacklist;
    ObservableList<tbl_blacklist> tblblacklist1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> bname;
    @FXML
    private TableColumn<?, ?> breason;
    @FXML
    private TableColumn<?, ?> boffense;
    @FXML
    private TableColumn<?, ?> btime;
    @FXML
    private TableColumn<?, ?> bdate;
    @FXML
    private TableColumn<?, ?> bstatus;
    @FXML
    private AnchorPane blockanchor;
    @FXML
    private FontAwesomeIconView capturesavebtn;
    
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
      updateblacklist();
      AlertBox.tooltip("Enable Camera", btngo);
      AlertBox.tooltip("Enable Camera", btnvapp);
      VisitorTableEmployee();
      loadvisitortable();
      visitortableclick();
      tblvisitoregistration();
      monitortable();
      appointmenttbl();
      visitorappointmentinfo();
      enable();
      blacklistdata();
    }  
    
    //Readd QRCode
     public String readQRCode(String filePath, String charset, Map hintMap)
            throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);
        return qrCodeResult.getText();
    }
     
     //OpenQRCode
    @FXML
     public void openQRcode() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(directory));

        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");

        fileChooser.getExtensionFilters()
                .addAll(extFilterpng, extFilterJPG, extFilterjpg, extFilterPNG);
        
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                String charset = "UTF-8"; // or "ISO-8859-1"
                Map hintMap = new HashMap();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                String readQRCode = readQRCode(file.getAbsolutePath(), charset, hintMap);

                txtvisitorfn2.setText(readQRCode.substring(readQRCode.indexOf("First Name:") + 12, readQRCode.indexOf("Middle Initial:")));
                txtvisitormi1.setText(readQRCode.substring(readQRCode.indexOf("Middle Initial:") + 16, readQRCode.indexOf("Sur Name:")));
                txtvisitorsn1.setText(readQRCode.substring(readQRCode.indexOf("Sur Name:") + 10, readQRCode.indexOf("Person to Meet:")));
                txtptm1.setText(readQRCode.substring(readQRCode.indexOf("Person to Meet:") + 16, readQRCode.indexOf("Purpose of Visit:")));
                txtdestinationvr1.setText(readQRCode.substring(readQRCode.indexOf("Purpose of Visit:") + 18, readQRCode.indexOf("Destination:")));
                txtvisitorpov1.setText(readQRCode.substring(readQRCode.indexOf("Destination:") + 13, readQRCode.length() ));
              
                File f = new File(capturedirectory+(txtvisitorfn2.getText()+" "+txtvisitormi1.getText()+" "+txtvisitorsn1.getText())+".png");
                Image image = new Image(new FileInputStream(f.getAbsolutePath()));
                imagetimeout.setImage(image);
                
                try{
                   
                    java.util.Date d = new java.util.Date();
                    long t = d.getTime();
                    java.sql.Time st = new java.sql.Time(t);
                    String update = "update aerolink.admin_visitor_registration set [Time Out] = '"+st+"', Status = 'Checked Out' where Name = '"+(txtvisitorfn2.getText()+" "+txtvisitormi1.getText()+" "+txtvisitorsn1.getText())+"'";
                    pst = con.prepareStatement(update);
                    pst.execute();
                    AlertBox.display("Alert", "Visitor is now Checked Out");
                    monitortable();
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                
            } catch (IOException | NotFoundException ex) {
                Logger.getLogger(Visitor_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
     //Generate QRCode
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
                content += "Person to Meet: " + txtptm.getText() + "\n";
                content += "Purpose of Visit: " + txtvisitorpov.getText() + "\n";
                content += "Destination: " + txtdestinationvr.getText() + "\n";
                                
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
    
    //Start Cam for Visitor Registration
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
               
               
                }
                else{
                    AlertBox.error("Alert", "There is no Camera Installed");
                }
        }
        else{
            this.camactive = false;
            
        }
    }
    
    // For Visitor Appointments
    @FXML
     public void startcam1(){    
        if(!this.camactive){
            this.capture.open(camid);
                
                if(this.capture.isOpened()){
                    this.camactive = true;
                      
                    Runnable framegrab =  new Runnable() {
                        @Override
                        public void run() {
                            
                            frame = grabFrame();
                            Image ima = u.mat2Image(frame);
                            updateimg(capturephoto1,ima);
                            
                        }
                    };
                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(framegrab, 0, 33, TimeUnit.MILLISECONDS);
                savebtn.setDisable(false);
               
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
    
    @FXML
    public void stopcam1(){
    if(this.timer!=null && !this.timer.isShutdown()){
            try{
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            }catch(Exception ex){
                System.out.print(ex.getMessage());
            }
        }if(this.capture.isOpened()){
                this.capture.release();
          }
        
         if(btnvapp.isArmed()){
                btntakephoto.setDisable(true);
                capturesavebtn.setDisable(true);
                cameraclick.setDisable(false);
                savebtn.setDisable(true);
                AlertBox.display("Alert", "Visitor Appointments Camera is now Enable");
                }else if(btngo.isArmed()){
                btntakephoto.setDisable(false);
                capturesavebtn.setDisable(true);
                cameraclick.setDisable(true);
                savebtn.setDisable(true);
                AlertBox.display("Alert", "Visitor Registration Camera is now Enable");
                }
    }   
    
    //capture btn for visitor appointments
    @FXML
     public void visitorappointmentsstop(){
         if(schedfn.getText().equals("")){
                AlertBox.display("Alert", "Visitor First Name is Empty");
            }else if(schedmn.getText().equals("")){
                AlertBox.display("Alert", "Visitor Middle Name is Empty");
            }
            else if(schedsn.getText().equals("")){
                AlertBox.display("Alert", "Visitor Sur Name is Empty");
            }else{
        
        if(this.timer!=null && !this.timer.isShutdown()){
            try{
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            
            }catch(Exception ex){
                System.out.print(ex.getMessage());
            }
        }if(this.capture.isOpened()){
                this.capture.release();
                savecapture1();
                AlertBox.display("Alert", "You Capture this Image");
         }
            }
    
    }
    
    
    
    //Capture Image
    @FXML
    public void stopcamforreg(){
         if(txtvisitorfn.getText().equals("")){
                AlertBox.display("Alert", "Visitor First Name is Empty");
            }else if(txtvisitormi.getText().equals("")){
                AlertBox.display("Alert", "Visitor Middle Name is Empty");
            }
            else if(txtvisitorsn.getText().equals("")){
                AlertBox.display("Alert", "Visitor Sur Name is Empty");
            }else{
        
        if(this.timer!=null && !this.timer.isShutdown()){
            try{
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            
            }catch(Exception ex){
                System.out.print(ex.getMessage());
            }
        }if(this.capture.isOpened()){
                this.capture.release();
                AlertBox.display("Alert", "You capture this image");
                savecapture();
         }
            }
    
    }
    BufferedImage bfm;
    
    //Insert Data
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
    
    public void savecapture1(){      
         Imgcodecs.imwrite(capturedirectory2+(schedfn.getText()+" "+schedmn.getText()+" "+schedsn.getText())+".png", frame);
            
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
        
       appointmentid.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
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
              aptbl.add(new tbl_visitor_appointments(""+rs.getInt("Appointment ID"),rs.getString("Name"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("EmpName"),rs.getString("Status")));
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
       
        if(txtvisitorfn.getText().equals("")||txtvisitormi.getText().equals("") || txtvisitorsn.getText().equals("") || txtptm.getText().equals("") || txtdestinationvr.getText().equals("")|| txtvisitorpov.getText().equals("")){
            
            AlertBox.display("Alert", "Visitors information must be completed");
        }
        else{
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
       
        AlertBox.cleartxt(anchorvinfo);
        }
      }
    
    
    @FXML
    public void insertappointment(){
        String sql = "insert into aerolink.admin_visitor_appointments values (?,?,?,?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, schedfn.getText());
            pst.setString(2, schedmn.getText());
            pst.setString(3, schedsn.getText());
            pst.setString(4, ((TextField)schedtime.getEditor()).getText());
            pst.setString(5, ((TextField)scheddate.getEditor()).getText());
            pst.setString(6, schedempid.getText());
            pst.setString(7,schedpurpose.getText());
            
            
            File f = new File(capturedirectory2+(schedfn.getText()+" "+schedmn.getText()+" "+schedsn.getText())+".png");
            bfm = ImageIO.read(f);
            Image image = SwingFXUtils.toFXImage(bfm, null);
            capturephoto1.setImage(image);
            FileInputStream fin = new FileInputStream(f);
            int len = (int)f.length();
            pst.setBinaryStream(8,fin ,len);
            pst.setString(9, "Arriving");
            pst.execute();
            AlertBox.display("Alert", "Vistor Appointment is now Added");
        }catch(Exception ex){
            AlertBox.display("Alert", "Capture the Image First");
        }
        
     
        
    
    }
     public void visitorappointmentinfo(){
         appointmenttable.setOnMouseClicked(e->{
         
                tbl_visitor_appointments va = appointmenttable.getItems().get(appointmenttable.getSelectionModel().getSelectedIndex());
                try{
                    String query = "select *,(aavd.[First Name]+' '+aavd.[Middle Name]+' '+aavd.[Sur Name]) as 'employeename', (aavd.Floor+'-'+aavd.Unit) as 'destination' from aerolink.admin_visitor_appointments as aava inner join aerolink.admin_visitor_destination as aavd on aava.Company_ID = aavd.Company_ID where [Appointment ID] = '"+va.getAppointmentID()+"' ";
                    pst = con.prepareStatement(query);
                    rs = pst.executeQuery();
                    while(rs.next()){
                        schedfn.setText(rs.getString("First Name"));
                        schedmn.setText(rs.getString("Middle Name"));
                        schedsn.setText(rs.getString("Sur Name"));
                        scheddate.setValue(rs.getDate("Date").toLocalDate());
                        schedtime.setValue(rs.getTime("Time").toLocalTime());
                        schedptm.setText(rs.getString("employeename"));
                        scheddes.setText(rs.getString("destination"));
                        schedpurpose.setText(rs.getString("Purpose"));
                        
                        File f = new File(capturedirectory2+va.getName()+".png");
                        Image img = new Image(new FileInputStream(f.getAbsolutePath()));
                        capturephoto1.setImage(img);
                        }
                
                }catch(Exception ex){
                    AlertBox.display("Alert", "No Value Selected");
                }
         
         });
         
     
     }
     
    @FXML
     public void searchap(){
         aptbl.clear();
         try{
             String query = "Select [Appointment ID], (aava.[First Name]+ ' '+ aava.[Middle Name] + ' ' + aava.[Sur Name]) as Name, Time,Date, Status,(aavd.[First Name]+ ' '+ aavd.[Middle Name] + ' ' + aavd.[Sur Name]) as EmpName from aerolink.admin_visitor_appointments as aava inner join aerolink.admin_visitor_destination as aavd on aava.Company_ID = aavd.Company_ID  where [Appointment ID] like '"+serachapp.getText()+"%' or aava.[First Name] like '"+serachapp.getText()+"%'or Date like '"+serachapp.getText()+"%' or Time like '"+serachapp.getText()+"%' ";
                 pst = con.prepareStatement(query);
             rs = pst.executeQuery();
             while(rs.next()){
              aptbl.add(new tbl_visitor_appointments(""+rs.getInt("Appointment ID"),rs.getString("Name"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("EmpName"),rs.getString("Status")));
             }
         appointmenttable.setItems(aptbl);
         }catch(Exception ex){
             System.out.println(ex.getMessage());
         }
     }
     
    @FXML
       public void print_tbl_appointments(){
      
            try{
               
           String src = "src\\FXMLS\\Administrative\\vappointment.jrxml";
           
            JasperReport jpr = JasperCompileManager.compileReport(src);
            HashMap<String, Object> hm1 = new HashMap<>();
          
            ArrayList<tbl_visitor_appointments> docfiles = new ArrayList<>();
            for(tbl_visitor_appointments tf : aptbl){
                docfiles.add(new tbl_visitor_appointments(""+tf.getAppointmentID(),""+tf.getName(),""+tf.getTime(),""+tf.getDate(),""+tf.getEmpname(),""+tf.getStatus()));
            }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(docfiles);
            JasperPrint jp = JasperFillManager.fillReport(jpr, hm1, jcs);
            JasperViewer.viewReport(jp,false);
            }
                catch(Exception ex){
                System.out.println(ex.getMessage());
            }
    }
       
       public void cleartex(Button b){
            AlertBox.cleartxt(anchorvinfo);
           
       }
       
    @FXML
       public void clearfields(){
           AlertBox.cleartxt(anchorvinfo);
       }
     
       public void enable(){
           capturebtn.setOnMouseClicked(e->{
               edit.setDisable(false);
           });
       }
       
    @FXML
       public void searchlist(){
           try{
               String search = "select * from aerolink.admin_visitor_registration where Visitor_ID = '"+searchblist.getText()+"'";
               pst = con.prepareStatement(search);
               rs = pst.executeQuery();
               while(rs.next()){
                   blistname.setText(rs.getString("Name"));
                   vid.setText(rs.getString("Visitor_ID"));
               }
           }catch(Exception ex){
               System.out.println(ex.getMessage());                       
           }
           
           
       }
       
    @FXML
        public void insertblacklist(){
        try{
            String insert = "insert into aerolink.admin_visitor_blacklist values(?,?,?,?,?,?)";
            pst = con.prepareStatement(insert);
            pst.setString(1, vid.getText());
            pst.setString(2, reasontxt.getText());
            pst.setString(3, txtlvloffense.getText());
              
            java.util.Date d = new java.util.Date();
            long t = d.getTime();
            java.sql.Time st = new java.sql.Time(t);
            pst.setTime(4, st);
          
            java.sql.Date date = new java.sql.Date(t);
            pst.setDate(5, date);
            
            pst.setString(6, "Block");
            pst.execute();
            AlertBox.display("Alert", "Person is now Block");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
        
        
    private void blacklistdata(){
        
        bname.setCellValueFactory(new PropertyValueFactory<>("name"));
        breason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        boffense.setCellValueFactory(new PropertyValueFactory<>("lvlof"));
        btime.setCellValueFactory(new PropertyValueFactory<>("time"));
        bdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        bstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        tblblacklist1.clear();
        try{
            String select =  "select * from aerolink.admin_visitor_blacklist aavb inner join aerolink.admin_visitor_registration aavr on aavb.Visitor_ID = aavr.Visitor_ID where aavb.Status = 'Block'";
            pst = con.prepareStatement(select);
            rs = pst.executeQuery();
            while(rs.next()){
                    tblblacklist1.add(new tbl_blacklist(""+rs.getString("Blacklist No"),rs.getString("Name"),rs.getString("Reason"),rs.getString("Level of Offense"),""+rs.getTime("Time"),""+rs.getDate("Date"),rs.getString("Status"))); 
                }
            tblblacklist.setItems(tblblacklist1);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateblacklist(){
        tblblacklist.setOnMouseClicked(e ->{
            
            try{
            tbl_blacklist tb = tblblacklist.getItems().get(tblblacklist.getSelectionModel().getSelectedIndex());
           
            String select = "select * from aerolink.admin_visitor_blacklist aavb inner join aerolink.admin_visitor_registration aavr on aavb.Visitor_ID = aavr.Visitor_ID where [Blacklist No] = '"+tb.getBno()+"'";
            pst = con.prepareStatement(select);
            rs = pst.executeQuery();
            while(rs.next()){
                blistname.setText(rs.getString("Name"));
                reasontxt.setText(rs.getString("Reason"));
                txtlvloffense.setText(rs.getString("Level of Offense"));
            }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            
        });
    }
    
    @FXML
    public void ublock(){
        try{
            tbl_blacklist tb = tblblacklist.getItems().get(tblblacklist.getSelectionModel().getSelectedIndex());
            String update = "update aerolink.admin_visitor_blacklist set Status = 'Unblock' where [Blacklist No] = '"+tb.getBno()+"' ";
            
            pst = con.prepareStatement(update);
            
            AlertBox.display("Alert", "Person is now UnBlock");
            AlertBox.cleartxt(blockanchor);
            pst.execute();
            
            blacklistdata();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
     
     
    
}
