/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import FXMLS.Core2.ClassFiles.TableModel_FAQs;
import FXMLS.Core2.ClassFiles.TableModel_WebQuestion;
import FXMLS.Core2.ClassFiles.CRMTable_customer_cases;
import Model.Core2.CORE2_Customer_Service;
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
    ObservableList<CRMTable_customer_cases> tblcases = FXCollections.observableArrayList();
    Core2_WebQuestion wq = new Core2_WebQuestion();
    CORE2_Customer_Service cs = new CORE2_Customer_Service();

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
    @FXML
    private TableView<CRMTable_customer_cases> tblCase;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem viewIssue;

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
        // for online complaints
        tblwq.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblNotification.setItems(tblwq);
        });
        // for case
        tblcases.addListener((ListChangeListener.Change<? extends Object> c) -> {
            tblCase.setItems(tblcases);
        });
        // for online complaints 
        this.generateTableColumn();
        this.populateTable();
        // for case
        this.generateCase();
        this.populateCase();

    }

    // for faq's
    public void GenerateQA() {
//        TableColumn<TableModel_FAQs, String> question = new TableColumn<>("QUESTION");
//        TableColumn<TableModel_FAQs, String> answer = new TableColumn<>("ANSWER");
//        question.setCellValueFactory(value -> value.getValue().question);
//        answer.setCellValueFactory(value -> value.getValue().answer);
        colQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        colAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
    }

    // for online complaints
    public void generateTableColumn() {
        tblNotification.getItems().clear();
        tblNotification.getColumns().removeAll(tblNotification.getColumns());

        TableColumn<TableModel_WebQuestion, String> notif = new TableColumn<>("NOTIFICATIONS");
        notif.setCellValueFactory((TableColumn.CellDataFeatures<TableModel_WebQuestion, String> param) -> param.getValue().email);
        tblNotification.getColumns().addAll(notif);
    }

    // for customer service
    public void generateCase() {
        tblCase.getItems().clear();
        tblCase.getColumns().removeAll(tblCase.getColumns());

        TableColumn<CRMTable_customer_cases, String> case_no = new TableColumn<>("CASE NUMBER");
        TableColumn<CRMTable_customer_cases, String> emp_id = new TableColumn<>("EMPLOYEE ID");
        TableColumn<CRMTable_customer_cases, String> ref_no = new TableColumn<>("REFERENCE NUMBER");
        TableColumn<CRMTable_customer_cases, String> category = new TableColumn<>("CATEGORY");
        TableColumn<CRMTable_customer_cases, String> date = new TableColumn<>("DATE ISSUE");
        TableColumn<CRMTable_customer_cases, String> lvl = new TableColumn<>("LEVEL");
        TableColumn<CRMTable_customer_cases, String> status = new TableColumn<>("STATUS");
        case_no.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().case_no);
        emp_id.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().emp_id);
        ref_no.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().ref_no);
        category.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().category);
        date.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().date);
        lvl.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().lvl);
        status.setCellValueFactory((TableColumn.CellDataFeatures<CRMTable_customer_cases, String> param) -> param.getValue().status);
        tblCase.getColumns().addAll(case_no, emp_id, ref_no, category, date, lvl, status);
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
                                    .get("CONCAT(email,' - ',created_at) as notif", "name", "comment");
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

    // dito isinasagawa yung pag query and etc or inner|left join
    public void populateCase() {
        Thread th = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                while (true) {
                    CompletableFuture.supplyAsync(() -> {
                        List list = cs.get(("COUNT(*) as total"));
                        return Integer.parseInt(((HashMap) list.get(0)).get("total").toString());
                    }).thenApply((count) -> {
                        List rs = new ArrayList<>();
                        if (count != Global_Count) {
                            rs = cs
                                    //.join(Model.JOIN.LEFT, "aerolink.tbl_core1_customer_details", "customer_id", "cid", "=", "customer_id") 
                                    // yung nasa baba nito ayan yung query na select baka malito kayo eh 
                                    .get("CONCAT('0000',case_no) as case_no", "ref_no", "emp_id", "category", "date", "lvl", "status","issue");
                            Global_Count = count;
                        }
                        return rs;
                    }).thenAccept((rs) -> {
                        if (!rs.isEmpty()) {
                            AddTableCase(rs);
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
        FXMLS.Core2.Modals.CRMviewComplaintsReplyController.email = tblNotification.getSelectionModel().getSelectedItem().email.getValue();
        FXMLS.Core2.Modals.CRMviewComplaintsReplyController.name = tblNotification.getSelectionModel().getSelectedItem().name.getValue();
        FXMLS.Core2.Modals.CRMviewComplaintsReplyController.comment = tblNotification.getSelectionModel().getSelectedItem().comment.getValue();

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
            String name = (String) drow.get("name");
            String comment = (String) drow.get("comment");

            tblwq.add(new TableModel_WebQuestion(email, name, comment));
        }
    }

    // need sya para lumabas yung result sa tableview ez LAWGIC
    public void AddTableCase(List rs) {
        tblcases.clear();

        for (Object row : rs) {
            HashMap trow = (HashMap) row;

            String case_no = (String) trow.get("case_no");
            String ref_no = (String) trow.get("ref_no");
            String emp_id = (String) trow.get("emp_id");
            String category = (String) trow.get("category");
            String date = (String) trow.get("date");
            String lvl = (String) trow.get("lvl");
            String status = (String) trow.get("status");
            String issue = (String) trow.get("issue");

            tblcases.add(new CRMTable_customer_cases(case_no, ref_no, emp_id, category, date, lvl, status,issue));
        }
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

    @FXML
    private void viewAddCase(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/CRMaddCase.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.CRMaddCaseController.modalOpen = false;
        });
    }

    @FXML
    private void viewIssue(ActionEvent event) {
        FXMLS.Core2.Modals.CRMviewIssueController.issue = tblCase.getSelectionModel().getSelectedItem().issue.getValue();
        
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/CRMviewIssue.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.CRMviewIssueController.modalOpen = false;
        });
    }

    @FXML
    private void viewSearch(ActionEvent event) {
    }

}
