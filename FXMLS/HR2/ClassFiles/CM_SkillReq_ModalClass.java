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

    public static String sr_id, dept_name, title;

    public static void initSkillReq(String sid, String dn, String jt) {
        sr_id = sid;
        dept_name = dn;
        title = jt;
    }

}
