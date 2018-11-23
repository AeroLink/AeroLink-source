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
import Synapse.SysDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ARIELLECIAS
 */
public class MainDashController implements Initializable {

    public static BorderPane dpp;

    @FXML
    public BorderPane DropPoint;

    private AnchorPane ACPaneRight;

    public Boolean paneOpen = false;
    @FXML
    private ContextMenu contextM;
    @FXML
    private MenuItem userManagement;
    @FXML
    public BorderPane Sidebar;
    @FXML
    private JFXButton closeBtn;

    private final static Duration DEFAULT_TIME_ANIM = new Duration(200);
    private static final double DEFAULT_WIDTH_NAV = 546;
    @FXML
    private JFXButton btnRight;
    @FXML
    private MenuItem importAndExport;
    @FXML
    private Region regionBlack;
    @FXML
    private AnchorPane acSide;
    @FXML
    private JFXHamburger Drawer;
    @FXML
    private StackPane spane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpp = DropPoint;
        acSide.setVisible(false);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(primaryScreenBounds);
        Sidebar.setPrefHeight(primaryScreenBounds.getHeight() + 50);
        drawerAnimation();
        // TODO
        if (Session.getPermissions().contains("SysAdmin")) {
            btnRight.setContextMenu(contextM);
            userManagement.setOnAction(event -> {
                Session.CurrentRoute = "usmManageUsers";
                LoadSubSystem loadSubSystem = new Helpers.LoadSubSystem("usmManageUsers", DropPoint);
            });
        }

        acSide.setOnMouseClicked(value -> {
            triggerNav();
        });

        Drawer.setOnMouseClicked(value -> {
            triggerNav();
        });
    }

    @FXML
    private void btnClose(ActionEvent event) {

        SysDialog dialog = new SysDialog();

        VBox vbox = new VBox(
                new Label("Do you really want to exit?")
        );

        JFXButton btnSubmitInit = new JFXButton("Yes");
        JFXButton btnCancel = new JFXButton("No");

        btnSubmitInit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        btnSubmitInit.setOnMouseClicked(value -> {
            System.exit(0);
        });

        btnCancel.setOnMouseClicked(value -> {
            dialog.closeDialog();
        });

        dialog.createLayout(new Text("Halt!")).showDialog(spane, JFXDialog.DialogTransition.BOTTOM, vbox, btnSubmitInit, new JFXButton(""), btnCancel);

    }

    public void triggerNav() {
        if (paneOpen) {
            paneOpen = false;
            drawerAnimation();
        } else {
            acSide.setVisible(true);
            regionBlack.setVisible(true);
            paneOpen = true;
            drawerAnimation();
        }
    }

    private void drawerAnimation() {
        Timeline animation;
        if (paneOpen) {
            animation = new Timeline(new KeyFrame(DEFAULT_TIME_ANIM,
                    new KeyValue(Sidebar.translateXProperty(), 0, Interpolator.EASE_OUT)
            ));

        } else {
            animation = new Timeline(new KeyFrame(DEFAULT_TIME_ANIM,
                    new KeyValue(Sidebar.translateXProperty(), -DEFAULT_WIDTH_NAV, Interpolator.EASE_IN)
            ));
        }

        animation.setOnFinished(evt -> {
            if (!paneOpen) {
                regionBlack.setVisible(false);
                acSide.setVisible(false);
            }
        });
        animation.play();
    }

    @FXML
    private void pullRight(MouseEvent event) {
        contextM.show(btnRight, Side.RIGHT, event.getX(), event.getY());
    }

}
