/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import FXMLS.HR4.Model.HR4_GenderChartModel;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Piechart();
    }
    HR4_GenderChartModel gcm = new HR4_GenderChartModel();
   public void Piechart() {

        pie.clear();
        List<HashMap> list3x = gcm
                .groupBy("gender")
                .get("gender,COUNT(gender) as Total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("gender").toString(),
                    Double.parseDouble(row.get("Total").toString())));
        });
        pieChart1.setData(pie);
        pieChart1.setLegendSide(Side.RIGHT);
        pieChart1.setStartAngle(90);

   }
}

   
