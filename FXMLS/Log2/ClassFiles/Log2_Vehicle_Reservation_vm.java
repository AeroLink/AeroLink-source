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
public class Log2_Vehicle_Reservation_vm {
  
    private String vm_id;
    private String vm_requestor;
    private String vm_vmodel;
    private String vm_plateno;
    private String vm_purpose;
    private String vm_date;
    private String vm_time;
    private String vm_status;

    public Log2_Vehicle_Reservation_vm(String vm_id, String vm_requestor, String vm_vmodel, String vm_plateno, String vm_purpose, String vm_date, String vm_time, String vm_status) {
         this.vm_id = vm_id;
        this.vm_requestor = vm_requestor;
        this.vm_vmodel = vm_vmodel;
        this.vm_plateno = vm_plateno;
        this.vm_purpose = vm_purpose;
        this.vm_date = vm_date;
        this.vm_time = vm_time;
        this.vm_status = vm_status;
    }

      /**
     * @return the vm_id
     */
    public String getVm_id() {
        return vm_id;
    }

    /**
     * @param vm_id the vm_id to set
     */
    public void setVm_id(String vm_id) {
        this.vm_id = vm_id;
    }
    
    
    /**
     * @return the vm_requestor
     */
    public String getVm_requestor() {
        return vm_requestor;
    }

    /**
     * @param vm_requestor the vm_requestor to set
     */
    public void setVm_requestor(String vm_requestor) {
        this.vm_requestor = vm_requestor;
    }

    /**
     * @return the vm_vmodel
     */
    public String getVm_vmodel() {
        return vm_vmodel;
    }

    /**
     * @param vm_vmodel the vm_vmodel to set
     */
    public void setVm_vmodel(String vm_vmodel) {
        this.vm_vmodel = vm_vmodel;
    }

    /**
     * @return the vm_plateno
     */
    public String getVm_plateno() {
        return vm_plateno;
    }

    /**
     * @param vm_plateno the vm_plateno to set
     */
    public void setVm_plateno(String vm_plateno) {
        this.vm_plateno = vm_plateno;
    }

    /**
     * @return the vm_purpose
     */
    public String getVm_purpose() {
        return vm_purpose;
    }

    /**
     * @param vm_purpose the vm_purpose to set
     */
    public void setVm_purpose(String vm_purpose) {
        this.vm_purpose = vm_purpose;
    }

    /**
     * @return the vm_date
     */
    public String getVm_date() {
        return vm_date;
    }

    /**
     * @param vm_date the vm_date to set
     */
    public void setVm_date(String vm_date) {
        this.vm_date = vm_date;
    }

    /**
     * @return the vm_time
     */
    public String getVm_time() {
        return vm_time;
    }

    /**
     * @param vm_time the vm_time to set
     */
    public void setVm_time(String vm_time) {
        this.vm_time = vm_time;
    }
   
     /**
     * @return the vm_status
     */
    public String getVm_status() {
        return vm_status;
    }

    /**
     * @param vm_status the vm_status to set
     */
    public void setVm_status(String vm_status) {
        this.vm_status = vm_status;
    }

    
}
