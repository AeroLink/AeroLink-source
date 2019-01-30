/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class HR4_AnalyticsController implements Initializable {
    Button bt = new Button();
    ChoiceBox<String> cb = new ChoiceBox<>();
    @FXML
    private BarChart<?, ?> emp_chart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private BarChart<?, ?> peg_barchart;
    @FXML
    private BarChart<?, ?> tag_barchart;
    @FXML
    private BarChart<?, ?> pegg_barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*cb.getItems().add("Gender");
        cb.getItems().add("Employees");
        cb.getItems().add("Trainees");
        cb.getItems().add("Applicants");
        cb.getItems().addAll("Passed","Failes","Ongoing");
        
        cb.setValue("Gender");
        bt.setOnAction(e -> getChoice(cb));
        */
        
        XYChart.Series set1 = new XYChart.Series<>();
        XYChart.Series set2 = new XYChart.Series<>();
        XYChart.Series set3 = new XYChart.Series<>();
        XYChart.Series set4 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Male",3));
        set1.getData().add(new XYChart.Data("Female",2));
        set2.getData().add(new XYChart.Data("Approved",5));
        set2.getData().add(new XYChart.Data("Rejected",2));
        set2.getData().add(new XYChart.Data("Complete",5));
        set2.getData().add(new XYChart.Data("On Process",10));
        set3.getData().add(new XYChart.Data("Passed",5));
        set3.getData().add(new XYChart.Data("Failed",5));
        set4.getData().add(new XYChart.Data("Male",20));
        set4.getData().add(new XYChart.Data("Female",15));
        emp_chart.getData().addAll(set1);
        peg_barchart.getData().addAll(set2);
        pegg_barchart.getData().addAll(set3);
        tag_barchart.getData().addAll(set4);
        
    }
    /*public void getChoice(ChoiceBox<String> cb){
        String type = cb.getValue();
        System.out.println(type);*/
        
    
}
