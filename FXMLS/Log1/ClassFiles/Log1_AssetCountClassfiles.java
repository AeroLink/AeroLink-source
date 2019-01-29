package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetCountClassfiles {
    public SimpleStringProperty AssetCountID;
    public SimpleStringProperty AssetLand;
    public SimpleStringProperty AssetBuilding;
    public SimpleStringProperty AssetFacility;
    public SimpleStringProperty AssetVehicle;
    public SimpleStringProperty AssetWarehouseSupply;
    public SimpleStringProperty AssetWarehouseEquipment;

    public Log1_AssetCountClassfiles(
            String AssetCountID,
            String AssetLand,
            String AssetBuilding,
            String AssetFacility,
            String AssetVehicle,
            String AssetWarehouseSupply,
            String AssetWarehouseEquipment
    ){
        this.AssetCountID = new SimpleStringProperty(AssetCountID);
        this.AssetLand = new SimpleStringProperty(AssetLand);
        this.AssetBuilding = new SimpleStringProperty(AssetBuilding);
        this.AssetFacility = new SimpleStringProperty(AssetFacility);
        this.AssetVehicle = new SimpleStringProperty(AssetVehicle);
        this.AssetWarehouseSupply = new SimpleStringProperty(AssetWarehouseSupply);
        this.AssetWarehouseEquipment = new SimpleStringProperty(AssetWarehouseEquipment);
    }

    public String getAssetCountID() {
        return AssetCountID.get();
    }

    public String getAssetLand() {
        return AssetLand.get();
    }

    public String getAssetBuilding() {
        return AssetBuilding.get();
    }

    public String getAssetFacility() {
        return AssetFacility.get();
    }

    public String getAssetVehicle() {
        return AssetVehicle.get();
    }

    public String getAssetWarehouseSupply() {
        return AssetWarehouseSupply.get();
    }

    public String getAssetWarehouseEquipment() {
        return AssetWarehouseEquipment.get();
    }
}
