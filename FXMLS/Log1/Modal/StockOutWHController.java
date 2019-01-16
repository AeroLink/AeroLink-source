package FXMLS.Log1.Modal;

import FXMLS.Log1.ClassFiles.Log1_fullInventoryList;
import FXMLS.Log1.util.AlertMaker;
import Model.Log1.Log1_ActivityLogforWarehouseModel;
import Model.Log1.Log1_WarehouseItems;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StockOutWHController implements Initializable {

    @FXML
    private Label itemDescript_txt;
    @FXML
    private Label stock_txt;
    @FXML
    private Label status_txt;
    @FXML
    private Label itemLoc_txt;
    @FXML
    private JFXTextField secretStatus_txt;
    @FXML
    private JFXTextField StockQuantityPlusEnterAmount_txt;
    @FXML
    private JFXTextField CriticalQuantity_txt;
    @FXML
    private JFXTextField ItemID_txt;
    @FXML
    private Button Takeout_btn;
    @FXML
    private TextField minusx_txt;
    @FXML
    private JFXTextField showInMainWindow_txt;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Takeout_btn.setOnMouseClicked(e -> StockOut());
    }    

    public void inflateUI(Log1_fullInventoryList selectedForAddStock) {
        itemDescript_txt.setText(selectedForAddStock.getItemDescription());
        stock_txt.setText(selectedForAddStock.getStockQuantity());
        status_txt.setText(selectedForAddStock.getStatus());
        itemLoc_txt.setText(selectedForAddStock.getItemLocation());
        CriticalQuantity_txt.setText(selectedForAddStock.getCriticalQuantity());
        ItemID_txt.setText(selectedForAddStock.getItemID());
    }
    public void StockOut(){
        String input = minusx_txt.getText();
        
        Boolean flag = input.isEmpty();
        
        if(!flag){
            int x=Integer.parseInt(stock_txt.getText());
            int y=Integer.parseInt(minusx_txt.getText());
            
        String answer=String.valueOf(x-y);
        StockQuantityPlusEnterAmount_txt.setText(answer);
        int w=Integer.parseInt(StockQuantityPlusEnterAmount_txt.getText());
        int z=Integer.parseInt(CriticalQuantity_txt.getText());
            if(w <= z){
                secretStatus_txt.setText("Need to Reorder!");
                showInMainWindow_txt.setText("yes");
            }else{
                secretStatus_txt.setText("Need to Reorder!");
                showInMainWindow_txt.setText("yes");
            }
        
        Log1_ActivityLogforWarehouseModel coa = new Log1_ActivityLogforWarehouseModel();
        Log1_WarehouseItems coa1 = new Log1_WarehouseItems();
        try{coa.insert(new String [][]{
            {"ActivityItemName",itemDescript_txt.getText()},
            {"ActivityUser", "rb"},
            {"ActivityAction", Takeout_btn.getText()},
            {"ActivityValueAddedOrRemoved", minusx_txt.getText()}
            });
            if(coa1.update(new Object[][]{ 
                {"ItemDescription",itemDescript_txt.getText()},
                {"StockQuantity",StockQuantityPlusEnterAmount_txt.getText()},
                {"Status",secretStatus_txt.getText()},
                {"ShowInMainWindow", showInMainWindow_txt.getText()}
            }).where(new Object[][]{
                {"ItemID","=",ItemID_txt.getText()}
            }).executeUpdate()){
                AlertMaker.showSimpleAlert(null,"Operation Success!");
                minusx_txt.setText("");
                return;
            }else{
                AlertMaker.showErrorMessage(null,"Operation Failed");
                return;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
            
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("please enter a value");
            alert.showAndWait();
            return;
        }
        
        itemDescript_txt.setText("");
        stock_txt.setText("");
        status_txt.setText("");
        itemLoc_txt.setText("");
        CriticalQuantity_txt.setText("");
        ItemID_txt.setText("");
    }    

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) ItemID_txt.getScene().getWindow();
        stage.close();
    }
    
}
