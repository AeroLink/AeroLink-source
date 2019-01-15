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
public class VisitorTable_Employee {
    private String Company_ID;
    private String Name;
    private String Office;
    private String Floor;

    public VisitorTable_Employee(String Company_ID, String Name, String Office, String Floor) {
        this.Company_ID = Company_ID;
        this.Name = Name;
        this.Office = Office;
        this.Floor = Floor;
    }

    /**
     * @return the Company_ID
     */
    public String getCompany_ID() {
        return Company_ID;
    }

    /**
     * @param Company_ID the Company_ID to set
     */
    public void setCompany_ID(String Company_ID) {
        this.Company_ID = Company_ID;
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
     * @return the Office
     */
    public String getOffice() {
        return Office;
    }

    /**
     * @param Office the Office to set
     */
    public void setOffice(String Office) {
        this.Office = Office;
    }

    /**
     * @return the Floor
     */
    public String getFloor() {
        return Floor;
    }

    /**
     * @param Floor the Floor to set
     */
    public void setFloor(String Floor) {
        this.Floor = Floor;
    }
    
}
