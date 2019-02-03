/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Jaeeeee
 */
public class HR4_EmpInfoClass extends RecursiveTreeObject{
    public StringProperty employee_code;
    public StringProperty fnn;
    public StringProperty job_id;
    public StringProperty dept_id;
    public StringProperty status_id;
    private CheckBox Select;
    
    
    public HR4_EmpInfoClass(String employee_code,String fnn,String job_id,String dept_id,String status_id){
        this.Select = new CheckBox("Select");
        this.employee_code = new SimpleStringProperty(employee_code);
        this.fnn = new SimpleStringProperty(fnn);
        this.job_id = new SimpleStringProperty(job_id);
        this.dept_id = new SimpleStringProperty(dept_id);
        this.status_id = new SimpleStringProperty(status_id);
    }
    public CheckBox getSelect(){
         return Select;
     }
    public void setSelect(CheckBox Select){
        this.Select = Select;
    }
}
