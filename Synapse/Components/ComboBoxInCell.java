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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Lei
 * @param <rt>
 */
public class ComboBoxInCell<rt extends RecursiveTreeObject> {
    
    
    private ContextMenu contextMenu;
    
    private class comboboxCell extends TableCell<rt, Boolean> {
        
        final ComboBox btn;
        
        public comboboxCell(String name) {
            this.btn = new ComboBox();
            this.btn.setPromptText(name);
            this.btn.setContextMenu(contextMenu);
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
    
    public TableCell<rt, Boolean> create(String ButtonName, ContextMenu e) {
        this.contextMenu = e;
        return new comboboxCell(ButtonName);
    }
    
    
    
    
}
