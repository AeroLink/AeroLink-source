/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Helpers.LoadSubSystem;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Route;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author ARIELLECIAS
 */
public class MainDashController implements Initializable {

    @FXML
    private BorderPane DropPoint;
    @FXML
    private JFXButton btnRight;

    private AnchorPane ACPaneRight;

    public Boolean paneOpen = false;
    @FXML
    private Menu CORE;
    @FXML
    private Menu HR;
    @FXML
    private Menu LOG;
    @FXML
    private Menu FINANCE;
    @FXML
    private Menu ADMIN;
    @FXML
    private ContextMenu contextM;
    @FXML
    private MenuItem userManagement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (!Session.getPermissions().contains("SysAdmin")) {
            String[] mods = {"CORE", "ADMIN", "FINANCE", "LOG", "HR"};

            for (String m : mods) {
                if (!Session.getPermissions().contains(Session.ModularPermission.get(m))) {
                    switch (m) {
                        case "CORE":
                            CORE.visibleProperty().setValue(false);
                            break;
                        case "ADMIN":
                            ADMIN.visibleProperty().setValue(false);
                            break;
                        case "FINANCE":
                            FINANCE.visibleProperty().setValue(false);
                            break;
                        case "LOG":
                            LOG.visibleProperty().setValue(false);
                            break;
                        case "HR":
                            HR.visibleProperty().setValue(false);
                            break;
                        default:
                            break;
                    }
                }
            }
        } else {
            btnRight.setContextMenu(contextM);
            userManagement.setOnAction(event -> {
                Session.CurrentRoute = "usmManageUsers" ;
                LoadSubSystem loadSubSystem = new Helpers.LoadSubSystem("usmManageUsers", DropPoint);

            });
        }
    }

    @FXML
    public void triggerButton(ActionEvent event) {

        if (!Session.getPermissions().contains(Route.routePermission.get(((MenuItem) event.getSource()).getId()))) {
            Modal md = Modal.getInstance(new Form("/FXMLS/Deny.fxml").getParent());
            md.open();
        } else {
            Session.CurrentRoute = ((MenuItem) event.getSource()).getId();
            LoadSubSystem loadSubSystem = new Helpers.LoadSubSystem(((MenuItem) event.getSource()).getId(), DropPoint);

        }

    }


    @FXML
    private void btnClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void pullRight(MouseEvent event) {
        contextM.show(btnRight, Side.RIGHT, event.getX(), event.getY());
    }

}
