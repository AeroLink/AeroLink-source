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
public class HR4_SSSModel extends Synapse.Model{
    public HR4_SSSModel()
    {
        setColumns("rocmin","rocmax","msc","er1","ee1","total1","ec_er","er2","ee2","total2","totalcon");
        this.initTable("tbl_hr4_sss");
    }
    
}
