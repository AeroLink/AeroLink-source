/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Jaeeeee
 */
public class HR4_WareHouse {
    public StringProperty ItemDescription;
    public StringProperty UnitPrice;
    private CheckBox Select;
    
    
    public HR4_WareHouse(String ItemDescription,String UnitPrice){
        this.Select = new CheckBox("Select");
        this.ItemDescription = new SimpleStringProperty(ItemDescription);
        this.UnitPrice = new SimpleStringProperty(UnitPrice);
    }
    public CheckBox getSelect(){
         return Select;
     }
    public void setSelect(CheckBox Select){
        this.Select = Select;
        
    }
}
