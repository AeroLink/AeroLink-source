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
public class tbl_retrieved_files {
    private String docid;
    private String docname;
    private String catname;
    private String status;

    public tbl_retrieved_files(String docid, String docname, String catname, String status) {
        this.docid = docid;
        this.docname = docname;
        this.catname = catname;
        this.status = status;
    }

    /**
     * @return the docid
     */
    public String getDocid() {
        return docid;
    }

    /**
     * @param docid the docid to set
     */
    public void setDocid(String docid) {
        this.docid = docid;
    }

    /**
     * @return the docname
     */
    public String getDocname() {
        return docname;
    }

    /**
     * @param docname the docname to set
     */
    public void setDocname(String docname) {
        this.docname = docname;
    }

    /**
     * @return the catname
     */
    public String getCatname() {
        return catname;
    }

    /**
     * @param catname the catname to set
     */
    public void setCatname(String catname) {
        this.catname = catname;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
