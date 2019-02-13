package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jaeeeee
 */
public class HR4_NewPayrollClass1 {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public HR4_NewPayrollClass1(String payroll_code,String start_date,String end_date){
     this.a = new SimpleStringProperty(payroll_code);
        this.b = new SimpleStringProperty(start_date);
        this.c = new SimpleStringProperty(end_date);
        
    }
    
}
