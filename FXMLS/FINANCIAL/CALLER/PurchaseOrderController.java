/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CALLER;

import Model.Financial.Log_assetsales_model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class PurchaseOrderController implements Initializable {

    @FXML
    private JFXTextField lname_txt;
    @FXML
    private JFXTextField fname_txt;
    @FXML
    private JFXTextArea des_txt;
    @FXML
    private JFXTextField amount_txt;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXComboBox stats;
    
ObservableList<String> cmbbx_stats = FXCollections.observableArrayList("Collected","Uncollected");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stats.setItems(cmbbx_stats);
        save_btn.setOnMouseClicked(e -> savePO());
        
    }    
    
    
    Log_assetsales_model arm = new Log_assetsales_model();
    
    public void savePO(){
        try{
             String[][] insertPO =
            {
                    {"ast_lastname",lname_txt.getText()},
                    {"ast_firstname",fname_txt.getText()},
                    {"ast_description",des_txt.getText()},
                    {"ast_amount",amount_txt.getText()},
                    {"ast_type","Collection Sales"},
                    {"ast_status2","Collected"},
                    {"ast_status",stats.getValue().toString()}
            };
             arm.insert(insertPO);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Inserted");
             alert.setContentText("Saved"); 
             alert.showAndWait();
             Stage stage =(Stage) save_btn.getScene().getWindow();
             stage.close();
             
        }catch (Exception e){
            System.out.print(e);
        }
        
        
    }
}
