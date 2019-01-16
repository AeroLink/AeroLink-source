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
public class Visitor_TableRegistration {
    private String Visitor_ID;
    private String Name;
    private String PTM;
    private String Purpose;

    public Visitor_TableRegistration(String Visitor_ID, String Name, String PTM, String Purpose) {
        this.Visitor_ID = Visitor_ID;
        this.Name = Name;
        this.PTM = PTM;
        this.Purpose = Purpose;
    }

    /**
     * @return the Visitor_ID
     */
    public String getVisitor_ID() {
        return Visitor_ID;
    }

    /**
     * @param Visitor_ID the Visitor_ID to set
     */
    public void setVisitor_ID(String Visitor_ID) {
        this.Visitor_ID = Visitor_ID;
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
     * @return the PTM
     */
    public String getPTM() {
        return PTM;
    }

    /**
     * @param PTM the PTM to set
     */
    public void setPTM(String PTM) {
        this.PTM = PTM;
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
}
