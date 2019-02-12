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
public class Log2_Document_Trackingrequestlist {
     private SimpleStringProperty Requestrequestor;
    private SimpleStringProperty Requestposition;
    private SimpleStringProperty Requestsubject;
    private SimpleStringProperty Requesttypeofdocu;
    private SimpleStringProperty Requestdepartment;
    private SimpleStringProperty Requestpurpose;
    private SimpleStringProperty Requestrequestdate;
     private SimpleStringProperty Status;

    public Log2_Document_Trackingrequestlist(String requestor, String position, String subject, String typeofdocu, String
            department, String purpose, String requestdate, String Status) {
        this.Requestrequestor = new SimpleStringProperty(requestor);
        this.Requestposition = new SimpleStringProperty(position);
        this. Requestsubject = new SimpleStringProperty(subject);
         this.Requesttypeofdocu = new SimpleStringProperty(typeofdocu);
        this.Requestdepartment = new SimpleStringProperty(department);
        this. Requestpurpose= new SimpleStringProperty(purpose);
         this.Requestrequestdate= new SimpleStringProperty(requestdate);
          this.Status= new SimpleStringProperty(Status);

    }

    /**
     * @return the Requestrequestor
     */
    public SimpleStringProperty getRequestrequestor() {
        return Requestrequestor;
    }

    /**
     * @param Requestrequestor the Requestrequestor to set
     */
    public void setRequestrequestor(SimpleStringProperty Requestrequestor) {
        this.Requestrequestor = Requestrequestor;
    }

    /**
     * @return the Requestposition
     */
    public SimpleStringProperty getRequestposition() {
        return Requestposition;
    }

    /**
     * @param Requestposition the Requestposition to set
     */
    public void setRequestposition(SimpleStringProperty Requestposition) {
        this.Requestposition = Requestposition;
    }

    /**
     * @return the Requestsubject
     */
    public SimpleStringProperty getRequestsubject() {
        return Requestsubject;
    }

    /**
     * @param Requestsubject the Requestsubject to set
     */
    public void setRequestsubject(SimpleStringProperty Requestsubject) {
        this.Requestsubject = Requestsubject;
    }

    /**
     * @return the Requesttypeofdocu
     */
    public SimpleStringProperty getRequesttypeofdocu() {
        return Requesttypeofdocu;
    }

    /**
     * @param Requesttypeofdocu the Requesttypeofdocu to set
     */
    public void setRequesttypeofdocu(SimpleStringProperty Requesttypeofdocu) {
        this.Requesttypeofdocu = Requesttypeofdocu;
    }

    /**
     * @return the Requestdepartment
     */
    public SimpleStringProperty getRequestdepartment() {
        return Requestdepartment;
    }

    /**
     * @param Requestdepartment the Requestdepartment to set
     */
    public void setRequestdepartment(SimpleStringProperty Requestdepartment) {
        this.Requestdepartment = Requestdepartment;
    }

    /**
     * @return the Requestpurpose
     */
    public SimpleStringProperty getRequestpurpose() {
        return Requestpurpose;
    }

    /**
     * @param Requestpurpose the Requestpurpose to set
     */
    public void setRequestpurpose(SimpleStringProperty Requestpurpose) {
        this.Requestpurpose = Requestpurpose;
    }

    /**
     * @return the Requestrequestdate
     */
    public SimpleStringProperty getRequestrequestdate() {
        return Requestrequestdate;
    }

    /**
     * @param Requestrequestdate the Requestrequestdate to set
     */
    public void setRequestrequestdate(SimpleStringProperty Requestrequestdate) {
        this.Requestrequestdate = Requestrequestdate;
    }

    /**
     * @return the Status
     */
    public SimpleStringProperty getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(SimpleStringProperty Status) {
        this.Status = Status;
    }
    
}
