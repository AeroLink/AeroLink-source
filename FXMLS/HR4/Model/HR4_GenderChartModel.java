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
public class HR4_GenderChartModel extends Synapse.Model{
    public HR4_GenderChartModel()
    {
        setColumns("id","gender");
        this.initTable("tbl_hr4_employee_profiles");
    }
    
}
