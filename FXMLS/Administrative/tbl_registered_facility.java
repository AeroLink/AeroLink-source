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
public class tbl_registered_facility {
 
    private String facilitytd;
    private String facilityname;
    private String facilitytype;
    private String facilitystatus;

    public tbl_registered_facility(String facilitytd, String facilityname, String facilitytype, String facilitystatus) {
        this.facilitytd = facilitytd;
        this.facilityname = facilityname;
        this.facilitytype = facilitytype;
        this.facilitystatus = facilitystatus;
    }

    /**
     * @return the facilitytd
     */
    public String getFacilitytd() {
        return facilitytd;
    }

    /**
     * @param facilitytd the facilitytd to set
     */
    public void setFacilitytd(String facilitytd) {
        this.facilitytd = facilitytd;
    }

    /**
     * @return the facilityname
     */
    public String getFacilityname() {
        return facilityname;
    }

    /**
     * @param facilityname the facilityname to set
     */
    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    /**
     * @return the facilitytype
     */
    public String getFacilitytype() {
        return facilitytype;
    }

    /**
     * @param facilitytype the facilitytype to set
     */
    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    /**
     * @return the facilitystatus
     */
    public String getFacilitystatus() {
        return facilitystatus;
    }

    /**
     * @param facilitystatus the facilitystatus to set
     */
    public void setFacilitystatus(String facilitystatus) {
        this.facilitystatus = facilitystatus;
        }
    
}
