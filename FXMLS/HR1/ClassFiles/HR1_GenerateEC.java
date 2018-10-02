/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lei
 */
public class HR1_GenerateEC {

    public static String generateEC(String Designation, String Salary, String date, String name, String WhomManage, String Department, String ApplicantID) {

        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdf/ECP_"+ ApplicantID +".pdf"));
            document.open();

            document.addTitle("test");

            String curDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

            Paragraph title = new Paragraph("Employment Contract", FontFactory.getFont(FontFactory.defaultEncoding, 14, Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            Paragraph content = new Paragraph("\n\n" + curDate + "\n\n"
                    + "Dear " + name + ",\n"
                    + "\n"
                    + "We are pleased to inform you that we are engaging your services as a " + Designation + " effective " + date + " with a basic salary of " + Salary + " and payable in two parts, every 15th and end of the month. You will be reporting directly to " + WhomManage +  " at " + Department + ".\n"
                    + "The following are the conditions of your employment with this Company:\n"
                    + "\n"
                    + "\n"
                    + "1. You shall be on probation for a period of six (6) months commencing on your first day of work with the Company. During your probationary employment, you will be working with us on a trial basis to determine your fitness for regularization. Your conversion to permanent status shall be primarily conditioned and dependent upon your satisfactory service and performance of the work assigned to you and it is within the exclusive discretion of the Company to determine whether or not such service is satisfactorily performed and on your having successfully passed / complied with our established standards for regularization which include, among others, the following criteria: dependability, trustworthiness, efficiency, initiative, attitude towards work/ the public/ the Company, its officers and co-employees, cooperation, client response, judgment, punctuality, quality/ quantity of work, educability, articulateness and professionalism;\n"
                    + "\n"
                    + "2. The Company likewise reserves its rights to terminate your probationary employment, even prior to the expiration of your probationary period, for any of the just and authorized causes provided by existing law or for your having failed to satisfactorily meet and comply with the above-mentioned standards, condi­tions and requirements. In such event, you will be entitled to collect only your salary up to the end of working hours of the last day of your actual service;\n"
                    + "\n"
                    + "3.You are required to comply with the all existing rules, regulations and policies of the Company as well as those which may hereafter be issued, including but not limited to those governing order and discipline, honesty, safety and security, work assignments and standard operating procedures, use of Company properties and access to matters of confidentiality, and such other rules deemed necessary in the conduct of our business;\n"
                    + "\n"
                    + "4.This probationary employment does not entitle you to the benefits that is or may hereafter be granted only to regular and permanent employees, except those which the Company as a matter of policy and upon its discretion, extends to all employees regardless of status and to those provided by law;\n"
                    + "\n"
                    + "5.You agree that all record and documents of the Company and all information pertaining to its business and/or its affairs and that of its customers are absolutely confidential and unauthorized disclosure or reproduction of the same will not be made by you at any time during or after your employment. You agree that any breach of confidentiality will constitute sufficient ground for immediate termination of your employment for cause and/or civil and criminal liability;\n"
                    + "\n"
                    + "6.You agree to be assigned to any work or work station or branch of the Company for such periods as may be determined by the Company and whenever the service requires such assignments;\n"
                    + "\n"
                    + "7.In case you intend to resign from the Company, you are required to notify the Company at least thirty (30) days prior to the effectively of your resignation, otherwise, failure on your part to do so will render you liable for damages. However, it is within the sole discretion of the Company whether or not to accept such resignation earlier than the expiration of said period.\n"
                    + "\n"
                    + "If you agree with the above terms and conditions, please indicate your conformity by signing on the space provided below for this purpose.\n"
                    + "\n"
                    + "Very truly yours,\n"
                    + "\n"
                    + "Name of Company\n"
                    + "\n"
                    + "By: General Manager\n"
                    + "\n"
                    + "I HEREBY CERTIFY that I have read and have fully understood the foregoing terms and conditions of my employment with the Agency and that I accept the same completely.\n"
                    + "\n"
                    + "EMPLOYEE");

            document.add(title);
            document.add(content);

            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(HR1_GenerateEC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "pdf/ECP_"+ ApplicantID +".pdf";

    }
}
