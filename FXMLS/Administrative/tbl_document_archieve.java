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
public class tbl_document_archieve {
    private String docno;
    private String docname;
    private String cate;
    private String storetime;
    private String storedate;
    private String status;

    public tbl_document_archieve(String docno, String docname, String cate, String storetime, String storedate, String status) {
        this.docno = docno;
        this.docname = docname;
        this.cate = cate;
        this.storetime = storetime;
        this.storedate = storedate;
        this.status = status;
    }

    /**
     * @return the docno
     */
    public String getDocno() {
        return docno;
    }

    /**
     * @param docno the docno to set
     */
    public void setDocno(String docno) {
        this.docno = docno;
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
     * @return the cate
     */
    public String getCate() {
        return cate;
    }

    /**
     * @param cate the cate to set
     */
    public void setCate(String cate) {
        this.cate = cate;
    }

    /**
     * @return the storetime
     */
    public String getStoretime() {
        return storetime;
    }

    /**
     * @param storetime the storetime to set
     */
    public void setStoretime(String storetime) {
        this.storetime = storetime;
    }

    /**
     * @return the storedate
     */
    public String getStoredate() {
        return storedate;
    }

    /**
     * @param storedate the storedate to set
     */
    public void setStoredate(String storedate) {
        this.storedate = storedate;
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
