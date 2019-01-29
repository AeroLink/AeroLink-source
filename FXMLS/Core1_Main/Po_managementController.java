/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main;

import FXMLS.Core1_Main.Modals.Booking_informationController;
import FXMLS.Core1_Main.Modals.Invoice_viewController;
import FXMLS.Core1_Main.Modals.Pom_chargesController;
import FXMLS.Core1_Main.Modals.Pom_viewController;
import FXMLS.Core1_Main.Model.db_booking;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Po_managementController implements Initializable {
    
    Stage newStage = new Stage();
    Date date = new Date();
    String invoice_no;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ObservableList<db_booking> oblist  = FXCollections.observableArrayList();
    
    @FXML
    private TableView<db_booking> tbl_pom;
    @FXML
    private TableColumn<db_booking, String> col_ref;
    @FXML
    private TableColumn<db_booking, String> col_status;
    @FXML
    private Label lbl_po_date;
    @FXML
    private Label lbl_po_no;
    @FXML
    private Label lbl_track;
    @FXML
    private Label lbl_ref_no;
    @FXML
    private Label lbl_ship_add;
    @FXML
    private Label lbl_rec_add;
    @FXML
    private Label lbl_serv_type;
    @FXML
    private Label lbl_box;
    @FXML
    private Label lbl_weight;
    @FXML
    private Label lbl_cost_freight;
    @FXML
    private Label lbl_cost_serv_type;
    @FXML
    private Label lbl_cost_box;
    @FXML
    private Label lbl_cost_weight;
    @FXML
    private Label lbl_sub_total;
    @FXML
    private Label lbl_vat;
    @FXML
    private Label lbl_charge;
    @FXML
    private Label lbl_total;
    
    public static final Font Title = new Font(FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
    public static final Font Others = new Font(FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
    public static final Font for_bill = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
    public static final Font for_from = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
    public static final Font for_to = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
    Chunk title = new Chunk("AeroLink", Title);
    Chunk invoice = new Chunk("INVOICE", Title);
    Chunk billto = new Chunk("Bill To :", for_bill);
    Chunk from = new Chunk("From :", for_from);
    Chunk to = new Chunk("To :", for_to);
    @FXML
    private Label lbl_book_no;
    @FXML
    private Label lbl_quantity;
    
    Double insurance_cost;
    Double liability_cost;
    @FXML
    private TextField txt_search;
    @FXML
    private Label lbl_weight_total;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        setCellValue();
        initClock();
        setPO();
        setInvoice();
        searchData();

    }    
    
    public void initTable(){
        oblist.clear();
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking inner join tbl_core2_sop on aerolink.tbl_core1_booking.ref_no = tbl_core2_sop.ref_no where aerolink.tbl_core1_booking.status='Approved/For Payment' or aerolink.tbl_core1_booking.status='To print invoice'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("weight")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("insurance"), rs.getString("liability"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_pom.setItems(oblist);
        
    }
    
   public void setCellValue(){
        
       col_ref.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
       col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
    }
   
   private void initClock() {

    Format formatter = new SimpleDateFormat("MMM-dd-yyyy");
    String s = formatter.format(date);
    lbl_po_date.setText(s);
    }
   
   public void setPO(){
        
        String po_no;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select max(pom_no) as pom_no from aerolink.tbl_core1_pom";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
               
               po_no = String.valueOf(rs.getInt("pom_no") + 1);
               lbl_po_no.setText("PO00" + po_no);
               lbl_track.setText("AWB0" + po_no);
               
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
   public void setInvoice(){
        
        String invoice_nos;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select max(invoice_count) as invoice_count from aerolink.tbl_core1_invoices";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
               
               invoice_nos = String.valueOf(rs.getInt("invoice_count") + 1);
               invoice_no = "INV0" + invoice_nos;
               
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
   @FXML
    public void displayTable(){
        tbl_pom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                db_booking cbt = tbl_pom.getItems().get(tbl_pom.getSelectionModel().getSelectedIndex());
                try{
                    lbl_ref_no.setText(cbt.getRef_no());
                    lbl_ship_add.setText(cbt.getShip_address() + " " + cbt.getShip_brgy() + " " + cbt.getShip_city() + ", " + cbt.getShip_province() + " " + cbt.getShip_zip());
                    lbl_rec_add.setText(cbt.getRec_address() + " " + cbt.getRec_brgy() + " " + cbt.getRec_city() + ", " + cbt.getRec_province() + " " + cbt.getRec_zip());
                    lbl_serv_type.setText(cbt.getServ_type());
                    lbl_box.setText(cbt.getServ_box());
                    lbl_weight.setText(cbt.getBook_date());
                    lbl_book_no.setText(cbt.getBook_no());
                    lbl_quantity.setText(cbt.getServ_quantity());
                    lbl_cost_freight.setText("100.00");
                    lbl_cost_weight.setText("40.00");
                    
                    setServ_typeCost();
                    setBox_sizeCost();
                    computation();
                    
                }catch(Exception ex){
                    Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void setServ_typeCost(){
        String serv_type_cost;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from tbl_core2_services where serv_name='"+lbl_serv_type.getText()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
               
               serv_type_cost = String.valueOf(rs.getString("cost"));
               lbl_cost_serv_type.setText(serv_type_cost);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void setBox_sizeCost(){
        String box_size_cost;
        
         try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from tbl_core2_boxes where box_description='"+lbl_box.getText()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
               
               box_size_cost = String.valueOf(rs.getString("box_amount"));
               lbl_cost_box.setText(box_size_cost);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Booking_informationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void computation(){
         
         Double serv_type_cost;
         Double box_size_cost;
         Double freight_cost;
         Double weight_cost;
         Double sub_total;
         Double vat;
         Double total;
         Double weight_total;
         
         serv_type_cost = Double.parseDouble(lbl_cost_serv_type.getText());
         box_size_cost = Double.parseDouble(lbl_cost_box.getText());
         freight_cost = Double.parseDouble(lbl_cost_freight.getText());
         weight_cost = Double.parseDouble(lbl_cost_weight.getText()) * Double.parseDouble(lbl_quantity.getText());
         weight_total = Double.parseDouble(lbl_weight.getText()) * Double.parseDouble(lbl_quantity.getText());
         
         sub_total = serv_type_cost + box_size_cost + freight_cost + weight_total ;
         vat = sub_total * 0.12;
         
         total = sub_total + vat + Double.parseDouble(lbl_charge.getText());
         
         lbl_sub_total.setText(new DecimalFormat("##.##").format(sub_total));
         lbl_vat.setText(new DecimalFormat("##.##").format(vat));
         lbl_total.setText(new DecimalFormat("##.##").format(total));
         lbl_weight_total.setText(weight_total.toString());
         charges_computation();

     }

    @FXML
    private void viewCharges(ActionEvent event) {
        
//        Modal md = Modal.getInstance(new Form("/FXMLS/Core1_Main/Modals/pom_charges.fxml").getParent());
//        md.open();
//        md.getF().getStage().setOnCloseRequest(action -> {
//            
//        FXMLS.Core1_Main.Modals.Pom_chargesController.modalOpen = false;
        
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLS/Core1_Main/Modals/pom_charges.fxml")); 
//        
//            try {  
//                
//            String insurance;
//            String liability;
//            Double sub;
//            conn = DBConnector.getConnection();
//            
//            String query = "select * from aerolink.tbl_core1_booking where ref_no='"+lbl_ref_no.getText()+"'";
//            pst = conn.prepareStatement(query);
//            rs = pst.executeQuery();
//            
//            while(rs.next()){
//               
//               insurance = rs.getString("insurance");
//               liability = rs.getString("liability");
//               sub = Double.parseDouble(lbl_sub_total.getText());
//               Parent root = (Parent)fxmlLoader.load();          
//               Pom_chargesController controller = fxmlLoader.<Pom_chargesController>getController();
//               controller.setData(insurance , liability , sub);
//               Scene scene = new Scene(root); 
//
//               newStage.setScene(scene);    
//
//                newStage.show(); 
//            }
//            pst.close();
//            rs.close();
//        } catch (SQLException | IOException ex) {
//            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    });
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("/FXMLS/Core1_Main/Modals/pom_charges.fxml"));
        try{
            load.load();
            String insurance;
            String liability;
            Double sub;
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core1_booking where ref_no='"+lbl_ref_no.getText()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
             while(rs.next()){
               
                insurance = rs.getString("insurance");
                liability = rs.getString("liability");
                sub = Double.parseDouble(lbl_sub_total.getText());
                
                Pom_chargesController vcc = load.getController();
                Parent p = load.getRoot();
                Stage st = new Stage();
                st.setScene(new Scene(p));
                st.setResizable(false);
                st.initStyle(StageStyle.DECORATED);
                st.show();
                vcc.setData(insurance, liability, sub);
                }
                    pst.close();
                    rs.close();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }


    
 } 
    
    public void charges_computation(){
        
        String insurance;
        String liability;
        Double charges;
        
        try {   
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core1_booking where ref_no='"+lbl_ref_no.getText()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
               
                insurance = rs.getString("insurance");
                liability = rs.getString("liability");
                
                if(insurance.equals("Yes")){
                   insurance_cost = Double.parseDouble(lbl_sub_total.getText()) * 0.02; 
                }else{
                    insurance_cost = 0.0;
                }
                
                if(liability.equals("Yes")){
                    liability_cost = 240.0;
                }else{
                    liability_cost = 0.0;
                }
                
                charges = insurance_cost + liability_cost;
                lbl_charge.setText(charges.toString());
               
            }
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void update_status(){
        
        try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_booking set status ='For Consolidation' where ref_no='"+lbl_ref_no.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
     
        } catch (SQLException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    public void update_status3(){
        
        try {
            conn = DBConnector.getConnection();
            
            
            String query = "Update tbl_core2_sop set weight='"+Double.parseDouble(lbl_weight_total.getText())+"' where ref_no='"+lbl_ref_no.getText()+"'";
            pst = conn.prepareStatement(query);
            pst.execute();

                    

                         
        } catch (SQLException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void viewPO(ActionEvent event) {
        
//        Modal md = Modal.getInstance(new Form("/FXMLS/Core1_Main/Modals/pom_view.fxml").getParent());
//        md.open();
//        md.getF().getStage().setOnCloseRequest(action -> {
//        FXMLS.Core1_Main.Modals.Pom_viewController.modalOpen = false;
//          
//        });
        AlertBox a =  new AlertBox();
        Pom_viewController ad = new Pom_viewController();
        a.loadfxml("/FXMLS/Core1_Main/Modals/pom_view.fxml", ad);
        
    }

    @FXML
    private void print(ActionEvent event) throws DocumentException, IOException, SQLException, ClassNotFoundException {
        
        
        
        try {
            setInvoice();
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core1_booking inner join tbl_core2_sop on aerolink.tbl_core1_booking.ref_no = tbl_core2_sop.ref_no where aerolink.tbl_core1_booking.book_no='"+lbl_book_no.getText()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
            String name = rs.getString("ship_name");
            String contact = rs.getString("ship_contact");
            String email = rs.getString("ship_email");
            String description = rs.getString("quantity") +" "+ rs.getString("description");
            String weight = rs.getString("weight");
            Float quantity = rs.getFloat("quantity");
            Float total_weight = quantity * Float.parseFloat(weight);
                
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("Report.pdf"));
            document.open();
            Image image = Image.getInstance("src/Assets/300 x 300.png");
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(420, 700);
            document.add(image);
            
            document.add(new Paragraph(title));
            document.add(new Paragraph("Phone: (02) 912-2727",Others));
            document.add(new Paragraph("Email: support@aerolink.com",Others));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("#1071 Brgy. Kaligayahan, Quirino Highway",Others));
            document.add(new Paragraph("Novaliches, 1123 Quezon City, Philippines",Others));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            FixText("INVOICE",35,650,writer,20);
            LineSeparator ls = new LineSeparator();
            document.add(new Chunk(ls));
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph(billto));
            document.add(new Paragraph(name));
            document.add(new Paragraph(contact));
            document.add(new Paragraph(email));
            FixText("Invoice No : " ,395,610,writer,11);
            FixText(invoice_no,485,610,writer,11);
            FixText("Invoice Date : " ,395,593,writer,11);
            FixText(lbl_po_date.getText(),485,593,writer,11);
            FixText("Total Weight : " ,395,576,writer,11);
            FixText(total_weight.toString(),485,576,writer,11);
            FixText("Due Date : ",395,559,writer,11);
            FixText("30 Days",485,559,writer,11);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            FixText(from.toString(),35,520,writer,11);
            FixText(to.toString(),35,502,writer,11);
            FixText(lbl_ship_add.getText(),80,520,writer,11);
            FixText(lbl_rec_add.getText(),80,502,writer,11);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            PdfPTable package_table = new PdfPTable(3);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Description"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPadding(10);
            cell1.setFixedHeight(30f);
            package_table.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Weight per piece"));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setPadding(10);
            package_table.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Amount"));
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setPadding(10);
            package_table.addCell(cell3);
            
            PdfPCell desc = new PdfPCell(new Paragraph(description));
            desc.setHorizontalAlignment(Element.ALIGN_CENTER);
            desc.setPaddingTop(10);
            desc.setFixedHeight(130f);
            package_table.addCell(desc);
            PdfPCell tbl_weight = new PdfPCell(new Paragraph(weight));
            tbl_weight.setHorizontalAlignment(Element.ALIGN_CENTER);
            tbl_weight.setPaddingTop(10);
            package_table.addCell(tbl_weight);
            PdfPCell amount = new PdfPCell(new Paragraph(lbl_sub_total.getText()));
            amount.setHorizontalAlignment(Element.ALIGN_CENTER);
            amount.setPaddingTop(10);
            package_table.addCell(amount);
   
            package_table.setWidths(new int[]{350,100,100});;
            package_table.setWidthPercentage(100);
            document.add(package_table);
            
            FixText("Sub Total : ",395,280,writer,11);
            FixText("₱ " +lbl_sub_total.getText(),495,280,writer,11);
            FixText("Vat (12%) : ",395,260,writer,11);
            FixText("₱ " +lbl_vat.getText(),495,260,writer,11);
            FixText("Charges : " ,395,240,writer,11);
            FixText("₱ " +lbl_charge.getText(),495,240,writer,11);
            FixText("Total Amount : " ,395,220,writer,11);
            FixText("₱ " +lbl_total.getText(),495,220,writer,11);
            FixText("Tracking No. : ",395,650,writer,11);
            FixText(lbl_track.getText(),485,650,writer,11);

            document.close();

            }
            pst.close();
            rs.close();
        add_invoice();
        add_po();
        inserthawb();
        update_status();
        update_status3();
        initTable();
        clear();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Po_managementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String pdfFile="Report.pdf";
        File f = new File(pdfFile);
        if (pdfFile.toString().endsWith(".pdf")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
        } else {
            //For cross platform use
            Desktop desktop = Desktop.getDesktop();

            desktop.open(f);
        }
        
        
        
    }
    
    private static void FixText(String text, int x, int y,PdfWriter writer,int size) {
    try {
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.saveState();
        cb.beginText();
        cb.moveText(x, y);
        cb.setFontAndSize(bf, size);
        cb.showText(text);
        cb.endText();
        cb.restoreState();
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    }
}
    
    public void add_invoice(){
        try{
    
                        conn = DBConnector.getConnection();

                        String sql = "INSERT INTO aerolink.tbl_core1_invoices(invoice_no, po_no, ref_no, invoice_date) VALUES(?,?,?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, invoice_no);
                        pst.setString(2, lbl_po_no.getText());
                        pst.setString(3, lbl_ref_no.getText());
                        pst.setString(4, lbl_po_date.getText());

                        pst.execute();
                        
                         
                   }catch(SQLException ex){
                        System.err.println(ex);
                }
    }
    
    public void add_po(){
        
        if("".equals(lbl_ref_no.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "Purchase Order", "Select information you want to save !");
            alert.open();
        }
        try{
    
                        conn = DBConnector.getConnection();

                        String sql = "INSERT INTO aerolink.tbl_core1_pom(po_no, po_date, track_no, ref_no, sub, total, vat, charge, freight, insurance_cost, liability_cost) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, lbl_po_no.getText());
                        pst.setString(2, lbl_po_date.getText());
                        pst.setString(3, lbl_track.getText());
                        pst.setString(4, lbl_ref_no.getText());
                        pst.setString(5, lbl_sub_total.getText());
                        pst.setString(6, lbl_total.getText());
                        pst.setString(7, lbl_vat.getText());
                        pst.setString(8, lbl_charge.getText());
                        pst.setString(9, lbl_cost_freight.getText());
                        pst.setString(10,insurance_cost.toString());
                        pst.setString(11,liability_cost.toString());
                        

                        pst.execute();
                        setPO();
                                               
                        Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.INFORMATION, "", "Purchase Order", "Saved Successfully !");
                        alert.open();
                         
                   }catch(SQLException ex){
                        System.err.println(ex);
                }
    }
    
     public void searchData(){
        
        FilteredList<db_booking> filteredData = new FilteredList<>(oblist, p -> true);
        
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(db_booking -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (db_booking.getRef_no().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<db_booking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl_pom.comparatorProperty());
        tbl_pom.setItems(sortedData);
   
    }

    @FXML
    private void clear() {
        
        lbl_ref_no.setText("");
        lbl_ship_add.setText("");
        lbl_rec_add.setText("");
        lbl_cost_box.setText("0.0");
        lbl_cost_freight.setText("0.0");
        lbl_cost_weight.setText("0.0");
        lbl_quantity.setText("");
        lbl_cost_serv_type.setText("0.0");
        lbl_sub_total.setText("0.0");
        lbl_vat.setText("0.0");
        lbl_charge.setText("0.0");
        lbl_total.setText("0.0");
        lbl_serv_type.setText("");
        lbl_box.setText("");
        lbl_weight.setText("");
        
    }


    @FXML
    private void viewInvoices(ActionEvent event) {
        
//        Modal md = Modal.getInstance(new Form("/FXMLS/Core1_Main/Modals/invoice_view.fxml").getParent());
//        md.open();
//        md.getF().getStage().setOnCloseRequest(action -> {
//        FXMLS.Core1_Main.Modals.Invoice_viewController.modalOpen = false;
//          
//        });

        AlertBox a =  new AlertBox();
        Invoice_viewController ad = new Invoice_viewController();
        a.loadfxml("/FXMLS/Core1_Main/Modals/invoice_view.fxml", ad);
        
    }
    
    public void inserthawb(){
        if("".equals(lbl_ref_no.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "Purchase Order", "Select information you want to save !");
            alert.open();
        }
        try{
    
                        conn = DBConnector.getConnection();

                        String sql = "INSERT INTO aerolink.tbl_core1_hawb(ref_no , remarks) VALUES(?,?);";
                        pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, lbl_ref_no.getText());
                        pst.setString(2, "For Print");
                        
                        pst.execute();
                         
                   }catch(SQLException ex){
                        System.err.println(ex);
                }
    }
     
}
