/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class CM_SkillReq_ModalClass {

    public static String sr_id, dept_name, title, req_status_id, req_status;

    public static void initSkillReq(String sid, String dn, String jt, String rsi, String rqq) {
        sr_id = sid;
        dept_name = dn;
        title = jt;
        req_status_id = rsi;
        req_status = rqq;
    }

}
