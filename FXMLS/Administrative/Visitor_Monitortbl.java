/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

/**
 *
 * @author Onodera
 */
public class Visitor_Monitortbl {
    private String monitorid;
    private String monitorname;
    private String monitorptm;
    private String timein;
    private String timeout;
    private String date;
    private String status;

    public Visitor_Monitortbl(String monitorid, String monitorname, String monitorptm, String timein, String timeout, String date, String status) {
        this.monitorid = monitorid;
        this.monitorname = monitorname;
        this.monitorptm = monitorptm;
        this.timein = timein;
        this.timeout = timeout;
        this.date = date;
        this.status = status;
    }

    /**
     * @return the monitorid
     */
    public String getMonitorid() {
        return monitorid;
    }

    /**
     * @param monitorid the monitorid to set
     */
    public void setMonitorid(String monitorid) {
        this.monitorid = monitorid;
    }

    /**
     * @return the monitorname
     */
    public String getMonitorname() {
        return monitorname;
    }

    /**
     * @param monitorname the monitorname to set
     */
    public void setMonitorname(String monitorname) {
        this.monitorname = monitorname;
    }

    /**
     * @return the monitorptm
     */
    public String getMonitorptm() {
        return monitorptm;
    }

    /**
     * @param monitorptm the monitorptm to set
     */
    public void setMonitorptm(String monitorptm) {
        this.monitorptm = monitorptm;
    }

    /**
     * @return the timein
     */
    public String getTimein() {
        return timein;
    }

    /**
     * @param timein the timein to set
     */
    public void setTimein(String timein) {
        this.timein = timein;
    }

    /**
     * @return the timeout
     */
    public String getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(String timeout) {
        this.timeout = timeout;
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
