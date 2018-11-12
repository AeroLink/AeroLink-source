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
public class SP_JV_with_Skills_Modal {
    
    public SimpleStringProperty j_skills;
    public static String jobs, skills;
    
    
    
    public static void view_skill(String j){
        jobs = j;
    }

    public SP_JV_with_Skills_Modal(String js) {
       j_skills = new SimpleStringProperty(js);
    }
}
