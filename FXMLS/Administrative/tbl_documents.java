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
public class tbl_documents {
    private String dtdocumentno;
    private String dtdocumentname;
    private String dtdepartment;
    private String dtcategory;
    private String dttimestored;
    private String dtdatestored;

    public tbl_documents(String dtdocumentno, String dtdocumentname, String dtdepartment, String dtcategory, String dttimestored, String dtdatestored) {
        this.dtdocumentno = dtdocumentno;
        this.dtdocumentname = dtdocumentname;
        this.dtdepartment = dtdepartment;
        this.dtcategory = dtcategory;
        this.dttimestored = dttimestored;
        this.dtdatestored = dtdatestored;
    }

    /**
     * @return the dtdocumentno
     */
    public String getDtdocumentno() {
        return dtdocumentno;
    }

    /**
     * @param dtdocumentno the dtdocumentno to set
     */
    public void setDtdocumentno(String dtdocumentno) {
        this.dtdocumentno = dtdocumentno;
    }

    /**
     * @return the dtdocumentname
     */
    public String getDtdocumentname() {
        return dtdocumentname;
    }

    /**
     * @param dtdocumentname the dtdocumentname to set
     */
    public void setDtdocumentname(String dtdocumentname) {
        this.dtdocumentname = dtdocumentname;
    }

    /**
     * @return the dtdepartment
     */
    public String getDtdepartment() {
        return dtdepartment;
    }

    /**
     * @param dtdepartment the dtdepartment to set
     */
    public void setDtdepartment(String dtdepartment) {
        this.dtdepartment = dtdepartment;
    }

    /**
     * @return the dtcategory
     */
    public String getDtcategory() {
        return dtcategory;
    }

    /**
     * @param dtcategory the dtcategory to set
     */
    public void setDtcategory(String dtcategory) {
        this.dtcategory = dtcategory;
    }

    /**
     * @return the dttimestored
     */
    public String getDttimestored() {
        return dttimestored;
    }

    /**
     * @param dttimestored the dttimestored to set
     */
    public void setDttimestored(String dttimestored) {
        this.dttimestored = dttimestored;
    }

    /**
     * @return the dtdatestored
     */
    public String getDtdatestored() {
        return dtdatestored;
    }

    /**
     * @param dtdatestored the dtdatestored to set
     */
    public void setDtdatestored(String dtdatestored) {
        this.dtdatestored = dtdatestored;
    }
    
}
