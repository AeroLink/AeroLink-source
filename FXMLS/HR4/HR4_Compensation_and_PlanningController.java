/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.ClassFiles.HR4_PHClass;
import FXMLS.HR4.ClassFiles.HR4_SSSClass;
import FXMLS.HR4.ClassFiles.HR4_SalaryClass;
import FXMLS.HR4.ClassFiles.HR4_TaxClass;
import FXMLS.HR4.Model.HR4_PHModel;
import FXMLS.HR4.Model.HR4_SSSModel;
import FXMLS.HR4.Model.HR4_SalaryModel;
import FXMLS.HR4.Model.HR4_TaxModel;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import Synapse.Model;
import Synapse.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_Compensation_and_PlanningController implements Initializable {
    Boolean searchStatus = false;
    @FXML
    private TableView<HR4_SalaryClass> tbl_salary;
    ObservableList<HR4_SSSClass> sss = FXCollections.observableArrayList();
    ObservableList<HR4_PHClass> ph = FXCollections.observableArrayList();
    ObservableList<HR4_TaxClass> wh = FXCollections.observableArrayList();
    ObservableList<HR4_SalaryClass> obj3 = FXCollections.observableArrayList();
    HR4_TaxModel taxmodel = new HR4_TaxModel();
    HR4_SSSModel sssmodel = new HR4_SSSModel();
    HR4_PHModel phmodel = new HR4_PHModel();
    HR4_SalaryModel salarymodel = new HR4_SalaryModel();
    @FXML
    private ComboBox <String>loans_cbx;
    
    ObservableList<String> LoansTypee = FXCollections.observableArrayList("SSS","PhilHealth","WithHolding Tax","Pag Ibig");
    @FXML
    private TableView<HR4_SSSClass> tbl_sss;
    @FXML
    private TableView<HR4_PHClass> tbl_ph;
    @FXML
    private TableView<HR4_TaxClass> tbl_wh;
    @FXML
    private TableView<?> tbl_pi;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generateSalaryGrade();
        this.populateSalaryGrade();
        
        this.generateTable();
        this.populateTable();
        
        this.generateTable1();
        this.populateTable1();
        
        this.generateTable2();
        this.populateTable2();
        loans_cbx.setItems(LoansTypee);
    
    }
     public void generateTable() {

        tbl_sss.getItems().clear();
        tbl_sss.getColumns().removeAll(tbl_sss.getColumns());
        TableColumn<HR4_SSSClass, String> roc = new TableColumn<>("Range of Compensation");
        TableColumn<HR4_SSSClass, String> msc = new TableColumn<>("Monthly Salary Credit");
        TableColumn<HR4_SSSClass, String> er1 = new TableColumn<>("Employer");
        TableColumn<HR4_SSSClass, String> ee1 = new TableColumn<>("Employee");
        TableColumn<HR4_SSSClass, String> total1 = new TableColumn<>("Total");
        TableColumn<HR4_SSSClass, String> ec_er = new TableColumn<>("Designation");
        TableColumn<HR4_SSSClass, String> er2 = new TableColumn<>("Employer");
        TableColumn<HR4_SSSClass, String> ee2 = new TableColumn<>("Employee");
        TableColumn<HR4_SSSClass, String> total2 = new TableColumn<>("Total");
        TableColumn<HR4_SSSClass, String> totalcon = new TableColumn<>("Total Contribution");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        roc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().a);
        msc.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().c);
        er1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().d);
        ee1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().e);
        total1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().f);
        ec_er.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().g);
        er2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().h);
        ee2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().i);
        total2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().j);
        totalcon.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SSSClass, String> param) -> param.getValue().k);

        //</editor-fold>
        tbl_sss.getColumns().addAll(roc, msc, er1, ee1, total1, ec_er, er2, ee2, total2, totalcon);
    }
        
    long DummyCount = 0;
    long GlobalCount = 0;
    public void populateTable() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    sssmodel.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount != GlobalCount) {
                            List rs = sssmodel
                                    .get(
                                            "CONCAT(rocmin,' - ',rocmax) as roc",
                                            "msc",
                                            "er1",
                                            "ee1",
                                            "total1",
                                            "ec_er",
                                            "er2",
                                            "ee2",
                                            "total2",
                                            "totalcon");
                        AddJobToTable(rs);
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

    public void AddJobToTable(List rs) {
        sss.clear();
        tbl_sss.getItems().clear();

        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String roc = String.valueOf(crow.get("roc"));
            String msc = (String) crow.get("msc");
            String er1 = (String) crow.get("er1");
            String ee1 = (String) crow.get("ee1");
            String total1 = (String) crow.get("total1");
            String ec_er = (String) crow.get("ec_er");
            String er2 = (String) crow.get("er2");
            String ee2 = (String) crow.get("ee2");
            String total2 = (String) crow.get("total2");
            String totalcon = (String) crow.get("totalcon");
            sss.add(new HR4_SSSClass(roc, msc, er1, ee1, total1,ec_er,er2,ee2,total2,totalcon));
        }
        tbl_sss.setItems(sss);
    }
    public void generateTable1() {

        tbl_ph.getItems().clear();
        tbl_ph.getColumns().removeAll(tbl_ph.getColumns());
        TableColumn<HR4_PHClass, String> mbs_min = new TableColumn<>("Monthly Basic Salary(min)");
        TableColumn<HR4_PHClass, String> mbs_max = new TableColumn<>("Monthly Basic Salary(max)");
        TableColumn<HR4_PHClass, String> monthly_premium_min = new TableColumn<>("Monthly Premium(min)");
        TableColumn<HR4_PHClass, String> monthly_premium_max = new TableColumn<>("Monthly Premium(max)");
        TableColumn<HR4_PHClass, String> ee_share_min = new TableColumn<>("Employee Share(min)");
        TableColumn<HR4_PHClass, String> ee_share_max = new TableColumn<>("Employee Share(max)");
        TableColumn<HR4_PHClass, String> er_share_min = new TableColumn<>("Employer Share(min)");
        TableColumn<HR4_PHClass, String> er_share_max = new TableColumn<>("Employer Share(max)");

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        mbs_min.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().a);
        mbs_max.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().b);
        monthly_premium_min.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().c);
        monthly_premium_max.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().d);
        ee_share_min.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().e);
        ee_share_max.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().f);
        er_share_min.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().g);
        er_share_max.setCellValueFactory((TableColumn.CellDataFeatures<HR4_PHClass, String> param) -> param.getValue().h);
        //</editor-fold>
        tbl_ph.getColumns().addAll(mbs_min,mbs_max,monthly_premium_min,monthly_premium_max,ee_share_min,ee_share_max,er_share_min,er_share_max);
    }
    long DummyCount1 = 0;
    long GlobalCount1 = 0;
    public void populateTable1() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    phmodel.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount1 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount1 != GlobalCount1) {
                            List rs = phmodel
                                    .get(
                                            "mbs_min","mbs_max","monthly_premium_min","monthly_premium_max","ee_share_min","ee_share_max","er_share_min","er_share_max");
                        AddJobToTable1(rs);
                        GlobalCount1 = DummyCount1;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable1(List rs) {
        ph.clear();
        tbl_ph.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String mbs_min = String.valueOf(crow.get("mbs_min"));
            String mbs_max = (String) crow.get("mbs_max");
            String monthly_premium_min = (String) crow.get("monthly_premium_min");
            String monthly_premium_max = (String) crow.get("monthly_premium_max");
            String ee_share_min = (String) crow.get("ee_share_min");
            String ee_share_max = (String) crow.get("ee_share_max");
            String er_share_min = (String) crow.get("er_share_min");
            String er_share_max = (String) crow.get("er_share_max");
            ph.add(new HR4_PHClass(mbs_min,mbs_max,monthly_premium_min,monthly_premium_max,ee_share_min,ee_share_max,er_share_min,er_share_max));
        }
        tbl_ph.setItems(ph);
    }
    public void generateTable2() {

        tbl_wh.getItems().clear();
        tbl_wh.getColumns().removeAll(tbl_wh.getColumns());
        TableColumn<HR4_TaxClass, String> min = new TableColumn<>("Min");
        TableColumn<HR4_TaxClass, String> max = new TableColumn<>("Max");
        TableColumn<HR4_TaxClass, String> basic_amount = new TableColumn<>("Basic Amount");
        TableColumn<HR4_TaxClass, String> additional_rate = new TableColumn<>("Additional Rate");
        TableColumn<HR4_TaxClass, String> of_excess_over = new TableColumn<>("Of Excess Over");
        TableColumn<HR4_TaxClass, String> total_tax = new TableColumn<>("Total Tax");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        min.setCellValueFactory((TableColumn.CellDataFeatures<HR4_TaxClass, String> param) -> param.getValue().a);
        max.setCellValueFactory((TableColumn.CellDataFeatures<HR4_TaxClass, String> param) -> param.getValue().b);
        basic_amount.setCellValueFactory((TableColumn.CellDataFeatures<HR4_TaxClass, String> param) -> param.getValue().c);
        additional_rate.setCellValueFactory((TableColumn.CellDataFeatures<HR4_TaxClass, String> param) -> param.getValue().d);
        of_excess_over.setCellValueFactory((TableColumn.CellDataFeatures<HR4_TaxClass, String> param) -> param.getValue().e);
        total_tax.setCellValueFactory((TableColumn.CellDataFeatures<HR4_TaxClass, String> param) -> param.getValue().f);
        //</editor-fold>
        tbl_wh.getColumns().addAll(min,max,basic_amount,additional_rate,of_excess_over,total_tax);
    }
    long DummyCount2 = 0;
    long GlobalCount2 = 0;
    public void populateTable2() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    taxmodel.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount2 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount2 != GlobalCount2) {
                            List rs = taxmodel
                                    .get(
                                            "min","max","basic_amount","additional_rate","of_excess_over","total_tax");
                        AddJobToTable2(rs);
                        GlobalCount2 = DummyCount2;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddJobToTable2(List rs) {
        wh.clear();
        tbl_wh.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String min = String.valueOf(crow.get("min"));
            String max = (String) crow.get("max");
            String basic_amount = (String) crow.get("basic_amount");
            String additional_rate = (String) crow.get("additional_rate");
            String of_excess_over = (String) crow.get("of_excess_over");
            String total_tax = (String) crow.get("total_tax");
            wh.add(new HR4_TaxClass(min,max,basic_amount,additional_rate,of_excess_over,total_tax));
        }
       tbl_wh.setItems(wh);
    }
    public void generateSalaryGrade() {

        tbl_salary.getItems().clear();
        tbl_salary.getColumns().removeAll(tbl_salary.getColumns());
        TableColumn<HR4_SalaryClass, String> salary_grade = new TableColumn<>("Salary Grade");
        TableColumn<HR4_SalaryClass, String> step1 = new TableColumn<>("Step 1");
        TableColumn<HR4_SalaryClass, String> step2 = new TableColumn<>("Step 2");
        TableColumn<HR4_SalaryClass, String> step3 = new TableColumn<>("Step 3");
        TableColumn<HR4_SalaryClass, String> step4 = new TableColumn<>("Step 4");
        TableColumn<HR4_SalaryClass, String> step5 = new TableColumn<>("Step 5");
        TableColumn<HR4_SalaryClass, String> step6 = new TableColumn<>("Step 6");
        TableColumn<HR4_SalaryClass, String> step7 = new TableColumn<>("Step 7");
        TableColumn<HR4_SalaryClass, String> step8 = new TableColumn<>("Step 8");
        
        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        salary_grade.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().a);
        step1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().b);
        step2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().c);
        step3.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().d);
        step4.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().e);
        step5.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().f);
        step6.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().g);
        step7.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().h);
        step8.setCellValueFactory((TableColumn.CellDataFeatures<HR4_SalaryClass, String> param) -> param.getValue().i);
        //</editor-fold>
        tbl_salary.getColumns().addAll(salary_grade,step1,step2,step3,step4,step5,step6,step7,step8);
    }
    long DummyCount3 = 0;
    long GlobalCount3 = 0;
    public void populateSalaryGrade() {
        CompletableFuture.supplyAsync(() -> {

            while (Session.CurrentRoute.equals("hr4cnp")) {
                try {
                    salarymodel.get("CHECKSUM_AGG(BINARY_CHECKSUM(*)) as chk").stream().forEach(e -> {
                        DummyCount3 = Long.parseLong(((HashMap) e).get("chk").toString());
                    });

                    if (DummyCount3 != GlobalCount3){   
                        List rs = salarymodel
                                    .get(
                                            "salary_grade",
                                            "step1",
                                            "step2",
                                            "step3",
                                            "step4",
                                            "step5",
                                            "step6",
                                            "step7",
                                            "step8");
                        AddSalaryGrade(rs);
                        GlobalCount3 = DummyCount3;
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HR4_Core_Human_Capital_ManagementController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            return 0;
        }, Session.SessionThreads);

    }

    public void AddSalaryGrade(List rs) {
        obj3.clear();
        tbl_salary.getItems().clear();
        for (Object row : rs) {
            HashMap crow = (HashMap) row;
            String salary_grade = String.valueOf(crow.get("salary_grade"));
            String step1 = (String) crow.get("step1");
            String step2 = (String) crow.get("step2");
            String step3 = (String) crow.get("step3");
            String step4 = (String) crow.get("step4");
            String step5 = (String) crow.get("step5");
            String step6 = (String) crow.get("step6");
            String step7 = (String) crow.get("step7");
            String step8 = (String) crow.get("step8");
            obj3.add(new HR4_SalaryClass(salary_grade,step1,step2,step3,step4,step5,step6,step7,step8));
        }
            tbl_salary.setItems(obj3);
    }

    @FXML
    private void OtherDeducBtn(MouseEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_OtherDeduc.fxml").getParent());
        md.open();
    }

    @FXML
    private void EditSalaryBtn(ActionEvent event) {
        Modal md = Modal.getInstance(new Form("/FXMLS/HR4/Modals/HR4_Settings.fxml").getParent());
        md.open();
    }

    @FXML
    private void SelectLoans(ActionEvent event) throws IOException {
        String LoansType = loans_cbx.getValue();
        
        if(LoansType=="SSS"){
            tbl_sss.setDisable(false);
            tbl_sss.setVisible(true);
            
            tbl_ph.setDisable(true);
            tbl_ph.setVisible(false);
            
            tbl_wh.setDisable(true);
            tbl_wh.setVisible(false);
            
            tbl_pi.setDisable(true);
            tbl_pi.setVisible(false);
        }
        else if(LoansType =="PhilHealth"){
            tbl_ph.setDisable(false);
            tbl_ph.setVisible(true);
            
            tbl_sss.setDisable(true);
            tbl_sss.setVisible(false);
            
            tbl_wh.setDisable(true);
            tbl_wh.setVisible(false);
            
            tbl_pi.setDisable(true);
            tbl_pi.setVisible(false);
          
        }
        else if(LoansType =="WithHolding Tax"){
            tbl_wh.setDisable(false);
            tbl_wh.setVisible(true);
            
            tbl_ph.setDisable(true);
            tbl_ph.setVisible(false);
            
            tbl_sss.setDisable(true);
            tbl_sss.setVisible(false);
            
            tbl_pi.setDisable(true);
            tbl_pi.setVisible(false);
        }
        else if(LoansType =="Pag Ibig"){
            tbl_pi.setDisable(false);
            tbl_pi.setVisible(true);
            
            tbl_sss.setDisable(true);
            tbl_sss.setVisible(false);
            
            tbl_wh.setDisable(true);
            tbl_wh.setVisible(false);
            
            tbl_ph.setDisable(true);
            tbl_ph.setVisible(false);
        }
    }
}
