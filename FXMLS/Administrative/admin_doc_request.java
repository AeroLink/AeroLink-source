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
public class admin_doc_request {
    private String docreq;
    private String reqtitle;
    private String description;
    private String reqby;
    private String datereq;

    public admin_doc_request(String docreq, String reqtitle, String description, String reqby, String datereq) {
        this.docreq = docreq;
        this.reqtitle = reqtitle;
        this.description = description;
        this.reqby = reqby;
        this.datereq = datereq;
    }

    /**
     * @return the docreq
     */
    public String getDocreq() {
        return docreq;
    }

    /**
     * @param docreq the docreq to set
     */
    public void setDocreq(String docreq) {
        this.docreq = docreq;
    }

    /**
     * @return the reqtitle
     */
    public String getReqtitle() {
        return reqtitle;
    }

    /**
     * @param reqtitle the reqtitle to set
     */
    public void setReqtitle(String reqtitle) {
        this.reqtitle = reqtitle;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the reqby
     */
    public String getReqby() {
        return reqby;
    }

    /**
     * @param reqby the reqby to set
     */
    public void setReqby(String reqby) {
        this.reqby = reqby;
    }

    /**
     * @return the datereq
     */
    public String getDatereq() {
        return datereq;
    }

    /**
     * @param datereq the datereq to set
     */
    public void setDatereq(String datereq) {
        this.datereq = datereq;
    }
    
}
