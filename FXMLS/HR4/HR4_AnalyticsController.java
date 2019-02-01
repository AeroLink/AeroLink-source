/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.Model.HR4_InfoChartModel;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Piechart1();
        //this.Piechart2();
        //this.Piechart3();
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

    }/*
    public void Piechart2() {

        pie.clear();
        List<HashMap> list3x = gcm
                .groupBy("gender")
                .get("gender,COUNT(gender) as Total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("gender").toString(),
                    Double.parseDouble(row.get("Total").toString())));
        });
        pieChart2.setData(pie);
        pieChart2.setLegendSide(Side.RIGHT);
        pieChart2.setStartAngle(90);

    }
    public void Piechart3() {

        pie.clear();
        List<HashMap> list3x = gcm
                .groupBy("gender")
                .get("gender,COUNT(gender) as Total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("gender").toString(),
                    Double.parseDouble(row.get("Total").toString())));
        });
        pieChart3.setData(pie);
        pieChart3.setLegendSide(Side.BOTTOM);
        pieChart3.setStartAngle(90);

    }*/
}

   
