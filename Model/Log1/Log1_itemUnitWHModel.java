package Model.Log1;

import static Synapse.Model.setColumns;

public class Log1_itemUnitWHModel extends Synapse.Model {
    public Log1_itemUnitWHModel(){
        setColumns(
                "DesiredItemUnit"
        );
        this.initTable("tbl_log1_itemUnitWH");
    }
}
