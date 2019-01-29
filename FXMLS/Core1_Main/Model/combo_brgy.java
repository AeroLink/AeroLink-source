/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Model;

/**
 *
 * @author lemnovo
 */
public class combo_brgy {
    
    private String brgy_no;
    private String brgy_name;

    public combo_brgy(String brgy_no, String brgy_name) {
        this.brgy_no = brgy_no;
        this.brgy_name = brgy_name;
    }

    public String getBrgy_no() {
        return brgy_no;
    }

    public void setBrgy_no(String brgy_no) {
        this.brgy_no = brgy_no;
    }

    public String getBrgy_name() {
        return brgy_name;
    }

    public void setBrgy_name(String brgy_name) {
        this.brgy_name = brgy_name;
    }
    
    
    
}
