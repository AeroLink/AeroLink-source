/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Crenz
 */
public class Log1_itemTypeWHClassFile {
    public SimpleStringProperty DesiredItemType;
    
    public Log1_itemTypeWHClassFile(String DesiredItemType){
        this.DesiredItemType = new SimpleStringProperty(DesiredItemType);
    }

    public String getDesiredItemType() {
        return DesiredItemType.get();
    }
}
