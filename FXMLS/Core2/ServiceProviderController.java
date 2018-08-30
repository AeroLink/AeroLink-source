/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2;

import FXMLS.Core2.icons.icon;
import Model.C2_Serviceprovider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jpeg
 */
public class ServiceProviderController implements Initializable {

    @FXML private JFXTextField pcompany;
    @FXML private JFXTextField pcontact;
    @FXML private JFXTextField pemail;
    @FXML private JFXTextField pusername;
    @FXML private JFXCheckBox pdoor;
    @FXML private JFXCheckBox ppickup;
    @FXML private JFXCheckBox pexpress;
    @FXML private JFXTextField pcountry;
    @FXML private JFXTextField pregion;
    @FXML private JFXTextField pother;
    @FXML private JFXTextField phousenumber;
    @FXML private JFXTextField pstreet;
    @FXML private JFXTextField pbarangay;
    @FXML private JFXTextField pcity;
    @FXML private JFXTextField pprovince;
    @FXML private JFXTextField pzip;
    @FXML private JFXPasswordField ppassword;
    @FXML private JFXButton padd;
    @FXML private JFXButton btn_registered;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        padd.setOnMouseClicked(e -> Save());
    }    
 
    // INSERT QUERY
    public void Save(){
      C2_Serviceprovider sp = new C2_Serviceprovider();
      // ginawa ko to para mag popup yung else sa loob ng try
      String company = pcompany.getText();
      String contact = pcontact.getText();
      String email = pemail.getText();
      String username = pusername.getText();
      String door = pdoor.getText();
      String pickup = ppickup.getText();
      String express = pexpress.getText();
      String location = pcountry.getText();
      String region = pregion.getText();
      String other = pother.getText();
      String housenumber = phousenumber.getText();
      String street = pstreet.getText();
      String barangay = pbarangay.getText();
      String city = pcity.getText();
      String province = pprovince.getText();
      String zip = pzip.getText();
      String password = ppassword.getText();
      
        try{
            String[][] sp_data = {
                {"company_name" , pcompany.getText()},
                {"contact_number" , pcontact.getText()},
                {"house_number" , phousenumber.getText()},
                {"street" , pstreet.getText()},
                {"barangay" , pbarangay.getText()},
                {"city_monicipality" , pcity.getText()},
                {"zipcode" , pzip.getText()},
                {"email" , pemail.getText()},
                {"username" , pusername.getText()},
                {"password" , ppassword.getText()},
                {"doortodoor" , pdoor.getText()},
                {"pickup" , ppickup.getText()},
                {"express" , pexpress.getText()},
                {"other" , pother.getText()},
                {"region" , pregion.getText()},
                {"country" , pcountry.getText()} 
            }; 
                // Data Save kapag kumpleto yung nilagay sa mga JFXTextField
                if(sp.insert(sp_data)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Data Saved");
                    alert.showAndWait();
                }else{
                    // Not Inserted kapag hindi kumpleto oh walang nilagay sa mga JFXTextField
                    if((company.isEmpty() || contact.isEmpty() || email.isEmpty() 
                        || username.isEmpty() || location.isEmpty() || region.isEmpty() 
                        || other.isEmpty() || housenumber.isEmpty() || street.isEmpty() 
                        || barangay.isEmpty() || city.isEmpty() || province.isEmpty()
                        || zip.isEmpty() || password.isEmpty())){
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

    @FXML
    private void listprovider(ActionEvent event) {
        loadWindow("/FXMLS/Core2/crm/SPtbList.fxml","List of Providers");
    }
    
        void loadWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            icon.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(CustomerRelationshipManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
