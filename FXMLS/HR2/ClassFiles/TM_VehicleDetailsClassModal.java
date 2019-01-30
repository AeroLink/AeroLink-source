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
public class TM_VehicleDetailsClassModal {
    
    public static String VehicleID, VehicleType, VehicleModel, VehicleStatus;
    
    public static void initVD(String vid, String vt, String vm, String vs){
        VehicleID = vid;
        VehicleType = vt;
        VehicleModel = vm;
        VehicleStatus = vs;
    }
    
}
