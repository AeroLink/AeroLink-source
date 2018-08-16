/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse.Components;
    
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 *
 * @author Lei
 * @param <rt>
 */
public class ButtonInCell<rt extends RecursiveTreeObject> {
    
    
    private EventHandler<ActionEvent> e;
    
    private class btnCel extends TableCell<rt, Boolean> {
        
        final Button btn;
        
        public btnCel(String btnName) {
            this.btn = new Button(btnName);
            this.btn.setOnAction(e);
        }
        
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(this.btn);
            }
        }
    }
    
    public TableCell<rt, Boolean> create(String ButtonName, EventHandler<ActionEvent> e) {
        this.e = e;
        return new btnCel(ButtonName);
    }
    
    
    
    
}
