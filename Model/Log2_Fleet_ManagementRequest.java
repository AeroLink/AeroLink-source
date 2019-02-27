/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementRequest extends Synapse.Model{
    public Log2_Fleet_ManagementRequest() {
             
        setColumns("requestno","department","item_name","size","quantity","destination","consignee","departure","typeoftransaction");
        this.initTable("aerolink.tbl_log2_request");
    }

  
    
}
