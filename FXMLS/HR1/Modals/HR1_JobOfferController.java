/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_Applicant;
import Model.HR1.HR1_JobOffers;
import Model.HR1.JobPosting;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_JobOfferController implements Initializable {

    public String fulltime;
    public String jobTitle;

    HR1_JobOffers jobOffers = new HR1_JobOffers();

    public static String dateExpire;

    @FXML
    private TextField txtReportTo;
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private TextField txtWorkPlace;
    @FXML
    private TextField txtAnnualSalary;
    @FXML
    private ListView listDR;
    @FXML
    private TextArea txtDR;
    @FXML
    private JFXButton btnPushDR;
    @FXML
    private JFXButton btnRemoveDR;
    @FXML
    private DatePicker txtExpiration;
    @FXML
    private ListView listB;
    @FXML
    private TextArea txtB;
    @FXML
    private JFXButton btnAddB;
    @FXML
    private JFXButton btnRemoveB;
    @FXML
    private JFXButton btnExport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        dateExpire = "x";

        if (jobOffers.where(new Object[][]{
            {"app_id", "=", HR1_Applicant.app_id}
        }).get().stream().count() != 0) {
            jobOffers.where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).get().stream().forEach(event -> {
                HashMap row = (HashMap) event;

                txtAnnualSalary.setText(row.get("salary").toString());
                txtExpiration.setValue(LocalDate.parse(row.get("offerexpiration").toString()));
                txtReportTo.setText(row.get("reportTo").toString());
                txtStartDate.setValue(LocalDate.parse(row.get("start_date").toString()));
                txtWorkPlace.setText(row.get("location").toString());

                String[] a = row.get("duties").toString().split(", ");

                String[] b = row.get("benefits").toString().split(", ");

                for (String c : a) {
                    listDR.getItems().add(c);
                }

                for (String d : b) {
                    listB.getItems().add(d);
                }

            });
        }

        JobPosting jp = new JobPosting();

        jp.where(new Object[][]{
            {"id", "=", HR1_Applicant.jobPosted_id}
        }).get().stream().forEach(event -> {
            HashMap row = (HashMap) event;

            this.fulltime = row.get("status").toString();
            this.jobTitle = row.get("title").toString();
        });

        //Bind
        btnAddB.setOnAction(event -> {
            pushtoList(listB, txtB);
        });

        btnRemoveB.setOnAction(event -> {
            removeFromList(listB);
        });

        btnPushDR.setOnAction(event -> {
            pushtoList(listDR, txtDR);
        });

        btnRemoveDR.setOnAction(event -> {
            removeFromList(listDR);
        });

        btnExport.setOnAction(event -> ExportToPDF());

        txtAnnualSalary.setOnKeyTyped(value -> {
            System.err.println(value.getCharacter());
            if (!Character.isDigit(value.getCharacter().charAt(0))) {
                value.consume();
            }
        });

        txtAnnualSalary.setOnKeyReleased(value -> {
            if (txtAnnualSalary.getText().isEmpty()) {
                txtAnnualSalary.setText("0");
            } else {

                txtAnnualSalary.setText(NumberFormat.getInstance().format(Double.parseDouble(txtAnnualSalary.getText().replace(",", ""))));
                txtAnnualSalary.end();
            }
        });

    }

    public void pushtoList(ListView lst, TextArea txt) {
        if (!txt.getText().isEmpty()) {
            lst.getItems().add(txt.getText());
        }
    }

    public void removeFromList(ListView lst) {
        if (lst.getSelectionModel().getSelectedItem() != null) {
            lst.getItems().remove(lst.getSelectionModel().getSelectedIndex());
        }
    }

    String finalListOfDR = "";
    String finalListOfB = "";
    String DListOfDR = "";
    String DListOfB = "";

    public void ExportToPDF() {
        //TODO: Add File Transfer ! and Email 
        try {
            dateExpire = txtExpiration.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);

            finalListOfDR = "";
            finalListOfB = "";
            DListOfDR = "";
            DListOfB = "";

            listDR.getItems().stream().forEach(list -> {
                finalListOfDR += " - " + list.toString() + "\n";
                DListOfDR += list.toString() + ", ";
            });

            listB.getItems().stream().forEach(list -> {
                finalListOfB += " - " + list.toString() + "\n";
                DListOfB += list.toString() + ", ";
            });

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdf/APPLICANT_" + HR1_Applicant.app_id + "_JobOfferLetter.pdf"));

            document.open();

            Paragraph paragraph = new Paragraph();

            paragraph.add("Dear " + HR1_Applicant.fullname + ",\n"
                    + " \n"
                    + "We are pleased to offer you the " + this.fulltime + " position of " + this.jobTitle + " at AeroLink with a start date of " + txtStartDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ". You will be reporting directly to " + txtReportTo.getText() + " at " + txtWorkPlace.getText() + ". We believe your skills and experience are an excellent match for our company. \n"
                    + " \n"
                    + "In this role, you will be required to \n" + finalListOfDR + "\n"
                    + " \n"
                    + "The annual starting salary for this position is PHP " + txtAnnualSalary.getText() + " to be paid on a monthly basis by direct payment.\n"
                    + " \n"
                    + " \n"
                    + "Your employment with AeroLink will be on an at-will basis, which means you and the company are free to terminate the employment relationship at any time for any reason. This letter is not a contract or guarantee of employment for a definite amount of time. \n"
                    + " \n"
                    + "As an employee of AeroLink, you are also eligible for our benefits program, which includes \n" + finalListOfB + "\n and other benefits which will be described in more detail in the employee handbook and orientation package. \n"
                    + " \n"
                    + "Please confirm your acceptance of this offer by signing and returning this letter by " + txtExpiration.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ".\n"
                    + " \n"
                    + "We are excited to have you join our team! If you have any questions, please feel free to reach out at any time.");

            document.add(paragraph);

            document.close();

            Helpers.EIS_Response.SuccessResponse("Success", "APPLICANT_" + HR1_Applicant.app_id + "_JobOfferLetter.pdf was successfully created and saved to pdf directory");

            if (jobOffers.where(new Object[][]{
                {"app_id", "=", HR1_Applicant.app_id}
            }).get().stream().count() == 0) {
                jobOffers.insert(new Object[][]{
                    {"app_id", HR1_Applicant.app_id},
                    {"start_date", txtStartDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)},
                    {"offerexpiration", txtExpiration.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)},
                    {"reportTo", txtReportTo.getText()},
                    {"location", txtWorkPlace.getText()},
                    {"salary", txtAnnualSalary.getText()},
                    {"duties", DListOfDR},
                    {"benefits", DListOfB},
                    {"status", 0},
                    {"filePath", " "}
                });

            } else {

                jobOffers.update(new Object[][]{
                    {"start_date", txtStartDate.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)},
                    {"offerexpiration", txtExpiration.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)},
                    {"reportTo", txtReportTo.getText()},
                    {"location", txtWorkPlace.getText()},
                    {"salary", txtAnnualSalary.getText()},
                    {"duties", DListOfDR},
                    {"benefits", DListOfB},
                    {"status", 0},
                    {"filePath", "pdf/APPLICANT_" + HR1_Applicant.app_id + "_JobOfferLetter.pdf"}
                }).where(new Object[][]{
                    {"app_id", "=", HR1_Applicant.app_id}
                }).executeUpdate();

                if (dateExpire.equals(txtExpiration.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE))) {
                    dateExpire = "x";
                }
            }

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(HR1_JobOfferController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
