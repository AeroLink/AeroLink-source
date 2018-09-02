/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.USM.Controllers;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Route;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author Lei
 */
public class Handlers {
    
     public static int permission_user_id = 0;
     
     public static EventHandler<ActionEvent> triggerPermissionsModal = (ActionEvent event) -> {
//        SetPermissionUSMController.UserID = FXMLS.USM.ManageUsersController
        Modal md = Modal.getInstance(new Form("/FXMLS/USM/Controllers/SetPermissionUSM.fxml").getParent());
        md.open();
     };
}
