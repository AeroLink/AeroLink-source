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
public class HR4_BenefitsModel extends Synapse.Model{
     public HR4_BenefitsModel()
    {
        setColumns("id","benefits_id","title","amout","description","days");
        this.initTable("tbl_hr4_benefits");
    }
}
