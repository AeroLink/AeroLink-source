/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import FXMLS.Core2.ClassFiles.TableModel_FAQs;
import FXMLS.Core2.ClassFiles.TableModel_WebQuestion;
import Model.Core2.CORE2_FAQs;
import Model.Core2.Core2_WebQuestion;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class CRMviewComplaintsController implements Initializable {

    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */

    @FXML
    private TableView<TableModel_WebQuestion> tblNotification;
    ObservableList<TableModel_WebQuestion> tblwq = FXCollections.observableArrayList();
    Core2_WebQuestion wq = new Core2_WebQuestion();

    CORE2_FAQs faqs = new CORE2_FAQs();
    ObservableList<TableModel_FAQs> tbl1 = FXCollections.observableArrayList();

    @FXML
    private TableView<TableModel_FAQs> tblOpenQA;
    @FXML
    private TableColumn<TableModel_FAQs, String> colQuestion;
    @FXML
    private TableColumn<TableModel_FAQs, String> colAnswer;
    @FXML
    private TextField question;
    @FXML
    private TextField answer;
    @FXML
    private JFXButton submit;
    @FXML
    private StackPane sp;
    // sila yung need para sa delete modal sa faqs
    @FXML
    private ContextMenu contextmenu;
    @FXML
    private MenuItem delete;
    // sila yung need para sa modal ng notificatioin
    @FXML
    private ContextMenu contextReply;
    @FXML
    private MenuItem ctmReply;
    @FXML
    private AnchorPane CRMrootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        submit.setOnMouseClicked(e -> Insert());
        GenerateQA();
        LoadQA();

        tblOpenQA.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TableModel_FAQs tb = tblOpenQA.getSelectionModel().getSelectedItem();
                this.editStage(
                        tb.id.getValue(),
                        tb.question.getValue(),
                        tb.answer.getValue());
            }
        });

        tblwq.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblNotification.setItems(tblwq);
        });
        this.generateTableColumn();
        this.populateTable();
    }

    public void GenerateQA() {
//        TableColumn<TableModel_FAQs, String> question = new TableColumn<>("QUESTION");
//        TableColumn<TableModel_FAQs, String> answer = new TableColumn<>("ANSWER");
//        question.setCellValueFactory(value -> value.getValue().question);
//        answer.setCellValueFactory(value -> value.getValue().answer);
        colQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        colAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
    }

    public void generateTableColumn() {
        tblNotification.getItems().clear();
        tblNotification.getColumns().removeAll(tblNotification.getColumns());

        TableColumn<TableModel_WebQuestion, String> notif = new TableColumn<>("NOTIFICATIONS");
        notif.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_WebQuestion, String> param) -> param.getValue().email);
        tblNotification.getColumns().addAll(notif);
    }

    //  select ng data para sa table lang
    public void LoadQA() {
        CORE2_FAQs qa = new CORE2_FAQs();
        ObservableList<TableModel_FAQs> complaints = FXCollections.observableArrayList();
        List b = qa.get();

        for (Object d : b) {
            HashMap hm = (HashMap) d;
            hm.get("id");
            hm.get("question");
            hm.get("answer");

            complaints.add(
                    new TableModel_FAQs(
                            String.valueOf("id"),
                            String.valueOf(hm.get("question")),
                            String.valueOf(hm.get("answer"))
                    ));
        }
        tblOpenQA.setItems(complaints);
    }

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateTable() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = wq.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = wq
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id") 
                                    // yung nasa baba nito ayan yung query na select baka malito kayo eh 
                                    .get("CONCAT(email,' - ',created_at) as notif");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTable(rs);
                        }
                    });
                    Thread.sleep(3000);
                }
            }
        });
        th.setDaemon(true);
        th.start();
    }

    // INSERT QUERY para sa mga textfield etc. ganon
    public void Insert() {
        CORE2_FAQs qa = new CORE2_FAQs();
        // ginawa ko to para mag popup yung else sa loob ng try
        String quest = question.getText();
        String ans = answer.getText();

        try {
            String[][] crm_data = {
                {"question", question.getText()},
                {"answer", answer.getText()}
            };
            // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
            if (qa.insert(crm_data)) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data has been Saved!");
                question.setText("");
                answer.setText("");
            } else {
                // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                if ((quest.isEmpty() || ans.isEmpty())) {
                    Helpers.EIS_Response.ErrorResponse("Error", "Data Not Save!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // para sa realty update ng data
        GenerateQA();
        LoadQA();
    }

    @FXML // Modal sya
    private void reply(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/CRMviewComplaintsReply.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.CRMviewComplaintsReplyController.modalOpen = false;
        });
    }

    // sya yung lalabas kapag gusto mo mag edit ng faqs
    public void editStage(String id, String question, String answer) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text("Edit FAQ's"));

        Label label = new Label("Question");
        TextArea txtQ = new TextArea(question);
        Label label2 = new Label("Answer");
        TextArea txtA = new TextArea(answer);
        VBox vbox = new VBox(
                new Label(""),
                label,
                new Label(""),
                txtQ,
                new Label(""),
                label2,
                new Label(""),
                txtA);

        layout.setBody(vbox);

        JFXDialog dialog = new JFXDialog(sp, layout, JFXDialog.DialogTransition.TOP);

        JFXButton btnSubmit = new JFXButton("Update");
        JFXButton btnCancel = new JFXButton("Cancel");

        btnSubmit.setOnMouseClicked(event -> {
            if (faqs.update(new Object[][]{
                {"question", txtQ.getText()},
                {"answer", txtA.getText()}
            }).where(new Object[][]{
                {"id", "=", id}
            }).executeUpdate()) {
                Helpers.EIS_Response.SuccessResponse("Success", "Data was updated successfully");
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

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTable(List rs) {
        tblwq.clear();

        for (Object row : rs) {
            HashMap drow = (HashMap) row;

            String email = (String) drow.get("notif");

            tblwq.add(new TableModel_WebQuestion(email));
        }
    }

    @FXML
    private void viewComplaints(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/CRMviewComplaints.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewReports(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/CRMviewReport.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewMonitoring(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/CustomerRelationshipManagement.fxml"));
        CRMrootPane.getChildren().setAll(pane);
    }

}
