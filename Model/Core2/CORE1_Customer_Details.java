/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Core2;


/**
 *
 * @author jpeg
 */
public class CORE1_Customer_Details extends Synapse.Model{
    public CORE1_Customer_Details(){
        this.initTable("tbl_core1_customer_details");
        setColumns("customer_id","firstname","middlename","lastname",
                "receiver_firstname","receiver_middlename","receiver_lastname","status","created_at");
    }
}
