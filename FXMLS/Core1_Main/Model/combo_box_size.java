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
public class combo_box_size {
    
    private String box_size_id; 
    private String box_size_name;

    public combo_box_size(String box_size_id, String box_size_name) {
        this.box_size_id = box_size_id;
        this.box_size_name = box_size_name;
    }

    public String getBox_size_id() {
        return box_size_id;
    }

    public void setBox_size_id(String box_size_id) {
        this.box_size_id = box_size_id;
    }

    public String getBox_size_name() {
        return box_size_name;
    }

    public void setBox_size_name(String box_size_name) {
        this.box_size_name = box_size_name;
    }
    
}
