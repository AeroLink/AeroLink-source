/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Model.HR4_PayrollSettingsModel;
import FXMLS.HR4.ClassFiles.HR4_PayrollSettingsClass;
import FXMLS.HR4.ClassFiles.HR4_SalaryClass;
import FXMLS.HR4.HR4_Core_Human_Capital_ManagementController;
import Synapse.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Model.HR2_TM_Training_Requisition;
import Synapse.Model;
import java.time.LocalTime;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_PayrollSettingsController implements Initializable {

    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;
    @FXML
    private TextField e;
    @FXML
    private TextField f;
    @FXML
    private TextField g;
    @FXML
    private TextField h;
    @FXML
    private TextField i;
    @FXML
    private TextField j;
    @FXML
    private TextField k;
    @FXML
    private TextField l;
    @FXML
    private JFXButton EditBtn;
    @FXML
    private JFXButton UpdateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    this.populateData();
    EditBtn.setOnMouseClicked(event -> EditBtn1());
    UpdateBtn.setOnMouseClicked(event -> UpdateSettings());
    }
    
    long GlobalCount = 0;
    long DummyCount = 0;
    public void populateData() {
        HR4_PayrollSettingsModel psm = new HR4_PayrollSettingsModel();
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4payroll")) {
                try {
                    psm.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {
                            List rs = psm
                                    .get("cola","overtime","special_holiday","special_holiday_ot","regular_holiday","regular_holiday_ot","double_holiday","double_holiday_ot","night_differentials","special_holiday_ns","regular_holiday_ns","double_holiday_ns");
                            
                        ListData(rs);
                        GlobalCount = DummyCount;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void ListData(List ba) {
            ba.stream().forEach(row -> {
            a.setText(((HashMap) row).get("cola").toString());
            b.setText(((HashMap) row).get("overtime").toString());
            c.setText(((HashMap) row).get("special_holiday").toString());
            d.setText(((HashMap) row).get("special_holiday_ot").toString());
            e.setText(((HashMap) row).get("regular_holiday").toString());
            f.setText(((HashMap) row).get("regular_holiday_ot").toString());
            g.setText(((HashMap) row).get("double_holiday").toString());
            h.setText(((HashMap) row).get("double_holiday_ot").toString());
            i.setText(((HashMap) row).get("night_differentials").toString());
            j.setText(((HashMap) row).get("special_holiday_ns").toString());
            k.setText(((HashMap) row).get("regular_holiday_ns").toString());
            l.setText(((HashMap) row).get("double_holiday_ns").toString());

        });
    }
    private void EditBtn1() {
        a.setDisable(false);
        b.setDisable(false);
        c.setDisable(false);
        d.setDisable(false);
        e.setDisable(false);
        f.setDisable(false);
        g.setDisable(false);
        h.setDisable(false);
        i.setDisable(false);
        j.setDisable(false);
        k.setDisable(false);
        l.setDisable(false);
    }
    
    private void UpdateSettings(){
    HR4_PayrollSettingsModel m = new HR4_PayrollSettingsModel();

            Boolean ab = m.where(new Object[][]{
                {"id", "=", "3"}
            }).update(new Object[][]{
                {"cola", a.getText()},
                {"overtime", b.getText()},
                {"special_holiday", c.getText()},
                {"special_holiday_ot", d.getText()},
                {"regular_holiday", e.getText()},
                {"regular_holiday_ot", f.getText()},
                {"double_holiday", g.getText()},
                {"double_holiday_ot", h.getText()},
                {"night_differentials", i.getText()},
                {"special_holiday_ns", j.getText()},
                {"regular_holiday_ns", k.getText()},
                {"double_holiday_ns", l.getText()},
            
            }).executeUpdate();
    
    }
}
