/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.Modals;

import FXMLS.HR2.ClassFiles.TM_FacilityDetailsClass_for_Modal;
import Model.HR2_Temp_Facilities;
import Synapse.Model;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author EdenRamoneda
 */
public class TM_ViewFacilityDetailsController implements Initializable {

    @FXML
    private Label lbl_RoomNumber;
    @FXML
    private Label lbl_facName;
    @FXML
    private Label lbl_BName;
    @FXML
    private Label lbl_Capacity;
    @FXML
    private Label lbl_category;
    @FXML
    private JFXTextArea lbl_building_desc;
    @FXML
    private Label lbl_contact;
    @FXML
    private Label lbl_year_built;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_facName.setText(TM_FacilityDetailsClass_for_Modal.facName);
        lbl_RoomNumber.setText(TM_FacilityDetailsClass_for_Modal.facRoomNumber);
        lbl_Capacity.setText(TM_FacilityDetailsClass_for_Modal.facCapacity);
        lbl_BName.setText(TM_FacilityDetailsClass_for_Modal.BName);
        DisplayDataInJLabels();
    }

    public void DisplayDataInJLabels() {
        try {
            HR2_Temp_Facilities facilities = new HR2_Temp_Facilities();
            List f = facilities.join(Model.JOIN.INNER, "aerolink.tbl_log1_AssetBuilding", "BuildingID", "ab", "=", "BuildingID")
                    .where(new Object[][]{{"FacilityID", "=", TM_FacilityDetailsClass_for_Modal.facID}})
                    .get("FacilityID, FacilityName, aerolink.tbl_log1_AssetFacility.AssetCategory, FacilityStatus, "
                            + "FacilityRoomNumber, FacilityCapacity, ab.BuildingName, ab.BuildingDescription, ab.BuildingContact, ab.BuildingYearBuilt");
               f.stream().forEach(row -> {
           
            lbl_category.setText(((HashMap) row).get("AssetCategory").toString());
            lbl_building_desc.setText(((HashMap) row).get("BuildingDescription").toString());
            lbl_contact.setText(((HashMap) row).get("BuildingContact").toString());
            lbl_year_built.setText(((HashMap) row).get("BuildingYearBuilt").toString());
        });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
