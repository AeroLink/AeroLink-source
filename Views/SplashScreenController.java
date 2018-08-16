/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import Synapse.Module;
import Synapse.Route;
import Synapse.Session;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class SplashScreenController implements Initializable {

    Controllers.SplashScreen cc = new Controllers.SplashScreen();
    
    public Boolean passed = false;
    public Thread th;
    @FXML
    private Label loadingPercentage;
    @FXML
    private Label loadingStatus;
    
    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Boolean getPassed() {
        return passed;
    }
    
    
    @FXML
    private ProgressBar LoadingBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tick();
        
    }    
    
    //Async Block
    public void tick(){
       th = new Thread(() -> {
           Double prog  = .01;
           while(LoadingBar.progressProperty().floatValue() != 1f ) {
               
               try {
                   Thread.sleep(50);
               } catch (InterruptedException ex) {
                   
               }
               
               initRoutingTable();
               initSession();
               initConnection();
               LoadingBar.setProgress(prog += .01);
               ControlPercent(String.valueOf((Math.round(prog * 100))) + " %");
               
           }
       
           PassedLogin();
       });
       
       th.start();
       
    }
    
    public void PassedLogin(){
       Platform.runLater(() -> {
           Synapse.Form.close(LoadingBar);
           Synapse.Form frm = new Synapse.Form("/FXMLS/Login.fxml");
           frm.open(StageStyle.UNDECORATED, false);
       });
    }

    public void ControlText(String text){
        Platform.runLater(() -> {
            this.loadingStatus.setText(text);
        });
    }
    
    public void ControlPercent(String text){
        Platform.runLater(() -> {
            this.loadingPercentage.setText(text);
        });
    }
    
    public void initRoutingTable(){
        if(LoadingBar.progressProperty().floatValue() == 0.2f ) {
            ControlText("Loading Routes");
            new Module(new Route()).init();
        }
    }
    
    public void initSession(){
        if(LoadingBar.progressProperty().floatValue() == 0.4f ) {
           ControlText("Loading Session");
           new Module(new Session()).init();
        }
    }

    public void initConnection(){
        if(LoadingBar.progressProperty().floatValue() == 0.5f ) {
            ControlText("Connecting to Server");
            if (!cc.checkCon()){
                JOptionPane.showMessageDialog(null, "Error while connecting to database");
                System.exit(0);
            }
            ControlText("Connected to Server");
        }
    }
}
