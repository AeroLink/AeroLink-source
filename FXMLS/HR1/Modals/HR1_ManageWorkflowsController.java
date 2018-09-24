/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.TableModel_Stages;
import Model.HR1.HR1_AppStages;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_ManageWorkflowsController implements Initializable {

    //Vars
    HR1_AppStages appStages = new HR1_AppStages();
    ObservableList<TableModel_Stages> list_stages = FXCollections.observableArrayList();
    long TableCount = 0;
    long CurrentCount = 0;
    public static Boolean modalOpen = true;

    private JFXTextField txtSearch;
    private TextField txtStageDescription;
    private TextField txtStageName;
    @FXML
    private TableView<TableModel_Stages> tblStages;

    @FXML
    private StackPane sp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modalOpen = true;
        
        this.GenerateTable();
        this.populateTable();
        
        tblStages.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TableModel_Stages tb = tblStages.getSelectionModel().getSelectedItem();
                this.editStage(
                        tb.stage_id.getValue(),
                        tb.stage_name.getValue(),
                        tb.stage_description.getValue());
            }
        });
    }

    public void GenerateTable() {
        tblStages.getItems().clear();
        tblStages.getColumns().removeAll(tblStages.getColumns());

        TableColumn<TableModel_Stages, String> stage_name = new TableColumn<>("Stage Name");
        TableColumn<TableModel_Stages, String> stage_description = new TableColumn<>("Stage Description");

        stage_name.setCellValueFactory(param -> param.getValue().stage_name);
        stage_description.setCellValueFactory(param -> param.getValue().stage_description);

        tblStages.getColumns().addAll(stage_name, stage_description);
    }

    public void populateTable() {
        CompletableFuture.supplyAsync(() -> {
            while (modalOpen) {
                try {
                    
                    appStages.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(row -> {
                        CurrentCount = Long.parseLong(((HashMap) row).get("chk").toString());
                    });

                    if (TableCount != CurrentCount) {

                        list_stages.clear();

                        appStages.get().stream().forEach(row -> {
                            HashMap current = (HashMap) row;
                            String stage_id = current.get("stage_id").toString();
                            String stage_name = current.get("stage_name").toString();
                            String stage_description = current.get("stage_description").toString();
                            list_stages.add(new TableModel_Stages(stage_id, stage_name, stage_description));
                        });

                        tblStages.setItems(list_stages);
                        TableCount = CurrentCount;
                    }

                } catch (Exception ex) {
                    System.err.println("Exception -> " + ex.getMessage());
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR1_ManageWorkflowsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);
    }

    private void addWorkflow(ActionEvent event) {
        try {
            if (appStages.insert(new Object[][]{
                {"stage_name", txtStageName.getText()},
                {"stage_description", txtStageDescription.getText()}
            })) {

                Helpers.EIS_Response.SuccessResponse("Success", "New Stage was added");
            }
        } catch (Exception ex) {
            System.err.println("Exception -> " + ex.getMessage());

        }
    }

    public void editStage(String id, String StageName, String StageDesc) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Edit Stage"));

        Label label = new Label("Stage Name");
        TextField txtName = new TextField(StageName);
        Label label2 = new Label("Stage Description");
        TextArea txtA = new TextArea(StageDesc);
        VBox vbox = new VBox(
                new Label(""),
                label,
                new Label(""),
                txtName,
                new Label(""),
                label2,
                new Label(""),
                txtA);

        layout.setBody(vbox);

        JFXDialog dialog = new JFXDialog(sp, layout, JFXDialog.DialogTransition.TOP);

        JFXButton btnSubmit = new JFXButton("Update");
        JFXButton btnCancel = new JFXButton("Cancel");

        btnSubmit.setOnMouseClicked(event -> {
            if (appStages.update(new Object[][]{
                {"stage_name", txtName.getText()},
                {"stage_description", txtA.getText()}
            }).where(new Object[][]{
                {"stage_id", "=", id}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Stage was updated successfully");

                dialog.close();
            }
        });

        btnCancel.setOnMouseClicked(event -> {
            dialog.close();
        });

        btnSubmit.getStyleClass().add("btn-primary");
        btnCancel.getStyleClass().add("btn-danger");

        layout.setActions(btnSubmit, new JFXButton(""), btnCancel);
        dialog.show();

    }

}
