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
  public HR4_SalaryGradeUpClass(String emp_code, String fnn, String grade, String status,String job_id,String dept_id){
      this.a = new SimpleStringProperty(emp_code);
      this.b = new SimpleStringProperty(grade);
      this.c = new SimpleStringProperty(status);
      this.d = new SimpleStringProperty(fnn);
      this.e = new SimpleStringProperty(job_id);
      this.f = new SimpleStringProperty(dept_id);
  }
   public String getEmpCode(){
       return a.get();
             }
   public String getGrade(){
       return b.get();
             }
   public String getStats(){
       return c.get();
             }
   public String getFN(){
       return d.get();
             }
   public String getJobs(){
       return e.get();
             }
   public String getDept(){
       return f.get();
             }
}
