/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randelle
 */
public class Log2_Document_TrackingClass {

  
    private SimpleStringProperty subject;
    private SimpleStringProperty purpose;
    private SimpleStringProperty referenceno;
    private SimpleStringProperty department;
    private SimpleStringProperty requestor;
    private SimpleStringProperty daterequest;
    private SimpleStringProperty datereleased;

    public Log2_Document_TrackingClass(String subject, String purpose, String referenceno,
            String department, String requestor, String daterequest, String datereleased) {
        // Status
       
        this.subject = new SimpleStringProperty(subject);
        this.purpose = new SimpleStringProperty(purpose);
        this.referenceno = new SimpleStringProperty(referenceno);
        this.department = new SimpleStringProperty(department);
        this.requestor = new SimpleStringProperty(requestor);
        this.daterequest = new SimpleStringProperty(daterequest);
        this.datereleased = new SimpleStringProperty(datereleased);

    }


    public String getSubject() {
        return subject.get();
    }

    public String getPurpose() {
        return purpose.get();
    }

    public String getReferenceno() {
        return referenceno.get();
    }

    public String getDepartment() {
        return department.get();
    }

    public String getRequestor() {
        return requestor.get();
    }

    public String getDaterequest() {
        return daterequest.get();
    }

    public String getDatereleased() {
        return datereleased.get();
    }

}
