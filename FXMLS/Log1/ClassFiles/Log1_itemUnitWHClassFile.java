package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_itemUnitWHClassFile {
    public SimpleStringProperty DesiredItemUnit;
    
    public Log1_itemUnitWHClassFile(String DesiredItemUnit){
        this.DesiredItemUnit = new SimpleStringProperty(DesiredItemUnit);
    }

    public String getDesiredItemUnit() {
        return DesiredItemUnit.get();
    }
}
