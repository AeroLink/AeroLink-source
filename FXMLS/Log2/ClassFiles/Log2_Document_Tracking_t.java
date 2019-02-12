/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

/**
 *
 * @author Randelle
 */
public class Log2_Document_Tracking_t {
    
    private String tdepartment;
     private String tsubject;
      private String tlastdatesubmitted;

    public Log2_Document_Tracking_t(String tdepartment, String tsubject, String tlastdatesubmitted) {
        this.tdepartment = tdepartment;
        this.tsubject = tsubject;
        this.tlastdatesubmitted = tlastdatesubmitted;
    }

    /**
     * @return the tdepartment
     */
    public String getTdepartment() {
        return tdepartment;
    }

    /**
     * @param tdepartment the tdepartment to set
     */
    public void setTdepartment(String tdepartment) {
        this.tdepartment = tdepartment;
    }

    /**
     * @return the tsubject
     */
    public String getTsubject() {
        return tsubject;
    }

    /**
     * @param tsubject the tsubject to set
     */
    public void setTsubject(String tsubject) {
        this.tsubject = tsubject;
    }

    /**
     * @return the tlastdatesubmitted
     */
    public String getTlastdatesubmitted() {
        return tlastdatesubmitted;
    }

    /**
     * @param tlastdatesubmitted the tlastdatesubmitted to set
     */
    public void setTlastdatesubmitted(String tlastdatesubmitted) {
        this.tlastdatesubmitted = tlastdatesubmitted;
    }
      
      
}
