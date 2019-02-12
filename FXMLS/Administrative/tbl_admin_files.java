/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

/**
 *
 * @author Onodera-Chan
 */
public class tbl_admin_files {
    private String name;
    private String timeadded;
    private String dateadded;
    private String department;

    public tbl_admin_files(String name, String timeadded, String dateadded, String department) {
        this.name = name;
        this.timeadded = timeadded;
        this.dateadded = dateadded;
        this.department = department;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the timeadded
     */
    public String getTimeadded() {
        return timeadded;
    }

    /**
     * @param timeadded the timeadded to set
     */
    public void setTimeadded(String timeadded) {
        this.timeadded = timeadded;
    }

    /**
     * @return the dateadded
     */
    public String getDateadded() {
        return dateadded;
    }

    /**
     * @param dateadded the dateadded to set
     */
    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
}
