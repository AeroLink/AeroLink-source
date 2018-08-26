/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIALS;

import Synapse.Form;
import Synapse.Route;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JaeJae
 */
public class FINANCIAL_GENERAL_LEDGERController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void openForm(Stage stage) throws IOException{
  
    }

    @FXML
    private void clicke_coa(ActionEvent event) throws IOException {
     // new Form(Route.routes.get("id_coa").toString()).open(StageStyle.DECORATED, false);
      try{
          FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXMLS/FINANCIALS/GENERAL_LEDGER/ChartOfAccounts.fxml"));
          Parent root1 = (Parent) loader.load();
          Stage stage = new Stage();
          stage.setScene(new Scene(root1));
          stage.show();
      }catch(Exception e){
          e.printStackTrace();
      }
    }
    
    
}
