/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Onodera-Chan
 */
public class AddcategoryController implements Initializable {

    
    private Connection con = DBconnection.con();
    private PreparedStatement pst;
    private ResultSet rs;
    
    @FXML
    private TextField categorytxt;
    @FXML
    private FontAwesomeIconView btnexit;
    @FXML
    private FontAwesomeIconView savebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void insert(){
        try{
            String query = "insert into aerolink.admin_document_category values(?)";
            pst = con.prepareStatement(query);
            pst.setString(1, categorytxt.getText());
            pst.execute();
            
            AlertBox.display("Alert", "New Category Added");
            AlertBox.iconclose(savebtn);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    
    }
    
    @FXML
    public void exit(){
        AlertBox.iconclose(btnexit);
    }
    
}
