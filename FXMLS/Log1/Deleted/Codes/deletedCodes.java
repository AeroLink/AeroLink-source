/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Deleted.Codes;

/**
 *
 * @author Crenz
 */
public class deletedCodes {
//            status.setCellFactory(column -> {
//    return new TableCell<Log1_WarehouseItemsClassfiles, String>() {
//        @Override
//        protected void updateItem(String item, boolean empty) {
//            super.updateItem(item, empty); //This is mandatory
//
//            if (item == null || empty) { //If the cell is empty
//                setText(null);
//                setStyle("");
//            } else { //If the cell is not empty
//
//                setText(item); //Put the String data in the cell
//
//                //We get here all the info of the Person of this row
//                Log1_WarehouseItemsClassfiles auxPerson = getTableView().getItems().get(getIndex());
//
//                // Style all persons wich name is "Edgard"
//                if (auxPerson.getItemStatus().equals("Need to Reorder!")) {
//                    setTextFill(Color.RED); //The text in red
//                    setStyle("-fx-background-color: yellow"); //The background of the cell in yellow
//                } else {
//                    //Here I see if the row of this cell is selected or not
//                    if(getTableView().getSelectionModel().getSelectedItems().contains(auxPerson))
//                        setTextFill(Color.WHITE);
//                    else
//                        setTextFill(Color.BLACK);
//                }
//            }
//        }
//    };
//});
    
