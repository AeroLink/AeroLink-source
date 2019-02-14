/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

/**
 *
 * @author Randelle
 */
public class Log2_Vehicle_Reservation_vd {
    private String vd_id; 
    private String vd_requestor;
    private String vd_vmodel;
    private String vd_plateno;
    private String vd_purpose;
    private String vd_dateofreservation;
    private String time;

    public Log2_Vehicle_Reservation_vd(String vd_id, String vd_requestor, String vd_vmodel, String vd_plateno, String vd_purpose, String vd_dateofreservation, String time) {
        this.vd_id = vd_id;
        this.vd_requestor = vd_requestor;
        this.vd_vmodel = vd_vmodel;
        this.vd_plateno = vd_plateno;
        this.vd_purpose = vd_purpose;
        this.vd_dateofreservation = vd_dateofreservation;
        this.time = time;
    }
    
    /**
     * @return the vd_id
     */
    public String getVd_id() {
        return vd_id;
    }

    /**
     * @param vd_id the vd_id to set
     */
    public void setVd_id(String vd_id) {
        this.vd_id = vd_id;
    }

    /**
     * @return the vd_requestor
     */
    public String getVd_requestor() {
        return vd_requestor;
    }

    /**
     * @param vd_requestor the vd_requestor to set
     */
    public void setVd_requestor(String vd_requestor) {
        this.vd_requestor = vd_requestor;
    }

    /**
     * @return the vd_vmodel
     */
    public String getVd_vmodel() {
        return vd_vmodel;
    }

    /**
     * @param vd_vmodel the vd_vmodel to set
     */
    public void setVd_vmodel(String vd_vmodel) {
        this.vd_vmodel = vd_vmodel;
    }

    /**
     * @return the vd_plateno
     */
    public String getVd_plateno() {
        return vd_plateno;
    }

    /**
     * @param vd_plateno the vd_plateno to set
     */
    public void setVd_plateno(String vd_plateno) {
        this.vd_plateno = vd_plateno;
    }

    /**
     * @return the vd_purpose
     */
    public String getVd_purpose() {
        return vd_purpose;
    }

    /**
     * @param vd_purpose the vd_purpose to set
     */
    public void setVd_purpose(String vd_purpose) {
        this.vd_purpose = vd_purpose;
    }

    /**
     * @return the vd_dateofreservation
     */
    public String getVd_dateofreservation() {
        return vd_dateofreservation;
    }

    /**
     * @param vd_dateofreservation the vd_dateofreservation to set
     */
    public void setVd_dateofreservation(String vd_dateofreservation) {
        this.vd_dateofreservation = vd_dateofreservation;
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
    
    
}
