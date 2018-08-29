/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

import static Synapse.Model.setColumns;
import static Synapse.Model.setTable;


/**
 *
 * @author JaeJae
 */
public class FINANCE_GL extends Synapse.Model{
    //constructor
     public FINANCE_GL(){
         
        setColumns("coa_id","code_no","account_title");
        setTable("tbl_finance_coa");
    
    }
     public List where() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
