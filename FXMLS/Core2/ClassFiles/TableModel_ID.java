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
public class TableModel_ID {

    public StringProperty drate;
    public StringProperty dcountry;

    public TableModel_ID(String drate, String dcountry) {
        this.drate = new SimpleStringProperty(drate);
        this.dcountry = new SimpleStringProperty(dcountry);
    }
}
