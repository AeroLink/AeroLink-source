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
public class TableModel_WebQuestion {
    public SimpleStringProperty email;
    
    public TableModel_WebQuestion(String email){
        this.email = new SimpleStringProperty(email);
    }

}
