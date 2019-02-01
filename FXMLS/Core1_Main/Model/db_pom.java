/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Model;

/**
 *
 * @author lemnovo
 */
public class db_pom {
    
    private String po_no;
    private String po_date;
    private String track_no;
    private String sub;
    private String total;
    private String vat;
    private String charge;
    private String pom_no;

    public db_pom(String po_no, String po_date, String track_no, String sub, String total, String vat, String charge, String pom_no) {
        this.po_no = po_no;
        this.po_date = po_date;
        this.track_no = track_no;
        this.sub = sub;
        this.total = total;
        this.vat = vat;
        this.charge = charge;
        this.pom_no = pom_no;
    }

    /**
     * @return the po_no
     */
    public String getPo_no() {
        return po_no;
    }

    /**
     * @param po_no the po_no to set
     */
    public void setPo_no(String po_no) {
        this.po_no = po_no;
    }

    /**
     * @return the po_date
     */
    public String getPo_date() {
        return po_date;
    }

    /**
     * @param po_date the po_date to set
     */
    public void setPo_date(String po_date) {
        this.po_date = po_date;
    }

    /**
     * @return the track_no
     */
    public String getTrack_no() {
        return track_no;
    }

    /**
     * @param track_no the track_no to set
     */
    public void setTrack_no(String track_no) {
        this.track_no = track_no;
    }

  
    public String getSub() {
        return sub;
    }

    /**
     * @param sub the sub to set
     */
    public void setSub(String sub) {
        this.sub = sub;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the vat
     */
    public String getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(String vat) {
        this.vat = vat;
    }

    /**
     * @return the charge
     */
    public String getCharge() {
        return charge;
    }

    /**
     * @param charge the charge to set
     */
    public void setCharge(String charge) {
        this.charge = charge;
    }

    /**
     * @return the pom_no
     */
    public String getPom_no() {
        return pom_no;
    }

    /**
     * @param pom_no the pom_no to set
     */
    public void setPom_no(String pom_no) {
        this.pom_no = pom_no;
    }
    
    
    
}
