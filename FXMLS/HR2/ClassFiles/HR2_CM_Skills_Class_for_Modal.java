/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Eden Ramoneda
 */
public class HR2_CM_Skills_Class_for_Modal {
    
    public static String j_id,j_title,j_Desc,j_Skill_id, j_Skill,j_Skill_d;
    
    public static void init_JClass(String title, String description,String skill, String Skill_description) {
        j_title = title;
        j_Desc = description;
        j_Skill = skill;
        j_Skill_d = Skill_description;
    }

}
