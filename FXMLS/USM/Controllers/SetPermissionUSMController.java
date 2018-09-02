/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.USM.Controllers;

import Model.UserPermissions;
import Synapse.Model;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class SetPermissionUSMController implements Initializable {

    @FXML
    private TableColumn<IPermissions, String> tbc_permissions;
    @FXML
    private TableView<IPermissions> tbl_permissions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.generateTable();
    }
   
    public static int UserID = 0;
   
    public void generateTable(){
        tbc_permissions.setCellValueFactory((TableColumn.CellDataFeatures<IPermissions, String> param) -> param.getValue().permissions);
        
        UserPermissions up = new UserPermissions();
        
        List listPermissions = up
                               .join(Model.JOIN.INNER, "aerolink.tbl_users", "id", "=", "user_id")
                               .join(Model.JOIN.INNER, "aerolink.tbl_permissions", "id", "=", "permission_id")
                               .where(new Object[][] {
                                   {"user_id", "=", UserID}
                               })
                               .get("aerolink.tbl_permissions.permission as UserPermission");
        
        System.out.println(Arrays.asList(listPermissions.toArray()));
        ObservableList objv = FXCollections.observableArrayList();
        
        for(Object obj : listPermissions) {
            HashMap hm = (HashMap) obj;
            
            objv.add(new IPermissions(String.valueOf(hm.get("UserPermission"))));
        }
        
        tbl_permissions.getItems().clear();
        tbl_permissions.setItems(objv);
        
    }

    
    
}
