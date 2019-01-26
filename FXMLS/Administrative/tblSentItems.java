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
public class tblSentItems {
    private String reqno;
    private String doctitle;
    private String senttime;
    private String sentdate;
    private String status;

    public tblSentItems(String reqno, String doctitle, String senttime, String sentdate, String status) {
        this.reqno = reqno;
        this.doctitle = doctitle;
        this.senttime = senttime;
        this.sentdate = sentdate;
        this.status = status;
    }

    /**
     * @return the reqno
     */
    public String getReqno() {
        return reqno;
    }

    /**
     * @param reqno the reqno to set
     */
    public void setReqno(String reqno) {
        this.reqno = reqno;
    }

    /**
     * @return the doctitle
     */
    public String getDoctitle() {
        return doctitle;
    }

    /**
     * @param doctitle the doctitle to set
     */
    public void setDoctitle(String doctitle) {
        this.doctitle = doctitle;
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
