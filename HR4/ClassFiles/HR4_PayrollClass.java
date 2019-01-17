/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jaeeeee
 */
public class HR4_PayrollClass extends RecursiveTreeObject{
    public StringProperty employee_code;
    public StringProperty name;
    public StringProperty job_id;
    public StringProperty dept_id;
    public StringProperty classification_id;
    public StringProperty gross_pay;
    public StringProperty net_pay;
    public StringProperty start_date;
    public StringProperty end_date;
    public HR4_PayrollClass(String employee_code,String name,String job_id,String dept_id,String classification_id,String gross_pay,String net_pay,String start_date,String end_date){
        this.employee_code = new SimpleStringProperty(employee_code);
        this.name = new SimpleStringProperty(name);
        this.job_id = new SimpleStringProperty(job_id);
        this.dept_id = new SimpleStringProperty(dept_id);
        this.classification_id = new SimpleStringProperty(classification_id);
        this.gross_pay = new SimpleStringProperty(gross_pay);
        this.net_pay = new SimpleStringProperty(net_pay);
        this.start_date = new SimpleStringProperty(start_date);
        this.end_date = new SimpleStringProperty(end_date);
        
    }
    
}
