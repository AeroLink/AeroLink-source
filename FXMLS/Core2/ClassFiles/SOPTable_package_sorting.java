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
public class SOPTable_package_sorting {

    public SimpleStringProperty description;

    public SOPTable_package_sorting(String description) {
        this.description = new SimpleStringProperty(description);
    }
}
