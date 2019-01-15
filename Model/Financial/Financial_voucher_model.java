/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Financial;

import static Synapse.Model.setColumns;

/**
 *
 * @author Gilbert
 */
public class Financial_voucher_model extends Synapse.Model{
       public Financial_voucher_model(){
         
              setColumns("drv_id",
                         "drv_date_req",
                         "drv_requestor",
                         "drv_amount",
                         "drv_department",
                         "drv_vt",
                         "drv_claimant",
                         "drv_organization",
                         "drv_date_rel");
        this.initTable("tbl_financials_dr_voucher");
    
}
}
