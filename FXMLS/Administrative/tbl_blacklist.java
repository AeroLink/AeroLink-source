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
public class tbl_blacklist {
     private String bno;
    private String name;
    private String reason;
    private String lvlof;
    private String time;
    private String date;
    private String status;

    public tbl_blacklist(String bno, String name, String reason, String lvlof, String time, String date, String status) {
        this.bno = bno;
        this.name = name;
        this.reason = reason;
        this.lvlof = lvlof;
        this.time = time;
        this.date = date;
        this.status = status;
    }

    /**
     * @return the bno
     */
    public String getBno() {
        return bno;
    }

    /**
     * @param bno the bno to set
     */
    public void setBno(String bno) {
        this.bno = bno;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the lvlof
     */
    public String getLvlof() {
        return lvlof;
    }

    /**
     * @param lvlof the lvlof to set
     */
    public void setLvlof(String lvlof) {
        this.lvlof = lvlof;
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
