/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

/**
 *
 * @author Onodera-Chan
 */
public class tbl_files {
    private String documentid;
    private String documentname;
    private String documentcategory;
    private String timeadded;
    private String dateadded;

    public tbl_files(String documentid, String documentname, String documentcategory, String timeadded, String dateadded) {
        this.documentid = documentid;
        this.documentname = documentname;
        this.documentcategory = documentcategory;
        this.timeadded = timeadded;
        this.dateadded = dateadded;
    }

    /**
     * @return the documentid
     */
    public String getDocumentid() {
        return documentid;
    }

    /**
     * @param documentid the documentid to set
     */
    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    /**
     * @return the documentname
     */
    public String getDocumentname() {
        return documentname;
    }

    /**
     * @param documentname the documentname to set
     */
    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    /**
     * @return the documentcategory
     */
    public String getDocumentcategory() {
        return documentcategory;
    }

    /**
     * @param documentcategory the documentcategory to set
     */
    public void setDocumentcategory(String documentcategory) {
        this.documentcategory = documentcategory;
    }

    /**
     * @return the timeadded
     */
    public String getTimeadded() {
        return timeadded;
    }

    /**
     * @param timeadded the timeadded to set
     */
    public void setTimeadded(String timeadded) {
        this.timeadded = timeadded;
    }

    /**
     * @return the dateadded
     */
    public String getDateadded() {
        return dateadded;
    }

    /**
     * @param dateadded the dateadded to set
     */
    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }
    
}
