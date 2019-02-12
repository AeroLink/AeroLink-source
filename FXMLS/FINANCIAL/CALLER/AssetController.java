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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gilbert
 */
public class AssetController implements Initializable {

    @FXML
    private JFXTextField astLname;
    @FXML
    private JFXTextField astFname;
    @FXML
    private JFXTextArea astDesc;
    @FXML
    private JFXTextField astAmount;
    private JFXTextField astQuantity;
    private Label astTotal_label;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXComboBox combbox;
    
ObservableList<String> cmbbx_stats = FXCollections.observableArrayList("Collected","Uncollected");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       combbox.setItems(cmbbx_stats);
        save_btn.setOnMouseClicked(e ->savebtn());
    }    
    
    
    public void savebtn(){
             Log_assetsales_model arm = new Log_assetsales_model();
          try
        {
           String[][] b_tbl =
        {
        {"ast_firstname",astFname.getText()},
        {"ast_lastname",astLname.getText()},
        {"ast_description",astDesc.getText()},
        {"ast_amount",astAmount.getText()},
        {"ast_status",combbox.getValue().toString()},
        {"ast_status2","CollectedAsset"},
        {"ast_type","Asset Sales"}
        };           
           
        if(arm.insert(b_tbl)){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Save");
             alert.setContentText("Saved"); 
             alert.showAndWait();
             Stage stage =(Stage) save_btn.getScene().getWindow();
             stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Error");
             alert.setContentText("Saving ERROR"); 
             alert.showAndWait();
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
    }
    
  

    
    
}
