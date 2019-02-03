/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HR3;

/**
 *
 * @author my
 */
public class HR3_Schedule_Recordss extends Synapse.Model{
    public HR3_Schedule_Recordss()
    {
    setColumns("id","employee_code","mon","tues","wed","thurs","fri");
    this.initTable("tbl_hr3_weekdays");
    }
}
