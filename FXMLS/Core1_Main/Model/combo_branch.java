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
public class combo_branch {
    
    private String branch_name;
    private String branch_location;

    public combo_branch(String branch_name, String branch_location) {
        this.branch_name = branch_name;
        this.branch_location = branch_location;
    }

    /**
     * @return the branch_name
     */
    public String getBranch_name() {
        return branch_name;
    }

    /**
     * @param branch_name the branch_name to set
     */
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    /**
     * @return the branch_location
     */
    public String getBranch_location() {
        return branch_location;
    }

    /**
     * @param branch_location the branch_location to set
     */
    public void setBranch_location(String branch_location) {
        this.branch_location = branch_location;
    }
    
    
    
}
