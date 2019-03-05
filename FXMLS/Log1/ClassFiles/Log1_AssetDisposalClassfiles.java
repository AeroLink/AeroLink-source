/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Crenz
 */
public class Log1_AssetDisposalClassfiles {
    public SimpleStringProperty DisposeID;
    public SimpleStringProperty AssetID;
    public SimpleStringProperty CategoryID;
    public SimpleStringProperty DisposedBy;
    public SimpleStringProperty Remarks;
    public SimpleStringProperty DisposalDate;
    public SimpleStringProperty AssetTitle;
    public SimpleStringProperty AssetCategory;
    public SimpleStringProperty AssetSerialNumber;
    public SimpleStringProperty CurrentPrice;
    
    public Log1_AssetDisposalClassfiles(
            String DisposeID,
            String AssetID,
            String CategoryID,
            String DisposedBy,
            String Remarks,
            String DisposalDate,
            String AssetTitle,
            String AssetCategory,
            String AssetSerialNumber,
            String CurrentPrice
    ){
        this.DisposeID = new SimpleStringProperty(DisposeID);
        this.AssetID = new SimpleStringProperty(AssetID);
        this.CategoryID = new SimpleStringProperty(CategoryID);
        this.DisposedBy = new SimpleStringProperty(DisposedBy);
        this.Remarks = new SimpleStringProperty(Remarks);
        this.DisposalDate = new SimpleStringProperty(DisposalDate);
        this.AssetTitle = new SimpleStringProperty(AssetTitle);
        this.AssetCategory = new SimpleStringProperty(AssetCategory);
        this.AssetSerialNumber = new SimpleStringProperty(AssetSerialNumber);
        this.CurrentPrice = new SimpleStringProperty(CurrentPrice);
    }

    public String getDisposeID() {
        return DisposeID.get();
    }
    
    public String getCategoryID() {
        return CategoryID.get();
    }

    public String getAssetID() {
        return AssetID.get();
    }

    public String getDisposedBy() {
        return DisposedBy.get();
    }

    public String getRemarks() {
        return Remarks.get();
    }

    public String getDisposalDate() {
        return DisposalDate.get();
    }

    public String getAssetTitle() {
        return AssetTitle.get();
    }

    public String getAssetCategory() {
        return AssetCategory.get();
    }

    public String getAssetSerialNumber() {
        return AssetSerialNumber.get();
    }

    public String getCurrentPrice() {
        return CurrentPrice.get();
    }

    
}
