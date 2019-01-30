/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jpeg
 */
public class CRMTable_customer_cases {

    public SimpleStringProperty case_no;
    public SimpleStringProperty ref_no;
    public SimpleStringProperty emp_id;
    public SimpleStringProperty category;
    public SimpleStringProperty date;
    public SimpleStringProperty lvl;
    public SimpleStringProperty status;
    public SimpleStringProperty issue;

    public CRMTable_customer_cases(String case_no, String ref_no,
            String emp_id, String category, String date,
            String lvl, String status,String issue) {

        this.case_no = new SimpleStringProperty(case_no);
        this.ref_no = new SimpleStringProperty(ref_no);
        this.emp_id = new SimpleStringProperty(emp_id);
        this.category = new SimpleStringProperty(category);
        this.date = new SimpleStringProperty(date);
        this.lvl = new SimpleStringProperty(lvl);
        this.status = new SimpleStringProperty(status);
        this.issue = new SimpleStringProperty(issue);
    }
}
