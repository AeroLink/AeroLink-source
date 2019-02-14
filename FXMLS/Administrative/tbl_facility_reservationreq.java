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
public class tbl_facility_reservationreq {
    private String rfacility;
    private String rdate;
    private String rstatus;

    public tbl_facility_reservationreq(String rfacility, String rdate, String rstatus) {
        this.rfacility = rfacility;
        this.rdate = rdate;
        this.rstatus = rstatus;
    }

    /**
     * @return the rfacility
     */
    public String getRfacility() {
        return rfacility;
    }

    /**
     * @param rfacility the rfacility to set
     */
    public void setRfacility(String rfacility) {
        this.rfacility = rfacility;
    }

    /**
     * @return the rdate
     */
    public String getRdate() {
        return rdate;
    }

    /**
     * @param rdate the rdate to set
     */
    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    /**
     * @return the rstatus
     */
    public String getRstatus() {
        return rstatus;
    }

    /**
     * @param rstatus the rstatus to set
     */
    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }
    
}
