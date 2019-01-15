/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

/**
 *
 * @author Onodera
 */
public class ADMINfacility {
    private String Facility_reservation_ID;
    private String Facility_ID;
    private String Purpose;
    private String Start_Date;
    private String Start_Time;  
    private String Reserved_By;
    private String Status;

    public ADMINfacility(String Facility_reservation_ID, String Facility_ID, String Purpose, String Start_Date, String Start_Time, String Reserved_By, String Status) {
        this.Facility_reservation_ID = Facility_reservation_ID;
        this.Facility_ID = Facility_ID;
        this.Purpose = Purpose;
        this.Start_Date = Start_Date;
        this.Start_Time = Start_Time;
        this.Reserved_By = Reserved_By;
        this.Status = Status;
    }

    /**
     * @return the Facility_reservation_ID
     */
    public String getFacility_reservation_ID() {
        return Facility_reservation_ID;
    }

    /**
     * @param Facility_reservation_ID the Facility_reservation_ID to set
     */
    public void setFacility_reservation_ID(String Facility_reservation_ID) {
        this.Facility_reservation_ID = Facility_reservation_ID;
    }

    /**
     * @return the Facility_ID
     */
    public String getFacility_ID() {
        return Facility_ID;
    }

    /**
     * @param Facility_ID the Facility_ID to set
     */
    public void setFacility_ID(String Facility_ID) {
        this.Facility_ID = Facility_ID;
    }

    /**
     * @return the Purpose
     */
    public String getPurpose() {
        return Purpose;
    }

    /**
     * @param Purpose the Purpose to set
     */
    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }

    /**
     * @return the Start_Date
     */
    public String getStart_Date() {
        return Start_Date;
    }

    /**
     * @param Start_Date the Start_Date to set
     */
    public void setStart_Date(String Start_Date) {
        this.Start_Date = Start_Date;
    }

    /**
     * @return the Start_Time
     */
    public String getStart_Time() {
        return Start_Time;
    }

    /**
     * @param Start_Time the Start_Time to set
     */
    public void setStart_Time(String Start_Time) {
        this.Start_Time = Start_Time;
    }

    /**
     * @return the Reserved_By
     */
    public String getReserved_By() {
        return Reserved_By;
    }

    /**
     * @param Reserved_By the Reserved_By to set
     */
    public void setReserved_By(String Reserved_By) {
        this.Reserved_By = Reserved_By;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }
}
