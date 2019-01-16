/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import Model.Core2.CORE2_ProvRegistration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SPviewRegistrationController implements Initializable {

    @FXML
    private JFXButton SPviewR;
    @FXML
    private JFXButton SPviewM;
    @FXML
    private JFXButton SPback1;
    @FXML
    private AnchorPane SProotPane;
    @FXML
    private JFXTextField proname;
    @FXML
    private JFXTextField proaddress;
    @FXML
    private JFXTextField proemail;
    @FXML
    private JFXTextField prowebsite;
    @FXML
    private JFXTextField procp;
    @FXML
    private JFXTextField protel;
    @FXML
    private JFXTextField fn;
    @FXML
    private JFXTextField position;
    @FXML
    private JFXTextField peremail;
    @FXML
    private JFXTextField mn;
    @FXML
    private JFXTextField ln;
    @FXML
    private JFXButton submit;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        submit.setOnMouseClicked(e -> Insert());
    }    

    @FXML
    private void back1(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ServiceProvider.fxml"));
        SProotPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void viewR(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/Change/SPviewReport.fxml"));
        SProotPane.getChildren().setAll(pane);
    }

    @FXML
    private void viewM(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ServiceProvider.fxml"));
        SProotPane.getChildren().setAll(pane);
    }
 
    
    // INSERT 
    public void Insert(){
        CORE2_ProvRegistration pr = new CORE2_ProvRegistration();
        // ginawa ko to para mag popup yung else sa loob ng try
      String pname = proname.getText();
      String paddress = proaddress.getText();
      String email = proemail.getText();
      String web = prowebsite.getText();
      String cp = procp.getText();
      String tel = protel.getText();
      String f = fn.getText();
      String m = mn.getText();
      String l = ln.getText();
      String pos = position.getText();
      String premail = peremail.getText();
        
        try{
            String[][] prov_data={
                {"pname", proname.getText()}, {"paddress", proaddress.getText()}, {"email", proemail.getText()},
                {"website", prowebsite.getText()}, {"cp_no", procp.getText()}, {"tel_no", protel.getText()},
                {"fname", fn.getText()}, {"mname", mn.getText()}, {"lname", ln.getText()}, {"position", position.getText()},
                {"pemail", peremail.getText()},
            };
                // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
                if(pr.insert(prov_data)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Data Saved");
                    alert.showAndWait();
                    
                    proname.setText(""); proaddress.setText(""); proemail.setText(""); prowebsite.setText("");
                    procp.setText(""); protel.setText(""); fn.setText(""); ln.setText(""); mn.setText("");
                    position.setText(""); peremail.setText("");
                }else{
                    // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                    if((pname.isEmpty() || paddress.isEmpty() || email.isEmpty() || web.isEmpty() || cp.isEmpty() || tel.isEmpty() || f.isEmpty() || m.isEmpty() || l.isEmpty() || pos.isEmpty() || premail.isEmpty())){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Not Inserted");
                        alert.showAndWait();
                    }
                }
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
