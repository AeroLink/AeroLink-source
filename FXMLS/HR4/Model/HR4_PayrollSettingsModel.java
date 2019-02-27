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
public class HR4_PayrollSettingsModel extends Synapse.Model{
     public HR4_PayrollSettingsModel()
    {
        setColumns("id","cola","overtime","special_holiday","special_holiday_ot","regular_holiday","regular_holiday_ot","double_holiday","double_holiday_ot","night_differentials","special_holiday_ns","regular_holiday_ns","double_holiday_ns");
        this.initTable("tbl_hr4_payroll_settings");
    }
    
}
