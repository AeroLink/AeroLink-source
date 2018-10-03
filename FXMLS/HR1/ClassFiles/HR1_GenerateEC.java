/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
            PdfWriter.getInstance(document, new FileOutputStream("pdf/ECP_" + ApplicantID + ".pdf"));
            document.open();

            document.addTitle("test");

            String curDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

            Paragraph title = new Paragraph("EMPLOYMENT CONTRACT", FontFactory.getFont(FontFactory.defaultEncoding, 14, Font.BOLD, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            Paragraph content = new Paragraph("\n\n" + curDate + "\n\n"
                    + "Dear " + name + ",\n"
                    + "\n"
                    + "We are pleased to inform you that we are engaging your services as a " + Designation + " effective " + date + " with a basic salary of " + Salary + " and payable in two parts, every 15th and end of the month. You will be reporting directly to " + WhomManage + " at " + Department + ".\n"
                    + "\n\nThe following are the conditions of your employment with this Company:\n"
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
                    + "Clarice XieLong\n"
                    + "Human Resources Manager\n"
                    + "\n\n"
                    + "I HEREBY CERTIFY that I have read and have fully understood the foregoing terms and conditions of my employment with the Agency and that I accept the same completely.\n"
                    + "\n"
                    + name + "\n"
                    + "Applicant");

            document.add(title);
            document.add(content);

            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(HR1_GenerateEC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "pdf/ECP_" + ApplicantID + ".pdf";

    }

    public static String generateRegEC(String name, String jobDesig, String EffectiveDate, String Salary, String empaddress, String EmpID) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdf/ECPReg_" + EmpID + ".pdf"));
            document.open();

            document.addTitle("Contract of Regular Employment");

            String curDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

            Paragraph title = new Paragraph("CONTRACT OF REGULAR EMPLOYMENT \n\n", FontFactory.getFont(FontFactory.defaultEncoding, 14, Font.BOLD, BaseColor.BLACK));
            Paragraph date = new Paragraph(Dateth(LocalDate.now().getDayOfMonth()) + " day of " + LocalDate.now().getMonth() + ", " + LocalDate.now().getYear(), FontFactory.getFont(FontFactory.defaultEncoding, 12, Font.BOLD, BaseColor.BLACK));
            Paragraph sup_title = new Paragraph("This Agreement entered into this " + date.getContent() + ", at, Quezon City, Philippines, by and between:", FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.PLAIN, BaseColor.BLACK));

            Paragraph c = new Paragraph("\n\nAeroLink,  a company duly organized and registered under the\n"
                    + "laws of the Philippines, with principal office address at address of company,\n"
                    + "herein represented by its Rank, ATTORNEY-IN-FACT, herein referred to as\n"
                    + "the “EMPLOYER”\n"
                    + "- and -\n"
                    + name + ", of legal age, with address at " + empaddress + ",\n"
                    + "hereinafter referred to as the “EMPLOYEE”.\n\n", FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.PLAIN, BaseColor.BLACK));

            Paragraph sup_2 = new Paragraph();
            sup_2.add(sup_title);
            sup_2.add(c);

            sup_title.setAlignment(Element.ALIGN_CENTER);
            c.setAlignment(Element.ALIGN_CENTER);

            Paragraph contract = new Paragraph("1. WHEREAS, the EMPLOYER is a corporation engaged in type of business;\n\n"
                    + "2. WHEREAS, the EMPLOYEE has qualified in the pre-employment and/or regularization requirements conducted by the EMPLOYER;\n\n"
                    + "3. WHEREAS, the EMPLOYER is interested in permanently engaging the EMPLOYEE as DESIGNATION OF EMPLOYEE;\n\n\n"
                    + "NOW, THEREFORE, for and in consideration of the foregoing premises, the parties hereby agree as follows:\n\n"
                    + "1. JOB TITLE & DESCRIPTION\n\n"
                    + "Based on the results of the probationary evaluation, the EMPLOYEE is hereby hired as one of the EMPLOYER’s\n"
                    + "permanent " + jobDesig + ", effective " + EffectiveDate + ".\n\n"
                    + "2. NATURE OF RELATIONSHIP\n\n"
                    + "As part of Management, the EMPLOYEE agree that the cornerstone of his/her relationship to the EMPLOYER is\n"
                    + "based on TRUST and CONFIDENCE. \n\n"
                    + "3. COMPENSATION AND OTHER BENEFITS\n\n"
                    + "The EMPLOYEE shall be paid a basic salary of PHP " + Salary + " gross per month, which includes payment\n"
                    + "for worked and unworked holidays and rest days. Furthermore, the EMPLOYEE is not\n"
                    + "entitled to overtime pay, whenever he/she have to render work beyond eight (8) hours on regular days, on his/her\n"
                    + "rest days and holidays. The salary of the EMPLOYEE is payable in two parts, every 15th and end of the month.\n"
                    + "The salary will be paid either through ATM, in cash, by a bank check, or by a bank or postal transfer, from which\n"
                    + "shall be deducted, where applicable, the social security contributions, withholding taxes and other government\n"
                    + "mandated deductions.\n\n"
                    + "The entitlement of the EMPLOYEE to other benefits such as, without limitation, Vacation Leave, Sick Leave,\n"
                    + "Health Benefit, and Insurance Benefit are subject to terms and conditions the details of which are set forth in\n"
                    + "existing policies and practices, and which may, from time to time, be amended exclusively by the EMPLOYER.\n"
                    + "Notwithstanding incidents when the EMPLOYER granted benefits, bonuses or allowances other than those defined\n"
                    + "in this contract, such incidents are not to be considered as an established practice or precedent and shall not form\n"
                    + "part of the benefits, bonuses and allowances due and demandable under this Contract of Employment.\n\n"
                    + "4. WORK HOURS\n\n"
                    + "You shall work for a period of eight (8) hours per day from Monday to Friday. Any work rendered in excess of\n"
                    + "eight (8) hours per day shall not be subject to payment of overtime. Management prescribes the work\n"
                    + "schedule, and it reserves the right to change the schedule as it may deem necessary to meet operational\n"
                    + "requirements.\n\n"
                    + "5. LEAVES\n\n"
                    + "Subject to the EMPLOYER's standard policies and procedures on such matters which may be reviewed from time\n"
                    + "to time, the EMPLOYEE shall be granted leaves based on the following scales:\n"
                    + "\tVacation Leave 5 working days / year of service\n"
                    + "In case of inability to work due to sickness or accident, the EMPLOYEE shall advise his/her superior, the Human\n"
                    + "Resource Department, or the Operations Head immediately on the first working day of absence. In addition, if the\n"
                    + "incapacity exceeds five (5) days, the EMPLOYEE shall be required to submit a medical certificate, preferably from\n"
                    + "the physician designated by the EMPLOYER.\n\n"
                    + "6. MISCELLANEOUS BONUS, INCENTIVES, BENEFITS\n\n"
                    + "The EMPLOYER may grant the EMPLOYEE other miscellaneous benefits such as life and health insurance,\n"
                    + "transportation, rice and meal subsidy, and other similar fringe benefits. Once granted, these benefits should not be\n"
                    + "construed as having permanency in nature of application and the EMPLOYER reserves the right to withhold,\n"
                    + "cancel, and discontinue future release thereof.\n\n"
                    + "7. PERFORMANCE APPRAISAL\n\n"
                    + "By signing this contract, the EMPLOYEE accept the policy of the EMPLOYER to review the performance of its\n"
                    + "employees regularly, based on performance criteria dictated by performance targets set by top Management and\n"
                    + "the respective key result areas and performance objectives of the division or department for the evaluation period.\n"
                    + "Such criteria shall be made known to the EMPLOYEE at the beginning of the evaluation period, and the expected\n"
                    + "outputs clearly spelled out by the superior of the EMPLOYEE. The EMPLOYEE further recognize that failure to\n"
                    + "achieve a minimum of satisfactory performance for two (2) consecutive evaluation periods shall be a ground for\n"
                    + "termination.\n\n"
                    + "8. ASSIGNMENT OF TASKS\n\n"
                    + "On signing this Contract, the EMPLOYEE recognize the right and prerogative of the EMPLOYER to, without\n"
                    + "limitation, assign and reassign him/her to perform such other tasks within the organization of the EMPLOYER, in\n"
                    + "other branches/units, wherever located as it may deem necessary and beneficial.\n\n"
                    + "9. MEDICAL/DRUG TESTS\n\n"
                    + "By signing this contract, the EMPLOYEE consent and agree, upon request from the EMPLOYER, to undergo, at a\n"
                    + "government accredited institute to be nominated by the EMPLOYER, a medical/drug tests at the expense of the\n"
                    + "EMPLOYEE. This is to be carried out for purposes of determining the physical and mental fitness of the\n"
                    + "EMPLOYEE to perform the functions of the job.\n\n"
                    + "10. COMPANY RULES AND REGULATIONS\n\n"
                    + "All existing as well as future rules and regulations issued by EMPLOYER are hereby deemed incorporated with this\n"
                    + "Contract. The EMPLOYEE recognizes that by signing this Contract, he/she shall be bound by all such rules and\n"
                    + "regulations which EMPLOYER may issue from time to time. On signing this Contract, the EMPLOYEE\n"
                    + "acknowledges his/her duty and responsibility to be aware of the rules and regulations of the EMPLOYER regarding\n"
                    + "his/her employment and to fully comply with this in good faith.\n\n"
                    + "11. DISCIPLINARY MEASURES\n\n"
                    + "On signing this Contract, the EMPLOYEE hereby recognizes the right of the EMPLOYER to impose disciplinary\n"
                    + "measures or sanctions, which may include, but are not limited to, termination of employment, suspensions, fines,\n"
                    + "salary deductions, withdrawal of benefits, loss of privileges, for any and all infraction, act or omission, irrespective of\n"
                    + "whether such infraction, act or omission constitutes a ground for termination.\n\n"
                    + "12. BUSINESS CODE OF CONDUCT\n\n"
                    + "On signing this Contract, the EMPLOYEE agrees to terminate all other business relationships or concerns that\n"
                    + "he/she may be personally involved with that are in the same line of business as that of the EMPLOYER.\n"
                    + "The EMPLOYEE acknowledges being aware of the code of discipline mandated by the EMPLOYER and all the\n"
                    + "rules and regulations issued by the EMPLOYER concerning the employment of the EMPLOYEE with the\n"
                    + "EMPLOYER. The EMPLOYEE acknowledges that it is his/her duty and responsibility to be aware of the\n"
                    + "EMPLOYER's code of discipline as well as rules and regulations regarding his/her employment and to fully comply\n"
                    + "with these in good faith.\n\n"
                    + "13. TERMINATION OF EMPLOYMENT\n\n"
                    + "Aside from the just and authorized causes for the termination of employment enumerated in Arts. 282 to 284 of the\n"
                    + "Labor Code, the following acts and/or omissions shall, without limitation, similarly constitute just and authorized\n"
                    + "grounds for the termination of employment by the EMPLOYER and/or grounds for the EMPLOYER to impose\n"
                    + "disciplinary measures:\n\n"
                    + "a. Intentional or unintentional violation of the EMPLOYER’s policies, rules, and regulations as embodied in the Code of Discipline;\n\n"
                    + "b. Commission of an act which effects a loss of confidence on the part of the EMPLOYER with regard to the EMPLOYEE’s ability to satisfactorily perform the duties and requirements of his/her employment\n\n"
                    + "c. In the event of the EMPLOYEE being incapacitated by ill health, accident or physical or mental incapacity from fully performing his/her duties with the EMPLOYER for an aggregate period of ninety "
                    + "(90) days in any one calendar year, such incapacity being duly certified as such by the EMPLOYER’s "
                    + "appointed doctor;\n\n"
                    + "d. Failure of the EMPLOYEE to pass two (2) consecutive evaluations of his/her work performance; and \n\n"
                    + "e. Other similar acts, omissions, and/or event.\n\n"
                    + "The Contract of employment may be terminated by the EMPLOYER for any of the foregoing grounds and by\n"
                    + "observing the due process requirements of the law. In the event that the EMPLOYEE wishes to terminate this\n"
                    + "Contract of Employment for any reason, he/she must give thirty (30) days written notice to EMPLOYER prior to\n"
                    + "the effective date of termination. Upon termination of this employment, the EMPLOYEE shall promptly account\n"
                    + "for, return, and deliver to the EMPLOYER at the EMPLOYER’s main office, his/her I.D. Cards, Code of\n"
                    + "Discipline manual, Employee Handbook and all the EMPLOYER’s property, which may have been assigned or\n"
                    + "entrusted to his/her care or custody.\n\n"
                    + "14. FINAL PAY\n\n"
                    + "The EMPLOYEE agree that all amounts due to him/her as entitlements, e.g., wages, bonuses or other similar\n"
                    + "monetary benefits from the EMPLOYER at the time of the EMPLOYEE’s separation, resignation or dismissal from\n"
                    + "employment, shall first be applied to any outstanding obligations that the EMPLOYEE may have with the\n"
                    + "EMPLOYER without prejudice to other recourses of the EMPLOYER should the amount due to him/her be less\n"
                    + "than his/her outstanding obligation.\n\n"
                    + "15. CONFIDENTIALITY\n\n"
                    + "It is the responsibility of the EMPLOYEE to ensure that no information gained by virtue of employment with the\n"
                    + "EMPLOYER is disclosed to outsiders unless the disclosure is for necessary business purposes and pursuant to\n"
                    + "properly approved and written agreements. Confidential or proprietary information of others should not be accepted\n"
                    + "by the EMPLOYEE on behalf of EMPLOYER, unless it is necessary and pursuant to the same sort of written\n"
                    + "agreement.\n\n"
                    + "Confidential information is any information belonging to EMPLOYER that could be used by people outside the\n"
                    + "company to the detriment of EMPLOYER. Appropriate steps should be taken by the EMPLOYEE in handling all the\n"
                    + "business information of the EMPLOYER in order to minimize the possibility of unauthorized disclosure.\n"
                    + "16. SEPARABILITY CLAUSE\n\n"
                    + "If any provisions of this document shall be construed to be illegal or invalid, they shall not affect the legality, validity,\n"
                    + "and enforceability of the other provisions of this document; the illegal or invalid provision shall be deleted from this\n"
                    + "document and no longer incorporated herein but all other provisions of this document shall continue.\n\n"
                    + "17. ENTIRE AGREEMENT\n\n"
                    + "This contract represents the entire agreement between the EMPLOYER and the EMPLOYEE and supersedes all"
                    + "previous oral or written communications, representations, or agreements between the parties. \n\n\ns"
                    + "IN WITNESS WHEREOF, the parties have executed this document as of the date and place first mentioned.\n\n"
                    + "AeroLink\n\n"
                    + "By:\n\n"
                    + "ATTORNEY-IN-FACT\n"
                    + "rank\n\n"
                    + name + "\n"
                    + jobDesig + ""
                    + "", FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.PLAIN, BaseColor.BLACK));

            title.setAlignment(Element.ALIGN_CENTER);
            sup_2.setAlignment(Element.ALIGN_CENTER);

            sup_2.setIndentationLeft(10);

            document.add(title);
            document.add(sup_2);

            document.add(contract);

            document.close();

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(HR1_GenerateEC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "pdf/ECPReg_" + EmpID + ".pdf";
    }

    public static String Dateth(int date) {
        String affix = "";
        switch ((String.valueOf(date).substring(String.valueOf(date).length() - 1))) {
            case "1":
                affix = "1st";
                break;
            case "2":
                affix = "2nd";
                break;
            case "3":
                affix = "3rd";
                break;
            default:
                affix = date + "th";
                break;
        }
        return affix;
    }
}
