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
public class Log1_equipmentTypeClassfiles {
    public SimpleStringProperty equipmentType;
    
    Log1_equipmentTypeClassfiles(String equipmentType){
        this.equipmentType = new SimpleStringProperty(equipmentType);
    }

    public String getEquipmentType() {
        return equipmentType.get();
    }
    
}
