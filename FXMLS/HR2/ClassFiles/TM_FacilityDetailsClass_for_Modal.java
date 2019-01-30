/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

/**
 *
 * @author EdenRamoneda
 */
public class TM_FacilityDetailsClass_for_Modal {
    
    public static String facID, facName, facRoomNumber, facCapacity,BName, facStatus;
    
    public static void FacilityDetails(String fid, String fn, String frn, String fc, String bn, String fs){
        facID = fid;
        facRoomNumber = frn;
        facCapacity = fc;
        facName = fn;
        BName = bn;
        facStatus = fs;
    }
    
}
