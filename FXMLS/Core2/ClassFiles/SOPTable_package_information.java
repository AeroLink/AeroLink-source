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
public class SOPTable_package_information {

    public SimpleStringProperty package_no;
    public SimpleStringProperty ref_no;
    public SimpleStringProperty ship_name;
    public SimpleStringProperty list_item;
    public SimpleStringProperty item_value;
    public SimpleStringProperty weight;
    public SimpleStringProperty note;
    public SimpleStringProperty status;
//    public SimpleStringProperty rec_name;
//    public SimpleStringProperty CompleteAddress;
//    public SimpleStringProperty rec_contact;
//    public SimpleStringProperty serv_type;
//    public SimpleStringProperty box;
//    public SimpleStringProperty quantity;
//    public SimpleStringProperty status;
//    public SimpleStringProperty book_date;

    public SOPTable_package_information(String package_no, String ref_no, String ship_name,String list_item,
            String item_value, String weight,String note,String status
//            ,String rec_name,String CompleteAddress,String rec_contact,String serv_type,String box,
//            String quantity,String status,String book_date
    ) {
        this.package_no = new SimpleStringProperty(package_no);
        this.ref_no = new SimpleStringProperty(ref_no);
        this.ship_name = new SimpleStringProperty(ship_name);
        this.list_item = new SimpleStringProperty(list_item);
        this.item_value = new SimpleStringProperty(item_value);
        this.weight = new SimpleStringProperty(weight);
        this.note = new SimpleStringProperty(note);
        this.status = new SimpleStringProperty(status);
//        this.rec_name = new SimpleStringProperty(rec_name);
//        this.CompleteAddress = new SimpleStringProperty(CompleteAddress);
//        this.rec_contact = new SimpleStringProperty(rec_contact);
//        this.serv_type = new SimpleStringProperty(serv_type);
//        this.box = new SimpleStringProperty(box);
//        this.quantity = new SimpleStringProperty(quantity);
//        this.status = new SimpleStringProperty(status);
//        this.book_date = new SimpleStringProperty(book_date);
    }
}
