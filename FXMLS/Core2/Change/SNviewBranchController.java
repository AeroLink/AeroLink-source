/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Change;

import Synapse.Components.Modal.Modal;
import Synapse.Form;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JPEG
 */
public class SNviewBranchController implements Initializable {

    @FXML
    private AnchorPane SNrootPane1;
    @FXML
    private JFXButton SNback1;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuVB;
    @FXML
    private MenuItem menuVP;
    @FXML
    private MenuItem menuVT;
    @FXML
    private TextField srSearch;
    @FXML
    private TableView<?> tblBranch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back1(ActionEvent event)throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXMLS/Core2/ServiceNetwork.fxml"));
        SNrootPane1.getChildren().setAll(pane);
    }
    
    @FXML
    public void codeModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewCode.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewCodeController.modalOpen = false;
        });
    }

    @FXML
    public void personnelModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewPersonnel.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewPersonnelController.modalOpen = false;
        });
    }

    @FXML
    public void transfortationModal() {
        Modal md = Modal.getInstance(new Form("/FXMLS/Core2/Modals/SNviewTransfortation.fxml").getParent());
        md.open();
        md.getF().getStage().setOnCloseRequest(action -> {
            FXMLS.Core2.Modals.SNviewTransfortationController.modalOpen = false;
        });
    }

    
}
