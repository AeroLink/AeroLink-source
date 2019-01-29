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
             
        setColumns("ast_id",
                "ast_date",
                "ast_firstname",
                "ast_lastname",
                "ast_description",
                "ast_amount",
                "ast_quantity",
                "ast_quantity",
                "ast_status",
                "ast_type",
                "journal_status");
        this.initTable("tbl_finance_asset_po_sales_report");
    
}
}
