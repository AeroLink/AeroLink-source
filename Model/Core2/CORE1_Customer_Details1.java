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
public class CORE1_Customer_Details1 extends Synapse.Model {

    public CORE1_Customer_Details1() {
        this.initTable("tbl_core1_booking");
        setColumns("book_id", "status", "created_at");
    }
}
