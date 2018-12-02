/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse.Components;
    
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Lei
 * @param <rt>
 */
public class MenuButtonCell<rt extends RecursiveTreeObject> {
    
    private class menuButtonCel extends TableCell<rt, Boolean> {
        
        final MenuButton btn;
        
        public menuButtonCel(String btnName, MenuItem... items) {
            FontAwesomeIconView f = new FontAwesomeIconView(FontAwesomeIcon.COGS);
            f.getStyleClass().add("fontIconTable");
            this.btn = new MenuButton(btnName, f, items);
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
    
    public TableCell<rt, Boolean> create(String ButtonName, MenuItem... items) {
        return new menuButtonCel(ButtonName, items);
    }
    
    
    
    
}
