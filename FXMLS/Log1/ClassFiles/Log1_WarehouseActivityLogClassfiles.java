package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class Log1_WarehouseActivityLogClassfiles {
    public SimpleStringProperty ActivityID;
    public SimpleStringProperty ActivityUser;
    public SimpleStringProperty ActivityItem;
    public SimpleStringProperty ActivityItemStock;
    public SimpleStringProperty ActivityAction;
    public SimpleStringProperty ActivityValue;
    public SimpleStringProperty ActivityItemStockRemaining;
    public SimpleStringProperty ActivityPurpose;
    public SimpleStringProperty ActivityDate;
    public SimpleStringProperty ActivityTime;
    
    public Log1_WarehouseActivityLogClassfiles(
            String ActivityID, 
            String ActivityUser,
            String ActivityItem, 
            String ActivityItemStock, 
            String ActivityAction, 
            String ActivityValue,
            String ActivityItemStockRemaining,
            String ActivityPurpose,
            String ActivityDate,
            String ActivityTime
    )
    {
        this.ActivityID = new SimpleStringProperty(ActivityID);
        this.ActivityUser = new SimpleStringProperty(ActivityUser);
        this.ActivityItem = new SimpleStringProperty(ActivityItem);
        this.ActivityItemStock = new SimpleStringProperty(ActivityItemStock);
        this.ActivityAction = new SimpleStringProperty(ActivityAction);
        this.ActivityValue = new SimpleStringProperty(ActivityValue);
        this.ActivityItemStockRemaining = new SimpleStringProperty(ActivityItemStockRemaining);
        this.ActivityPurpose = new SimpleStringProperty(ActivityPurpose);
        this.ActivityDate = new SimpleStringProperty(ActivityDate);
        this.ActivityTime = new SimpleStringProperty(ActivityTime);
    }

    public String getActivityID() {
        return ActivityID.get();
    }

    public String getActivityUser() {
        return ActivityUser.get();
    }

    public String getActivityItem() {
        return ActivityItem.get();
    }

    public String getActivityItemStock() {
        return ActivityItemStock.get();
    }

    public String getActivityAction() {
        return ActivityAction.get();
    }

    public String getActivityValue() {
        return ActivityValue.get();
    }

    public String getActivityItemStockRemaining() {
        return ActivityItemStockRemaining.get();
    }

    public String getActivityPurpose() {
        return ActivityPurpose.get();
    }

    public String getActivityDate() {
        return ActivityDate.get();
    }

    public String getActivityTime() {
        return ActivityTime.get();
    }

    
}

