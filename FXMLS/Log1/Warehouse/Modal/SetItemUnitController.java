
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseItemUnitModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SetItemUnitController implements Initializable {

    @FXML
    private TextField itemUnit_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void close(ActionEvent event) {
        clear();
        Stage stage = (Stage) itemUnit_txt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        String unit = itemUnit_txt.getText();
        
        Boolean flag = unit.isEmpty();
        
        Log1_WarehouseItemUnitModel unitDB = new Log1_WarehouseItemUnitModel();
        
        if(flag){
            AlertMaker.showErrorMessage("","Please enter an item unit.");
        }else{
            try{
                String[][] desiredUnit = {
                    {"itemUnit",itemUnit_txt.getText()}
                };
                if(unitDB.insert(desiredUnit)){
                    AlertMaker.showSimpleAlert("", "Item Unit Added.");
                }
                clear();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void clear() {
        itemUnit_txt.setText("");
    }
    
}
