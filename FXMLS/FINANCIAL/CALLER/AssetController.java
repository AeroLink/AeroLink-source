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
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    @FXML
    private JFXTextField astQuantity;
    @FXML
    private Label astTotal_label;
    @FXML
    private JFXButton save_btn;
    @FXML
    private JFXButton compute_btn;
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
        {"ast_quantity",astQuantity.getText()},
        {"ast_total_amount",astTotal_label.getText()},
        {"ast_status",combbox.getValue().toString()},
        {"ast_type","Asset Sales"}
        };           
           
        if(arm.insert(b_tbl)){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Save");
             alert.setContentText("Saved"); 
             alert.showAndWait();
             save_btn.setDisable(true);
             Stage stage =(Stage) astTotal_label.getScene().getWindow();
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
    
  

    @FXML
    private void ComputeAmount(ActionEvent event) {
        int quantity = Integer.parseInt(astQuantity.getText());
        int amount = Integer.parseInt(astAmount.getText());
            String total = String.valueOf(quantity * amount);
            astTotal_label.setText(total);
            
            
             save_btn.setDisable(false);
             
            
    }
    
    
}
