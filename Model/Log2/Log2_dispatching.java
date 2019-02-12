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
public class Log2_dispatching extends Synapse.Model{
    public Log2_dispatching(String path){
         switch (path) {
            case "dispatching":
                this.initTable("tbl_log2_requestdeliveryscheduling");
                break;
            default:
                break;
        }
    }
}
