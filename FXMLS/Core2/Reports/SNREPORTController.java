/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Reports;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class SNREPORTController implements Initializable {

    @FXML
    private TableView<?> tblPrint;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handlePrint(ActionEvent event) {
    }

//    private void printInvoice() {
//        String sourceFile = "C:\\AeroLink\\Staging\\src\\FXMLS\\Core2\\Reports\\ServiceNetworkReport.jrxml";
//        try {
//            JasperReport jr = JasperCompileManager.compileReport(sourceFile);
//            HashMap<String, Object> para = new HashMap<>();
//            para.put("branch_code", "country");
////            
//            ArrayList<branch> brch = new ArrayList<>();
//
//            for (OrderList   : orderData) {
//                brch.add(new branch(getBcountry(), getBcity()));
//            }
//            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(brch);
//            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
//            JasperViewer.viewReport(jp);
//
//        } catch (JRException ex) {
//            Logger.getLogger(SNREPORTController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
