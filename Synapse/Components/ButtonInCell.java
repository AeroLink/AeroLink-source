/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse.Components;
    
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Lei
 * @param <rt>
 */
public class ButtonInCell<rt extends RecursiveTreeObject> {
    
    
    private EventHandler<MouseEvent> e;
    
    private class btnCel extends TableCell<rt, Boolean> {
        
        final JFXButton btn;
        
        public btnCel(String btnName) {
            this.btn = new JFXButton(btnName);
            this.btn.setOnMouseClicked(e);
            this.btn.getStyleClass().add("btnTable");
        }
        
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(this.btn);
            }
        }
    }
    
    public TableCell<rt, Boolean> create(String ButtonName, EventHandler<MouseEvent> e) {
        this.e = e;
        return new btnCel(ButtonName);
    }
    
    
    
    
}
