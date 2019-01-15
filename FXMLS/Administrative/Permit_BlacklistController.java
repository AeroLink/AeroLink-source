/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Grey_Nich
 */
public class Permit_BlacklistController implements Initializable {

    @FXML
    private JFXButton edit1111;
    @FXML
    private ListView<String> listpermit;
    @FXML
    private JFXButton importpermit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) {
    }
    
    public void importp(){
        
    FileChooser f = new FileChooser();
        f.setInitialDirectory(new File("C:\\Users"));
        f.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.PDF"));
        List<File> selectedFiles = f.showOpenMultipleDialog(null);
         if(selectedFiles !=null){
            for(int i=0;i<selectedFiles.size();i++){
            listpermit.getItems().add(selectedFiles.get(i).getAbsolutePath());}
            }
        else{System.out.print("No File Chosen");}


    }


    }
    
    
    
    

