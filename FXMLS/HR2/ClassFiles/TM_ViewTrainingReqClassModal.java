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
public class TM_ViewTrainingReqClassModal {
    
    public static String tr_id,dept, job_position, date_req,status_id, status;
    
    public static void initVTRClass(String trid, String d, String jp, String dr, String sid, String s){
        tr_id = trid;
        dept = d;
        job_position = jp;
        date_req = dr;
        status_id = sid;
        status = s;
        
    }
    
}
