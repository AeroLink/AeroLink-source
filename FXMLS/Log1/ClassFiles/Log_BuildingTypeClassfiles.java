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
public class Log_BuildingTypeClassfiles {
    public SimpleStringProperty desiredBuildingType;
    
    public Log_BuildingTypeClassfiles(String DesiredBuildingType){
        this.desiredBuildingType = new SimpleStringProperty(DesiredBuildingType);
    }

    public String getDesiredBuildingType() {
        return desiredBuildingType.get();
    }
}
