/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Model;

import static Synapse.Model.setColumns;

/**
 *
 * @author Jaeeeee
 */
public class HR4_Benefits1Model extends Synapse.Model{
    public HR4_Benefits1Model()
    {
        setColumns("id","benefits_id","emp_code","balance","araw","files");
        this.initTable("tbl_hr4_benefits1");
    }
}
