
package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_WarehouseItemLocationModel;
import Model.Log1.Log1_WarehouseItemUnitModel;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SetItemLocationController implements Initializable {

    @FXML
    private ComboBox unit_combx;
    @FXML
    private TextField maxCapacity_txt;
    @FXML
    private TextField locCode_txt;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItemUnitToCombox();
    }    

    private void clear() {
        unit_combx.setValue("");
        maxCapacity_txt.setText("");
        locCode_txt.setText("");
    }
    
    @FXML
    private void close(ActionEvent event) {
        clear();
        Stage stage = (Stage) locCode_txt.getScene().getWindow();
        stage.close();
    }
    
    public void loadItemUnitToCombox(){
        Log1_WarehouseItemUnitModel unitDB = new Log1_WarehouseItemUnitModel();
        
        List itemUnit = unitDB.get();

        itemUnit.stream().forEach(row -> {
            HashMap hash = (HashMap) row;
            unit_combx.getItems().add(String.valueOf(hash.get("itemUnit")));
        });
    }
    
    @FXML
    private void add(ActionEvent event) {
        String capacity = maxCapacity_txt.getText();
        String locCode = locCode_txt.getText();
        
        Boolean flag = capacity.isEmpty() || locCode.isEmpty();
        
        Log1_WarehouseItemLocationModel locDB = new Log1_WarehouseItemLocationModel();
        
        if(flag){
            AlertMaker.showErrorMessage("","Please fill up the details.");
        }else{
            try{
                String [][] location ={
                    {"locationCode",locCode},
                    {"itemUnit",unit_combx.getValue().toString()},
                    {"limit", capacity},
                };
                if(locDB.insert(location)){
                    AlertMaker.showSimpleAlert("", "location added");
                    return;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    
}
