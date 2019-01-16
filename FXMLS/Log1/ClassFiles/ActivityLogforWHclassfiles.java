package FXMLS.Log1.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

public class ActivityLogforWHclassfiles {
    public SimpleStringProperty ActivityID;
    public SimpleStringProperty ActivityItemName;
    public SimpleStringProperty ActivityUser;
    public SimpleStringProperty ActivityAction;
    public SimpleStringProperty ActivityValueAddedOrRemoved;
    public SimpleStringProperty created_at;
    
    public ActivityLogforWHclassfiles(
            String ActivityID, 
            String ActivityItemName,
            String ActivityUser, 
            String ActivityAction, 
            String ActivityValueAddedOrRemoved, 
            String created_at)
    {
        this.ActivityID = new SimpleStringProperty(ActivityID);
        this.ActivityItemName = new SimpleStringProperty(ActivityItemName);
        this.ActivityUser = new SimpleStringProperty(ActivityUser);
        this.ActivityAction = new SimpleStringProperty(ActivityAction);
        this.ActivityValueAddedOrRemoved = new SimpleStringProperty(ActivityValueAddedOrRemoved);
        this.created_at = new SimpleStringProperty(created_at);
    }

    public String getActivityID() {
        return ActivityID.get();
    }

    public String getActivityItemName() {
        return ActivityItemName.get();
    }

    public String getActivityUser() {
        return ActivityUser.get();
    }

    public String getActivityAction() {
        return ActivityAction.get();
    }

    public String getActivityValueAddedOrRemoved() {
        return ActivityValueAddedOrRemoved.get();
    }

    public String getCreated_at() {
        return created_at.get();
    }

    
}

