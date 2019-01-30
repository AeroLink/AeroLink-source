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
public class tbldo_approved {
    private String requestno;
    private String documenttitle;
    private String requestby;
    private String senttime;
    private String sentdate;
    private String status;

    public tbldo_approved(String requestno, String documenttitle, String requestby, String senttime, String sentdate, String status) {
        this.requestno = requestno;
        this.documenttitle = documenttitle;
        this.requestby = requestby;
        this.senttime = senttime;
        this.sentdate = sentdate;
        this.status = status;
    }

    /**
     * @return the requestno
     */
    public String getRequestno() {
        return requestno;
    }

    /**
     * @param requestno the requestno to set
     */
    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    /**
     * @return the documenttitle
     */
    public String getDocumenttitle() {
        return documenttitle;
    }

    /**
     * @param documenttitle the documenttitle to set
     */
    public void setDocumenttitle(String documenttitle) {
        this.documenttitle = documenttitle;
    }

    /**
     * @return the requestby
     */
    public String getRequestby() {
        return requestby;
    }

    /**
     * @param requestby the requestby to set
     */
    public void setRequestby(String requestby) {
        this.requestby = requestby;
    }

    /**
     * @return the senttime
     */
    public String getSenttime() {
        return senttime;
    }

    /**
     * @param senttime the senttime to set
     */
    public void setSenttime(String senttime) {
        this.senttime = senttime;
    }

    /**
     * @return the sentdate
     */
    public String getSentdate() {
        return sentdate;
    }

    /**
     * @param sentdate the sentdate to set
     */
    public void setSentdate(String sentdate) {
        this.sentdate = sentdate;
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
