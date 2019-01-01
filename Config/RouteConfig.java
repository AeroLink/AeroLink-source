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
        {"Main", "/FXMLS/MainDash.fxml", "canAccessSystem"},
        
        //FINANCIALS
        {"id_apr", "/FXMLS/FINANCIALS/FINANCIAL_APR.fxml", "can_access_hr1_socialrecog"},
        {"id_budget", "/FXMLS/FINANCIALS/FINANCIAL_BUDGET.fxml", "can_access_hr1_socialrecog"},
        {"id_collection", "/FXMLS/FINANCIALS/FINANCIAL_COLLECTION.fxml", "can_access_hr1_socialrecog"},
        {"id_disbursement", "/FXMLS/FINANCIALS/FINANCIAL_DISBURSEMENT.fxml", "can_access_hr1_socialrecog"},
        {"id_gl", "/FXMLS/FINANCIALS/FINANCIAL_GENERAL_LEDGER.fxml", "can_access_hr1_socialrecog"},
        //HR1
        {"hr1RCC", "/FXMLS/HR1/HR1_Recruitment.fxml", "can_access_hr1_recruitment"},
        {"applicant", "/FXMLS/HR1/HR1_Applicant_Management.fxml", "can_access_hr1_appManagement"},
        {"newHire", "/FXMLS/HR1/HR1_New_Hire_On_Board.fxml", "can_access_hr1_newHireOnBoad"},
        {"performance", "/FXMLS/HR1/HR1_Performance_Management.fxml", "can_access_hr1_perfmanagement"},
        {"social", "/FXMLS/HR1/HR1_Social_Recognition.fxml", "can_access_hr1_socialrecog"},
        //HR2
        {"learning_management", "/FXMLS/HR2/HR2_Learning_Management.fxml", "can_access_hr2_learning_management"},
        {"training_management", "/FXMLS/HR2/HR2_Training_Management.fxml", "can_access_hr2_training_management"},
        {"competency_management", "/FXMLS/HR2/HR2_Competency_Management.fxml", "can_access_hr2_competency_management"},
        {"succession_planning", "/FXMLS/HR2/HR2_Succession_Planning.fxml", "can_access_hr2_succession_planning"},
        //HR3
        {"hr3sns", "/FXMLS/HR3/HR3_Shift_and_Scheduling.fxml", "can_access_HR3_Shift_and_Scheduling"},
        {"hr3tna", "/FXMLS/HR3/HR3_Time_and_Attendance.fxml", "can_access_HR3_Time_and_Attendance"},
        {"hr3cnr", "/FXMLS/HR3/HR3_Claims_and_Reimbursement.fxml", "can_access_HR3_Claim_Reimbursement"},
        {"hr3lm", "/FXMLS/HR3/HR3_Leave_Management.fxml", "can_access_HR3_Leave_Management"},
        {"hr3tm", "/FXMLS/HR3/HR3_Timesheet_Management.fxml", "can_access_HR3_Timesheet_Management"},
        //HR4
        {"hr4analytics", "/FXMLS/HR4/HR4_Analytics.fxml", "can_access_hr1_socialrecog"},
        {"hr4payroll", "/FXMLS/HR4/HR4_Payroll.fxml", "can_access_hr1_socialrecog"},
        {"hr4cnp", "/FXMLS/HR4/HR4_Compensation_and_Planning.fxml", "can_access_hr1_socialrecog"},
        {"hr4chc", "/FXMLS/HR4/HR4_Core_Human_Capital_Management.fxml", "can_access_hr4_corehumancapital"},
        //Logistic2
        {"log2vp", "/FXMLS/Log2/Log2_Vendor_Portal.fxml", "can_access_hr1_socialrecog"},
        {"log2am", "/FXMLS/Log2/Log2_Audit_Management.fxml", "can_access_hr1_socialrecog"},
        {"log2dt", "/FXMLS/Log2/Log2_Document_Tracking.fxml", "can_access_hr1_socialrecog"},
        {"log2fm", "/FXMLS/Log2/Log2_Fleet_Management.fxml", "can_access_hr1_socialrecog"},
        {"log2vr", "/FXMLS/Log2/Log2_Vehicle_Reservation.fxml", "can_access_hr1_socialrecog"},
        //Core2    
        {"core2SN", "/FXMLS/Core2/ServiceNetwork.fxml", "can_access_hr1_socialrecog"},
        {"core2SP", "/FXMLS/Core2/ServiceProvider.fxml", "can_access_hr1_socialrecog"},
        {"core2SR", "/FXMLS/Core2/ScheduleRates.fxml", "can_access_hr1_socialrecog"},
        {"core2SOP", "/FXMLS/Core2/StandardOperationalProcedure.fxml", "can_access_hr1_socialrecog"},
        {"core2CRM", "/FXML/Core2/CustomerRelationshipManagement.fxml", "can_access_hr1_socialrecog"},
        //Core1   
        {"core1_booking", "/FXMLS/core1/Core1_booking.fxml", "can_access_hr1_socialrecog"},
        {"core1pom", "/FXMLS/core1/Core1_pom.fxml", "can_access_hr1_socialrecog"},
        {"core1consol", "/FXMLS/core1/Core1_consol.fxml", "can_access_hr1_socialrecog"},
        {"core1mawb", "/FXMLS/core1/Core1_mawb.fxml", "can_access_hr1_socialrecog"},
        {"core1sf", "/FXML/core1/Core1_shipment.fxml", "can_access_hr1_socialrecog"},
        //USM
        {"usmManageUsers", "/FXMLS/USM/ManageUsers.fxml", "can_access_hr1_socialrecog"},
        //Requisitions
        {"requisitions", "/FXMLS/Requisition.fxml", "canAccessSystem"}
    
    };

}
