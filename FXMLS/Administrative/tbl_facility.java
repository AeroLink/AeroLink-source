/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

/**
 *
 * @author Onodera-Chan
 */
public class tbl_facility {
    private String fid;
    private String fname;
    private String ftype;

    public tbl_facility(String fid, String fname, String ftype) {
        this.fid = fid;
        this.fname = fname;
        this.ftype = ftype;
    }

    /**
     * @return the fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the ftype
     */
    public String getFtype() {
        return ftype;
    }

    /**
     * @param ftype the ftype to set
     */
    public void setFtype(String ftype) {
        this.ftype = ftype;
    }
    
    
}
