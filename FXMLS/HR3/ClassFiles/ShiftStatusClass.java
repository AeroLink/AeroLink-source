/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

/**
 *
 * @author my
 */
public class ShiftStatusClass {
    String Status;
    public ShiftStatusClass(String Status){
    this.Status = Status;
    }
     public String getStatus(){
        return Status;
    }
     public void setStatus(String Status){
        this.Status = Status;
    }
}
