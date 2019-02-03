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
    public SimpleStringProperty AssetName;
    public SimpleStringProperty DisposalValue;
    public SimpleStringProperty Quantity;
    public SimpleStringProperty DisposalDate;
    public SimpleStringProperty Note;
    
    public Log1_AssetDisposalClassfiles(
            String DisposeID,
            String AssetName,
            String DisposalValue,
            String Quantity,
            String DisposalDate,
            String Note
    ){
        this.DisposeID = new SimpleStringProperty(DisposeID);
        this.AssetName = new SimpleStringProperty(AssetName);
        this.DisposalValue = new SimpleStringProperty(DisposalValue);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.DisposalDate = new SimpleStringProperty(DisposalDate);
        this.Note = new SimpleStringProperty(Note);
    }

    public String getDisposeID() {
        return DisposeID.get();
    }

    public String getAssetName() {
        return AssetName.get();
    }

    public String getDisposalValue() {
        return DisposalValue.get();
    }

    public String getQuantity() {
        return Quantity.get();
    }

    public String getDisposalDate() {
        return DisposalDate.get();
    }

    public String getNote() {
        return Note.get();
    }
}
