/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1;

import FXMLS.HR1.ClassFiles.TableModel_NewHire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Lei
 */
public class HR1_New_Hire_On_BoardController implements Initializable {

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private TableView<TableModel_NewHire> tblNewHire;
    @FXML
    private ContextMenu contextMenuJobs;
    @FXML
    private MenuItem menuPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void GenerateTable(){
        
        //this.tblNewHire.new
        
        TableColumn<TableModel_NewHire, String> EmployeeCode = new TableColumn<>("Employee Code");
        TableColumn<TableModel_NewHire, String> Name = new TableColumn<>("Employee Name");
        TableColumn<TableModel_NewHire, String> Email = new TableColumn<>("Email");
        TableColumn<TableModel_NewHire, String> Contact = new TableColumn<>("Contact");
        
        
    }
    
}
