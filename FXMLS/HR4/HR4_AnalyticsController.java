/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;


import FXMLS.HR4.ClassFiles.HR4_EmployeeClass;
import FXMLS.HR4.ClassFiles.HR4_FemaleClass;
import FXMLS.HR4.ClassFiles.HR4_MaleClass;
import FXMLS.HR4.Model.HR4_EmployeeInfo;
import FXMLS.HR4.Model.HR4_InfoChartModel;
import FXMLS.HR4.Model.HR4_EmployeesProfileModel;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_AnalyticsController implements Initializable {
    
    @FXML
    private PieChart pieChart1;
    private final ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();
    @FXML
    private PieChart pieChart2;
    @FXML
    private PieChart pieChart3;
    @FXML
    private Label employee_label;
    @FXML
    private Label male_label;
    @FXML
    private Label female_label;
    @FXML
    private TableView<HR4_EmployeeClass> tbl_countemployee;
    @FXML
    private TableView<HR4_MaleClass> male_tbl;
    @FXML
    private TableView<HR4_FemaleClass> female_tbl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //employee
        addtableEmp();
        loadTableEMP();
        
        //male
        addtablegender();
        loadTablegender();
        
        //female
        addtableFgender();
        loadTableFgender();
    }
    HR4_InfoChartModel gcm = new HR4_InfoChartModel();
    
    public void Piechart1() {

        pie.clear();
        List<HashMap> list3x = gcm
                .groupBy("gender")
                .get("gender,COUNT(gender) as Total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("gender").toString(),
                    Double.parseDouble(row.get("Total").toString())));
        });
        pieChart1.setData(pie);
        pieChart1.setLegendSide(Side.BOTTOM);
        pieChart1.setStartAngle(90);

    }
      ObservableList<HR4_EmployeeClass> ec = FXCollections.observableArrayList();
    
    public void addtableEmp(){
          tbl_countemployee.getItems().clear();
          tbl_countemployee.getColumns().removeAll(tbl_countemployee.getColumns());
        
        TableColumn<HR4_EmployeeClass, String> e = new TableColumn<>("Employee");
        e.setCellValueFactory((TableColumn.CellDataFeatures<HR4_EmployeeClass, String> param) -> param.getValue().emp);
       
        tbl_countemployee.getColumns().addAll(e);
    }
    public void loadTableEMP(){
        HR4_EmployeeInfo cm = new HR4_EmployeeInfo();
          List rs = cm.get();
                     emp(rs);
                     
         int a = tbl_countemployee.getItems().size();
         employee_label.setText(String.valueOf(a));
        
        
    }
    private int emp(List employee){
        tbl_countemployee.getItems().clear();
        try {
              for(Object d : employee)
            {
              HashMap hm = (HashMap) d;   //exquisite casting
             ec.add(new HR4_EmployeeClass(
                            String.valueOf(hm.get("id"))
                ));   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        tbl_countemployee.setItems(ec);
        return 0;
        
    }
    
    
    
    //Male
    public void addtablegender(){
         male_tbl.getItems().clear();
         male_tbl.getColumns().removeAll(male_tbl.getColumns());
        
        TableColumn<HR4_MaleClass, String> e = new TableColumn<>("Male");
        e.setCellValueFactory((TableColumn.CellDataFeatures<HR4_MaleClass, String> param) -> param.getValue().gen);
       
        male_tbl.getColumns().addAll(e);
    }
    
    public void loadTablegender(){
        HR4_EmployeesProfileModel em = new HR4_EmployeesProfileModel();
         List g = em.where(new Object[][]{
             {"gender","=","Male"}
         }).get("gender");
                     gen(g);
                     
         int a = male_tbl.getItems().size();
         male_label.setText(String.valueOf(a));
    }
     ObservableList<HR4_MaleClass> gg = FXCollections.observableArrayList();
    
    private int gen(List g){
         male_tbl.getItems().clear();
        try {
              for(Object d : g)
            {
              HashMap hm = (HashMap) d;   //exquisite casting
             gg.add(new HR4_MaleClass(
                            String.valueOf(hm.get("gender"))
                ));   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        male_tbl.setItems(gg);
        return 0;
        
    }
    
    
    //female
    ObservableList<HR4_FemaleClass> gens1 = FXCollections.observableArrayList();
    public void addtableFgender(){
         female_tbl.getItems().clear();
         female_tbl.getColumns().removeAll(female_tbl.getColumns());
        
        TableColumn<HR4_FemaleClass, String> e = new TableColumn<>("female");
        e.setCellValueFactory((TableColumn.CellDataFeatures<HR4_FemaleClass, String> param) -> param.getValue().gen1);
       
        female_tbl.getColumns().addAll(e);
    }
    
     public void loadTableFgender(){
        HR4_EmployeesProfileModel em1 = new HR4_EmployeesProfileModel();
         List g = em1.where(new Object[][]{
             {"gender","=","Female"}
         }).get("gender");
                     gen1(g);
                     
         int a = female_tbl.getItems().size();
         female_label.setText(String.valueOf(a));
    }
    
    private int gen1(List g1){
         female_tbl.getItems().clear();
        try {
              for(Object d : g1)
            {
              HashMap hm = (HashMap) d;   //exquisite casting
             gens1.add(new HR4_FemaleClass(
                            String.valueOf(hm.get("gender"))
                ));   
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        female_tbl.setItems(gens1);
        return 0;
        
    }
    
     
     
     
}

   
