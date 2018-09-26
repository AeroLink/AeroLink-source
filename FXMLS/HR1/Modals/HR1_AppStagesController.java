/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.Modals;

import FXMLS.HR1.ClassFiles.HR1_Applicant;
import FXMLS.HR1.ClassFiles.TableModel_StageResults;
import Model.HR1.HR1_AppStages;
import Synapse.Model;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class HR1_AppStagesController implements Initializable {

    @FXML
    private StackPane spane;
    @FXML
    private TableView<TableModel_StageResults> tblResults;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.generateTable();
        this.StageResults();

        tblResults.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                viewResults();
            }
        });
    }

    public void generateTable() {
        tblResults.getItems().clear();
        tblResults.getColumns().removeAll(tblResults.getColumns());

        TableColumn<TableModel_StageResults, String> Stage = new TableColumn<>("Stage");
        TableColumn<TableModel_StageResults, String> result = new TableColumn<>("Results");
        TableColumn<TableModel_StageResults, String> remarks = new TableColumn<>("Remarks");

        Stage.setCellValueFactory(value -> value.getValue().Stage);
        result.setCellValueFactory(value -> value.getValue().result);
        remarks.setCellValueFactory(value -> value.getValue().remarks);

        tblResults.getColumns().addAll(Stage, result, remarks);

    }

    public void StageResults() {
        HR1_AppStages appres = new HR1_AppStages("stage_results");

        ObservableList obs = FXCollections.observableArrayList();

        appres.join(Model.JOIN.INNER, "aerolink.tbl_hr1_stages", "stage_id", "=", "stage_id").where(new Object[][]{
            {"app_id", "=", HR1_Applicant.app_id}
        }).get().stream().forEach(event -> {
            HashMap row = (HashMap) event;

            obs.add(new TableModel_StageResults(
                    row.get("result_id").toString(), 
                    row.get("stage_name").toString(), 
                    row.get("result").toString(),
                    row.get("remarks").toString()));
            
        });
        
        tblResults.getItems().clear();
        tblResults.setItems(obs);
    }
    
    public void viewResults() {
      //TODO: Later will add permission for editing for now we would now just deal with the deadline 
      
        JFXDialogLayout layout = new JFXDialogLayout();
        
        layout.setHeading(new Text("Viewing Result"));
        
        VBox vbox = new VBox(
                new Label(""),
                new Label("Stage Name"),
                new TextField(tblResults.getSelectionModel().getSelectedItem().Stage.getValue()),
                new Label(""),
                new Label("Stage Result"),
                new TextField(tblResults.getSelectionModel().getSelectedItem().result.getValue()),
                new Label(""),
                new Label("Remarks"),
                new TextArea(tblResults.getSelectionModel().getSelectedItem().remarks.getValue())
        );
        
        layout.setBody(vbox);
        
        JFXDialog dialog = new JFXDialog(spane, layout, JFXDialog.DialogTransition.TOP);
        
        dialog.show();
        
        
    }

}
