package Model.Log1;


public class Log1_ActivityLogforWarehouseModel extends Synapse.Model {
    public Log1_ActivityLogforWarehouseModel(){
         
        setColumns(
                "ActivityID",
                "userWH",
                "ItemName",
                "ActionWH",
                "ValueAddedOrRemoved",
                "created_at"
                 );
        this.initTable("tbl_log1_activityLogWH");
    
    }
}
