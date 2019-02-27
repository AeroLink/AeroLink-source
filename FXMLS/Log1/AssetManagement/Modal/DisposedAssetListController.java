/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.AssetManagement.Modal;

import FXMLS.Log1.ClassFiles.Log1_AssetClassfiles;
import FXMLS.Log1.ClassFiles.Log1_AssetDisposalClassfiles;
import Model.Log1.Log1_AssetDisposalModel;
import Model.Log1.Log1_AssetModel;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class DisposedAssetListController implements Initializable {
    
    ObservableList<String> AssetForDisposal = FXCollections.observableArrayList("Equipment","Vehicle");

    private TextField search_txt;
    @FXML
    private TableView<Log1_AssetDisposalClassfiles> DisposedAssets_tbl;
    @FXML
    private ComboBox<String> categ_combox;
    @FXML
    private DatePicker dispDate_dp;
    @FXML
    private JFXButton refresh_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
        categ_combox.setItems(AssetForDisposal);
        categ_combox.setValue("Select Category");
        dispDate_dp.getEditor().setText("");
        refresh_btn.setOnMouseClicked(e->refresh());
        fixDateFormat();
    }    
    
    public void refresh(){
        setDisposeTbl();
        disposeAsset();
        categ_combox.setValue("Select Category");
        dispDate_dp.getEditor().setText("");
    }
    
    public void fixDateFormat(){
        String pattern = "MM/dd/yyyy";

//        datePickur_txt.setPromptText(pattern.toLowerCase());

        dispDate_dp.setConverter(new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

        @Override 
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                return null;
                }
            }
        });
    }
    
    public void setDisposeTbl(){
        DisposedAssets_tbl.getItems().clear();
        DisposedAssets_tbl.getColumns().removeAll(DisposedAssets_tbl.getColumns());

        TableColumn<Log1_AssetDisposalClassfiles, String> AssetTitle = new TableColumn<>("Asset Title");
        TableColumn<Log1_AssetDisposalClassfiles, String> AssetCategory = new TableColumn<>("Category");
        TableColumn<Log1_AssetDisposalClassfiles, String> AssetSerialNumber = new TableColumn<>("Serial Number");
        TableColumn<Log1_AssetDisposalClassfiles, String> DispValue = new TableColumn<>("Dispose value");
        TableColumn<Log1_AssetDisposalClassfiles, String> DispBy = new TableColumn<>("Disposed by");
        TableColumn<Log1_AssetDisposalClassfiles, String> DispDate = new TableColumn<>("Disposal Date");

        AssetTitle.setCellValueFactory((param) -> param.getValue().AssetTitle);
        AssetCategory.setCellValueFactory((param) -> param.getValue().AssetCategory);
        AssetSerialNumber.setCellValueFactory((param) -> param.getValue().AssetSerialNumber);
        DispValue.setCellValueFactory((param) -> param.getValue().CurrentPrice);
        DispBy.setCellValueFactory((param) -> param.getValue().DisposedBy);
        DispDate.setCellValueFactory((param) -> param.getValue().DisposalDate);

        DisposedAssets_tbl.getColumns().addAll(AssetTitle, AssetCategory, AssetSerialNumber
                ,DispValue, DispBy, DispDate);
    }    
    
    
    public void disposeAsset(){
        Log1_AssetDisposalModel dispDB = new Log1_AssetDisposalModel();
        ObservableList<Log1_AssetDisposalClassfiles> dispCF = FXCollections.observableArrayList();
            List b = dispDB.join(Model.JOIN.INNER, "aerolink.tbl_log1_Asset", "AssetID", "=", "AssetID").where(new Object[][]{
                {"disposed","=","Yes"}
            }).orderBy("DisposalDate", Model.Sort.DESC).get();
            
                for(Object d : b){
                //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                dispCF.add(new Log1_AssetDisposalClassfiles(
                
                    String.valueOf(hm.get("DisposeID")),
                    String.valueOf(hm.get("AssetID")),
                    String.valueOf(hm.get("CategoryID")),
                    String.valueOf(hm.get("DisposedBy")),
                    String.valueOf(hm.get("Remarks")),
                    String.valueOf(hm.get("DisposalDate")),
                    String.valueOf(hm.get("AssetTitle")),
                    String.valueOf(hm.get("AssetCategory")),
                    String.valueOf(hm.get("AssetSerialNumber")),
                    String.valueOf(hm.get("CurrentPrice"))
                ));  
            }
        DisposedAssets_tbl.setItems(dispCF);
    }
    
    
    

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) DisposedAssets_tbl.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void filterByCateg(ActionEvent event) {
        Log1_AssetDisposalModel dispDB = new Log1_AssetDisposalModel();
        ObservableList<Log1_AssetDisposalClassfiles> dispCF = FXCollections.observableArrayList();
            List b = dispDB.join(Model.JOIN.INNER, "aerolink.tbl_log1_Asset", "AssetID", "=", "AssetID").where(new Object[][]{
                {"disposed","=","Yes"}
            }).andWhere("AssetCategory","=",categ_combox.getValue()).get();
            
                for(Object d : b){
                //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                dispCF.add(new Log1_AssetDisposalClassfiles(
                
                    String.valueOf(hm.get("DisposeID")),
                    String.valueOf(hm.get("AssetID")),
                    String.valueOf(hm.get("CategoryID")),
                    String.valueOf(hm.get("DisposedBy")),
                    String.valueOf(hm.get("Remarks")),
                    String.valueOf(hm.get("DisposalDate")),
                    String.valueOf(hm.get("AssetTitle")),
                    String.valueOf(hm.get("AssetCategory")),
                    String.valueOf(hm.get("AssetSerialNumber")),
                    String.valueOf(hm.get("CurrentPrice"))
                ));  
            }
        DisposedAssets_tbl.setItems(dispCF);
        categ_combox.setValue("Select Category");
        dispDate_dp.getEditor().setText("");
    }

    @FXML
    private void filterByDate(ActionEvent event) {
        Log1_AssetDisposalModel dispDB = new Log1_AssetDisposalModel();
        ObservableList<Log1_AssetDisposalClassfiles> dispCF = FXCollections.observableArrayList();
            List b = dispDB.join(Model.JOIN.INNER, "aerolink.tbl_log1_Asset", "AssetID", "=", "AssetID").where(new Object[][]{
                {"disposed","=","Yes"}
            }).andWhere("DisposalDate","=",dispDate_dp.getEditor().getText()).get();
            
                for(Object d : b){
                //rs = hm
                HashMap hm = (HashMap) d;   //exquisite casting
                
                dispCF.add(new Log1_AssetDisposalClassfiles(
                
                    String.valueOf(hm.get("DisposeID")),
                    String.valueOf(hm.get("AssetID")),
                    String.valueOf(hm.get("CategoryID")),
                    String.valueOf(hm.get("DisposedBy")),
                    String.valueOf(hm.get("Remarks")),
                    String.valueOf(hm.get("DisposalDate")),
                    String.valueOf(hm.get("AssetTitle")),
                    String.valueOf(hm.get("AssetCategory")),
                    String.valueOf(hm.get("AssetSerialNumber")),
                    String.valueOf(hm.get("CurrentPrice"))
                ));  
            }
        DisposedAssets_tbl.setItems(dispCF);
        categ_combox.setValue("Select Category");
        dispDate_dp.getEditor().setText("");
    }
    
}
