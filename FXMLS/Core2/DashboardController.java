/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.DesperadoDBConn;
import Model.Core2.CORE1_Book;
import Model.Core2.CORE2_Branch;
import Synapse.Model;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class DashboardController implements Initializable {
    CORE2_Branch branch = new CORE2_Branch();
    CORE1_Book customer = new CORE1_Book();
    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */

    //ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> data1 = FXCollections.observableArrayList();

    private final ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();

    @FXML
    private PieChart PieChart;
    @FXML
    private PieChart PieChart1;
//    private Connection connex;
    @FXML
    private Label branchCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //pieOne();
        pieTwo();
        this.chart();   //tawagin mo yung class para labasan
//        BarChart();
    }

    public void chart() {
        pie.clear();    //name ng observable List
        List<HashMap> list3x = customer //model
                .groupBy("status")
                .get("status, COUNT(status) as total");
        list3x.forEach((row) -> {
            pie.add(new PieChart.Data(row.get("status").toString(), Double.parseDouble(row.get("total").toString())));
        });
        PieChart.setData(pie);
        PieChart.setLegendSide(Side.LEFT);
        PieChart.setStartAngle(90);
    }

//    private void pieOne() {
//        data.add(new PieChart.Data("Analyn", 10));
//        data.add(new PieChart.Data("Mikee", 20));
//        data.add(new PieChart.Data("Princess", 20));
//        data.add(new PieChart.Data("Peter", 10));
//        data.add(new PieChart.Data("Romano", 40));
//        PieChart.setData(data);
//        PieChart.setLegendSide(Side.BOTTOM);
//        PieChart.setStartAngle(90);
//
//    }

    private void pieTwo() {
        data1.add(new PieChart.Data("L", 10));
        data1.add(new PieChart.Data("A", 10));
        data1.add(new PieChart.Data("W", 20));
        data1.add(new PieChart.Data("G", 20));
        data1.add(new PieChart.Data("I", 20));
        data1.add(new PieChart.Data("C", 20));
        PieChart1.setData(data1);
        PieChart1.setLegendSide(Side.BOTTOM);
        PieChart1.setStartAngle(90);
    }

}
