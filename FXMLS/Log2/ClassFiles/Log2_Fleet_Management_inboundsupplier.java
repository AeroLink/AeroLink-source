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
public class Log2_Fleet_Management_inboundsupplier {
    
    private String iitemname; 
    private String iquantity;
    private String idateofdelivery;
    private String istatus;

    public Log2_Fleet_Management_inboundsupplier(String iitemname, String iquantity, String idateofdelivery, String istatus) {
        this.iitemname = iitemname;
        this.iquantity = iquantity;
        this.idateofdelivery = idateofdelivery;
        this.istatus = istatus;
    }

    /**
     * @return the iitemname
     */
    public String getIitemname() {
        return iitemname;
    }

    /**
     * @param iitemname the iitemname to set
     */
    public void setIitemname(String iitemname) {
        this.iitemname = iitemname;
    }

    /**
     * @return the iquantity
     */
    public String getIquantity() {
        return iquantity;
    }

    /**
     * @param iquantity the iquantity to set
     */
    public void setIquantity(String iquantity) {
        this.iquantity = iquantity;
    }

    /**
     * @return the idateofdelivery
     */
    public String getIdateofdelivery() {
        return idateofdelivery;
    }

    /**
     * @param idateofdelivery the idateofdelivery to set
     */
    public void setIdateofdelivery(String idateofdelivery) {
        this.idateofdelivery = idateofdelivery;
    }

    /**
     * @return the istatus
     */
    public String getIstatus() {
        return istatus;
    }

    /**
     * @param istatus the istatus to set
     */
    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }
    
    
}
