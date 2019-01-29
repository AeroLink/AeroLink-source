/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Audit_ManagementController implements Initializable {

    @FXML
    private JFXButton btn_leadauditor;
    @FXML
    private JFXButton btn_auditmember;
    @FXML
    private TableView<?> tblar;
    @FXML
    private TableColumn<?, ?> columnar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_la(MouseEvent event) throws IOException {
        
         Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent parent = loader.load(getClass().getResource("/FXMLS/Log2/am/modals/Log2_Audit_Management_LeadAuditor.fxml"));

        Scene scene = new Scene(parent);

        stage.setFullScreen(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();
        
    }

    @FXML
    private void btn_am(MouseEvent event) throws IOException {
        
        
         Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent parent = loader.load(getClass().getResource("/FXMLS/Log2/am/modals/Log2_Audit_ManagementAuditMember.fxml"));

        Scene scene = new Scene(parent);

        stage.setFullScreen(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();
    }
    
}
