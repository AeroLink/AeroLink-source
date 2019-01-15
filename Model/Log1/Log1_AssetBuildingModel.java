/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Log1;

/**
 *
 * @author Crenz
 */
public class Log1_AssetBuildingModel extends Synapse.Model {
    public Log1_AssetBuildingModel(){
        setColumns(
                "BuildingID",
                "BuildingName",
                "BuildingAddress",
                "BuildingContact",
                "BuildingArea",
                "YearBuilt"
        );
        this.initTable("tbl_log1_AssetBuilding");
    }
    
}
