/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jaeeeee
 */
public class HR4_SalaryGradeUpClass {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    public SimpleStringProperty f;
    public SimpleStringProperty g;
    public SimpleStringProperty h;
    public SimpleStringProperty i;
    public SimpleStringProperty j;
    public SimpleStringProperty k;
    public SimpleStringProperty l;
    public SimpleStringProperty m;
    public SimpleStringProperty n;
    public SimpleStringProperty job;
    public SimpleStringProperty classification;
    public SimpleStringProperty dept;
    public SimpleStringProperty designation;
    public SimpleStringProperty idxx;
  public HR4_SalaryGradeUpClass(String req_code,String emp_code, String fnn, String job_id,String classification_id,String dept_id,String designation_id, String req_job_id, String req_classification_id, String req_dept_id, String req_designation_id,String productivity,String qualityofwork,String initiative,String teamwork,String prob_solv,String attendance,String ave,String status){
   this.a = new SimpleStringProperty(emp_code);
   this.b = new SimpleStringProperty(fnn);
   this.job = new SimpleStringProperty(job_id);
   this.classification = new SimpleStringProperty(classification_id);
   this.dept = new SimpleStringProperty(dept_id);
   this.designation = new SimpleStringProperty(designation_id);
   this.c = new SimpleStringProperty(req_job_id);
   this.d = new SimpleStringProperty(req_classification_id);
   this.e = new SimpleStringProperty(req_dept_id);
   this.f = new SimpleStringProperty(req_designation_id);
   this.g = new SimpleStringProperty(productivity);
   this.h = new SimpleStringProperty(qualityofwork);
   this.i = new SimpleStringProperty(initiative);
   this.j = new SimpleStringProperty(teamwork);
   this.k = new SimpleStringProperty(prob_solv);
   this.l = new SimpleStringProperty(attendance);
   this.m = new SimpleStringProperty(ave);
   this.n = new SimpleStringProperty(status);
   this.idxx = new SimpleStringProperty(req_code);
   
  }
  
}
