/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR1;

/**
 *
 * @author Lei
 */
public class HR1_AppStages extends Synapse.Model{

    public HR1_AppStages() {
        this.initTable("tbl_hr1_stages");
    }
    
    public HR1_AppStages(String trigger){
        switch(trigger){
            case "stage_results" : this.initTable("tbl_hr1_stageResults"); break;
            default: break;
        }
    }
   
}
