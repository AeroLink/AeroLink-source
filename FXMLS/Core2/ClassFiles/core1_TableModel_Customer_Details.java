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
public class core1_TableModel_Customer_Details {
    public SimpleStringProperty a;
    public SimpleStringProperty b;
    public SimpleStringProperty c;
    public SimpleStringProperty d;
    public SimpleStringProperty e;
    
    public core1_TableModel_Customer_Details(String customer_id,String pack_id,String shipper,String consignee,String status){
        this.a = new SimpleStringProperty(customer_id);
        this.b = new SimpleStringProperty(pack_id);
        this.c = new SimpleStringProperty(shipper);
        this.d = new SimpleStringProperty(consignee);
        this.e = new SimpleStringProperty(status);
    }

}
