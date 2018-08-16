/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse.Components.Modal;

import Synapse.Form;
import Synapse.Route;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 *
 * @author Lei
 */
public class Modal {

    private Modal() {}
    
    private static class Singleton{  
       private static final Modal mInstance = new Modal();
    }
    
    public static Modal getInstance(){
        return Singleton.mInstance;
    }
    
    public void open(){
        
        Form f = new Form("/Synapse/Components/Modal/Modal.fxml");
        f.getStage().setAlwaysOnTop(true);
        f.open(StageStyle.UNDECORATED);
        
    }
    
}
