package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.Log1_WarehouseItemsClassfiles;
import Model.Log1.Log1_WarehouseItemsModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WarehouseRequisitionController implements Initializable {
    
    ObservableList<String> PriorityLvl = 
    FXCollections.observableArrayList("Low","Priority","Emergency");

    Log1_WarehouseItemsModel model = new Log1_WarehouseItemsModel();
    ObservableList<Log1_WarehouseItemsClassfiles> table = FXCollections.observableArrayList(); 

    @FXML
    private TableView<Log1_WarehouseItemsClassfiles> item_tbl;
    @FXML
    private TextArea purpose_txt;
    @FXML
    private ComboBox<String> priority_combox;
    @FXML
    private TextField item_txt;
    @FXML
    private TextField quantity_txt;
    @FXML
    private TextField recieveThrough_txt;
    @FXML
    private JFXButton submit_btn;
    @FXML
    private JFXButton cancel_btn;
    @FXML
    private TextField requestor_txt;
    @FXML
    private TextField requestTitle_txt;
    @FXML
    private TextField unit_txt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        priority_combox.setItems(PriorityLvl);
        renderItemListTbl();
    }    
    public void renderItemListTbl(){
        item_tbl.getItems().clear();
        item_tbl.getColumns().removeAll(item_tbl.getColumns());

        TableColumn<Log1_WarehouseItemsClassfiles, String> item = new TableColumn<>("Item");

        item.setCellValueFactory((param) -> param.getValue().ItemName);

        item_tbl.getColumns().addAll(item);
    }
    public void loadItemData(){
        List b = model.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                table.add(new Log1_WarehouseItemsClassfiles(
                
                String.valueOf(hm.get("ItemID")),
                String.valueOf(hm.get("ItemName")),
                String.valueOf(hm.get("ItemBrand")),
                String.valueOf(hm.get("ItemLocation")),
                String.valueOf(hm.get("ItemUnit")),
                String.valueOf(hm.get("ItemStock")),
                String.valueOf(hm.get("ItemCriticalLevel")),
                String.valueOf(hm.get("ItemExpirationDate")),
                String.valueOf(hm.get("PurchasedPrice")),
                String.valueOf(hm.get("ItemStatus"))
                ));       
        }
        item_tbl.getItems().clear();
        item_tbl.setItems(table);
    }
}
