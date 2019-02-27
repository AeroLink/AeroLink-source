/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

/**
 *
 * @author EdenRamoneda
 */
public class LM_ViewExamRequest {
    public static String er_id, dept_id, job_id, reason, status_id,status;
    
    public static void initExam_Request(String erid, String dept, String title, String r, String sid, String s){
        er_id = erid;
        dept_id = dept;
        job_id = title;
        reason = r;
        status_id = sid;
        status = s;
    }
}
