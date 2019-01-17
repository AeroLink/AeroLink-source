/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Model;

/**
 *
 * @author Jaeeeee
 */
public class HR4_PHModel extends Synapse.Model{
    public HR4_PHModel(){
        setColumns("mbs_min","mbs_max","monthly_premium_min","monthly_premium_max","ee_share_min","ee_share_max","er_share_min","er_share_max");
        this.initTable("tbl_hr4_philhealth");
    }
    
}
