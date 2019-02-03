/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jpeg
 */
public class TableModel_IND {

    public StringProperty ndrate;
    public StringProperty ndcountry;

    public TableModel_IND(String ndrate, String ndcountry) {
        this.ndrate = new SimpleStringProperty(ndrate);
        this.ndcountry = new SimpleStringProperty(ndcountry);
    }
}
