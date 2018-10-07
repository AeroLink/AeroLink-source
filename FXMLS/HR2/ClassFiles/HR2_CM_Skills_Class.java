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
 *
 */
public class HR2_CM_Skills_Class {

    public SimpleStringProperty Skill_ID;
    public SimpleStringProperty Skill;
    public SimpleStringProperty Skill_Description;

    public HR2_CM_Skills_Class(String Skill_ID, String Skill, String Skill_Description) {
        this.Skill_ID = new SimpleStringProperty(Skill_ID);
        this.Skill = new SimpleStringProperty(Skill);
        this.Skill_Description = new SimpleStringProperty(Skill_Description);

    }
}
