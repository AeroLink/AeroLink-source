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
public class Log_assetsales_model extends Synapse.Model{
    public Log_assetsales_model(){
             
        setColumns("asset_id",
                "ast_date",
                "ast_invoice",
                "ast_description",
                "ast_amount",
                "ast_status",
                "ast_type");
        this.initTable("tbl_asset");
    
}
}
