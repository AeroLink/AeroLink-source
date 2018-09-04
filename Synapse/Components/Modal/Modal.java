/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse.Components.Modal;

import Synapse.Form;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Lei
 */
public class Modal {

    public static Parent ow;
    private Modal() {  }
    
    private static class Singleton{  
       private static final Modal mInstance = new Modal();
    }
    
    public static Modal getInstance() {
        return Singleton.mInstance;
    }
    
    public static Modal getInstance(Parent owner){
        ow = owner;
        return Singleton.mInstance;
    }
    
    public void open(){
        
        Form f = new Form("/Synapse/Components/Modal/Modal.fxml");
        f.getStage().setAlwaysOnTop(true);
        f.getStage().initModality(Modality.APPLICATION_MODAL);
        f.open(StageStyle.UTILITY);
        
        
        
    }
    
}
