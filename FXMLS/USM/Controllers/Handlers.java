/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.USM.Controllers;

import Synapse.Components.Modal.Modal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Lei
 */
public class Handlers {
    
     public static EventHandler<ActionEvent> triggerPermissionsModal = (ActionEvent event) -> {
        Modal md = Modal.getInstance();
        md.open();
     };
}
