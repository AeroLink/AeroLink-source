/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author EdenRamoneda
 */
public class EIS_Requisition extends Synapse.Model{
    
    public EIS_Requisition(){
    
    setColumns("request_id","requestor_id","request","request_description","request_status");
    this.initTable("tbl_eis_requisition");
    }
    
}
