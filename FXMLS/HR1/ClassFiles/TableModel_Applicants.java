/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lei
 */
public class TableModel_Applicants {
    
    public StringProperty id;
    public StringProperty applicant_code;
    public StringProperty fullname;
    public StringProperty email;
    public StringProperty educAttain;
    public StringProperty prevSchool;
    public StringProperty status;
    public StringProperty status_id;
    
    public TableModel_Applicants(String id, String applicant_code, String fullname, String email, String educAttain, String prevSchool, String status, String status_id) {
        this.id = new SimpleStringProperty(id);
        this.applicant_code = new SimpleStringProperty(applicant_code);
        this.fullname = new SimpleStringProperty(fullname);
        this.email = new SimpleStringProperty(email);
        this.educAttain = new SimpleStringProperty(educAttain);
        this.prevSchool = new SimpleStringProperty(prevSchool);
        this.status = new SimpleStringProperty(status);
        this.status_id = new SimpleStringProperty(status_id);
    }
    
    
}