    //    private void showTableView(ActionEvent event) {
//        String AssetTable = selectAssetForMonitoring_combox.getValue();
//        
//        if(AssetTable=="Land"){
//            
//        }else if(AssetTable=="Building"){
//            MonitoringLand_tbl.setVisible(false);
//            MonitoringBuilding_tbl.setVisible(true);
//            MonitoringFacility_tbl.setVisible(false);
//            MonitoringVehicle_tbl.setVisible(false);
//            MonitoringEquipment_tbl.setVisible(false);
//        }else if(AssetTable=="Facility"){
//            MonitoringLand_tbl.setVisible(false);
//            MonitoringBuilding_tbl.setVisible(false);
//            MonitoringFacility_tbl.setVisible(true);
//            MonitoringVehicle_tbl.setVisible(false);
//            MonitoringEquipment_tbl.setVisible(false);
//        }else if(AssetTable=="Vehicle"){
//            MonitoringLand_tbl.setVisible(false);
//            MonitoringBuilding_tbl.setVisible(false);
//            MonitoringFacility_tbl.setVisible(false);
//            MonitoringVehicle_tbl.setVisible(true);
//            MonitoringEquipment_tbl.setVisible(false);
//        }else if(AssetTable=="Equipment"){
//            MonitoringLand_tbl.setVisible(false);
//            MonitoringBuilding_tbl.setVisible(false);
//            MonitoringFacility_tbl.setVisible(false);
//            MonitoringVehicle_tbl.setVisible(false);
//            MonitoringEquipment_tbl.setVisible(true);
//        }
//    }
    
    
            //deadmeme
//    private void updateBuildingAction(ActionEvent event) {
//        Log1_AssetBuildingClassfiles selectedForEdit = building_tbl.getSelectionModel().getSelectedItem();
//        if(selectedForEdit == null){
//            AlertMaker.showErrorMessage("No Building Selected","Please Select A Building From the table to proceed");
//            return;
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLS/Log1/AssetManagement/Modal/AddBuilding.fxml"));
//            Parent parent = loader.load();
//            
//            AddBuildingController controller = (AddBuildingController) loader.getController();
//            controller.displayBuildingData(selectedForEdit);
//            
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("Update Building Details");
//            stage.setScene(new Scene(parent));
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(AssetManagementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
//    public void callSupplierData(){
//         Log1_SupplierModel coa = new Log1_SupplierModel();
//         ObservableList<Log1_SupplierClassfiles> supplier = FXCollections.observableArrayList();
//          
//            List b = coa.get();
//            
//            for(Object d : b)
//                {
//                    //rs = hm
//                HashMap hm = (HashMap) d;   //exquisite casting
//                
//               supplier.add(new Log1_SupplierClassfiles(
//                
//                String.valueOf(hm.get("SupplierID")),
//                String.valueOf(hm.get("SupplierName")),
//                String.valueOf(hm.get("SupplierAddress")),
//                String.valueOf(hm.get("SupplierContact")),
//                String.valueOf(hm.get("SupplierRepresentative"))
//                ));       
//        }
//        Supplier_tbl.setItems(supplier);
//    }
//    
//    public void displaySupplierData(){
//        name_col.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
//        address_col.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
//        contact_col.setCellValueFactory(new PropertyValueFactory<>("SupplierContact"));
//        representative_col.setCellValueFactory(new PropertyValueFactory<>("SupplierRepresentative"));
//    }
//    
//    public void loadItemUnitToCombox(){
//        Log1_WarehouseDesiredItemUnitModel iu = new Log1_WarehouseDesiredItemUnitModel();
//        List itemUnit = iu.get();
//
//        itemUnit.stream().forEach(row -> {
//            HashMap hash = (HashMap) row;
//
//            itemUnit_combox.getItems().add(String.valueOf(hash.get("DesiredItemUnit")));
//        });
//    }
//    public void loadItemTypeToCombox(){
//        Log1_WarehouseDesiredItemTypeModel it = new Log1_WarehouseDesiredItemTypeModel();
//        List itemUnit = it.get();
//
//        itemUnit.stream().forEach(row -> {
//            HashMap hash = (HashMap) row;
//
//            itemType_combox.getItems().add(String.valueOf(hash.get("DesiredItemType")));
//        });
//    }
//    
//
//    private void handleCloseAction() {
//        Stage stage = (Stage) itemDescription_txt.getScene().getWindow();
//        stage.close();
//    }
//
//    private void saveUpdateOrAddAction() {
//        String description = itemDescription_txt.getText();
//        String location = itemLoc_txt.getText();
//        String price = unitPrice_txt.getText();
//        String quantity = quantityStocked_txt.getText();
//        String reorder = reorderQuantity_txt.getText();
//        
//        
//        Boolean flag = description.isEmpty() || location.isEmpty()
//                || price.isEmpty() || quantity.isEmpty() || reorder.isEmpty();
//        
//        if(ifForUpdate){
//            useUpdateMethod();
//            return;
//        }
//        Log1_WarehouseActivityLogModel coa2 = new Log1_WarehouseActivityLogModel();
//        Log1_WarehouseItemModel model = new Log1_WarehouseItemModel();
//    ObservableList<Log1_WarehouseItemsClassfiles> table = FXCollections.observableArrayList(); 
//        if(!flag){
//            try{
//                String[][] coa_table ={
//                {"ItemDescription",itemDescription_txt.getText()},
//                {"SupplierID",supp_id.getText()},
//                {"ItemLocation",itemLoc_txt.getText()},
//                {"ItemType",itemType_combox.getValue().toString()},
//                {"ItemUnit",itemUnit_combox.getValue().toString()},
//                {"UnitPrice","Php" + unitPrice_txt.getText()+""},
//                {"StockQuantity",quantityStocked_txt.getText()},
//                {"CriticalQuantity",reorderQuantity_txt.getText()},
//                {"DisposalDate",disposalDate_datepick.getEditor().getText()},
//                {"Status","Good on stock"},
//                {"ShowInMainWindow", "yes"}
//                }; 
//                    if(model.insert(coa_table)){
//                        AlertMaker.showSimpleAlert("Saved", ""+ itemDescription_txt.getText() +" has been saved");
//                        coa2.insert(new String [][]{
//                            {"ActivityItemName",itemDescription_txt.getText()},
//                            {"ActivityUser", "rb"},
//                            {"ActivityAction", "New Item Added"},
//                            {"ActivityValueAddedOrRemoved", quantityStocked_txt.getText()}
//                            });
//                        clear();
//                        return;
//                        }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("please fill up all the details");
//            alert.showAndWait();
//            return;
//        }
//    }
//    
//    public void clear(){
//        itemDescription_txt.setText("");
//        itemType_combox.setValue("");
//        itemLoc_txt.setText("");
//        itemUnit_combox.setValue("");
//        unitPrice_txt.setText("");
//        quantityStocked_txt.setText("");
//        reorderQuantity_txt.setText("");
//        disposalDate_datepick.getEditor().setText("");
//    }
//    
////    public void displaySelectedIndex(Log1_fullInventoryList item){
////        itemID_txt.setText(item.getItemID());
////        itemDescription_txt.setText(item.getItemDescription());
////        itemType_combox.setValue(item.getItemType());
////        itemLoc_txt.setText(item.getItemLocation());
////        itemUnit_combox.setValue(item.getItemUnit());
////        unitPrice_txt.setText(item.getUnitPrice());
////        quantityStocked_txt.setText(item.getStockQuantity());
////        reorderQuantity_txt.setText(item.getCriticalQuantity());
////        disposalDate_datepick.getEditor().setText(item.getDisposalDate());
////        cantDoBooleanSoFuckThisShit.setText(item.getStatus());
////        
////        ifForUpdate = Boolean.TRUE;
////    }
//    
//    private void useUpdateMethod() {
//        Log1_WarehouseItemModel wh = new Log1_WarehouseItemModel();
//            try{
//                if(wh.update(new Object[][]{ 
//                    {"ItemDescription",itemDescription_txt.getText()},
//                    {"SupplierID",supp_id.getText()},
//                    {"ItemType",itemType_combox.getValue()},
//                    {"ItemLocation",itemLoc_txt.getText()},
//                    {"ItemUnit",itemUnit_combox.getValue()},
//                    {"UnitPrice",unitPrice_txt.getText()},
//                    {"StockQuantity",quantityStocked_txt.getText()},
//                    {"CriticalQuantity",reorderQuantity_txt.getText()},
//                    {"DisposalDate",disposalDate_datepick.getEditor().getText()},
//                    {"Status",cantDoBooleanSoFuckThisShit.getText()},
//                 }).where(new Object[][]{
//                     {"itemID","=",itemID_txt.getText()}
//                }).executeUpdate()){
//                     AlertMaker.showSimpleAlert("Update", ""+ desiredItemType_txt.getText()+" has been updated!");
//                }else{
//                     AlertMaker.showErrorMessage("Failed", ""+ desiredItemType_txt.getText()+" has not been updated.");
//                }
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//    }
//    
//    private void supplierClickEvent(MouseEvent event) {
//        supp_id.setText(Supplier_tbl.getSelectionModel().getSelectedItem().getSupplierID());
//        supplierName.setText(Supplier_tbl.getSelectionModel().getSelectedItem().getSupplierName());
//    }
//
//    private void showSetUnit(ActionEvent event) {
//        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ItemUnit.fxml"),
//                 "Set Item Unit", null);
//    }
//
//    private void showSetType(ActionEvent event) {
//        Log1Util.loadWindow(getClass().getResource("/FXMLS/Log1/Warehouse/Modal/ItemType.fxml"),
//                 "Set Item Type", null);
//    }
}
