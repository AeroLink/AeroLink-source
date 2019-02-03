/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Helpers.LoadSubSystem;
import Synapse.STORED_PROC;
import Synapse.Session;
import Synapse.SysDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

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
    @FXML
    private JFXButton btnRx;
    @FXML
    private ContextMenu contextM1;
    @FXML
    private MenuItem userManagement1;
    @FXML
    private MenuItem importAndExport1;
    @FXML
    private JFXButton btnNotification;

    PopOver notifications = new PopOver();

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

        btnRx.setOnMouseClicked(value -> {
            Session.CurrentRoute = "requisitions";
            LoadSubSystem loadSubSystem = new Helpers.LoadSubSystem("requisitions", DropPoint);
        });

        //PopOver Notifications
        notificationBody();
        notifications.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        notifications.setAnimated(true);
        notifications.setFadeOutDuration(Duration.seconds(0.5));
        notifications.setTitle("Notifications");

        btnNotification.setOnMouseClicked(value -> {
            notifications.show(btnNotification);
        });

    }

    private enum NotificationsType {
        SYSTEM_ADMINISTRATOR, NORMAL, URGENT, APPROVED, DENIED, WARNING
    }

    private NotificationsType getNotifType(String str) {
        NotificationsType x = NotificationsType.NORMAL;
        switch (str) {
            case "0":
                x = NotificationsType.SYSTEM_ADMINISTRATOR;
                break;
            case "1":
                x = NotificationsType.NORMAL;
                break;
            case "2":
                x = NotificationsType.URGENT;
                break;
            case "3":
                x = NotificationsType.APPROVED;
                break;
            case "4":
                x = NotificationsType.DENIED;
                break;
            case "5":
                x = NotificationsType.WARNING;
                break;
            default:
                break;
        }

        return x;
    }
    
    private String getColorNotif(NotificationsType e) {
        String x = "";
        switch(e) {
            case SYSTEM_ADMINISTRATOR : x = "notif-violet"; break;
            case NORMAL : x = "notif-blue"; break;
            case APPROVED : x = "notif-green"; break;
            case DENIED : x = "notif-red"; break;
            case URGENT : x = "notif-yellow"; break;
            case WARNING : x = "notif-orange"; break;
        }
        return x;
    }

    long DummyCount = 0;
    long GlobalCount = 0;
    long CountNotif = 0;

    public void notificationBody() {
        Notification notifs = new Notification();

        CompletableFuture.supplyAsync(() -> {

            while (true) {
                notifs.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                    DummyCount = Long.parseLong(((HashMap) row).get("chk").toString());
                });

                if (GlobalCount != DummyCount) {
                    CountNotif = 0;
                    VBox vbox = new VBox();
                    List<HashMap> list = STORED_PROC.executeCall("getAllNotifications");
                    list.stream().forEach((HashMap e) -> {
                        Button lbl_status = new Button(getNotifType(e.get("notif_type").toString()).name());
                        lbl_status.getStyleClass().add(getColorNotif(getNotifType(e.get("notif_type").toString())));
                        Label lbl_message = new Label(e.get("notif_desc").toString());
                        VBox xA = new VBox(lbl_status, lbl_message, new Label(""));
                        xA.setPrefWidth(300);
                        xA.setPadding(new Insets(5));
                        vbox.getChildren().add(xA);
                        CountNotif += 1;
                    });

                    Platform.runLater(() -> {
                        ScrollPane sc = new ScrollPane();
                        vbox.setPrefHeight(500);
                        sc.setContent(vbox);
                        Notifications nBuilder = Notifications.create()
                                .title("Alert!")
                                .text("New Notification Recieved.")
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.BOTTOM_RIGHT);

                        nBuilder.showInformation();
                        btnNotification.setText(CountNotif + " Notifications");
                        notifications.setContentNode(sc);
                    });

                    GlobalCount = DummyCount;
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainDashController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, Session.SessionThreads);
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

            Session.CurrentRoute = "LoggedOut";

            // get a handle to the stage
            Stage stage = (Stage) btnSubmitInit.getScene().getWindow();
            // do what you have to do
            stage.close();

            Session.getPermissions().clear();
            Synapse.Form frm = new Synapse.Form("/FXMLS/Login.fxml");
            frm.open(StageStyle.UNDECORATED, false);
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

    class Notification extends Synapse.Model {

        public Notification() {
            this.initTable("tbl_eis_notifications");
        }

    }

}
