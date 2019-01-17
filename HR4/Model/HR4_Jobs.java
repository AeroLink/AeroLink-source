/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FXMLS.HR4.Model;

import FXMLS.HR4.ClassFiles.TableModel_Jobs;
import static Synapse.Model.setColumns;
import static Synapse.Model.setTable;

/**
 * 
 * @author Eden RAMONEDA
 */
public class HR4_Jobs extends Synapse.Model{
            
         public HR4_Jobs()
            {
                setColumns("job_id","title","description", "classification", "designation", "department");
                this.initTable("tbl_hr4_jobs");
            }

    public void add(TableModel_Jobs tableModel_Jobs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
