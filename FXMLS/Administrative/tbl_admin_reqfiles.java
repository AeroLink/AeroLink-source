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
public class tbl_admin_reqfiles {
    
private String reqno;
    private String reqtitle;
    private String time;
    private String date;
    private String description;
    private String status;

    public tbl_admin_reqfiles(String reqno, String reqtitle, String time, String date, String description, String status) {
        this.reqno = reqno;
        this.reqtitle = reqtitle;
        this.time = time;
        this.date = date;
        this.description = description;
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
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
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
