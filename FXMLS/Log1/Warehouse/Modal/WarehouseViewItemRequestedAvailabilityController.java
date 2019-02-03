
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class WarehouseViewItemRequestedAvailabilityController implements Initializable {

    @FXML
    private Label itemName_txt;
    @FXML
    private Label location_txt;
    @FXML
    private Label stock_txt;
    @FXML
    private Label criticallvl_txt;
    @FXML
    private JFXButton close_btn;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        close_btn.setOnMouseClicked(e->handleCloseAction());
    }    
    
    public void inflateUI(Log1_WarehouseItemsClassfiles item){
        itemName_txt.setText(item.getItemName());
        location_txt.setText(item.getItemLocation());
        stock_txt.setText(item.getItemStock());
        criticallvl_txt.setText(item.getItemCriticalLevel());
    }
    private void handleCloseAction() {
        Stage stage = (Stage) criticallvl_txt.getScene().getWindow();
        stage.close();
    }
}
