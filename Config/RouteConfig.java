package Config;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ARIELLECIAS
 */

public class RouteConfig {
    
    public static String[][] links = {
        
        {"Main", "/FXMLS/MainDash.fxml"},
        //coac
       //{"id_coa", "/FXMLS/FINANCIALS/GENERAL_LEDGER/ChartOfAccounts.fxml"},
        
         //FINANCIALS
        {"id_apr", "/FXMLS/FINANCIALS/FINANCIAL_APR.fxml"},
        {"id_budget", "/FXMLS/FINANCIALS/FINANCIAL_BUDGET.fxml"},
        {"id_collection", "/FXMLS/FINANCIALS/FINANCIAL_COLLECTION.fxml"},
        {"id_disbursement", "/FXMLS/FINANCIALS/FINANCIAL_DISBURSEMENT.fxml"},
        {"id_gl", "/FXMLS/FINANCIALS/FINANCIAL_GENERAL_LEDGER.fxml"},
        
        //HR1
        {"hr1RCC", "/FXMLS/HR1/HR1_Recruitment.fxml"},
        {"applicant", "/FXMLS/HR1/HR1_Applicant_Management.fxml"},
        {"newHire", "/FXMLS/HR1/HR1_New_Hire_On_Board.fxml"},
        {"performance", "/FXMLS/HR1/HR1_Performance_Management.fxml"},
        {"social", "/FXMLS/HR1/HR1_Social_Recognition.fxml"},
        
              
        //HR2
        {"learning_management", "/FXMLS/HR2/HR2_Learning_Management.fxml"},
        {"training_management", "/FXMLS/HR2/HR2_Training_Management.fxml"},
        {"competency_management", "/FXMLS/HR2/HR2_Competency_Management.fxml"},
        {"succession_planning", "/FXMLS/HR2/HR2_Succession_Planning.fxml"},
       // {"social", "/FXMLS/HR1/HR1_Social_Recognition.fxml"},
        
        //HR3
        {"hr3sns", "/FXMLS/HR3/HR3_Shift_and_Scheduling.fxml"},
        {"hr3tna", "/FXMLS/HR3/HR3_Time_and_Attendance.fxml"},
        {"hr3cnr", "/FXMLS/HR3/HR3_Claims_and_Reimbursement.fxml"},
        {"hr3lm", "/FXMLS/HR3/HR3_Leave_Management.fxml"},
        {"hr3tm", "/FXMLS/HR3/HR3_Timesheet_Management.fxml"},
        
        //HR4
        {"hr4analytics", "/FXMLS/HR4/HR4_Analytics.fxml"},
        {"hr4payroll", "/FXMLS/HR4/HR4_Payroll.fxml"},
        {"hr4cnp", "/FXMLS/HR4/HR4_Compensation_and_Planning.fxml"},
        {"hr4chc", "/FXMLS/HR4/HR4_Core_Human_Capital_Management.fxml"},
        
        //Logistic2
        {"log2vp", "/FXMLS/Log2/Log2_Vendor_Portal.fxml"},
        {"log2am", "/FXMLS/Log2/Log2_Audit_Management.fxml"},
        {"log2dt", "/FXMLS/Log2/Log2_Document_Tracking.fxml"},
        {"log2fm", "/FXMLS/Log2/Log2_Fleet_Management.fxml"},
        {"log2vr", "/FXMLS/Log2/Log2_Vehicle_Reservation.fxml"},
      
        //Core2    
        {"core2SN","/FXMLS/Core2/ServiceNetwork.fxml"},
        {"core2SP","/FXMLS/Core2/ServiceProvider.fxml"},
        {"core2SR","/FXMLS/Core2/ScheduleRates.fxml"},
        {"core2SOP","/FXMLS/Core2/StandardOperationalProcedure.fxml"},
<<<<<<< HEAD
        {"core2CRM","/FXML/Core2/CustomerRelationshipManagement.fxml"},
        
        //Core1   
        {"core1_booking","/FXMLS/core1/Core1_booking.fxml"},
        {"core1pom","/FXMLS/core1/Core1_pom.fxml"},
        {"core1consol","/FXMLS/core1/Core1_consol.fxml"},
        {"core1mawb","/FXMLS/core1/Core1_mawb.fxml"},
        {"core1sf","/FXML/core1/Core1_shipment.fxml"},
=======
        {"core2CRM","/FXMLS/Core2/CustomerRelationshipManagement.fxml"},
>>>>>>> 6b280e19310fa651afadf627a1a1e4773021d4bf
           
            
        //USM
        {"usmManageUsers", "/FXMLS/USM/ManageUsers.fxml"},
        
        
    };
    
}
