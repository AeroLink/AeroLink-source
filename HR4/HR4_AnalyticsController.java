/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_AnalyticsController implements Initializable {
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    @FXML
    private PieChart pieChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    /*pieChartData.clear();
    try {
        ResultSet rs = connector.conn.prepareStatement("SELECT * FROM aerolink.tbl_").executeQuery();
        Double totalPercentage = null;
        while (rs.next()) {
            int totalSumPie;
            totalPercentage = rs.getInt("value") / totalSumPie * 100
            pieChartData.add(new PieChart.Data(rs.getString("name") + " " + new DecimalFormat("#,###.0").format(totalPercentage) + "%", rs.getInt("value")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(HR4.AnalyticsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        pieChart.setData(pieChartData);

   } */}
}
