/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.TableModel_AwardTable;
import FXMLS.HR1.ClassFiles.TableModel_RewardTable;
import Model.HR1.HR1_SC_Award;
import Model.HR1.HR1_SC_Reward;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javafx.scene.control.TableColumn;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_Social_RecognitionController implements Initializable {

    HR1_SC_Award award = new HR1_SC_Award();

    HR1_SC_Reward reward = new HR1_SC_Reward();
    @FXML
    private TableView<TableModel_AwardTable> tbl_award;
    @FXML
    private TableView<TableModel_RewardTable> tbl_reward;
    @FXML
    private JFXTextField txtAwardname;
    @FXML
    private JFXTextField txtAwarddes;
    @FXML
    private JFXDatePicker dateAdded;
    @FXML
    private JFXTextField txtRewardname;
    @FXML
    private JFXTextField txtRemarks;
    @FXML
    private JFXDatePicker dateadded;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.generateTable();
        this.renderTable();
        this.renderRewardTable();

    }

    public void generateTable() {

        tbl_award.getColumns().removeAll(tbl_award.getColumns());

        TableColumn<TableModel_AwardTable, String> award_name = new TableColumn<>("Award Name");
        TableColumn<TableModel_AwardTable, String> award_description = new TableColumn<>("Award Description");
        TableColumn<TableModel_AwardTable, String> date_added = new TableColumn<>("Date Added");

        award_name.setCellValueFactory(value -> value.getValue().award_name);
        award_description.setCellValueFactory(value -> value.getValue().award_description);
        date_added.setCellValueFactory(value -> value.getValue().date_added);

        tbl_award.getColumns().addAll(award_name, award_description, date_added);

        //
        tbl_reward.getColumns().removeAll(tbl_reward.getColumns());

        TableColumn<TableModel_RewardTable, String> reward_name = new TableColumn<>("Reward Name");
        TableColumn<TableModel_RewardTable, String> reward_remarks = new TableColumn<>("Remarks");
        TableColumn<TableModel_RewardTable, String> added_date = new TableColumn<>("Date Added");

        reward_name.setCellValueFactory(value -> value.getValue().reward_name);
        reward_remarks.setCellValueFactory(value -> value.getValue().reward_remarks);
        added_date.setCellValueFactory(value -> value.getValue().date_added);

        tbl_reward.getColumns().addAll(reward_name, reward_remarks, added_date);
    }

    public void renderTable() {
        ObservableList<TableModel_AwardTable> list = FXCollections.observableArrayList();
        award.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            list.add(new TableModel_AwardTable(row.get("award_name").toString(), row.get("award_description").toString(), row.get("date_added").toString()));
        });

        tbl_award.getItems().clear();
        tbl_award.setItems(list);
    }

    public void renderRewardTable() {
        ObservableList<TableModel_RewardTable> list = FXCollections.observableArrayList();
        reward.get().stream().forEach(action -> {
            HashMap row = (HashMap) action;
            list.add(new TableModel_RewardTable(row.get("reward_name").toString(), row.get("reward_remarks").toString(), row.get("date_added").toString()));
        });

        tbl_reward.getItems().clear();
        tbl_reward.setItems(list);
    }

    @FXML
    private void btnSaveAward(ActionEvent event) {

        if (award.insert(new Object[][]{
            {"award_name", txtAwardname.getText()},
            {"award_description", txtAwarddes.getText()},
            {"date_added", dateAdded.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)}
        })) {

            Helpers.EIS_Response.SuccessResponse("Success", "Adding New Award Successfuly");

            this.renderTable();
            txtAwardname.setText("");
            txtAwarddes.setText("");
            dateAdded.setValue(null);

        }

    }

    @FXML
    private void btnSaveReward(ActionEvent event) {

        if (reward.insert(new Object[][]{
            {"reward_name", txtRewardname.getText()},
            {"reward_remarks", txtRemarks.getText()},
            {"Date_added", dateadded.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE)}
        })) {

            Helpers.EIS_Response.SuccessResponse("Success", "Adding New Reward Successfuly");

            this.renderRewardTable();

            txtRewardname.setText("");
            txtRemarks.setText("");
            dateadded.setValue(null);
        }

    }

    @FXML
    private void btnawardclear(ActionEvent event) {
        txtAwardname.setText("");
        txtAwarddes.setText("");
        dateAdded.setValue(null);

    }

    @FXML
    private void btnrewardclear(ActionEvent event) {
        txtRewardname.setText("");
        txtRemarks.setText("");
        dateadded.setValue(null);
    }

}
