/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS;

import Helpers.LoadSubSystem;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Route;
import Synapse.Session;
import static Views.MainDashController.dpp;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ARIELLECIAS
 */
public class CoreSideController implements Initializable {

    @FXML
    private ImageView userImage;

    private final static Duration DEFAULT_TIME_ANIM = new Duration(200);
    private static final double DEFAULT_WIDTH_NAV = 273;
    public Boolean paneOpen = false;
    @FXML
    private Tab CORE;
    @FXML
    private Tab HR;
    @FXML
    private Tab LOG;
    @FXML
    private Tab ADMIN;
    @FXML
    private Tab FINANCE;
    @FXML
    private Label SessionUsername;
    @FXML
    private Tab FINANCE1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SessionUsername.setText(Session.pull("username").toString());

        Rectangle clip = new Rectangle(userImage.getFitWidth() - 30, userImage.getFitHeight() - 1);
        clip.setArcHeight(50);
        clip.setArcWidth(50);
        userImage.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = userImage.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        userImage.setClip(null);

        // apply a shadow effect.
        userImage.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        userImage.setImage(image);

        if (!Session.getPermissions().contains("SysAdmin")) {
            String[] mods = {"CORE", "ADMIN", "FINANCE", "LOG", "HR"};

            for (String m : mods) {
                if (!Session.getPermissions().contains(Session.ModularPermission.get(m))) {
                    switch (m) {
                        case "CORE":
                            CORE.disableProperty().setValue(true);
                            break;
                        case "ADMIN":
                            ADMIN.disableProperty().setValue(true);
                            break;
                        case "FINANCE":
                            FINANCE.disableProperty().setValue(true);
                            break;
                        case "LOG":
                            LOG.disableProperty().setValue(true);
                            break;
                        case "HR":
                            HR.disableProperty().setValue(true);
                            break;
                        default:
                            break;
                    }
                }
            }
        }

    }

    @FXML
    public void triggerButton(ActionEvent event) {

        if (!Session.getPermissions().contains(Route.routePermission.get(((JFXButton) event.getSource()).getId()))) {
//            Modal md = Modal.getInstance(new Form("/FXMLS/Deny.fxml").getParent());
//            md.open();

            Notifications nBuilder = Notifications.create()
                    .title("Access Denied")
                    .text("Unable to access this module due to lack of permission")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER);

            nBuilder.showError();
        } else {
            String nav_id = ((JFXButton) event.getSource()).getId();
            Session.CurrentRoute = nav_id;
            if (Session.CurrentRoute.contains("0xreq")) {
                Modal md = Modal.getInstance(new Form(Route.routes.get(nav_id).toString()).getParent());
                md.open();
            } else {
                LoadSubSystem loadSubSystem = new Helpers.LoadSubSystem(((JFXButton) event.getSource()).getId(), dpp);
            }
        }

    }

}
