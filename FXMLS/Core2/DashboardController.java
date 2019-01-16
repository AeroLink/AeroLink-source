/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.DesperadoDBConn;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    /* DECLARATION START */
    int Global_Count = 0;
    /* DECLARATION END */
    
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> data1 = FXCollections.observableArrayList();
//    Connection conn = null;
//    PreparedStatement pst = null;
//    ResultSet rs = null;

    @FXML
    private PieChart PieChart;
    @FXML
    private PieChart PieChart1;
    @FXML
    private BarChart<String, Double> BarChart;
    @FXML
    private BarChart<?, ?> BarChart2;
//    private Connection connex;
    @FXML
    private Label branchCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pieOne();
        pieTwo();
//        BarChart();
    }

    private void pieOne() {
        data.add(new PieChart.Data("Analyn", 10));
        data.add(new PieChart.Data("Mikee", 20));
        data.add(new PieChart.Data("Princess", 20));
        data.add(new PieChart.Data("Peter", 10));
        data.add(new PieChart.Data("Romano", 40));
        PieChart.setData(data);
        PieChart.setLegendSide(Side.BOTTOM);
        PieChart.setStartAngle(90);

    }

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

//    private void BarChart() {
//        String query = "select branch_code, branch_location from aerolink.tbl_core2_add_branch";
//        XYChart.Series<String, Double> series = new XYChart.Series<>();
//
//        try {
//            connex = connectDB();
//            pst = conn.prepareStatement(query);
//            rs = pst.executeQuery();
//            
//            
//            
//            ResultSet rs = connex.createStatement().executeQuery(query);
//            while (rs.next()) {
//                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
//            }
//            BarChart.getData().add(series);
//
//        } catch (Exception e) {
//        }
//    }
//
//    private Connection connectDB() throws ClassNotFoundException {
//        Connection conn = null;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AeroLink", "core", "core");
//
//            if (conn != null) {
//                System.out.println("Database Successfully connected");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }

}
