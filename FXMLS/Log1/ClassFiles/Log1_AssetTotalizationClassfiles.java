package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_AssetTotalizationClassfiles {
    public SimpleStringProperty AssetID;
    public SimpleStringProperty AssetName;
    public SimpleStringProperty AssetCategory;
    public SimpleStringProperty AssetUnit;
    public SimpleStringProperty AssetQuantity;
    public SimpleStringProperty AssetPricePerUnit;
    
    public Log1_AssetTotalizationClassfiles(
            String AssetID,
            String AssetName,
            String AssetCategory,
            String AssetUnit,
            String AssetQuantity,
            String AssetPricePerUnit
    ){
        this.AssetID = new SimpleStringProperty(AssetID);
        this.AssetName = new SimpleStringProperty(AssetName);
        this.AssetCategory = new SimpleStringProperty(AssetCategory);
        this.AssetUnit = new SimpleStringProperty(AssetUnit);
        this.AssetQuantity = new SimpleStringProperty(AssetQuantity);
        this.AssetPricePerUnit = new SimpleStringProperty(AssetPricePerUnit);
    }

    public String getAssetID() {
        return AssetID.get();
    }

    public String getAssetName() {
        return AssetName.get();
    }

    public String getAssetCategory() {
        return AssetCategory.get();
    }

    public String getAssetUnit() {
        return AssetUnit.get();
    }

    public String getAssetQuantity() {
        return AssetQuantity.get();
    }

    public String getAssetPricePerUnit() {
        return AssetPricePerUnit.get();
    }
}
