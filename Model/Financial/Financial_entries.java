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
public class Financial_entries extends Synapse.Model{  
     public Financial_entries(){ 
        setColumns(      "e_id",
                         "DateEntries",
                         "InvoiceEntries",
                         "FirstnameEntries",
                         "LastnameEntries",
                         "DescriptionEntries",
                         "AmountEntries",
                         "StatusEntries",
                         "TypeStatusEntries",
                         "AccountStatus",
                         "JournalStatusEntries");
        this.initTable("tbl_finance_entries");
     }
    
}
