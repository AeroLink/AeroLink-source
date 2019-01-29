/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main;

import FXMLS.Core1_Main.Model.db_booking;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author lemnovo
 */
public class Mawb_hawbController implements Initializable {
    
    ObservableList<db_booking> oblist  = FXCollections.observableArrayList();
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    Date date = new Date();
    Format formatter = new SimpleDateFormat("MMM-dd-yyyy");
    String s = formatter.format(date);
    
    public static final Font Title = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
    Chunk particular = new Chunk("Particular", Title);
    Chunk amount = new Chunk("Amount", Title);
    @FXML
    private TextField txt_search;
    @FXML
    private TableView<db_booking> tbl_hawb;
    @FXML
    private TableColumn<db_booking, String> col_ref;
    @FXML
    private TableColumn<db_booking, String> col_ship_from;
    @FXML
    private TableColumn<db_booking, String> col_ship_to;
    @FXML
    private TableColumn<db_booking, String> col_status;
    @FXML
    private Label lbl_ref;
    
    String track_no;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        setCellValue();
        searchData();
    }    
    
     public void initTable(){
        oblist.clear();
        try{  
        conn = DBConnector.getConnection();
      
        
        rs = conn.createStatement().executeQuery("select * from aerolink.tbl_core1_booking inner join aerolink.tbl_core1_hawb on aerolink.tbl_core1_booking.ref_no = aerolink.tbl_core1_hawb.ref_no where status ='For Consolidation' and aerolink.tbl_core1_hawb.remarks = 'For Print'");
        
        while (rs.next()){
            oblist.add(new db_booking(rs.getString("book_no"), rs.getString("book_date")
                                             , rs.getString("ship_name") ,rs.getString("ship_address") ,rs.getString("ship_brgy") ,rs.getString("ship_city"),rs.getString("ship_province"),rs.getString("ship_zip"),rs.getString("ship_email"),rs.getString("ship_contact")
                                             , rs.getString("rec_name") ,rs.getString("rec_address") ,rs.getString("rec_brgy") ,rs.getString("rec_city") ,rs.getString("rec_province") ,rs.getString("rec_zip") ,rs.getString("rec_contact")
                                             , rs.getString("serv_type"), rs.getString("box"), rs.getString("quantity"), rs.getString("insurance"), rs.getString("liability"), rs.getString("status"), rs.getString("ref_no")));
        }
        
      }catch (SQLException ex){
          
          Logger.getLogger(Booking_mainController.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        
        tbl_hawb.setItems(oblist);
        
    }
     
    public void setCellValue(){
        
       col_ref.setCellValueFactory(new PropertyValueFactory<>("ref_no"));
       col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
       col_ship_from.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getShipper_address()));
       col_ship_to.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getReceiver_address()));

    }
    
    @FXML
    public void displayTable(){
        tbl_hawb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                db_booking cbt = tbl_hawb.getItems().get(tbl_hawb.getSelectionModel().getSelectedIndex());
                try{
                    lbl_ref.setText(cbt.getRef_no());
                    
                }catch(Exception ex){
                    Logger.getLogger(Mawb_hawbController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
        sortedData.comparatorProperty().bind(tbl_hawb.comparatorProperty());
        tbl_hawb.setItems(sortedData);
   
    }
    
    
    @FXML
    private void print(ActionEvent event) throws DocumentException, BadElementException, IOException, SQLException, ClassNotFoundException {
        
        try {
          
            conn = DBConnector.getConnection();
            
            String query = "select * from aerolink.tbl_core1_booking inner join aerolink.tbl_core1_pom on aerolink.tbl_core1_booking.ref_no = aerolink.tbl_core1_pom.ref_no inner join tbl_core2_sop on aerolink.tbl_core1_pom.ref_no = tbl_core2_sop.ref_no where aerolink.tbl_core1_booking.ref_no='"+lbl_ref.getText()+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
            
   
            String ship_name = rs.getString("ship_name");
            String ship_contact = rs.getString("ship_contact");
            String rec_name = rs.getString("rec_name");
            String rec_contact = rs.getString("rec_contact");
            String origin = rs.getString("ship_city") + ", "+ rs.getString("ship_province") +" "+rs.getString("ship_zip");
            String destination = rs.getString("rec_city") + ", "+ rs.getString("rec_province")+" "+ rs.getString("rec_zip");
            String box = rs.getString("box");
            Double total_weight = rs.getDouble("weight") * rs.getDouble("quantity"); 
            String description = rs.getString("quantity") +" "+ rs.getString("description");
            Double freight = rs.getDouble("freight");
            Double insurance = rs.getDouble("insurance_cost");
            Double liability = rs.getDouble("liability_cost");
            Double vat = rs.getDouble("vat");
            Double total = freight + insurance + liability + vat ;
            String serv_type = rs.getString("serv_type");
            track_no = rs.getString("track_no");
                
                
            Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
            document.setMargins(20, 20, 130, 20);
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("Hawb.pdf"));
            document.open();
            Image image = Image.getInstance("src/Assets/300 x 300.png");
            image.scaleAbsolute(100, 100);
            image.setAbsolutePosition(40, 480);
            document.add(image);
            FixText("AeroLink ",150,530,writer,30);
            FixText("#1071 Brgy. Kaligayahan, Quirino Highway ",150,510,writer,11);
            FixText("Phone: (02) 912-2727 / Email: support@aerolink.com ",150,490,writer,11);
            
            FixText("Date :  "+s,650,524,writer,11);
            FixText("House Waybill ",650,507,writer,11);
            FixText("No : "+track_no,650,490,writer,11);
            PdfPTable hawb_table = new PdfPTable(10);
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("NAME & CONTACT NO. OF SHIPPER"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setColspan(5);
            cell1.setPadding(5);
            hawb_table.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Paragraph("NAME & CONTACT NO. OF CONSIGNEE"));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.setColspan(5);
            cell2.setPadding(5);
            hawb_table.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(new Paragraph(ship_name +"\n\n"+ ship_contact));
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setColspan(5);
            cell3.setPadding(5);
            cell3.setFixedHeight(40f);
            hawb_table.addCell(cell3);
            PdfPCell cell4 = new PdfPCell(new Paragraph(rec_name +"\n\n"+ rec_contact));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setColspan(5);
            cell4.setPadding(5);
            hawb_table.addCell(cell4);
            PdfPCell cell5 = new PdfPCell(new Paragraph(" Origin :"));
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setColspan(2);
            cell5.setPadding(3);
            hawb_table.addCell(cell5);
            PdfPCell cell6 = new PdfPCell(new Paragraph(origin));
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setColspan(3);
            cell6.setPadding(3);
            hawb_table.addCell(cell6);
            PdfPCell cell7 = new PdfPCell(new Paragraph(" Destination :"));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setColspan(2);
            cell7.setPadding(3);
            hawb_table.addCell(cell7);
            PdfPCell cell8 = new PdfPCell(new Paragraph(destination));
            cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell8.setColspan(3);
            cell8.setPadding(3);
            hawb_table.addCell(cell8);
            PdfPCell cell9 = new PdfPCell(new Paragraph(" Box Used :"));
            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell9.setColspan(1);
            cell9.setPadding(3);
            hawb_table.addCell(cell9);
            PdfPCell cell10 = new PdfPCell(new Paragraph(box));
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell10.setColspan(2);
            cell10.setPadding(3);
            hawb_table.addCell(cell10);
            PdfPCell cell11 = new PdfPCell(new Paragraph(" Total Weight :"));
            cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell11.setColspan(2);
            cell11.setPadding(3);
            hawb_table.addCell(cell11);
            PdfPCell cell12 = new PdfPCell(new Paragraph(total_weight.toString()+" Kg."));
            cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell12.setColspan(1);
            cell12.setPadding(3);
            hawb_table.addCell(cell12);
            PdfPCell cell13 = new PdfPCell(new Paragraph(" PAYMENT OF THE FOLLOWING "));
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell13.setColspan(4);
            cell13.setPaddingTop(5);
            hawb_table.addCell(cell13);
            PdfPCell cell14 = new PdfPCell(new Paragraph(" DESCRIPTION / CONTENT "));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell14.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell14.setColspan(6);
            cell14.setPadding(3);
            hawb_table.addCell(cell14);
            PdfPCell cell15 = new PdfPCell(new Paragraph(particular));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell15.setColspan(2);
            cell15.setPadding(3);
            hawb_table.addCell(cell15);
            PdfPCell cell16 = new PdfPCell(new Paragraph(amount));
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setColspan(2);
            cell16.setPadding(3);
            hawb_table.addCell(cell16);
            PdfPCell cell17 = new PdfPCell(new Paragraph(description));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell17.setColspan(6);
            cell17.setRowspan(8);
            cell17.setPaddingTop(20);
            hawb_table.addCell(cell17);
            PdfPCell cell18 = new PdfPCell(new Paragraph(" Freight : "));
            cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell18.setColspan(2);
            cell18.setPadding(3);
            hawb_table.addCell(cell18);
            PdfPCell cell19 = new PdfPCell(new Paragraph(new DecimalFormat("##.##").format(freight)));
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell19.setColspan(2);
            cell19.setPadding(3);
            hawb_table.addCell(cell19);
            PdfPCell cell20 = new PdfPCell(new Paragraph(" Valuation : "));
            cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell20.setColspan(2);
            cell20.setPadding(3);
            hawb_table.addCell(cell20);
            PdfPCell cell21 = new PdfPCell(new Paragraph("0.0"));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell21.setColspan(2);
            cell21.setPadding(3);
            hawb_table.addCell(cell21);
            PdfPCell cell22 = new PdfPCell(new Paragraph(" Insurance :"));
            cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell22.setColspan(2);
            cell22.setPadding(3);
            hawb_table.addCell(cell22);
            PdfPCell cell23 = new PdfPCell(new Paragraph(new DecimalFormat("##.##").format(insurance)));
            cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell23.setColspan(2);
            cell23.setPadding(3);
            hawb_table.addCell(cell23);
            PdfPCell cell24 = new PdfPCell(new Paragraph(" Ext. Liability :"));
            cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell24.setColspan(2);
            cell24.setPadding(3);
            hawb_table.addCell(cell24);
            PdfPCell cell25 = new PdfPCell(new Paragraph(new DecimalFormat("##.##").format(liability)));
            cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell25.setColspan(2);
            cell25.setPadding(3);
            hawb_table.addCell(cell25);
            PdfPCell cell26 = new PdfPCell(new Paragraph(" Pick Up Fee :"));
            cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell26.setColspan(2);
            cell26.setPadding(3);
            hawb_table.addCell(cell26);
            PdfPCell cell27 = new PdfPCell(new Paragraph("0.0"));
            cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell27.setColspan(2);
            cell27.setPadding(3);
            hawb_table.addCell(cell27);
            PdfPCell cell28 = new PdfPCell(new Paragraph(" Others :"));
            cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell28.setColspan(2);
            cell28.setPadding(3);
            hawb_table.addCell(cell28);
            PdfPCell cell29 = new PdfPCell(new Paragraph("0.0"));
            cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell29.setColspan(2);
            cell29.setPadding(3);
            hawb_table.addCell(cell29);
            PdfPCell cell30 = new PdfPCell(new Paragraph(" 12% Vat :"));
            cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell30.setColspan(2);
            cell30.setPadding(3);
            hawb_table.addCell(cell30);
            PdfPCell cell31 = new PdfPCell(new Paragraph(new DecimalFormat("##.##").format(vat)));
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31.setColspan(2);
            cell31.setPadding(3);
            hawb_table.addCell(cell31);
            PdfPCell cell32 = new PdfPCell(new Paragraph(" Total :"));
            cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell32.setColspan(2);
            cell32.setPadding(3);
            hawb_table.addCell(cell32);
            PdfPCell cell33 = new PdfPCell(new Paragraph(new DecimalFormat("##.##").format(total)));
            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33.setColspan(2);
            cell33.setPadding(3);
            hawb_table.addCell(cell33);
            PdfPCell cell34 = new PdfPCell(new Paragraph(" Service Type :"));
            cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell34.setColspan(2);
            cell34.setPadding(3);
            hawb_table.addCell(cell34);
            PdfPCell cell35 = new PdfPCell(new Paragraph(serv_type));
            cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell35.setColspan(4);
            cell35.setPadding(3);
            hawb_table.addCell(cell35);
            PdfPCell cell36 = new PdfPCell(new Paragraph(" "));
            cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell36.setColspan(2);
            cell36.setPadding(3);
            hawb_table.addCell(cell36);
            PdfPCell cell37 = new PdfPCell(new Paragraph(" "));
            cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell37.setColspan(2);
            cell37.setPadding(3);
            hawb_table.addCell(cell37);
            PdfPCell cell38 = new PdfPCell(new Paragraph(" Issued By :"));
            cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell38.setColspan(2);
            cell38.setPadding(3);
            hawb_table.addCell(cell38);
            PdfPCell cell39 = new PdfPCell(new Paragraph(" "));
            cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell39.setColspan(4);
            cell39.setPadding(3);
            hawb_table.addCell(cell39);
            PdfPCell cell40 = new PdfPCell(new Paragraph(" "));
            cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell40.setColspan(2);
            cell40.setPadding(3);
            hawb_table.addCell(cell40);
            PdfPCell cell41 = new PdfPCell(new Paragraph(" "));
            cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell41.setColspan(2);
            cell41.setPadding(3);
            hawb_table.addCell(cell41);
            PdfPCell cell42 = new PdfPCell(new Paragraph("Shipper certified that particulars on face hereof are correct agrees that forwarding liability shall not exceed the value declared. "));
            cell42.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell42.setColspan(5);
            cell42.setPadding(10);
            hawb_table.addCell(cell42);
            PdfPCell cell43 = new PdfPCell(new Paragraph("Consignee certifies that the above mentioned goods were delivered and received in good order and complete except as otherwise specified. "));
            cell43.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell43.setColspan(5);
            cell43.setPadding(10);
            hawb_table.addCell(cell43);
            PdfPCell cell44 = new PdfPCell(new Paragraph(" SHIPPER'S PRINTED NAME : "));
            cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell44.setColspan(5);
            cell44.setPadding(3);
            hawb_table.addCell(cell44);
            PdfPCell cell45 = new PdfPCell(new Paragraph(" CONSIGNEE'S PRINTED NAME : "));
            cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell45.setColspan(5);
            cell45.setPadding(3);
            hawb_table.addCell(cell45);
            PdfPCell cell46 = new PdfPCell(new Paragraph(" SIGNATURE : "));
            cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell46.setColspan(3);
            cell46.setPadding(3);
            hawb_table.addCell(cell46);
            PdfPCell cell47 = new PdfPCell(new Paragraph(" Date : "));
            cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell47.setColspan(2);
            cell47.setPadding(3);
            hawb_table.addCell(cell47);
            PdfPCell cell48 = new PdfPCell(new Paragraph(" SIGNATURE : "));
            cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell48.setColspan(3);
            cell48.setPadding(3);
            hawb_table.addCell(cell48);
            PdfPCell cell49 = new PdfPCell(new Paragraph(" Date : "));
            cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell49.setColspan(2);
            cell49.setPadding(3);
            hawb_table.addCell(cell49);
            
            hawb_table.setWidthPercentage(100);
            document.add(hawb_table);
            document.close();
            }
            update();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mawb_hawbController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String pdfFile="Hawb.pdf";
        File f = new File(pdfFile);
        if (pdfFile.toString().endsWith(".pdf")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
        } else {
            //For cross platform use
            Desktop desktop = Desktop.getDesktop();

            desktop.open(f);
        }
        initTable();
        
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
     
     public void update(){
         if("".equals(lbl_ref.getText())){
            Helpers.AlertResponse alert = new Helpers.AlertResponse(Alert.AlertType.ERROR, "", "ERROR", "Please enter information you want to print !");
            alert.open();
        }else{
             
            try {
            conn = DBConnector.getConnection();
            
            String sql = "Update aerolink.tbl_core1_hawb set remarks ='printed' where ref_no = '"+lbl_ref.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();

                         
        } catch (SQLException ex) {
            Logger.getLogger(Consolidation_deconsolidationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
     }
    
}
