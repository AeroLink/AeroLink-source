/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.USM;

import FXMLS.USM.Controllers.IUsers;
import FXMLS.USM.Controllers.SetPermissionUSMController;
import Model.Users;
import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author Lei
 */
public class ManageUsersController implements Initializable {

    @FXML
    public TableView<IUsers> tblUsers;

    
    ObservableList<IUsers> obj = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnNewUser;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GenerateTable();
        tblUsers.getSelectionModel().selectFirst();
    } 
    
    public void GenerateTable(){
        
        //remove columns and items
        tblUsers.getColumns().removeAll(tblUsers.getColumns());
        tblUsers.getItems().clear();
        
        
        //generate table Columns
        TableColumn<IUsers, String> id = new TableColumn<>("id");
        TableColumn<IUsers, String> username = new TableColumn<>("Username");
        TableColumn<IUsers, String> created_at = new TableColumn<>("created_at");
        TableColumn<IUsers, String> updated_at = new TableColumn<>("updated_at");
     
        //Cell Buttons
        TableColumn setPerms = new TableColumn<>("Pemission Column");
        setPerms.setSortable(false);
        
        setPerms.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<IUsers, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<IUsers, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
  
        setPerms.setCellFactory(new Callback<TableColumn<IUsers, Boolean>, TableCell<IUsers, Boolean>>() {
            @Override
            public TableCell<IUsers, Boolean> call(TableColumn<IUsers, Boolean> param) {
                return new Synapse.Components.ButtonInCell<IUsers>().create("Permissions", (MouseEvent e) -> {
                    SetPermissionUSMController.UserID = Integer.parseInt(tblUsers.getSelectionModel().getSelectedItem().id.getValue());
                    Modal md = Modal.getInstance(new Form("/FXMLS/USM/Controllers/SetPermissionUSM.fxml").getParent());
                    md.open();
                });
            }
        });
        
        id.setCellValueFactory((TableColumn.CellDataFeatures<IUsers, String> param) -> param.getValue().id);
        username.setCellValueFactory((TableColumn.CellDataFeatures<IUsers, String> param) -> param.getValue().username);
        created_at.setCellValueFactory( (TableColumn.CellDataFeatures<IUsers, String> param) -> param.getValue().created_at);
        updated_at.setCellValueFactory((TableColumn.CellDataFeatures<IUsers, String> param) -> param.getValue().update_at);
    
        
        //Select Query
        Users u = new Users();
        List rows = u.get();
        
        for(Object m : rows) {
            HashMap map = (HashMap) m;
            
            this.obj.add(new IUsers(String.valueOf(map.get("id")), String.valueOf(map.get("username")), String.valueOf(map.get("created_at")), String.valueOf(map.get("created_at"))));
        }

        tblUsers.setItems(this.obj);
        tblUsers.getColumns().addAll(id, username, created_at, updated_at, setPerms);
    
    }

    @FXML
    private void OpenModalNewUser(ActionEvent event) {
        
        Modal md = Modal.getInstance(new Form("/FXMLS/USM/Modal/HR4_NewUser.fxml").getParent());
        md.open();
    }
     
    
}
