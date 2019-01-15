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
public class Visitor_Appointments {
    private String Appointment_ID;
    private String Name;
    private String time;
    private String date;
    private String empname;
    private String status;

    public Visitor_Appointments(String Appointment_ID, String Name, String time, String date, String empname, String status) {
        this.Appointment_ID = Appointment_ID;
        this.Name = Name;
        this.time = time;
        this.date = date;
        this.empname = empname;
        this.status = status;
    }

    /**
     * @return the Appointment_ID
     */
    public String getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * @param Appointment_ID the Appointment_ID to set
     */
    public void setAppointment_ID(String Appointment_ID) {
        this.Appointment_ID = Appointment_ID;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the empname
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * @param empname the empname to set
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
