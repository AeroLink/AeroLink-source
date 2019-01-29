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
public class combo_service_type {
    
    private String serv_type_id; 
    private String serv_type_name;
    
    public combo_service_type(String serv_type_id , String serv_type_name){
        
        this.serv_type_id = serv_type_id;
        this.serv_type_name = serv_type_name;
        
    }

    /**
     * @return the serv_type_id
     */
    public String getServ_type_id() {
        return serv_type_id;
    }

    /**
     * @param serv_type_id the serv_type_id to set
     */
    public void setServ_type_id(String serv_type_id) {
        this.serv_type_id = serv_type_id;
    }

    /**
     * @return the serv_type_name
     */
    public String getServ_type_name() {
        return serv_type_name;
    }

    /**
     * @param serv_type_name the serv_type_name to set
     */
    public void setServ_type_name(String serv_type_name) {
        this.serv_type_name = serv_type_name;
    }
    
    
    
}
