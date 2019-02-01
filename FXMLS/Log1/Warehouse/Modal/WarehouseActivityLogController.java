package FXMLS.Log1.Warehouse.Modal;

import FXMLS.Log1.ClassFiles.ActivityLogforWHclassfiles;
import Model.Log1.Log1_WarehouseActivityLogModel;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class WarehouseActivityLogController implements Initializable {

    @FXML
    private TableView<ActivityLogforWHclassfiles> activityLogWH_tbl;
    @FXML
    private TableColumn<ActivityLogforWHclassfiles, String> itemDescript_col1;
    @FXML
    private TableColumn<ActivityLogforWHclassfiles, String> act_col2;
    @FXML
    private TableColumn<ActivityLogforWHclassfiles, String> val_col3;
    @FXML
    private TableColumn<ActivityLogforWHclassfiles, String> date_col4;
    @FXML
    private TableColumn<ActivityLogforWHclassfiles, String> user_col;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        refreshData();
    }    
    public void loadData(){
         Log1_WarehouseActivityLogModel coa = new Log1_WarehouseActivityLogModel();
         ObservableList<ActivityLogforWHclassfiles> ItemsXD = FXCollections.observableArrayList();
          
            List b = coa.get();
            
            for(Object d : b)
                {
                    //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
               ItemsXD.add(new ActivityLogforWHclassfiles(
                String.valueOf(hm.get("ActivityID")),
                String.valueOf(hm.get("ActivityItemName")),
                String.valueOf(hm.get("ActivityUser")),
                String.valueOf(hm.get("ActivityAction")),
                String.valueOf(hm.get("ActivityValueAddedOrRemoved")),
                String.valueOf(hm.get("created_at"))
                
                ));       
        }
        activityLogWH_tbl.setItems(ItemsXD);
    }
    
    public void refreshData(){
            user_col.setCellValueFactory(new PropertyValueFactory<>("ActivityUser"));
            itemDescript_col1.setCellValueFactory(new PropertyValueFactory<>("ActivityItemName"));
            act_col2.setCellValueFactory(new PropertyValueFactory<>("ActivityAction"));
            val_col3.setCellValueFactory(new PropertyValueFactory<>("ActivityValueAddedOrRemoved"));
            date_col4.setCellValueFactory(new PropertyValueFactory<>("created_at"));
    }
}
