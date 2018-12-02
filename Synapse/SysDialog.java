/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 *
 * @author BlackMoon
 */
public class SysDialog {

    private JFXDialogLayout layout = new JFXDialogLayout();
    private JFXDialog jfxDialog;

    public SysDialog createLayout(Node heading) {
        layout.setHeading(heading);
        return this;
    }

    public void showDialog(StackPane spane, JFXDialog.DialogTransition dialogTransition, Node body, Node... button_actions) {
        layout.setBody(body);
        layout.setActions(button_actions);

        jfxDialog = new JFXDialog(spane, layout, dialogTransition);
        jfxDialog.show();
    }
    
    public void closeDialog() {
        jfxDialog.close();
    }

}
