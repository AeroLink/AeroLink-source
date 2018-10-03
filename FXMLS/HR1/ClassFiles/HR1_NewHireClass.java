/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR1.ClassFiles;

/**
 *
 * @author Lei
 */
public class HR1_NewHireClass {
    
    public static String jobtitle;
    public static String name;
    public static String DateOfBirth;
    public static String PlaceOfBirth;
    public static String Gender;
    public static String CivilStatus;
    public static String Email;
    public static String Height;
    public static String Weight;
    public static String ContactNumber;
    public static String employee_code;

    
    public static void init(String vname, String vemployee_code, String vjobtitle, String vDateOfBirth, String vPlaceOfBirth, String vGender, String vCivilStatus, String  vEmail, String vHeight, String vWeight, String vContactNumber) {
        name = vname;
        employee_code = vemployee_code;
        jobtitle = vjobtitle;
        DateOfBirth = vDateOfBirth;
        PlaceOfBirth = vPlaceOfBirth;
        Gender = vGender;
        CivilStatus = vCivilStatus;
        Email = vEmail;
        Height = vHeight;
        Weight = vWeight;
        ContactNumber = vContactNumber;
    } 
}
