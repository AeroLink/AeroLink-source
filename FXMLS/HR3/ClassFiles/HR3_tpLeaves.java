/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BlackMoon
 */
public class HR3_tpLeaves {

    public SimpleStringProperty LeaveName;
    public SimpleStringProperty LimitDays;
    public SimpleStringProperty LeaveLimit;
    public SimpleStringProperty LeaveID;

    public HR3_tpLeaves(String id, String name, String days, String limit) {
        this.LeaveID = new SimpleStringProperty(id);
        this.LimitDays = new SimpleStringProperty(days);
        this.LeaveName = new SimpleStringProperty(name);
        this.LeaveLimit = new SimpleStringProperty(limit);
    }

}
