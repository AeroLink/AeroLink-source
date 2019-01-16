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
public class SOPTable_package_inspect {

    public SimpleStringProperty book_id;
    public SimpleStringProperty status;
    public SimpleStringProperty created_at;

    public SOPTable_package_inspect(String book_id, String status, String created_at) {
        this.book_id = new SimpleStringProperty(book_id);
        this.status = new SimpleStringProperty(status);
        this.created_at = new SimpleStringProperty(created_at);
    }
}
