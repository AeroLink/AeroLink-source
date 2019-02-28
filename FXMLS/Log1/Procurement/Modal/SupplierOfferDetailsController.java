/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log1.Procurement.Modal;

import FXMLS.Log1.ClassFiles.Log1_ProcurementBiddersClassfiles;
import FXMLS.Log1.ClassFiles.Log1_ProcurementPostedItemClassfiles;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Crenz
 */
public class SupplierOfferDetailsController implements Initializable {

    @FXML
    private ListView<String> bidderOffer_listView;
    @FXML
    private ListView<String> itemDetails;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) bidderOffer_listView.getScene().getWindow();
        stage.close();
    }

    public void populateFirstListView(Log1_ProcurementPostedItemClassfiles z) {
        ObservableList<String> itemInfo = FXCollections.observableArrayList();
        
        itemInfo.add("Item: " + z.getItemName());
        itemInfo.add("Item Unit: " + z.getItemUnit());
        itemInfo.add("Quantity: " + z.getQuantity());
        itemInfo.add("Description: " + z.getItemDescription());
        
        itemDetails.getItems().setAll(itemInfo);
    }
    
    public void populateSecondListView(Log1_ProcurementBiddersClassfiles x) {
        ObservableList<String> bidderOffer = FXCollections.observableArrayList();
        
        bidderOffer.add("Bidder: " + x.getBidderName());
        bidderOffer.add("Bidder's Price: " + x.getBidderPrice());
        bidderOffer.add("Bidder's Other offer: " + x.getBidderOtherOffer());
        
        bidderOffer_listView.getItems().setAll(bidderOffer);
    }

    
    
}
