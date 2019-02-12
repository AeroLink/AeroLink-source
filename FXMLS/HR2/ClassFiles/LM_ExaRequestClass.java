/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class LM_ExaRequestClass {

    public SimpleStringProperty er_id, dept_id, job_id, reason, status_id,status;

    public LM_ExaRequestClass(String er_id, String dept_id, String job_id, String reason, String status_id,String status) {
        this.er_id = new SimpleStringProperty(er_id);
        this.dept_id = new SimpleStringProperty(dept_id);
        this.job_id = new SimpleStringProperty(job_id);
        this.reason = new SimpleStringProperty(reason);
        this.status_id = new SimpleStringProperty(status_id);
        this.status = new SimpleStringProperty(status);
    }

}
