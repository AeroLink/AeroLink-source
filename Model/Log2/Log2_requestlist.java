/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Log2;

/**
 *
 * @author Randelle
 */
public class Log2_requestlist extends Synapse.Model {
    
      public Log2_requestlist(String path){
         switch (path) {
            case "requestlist":
                this.initTable("tbl_log2_document_tracking_request");
                break;
            default:
                break;
        }
    }
    
}
