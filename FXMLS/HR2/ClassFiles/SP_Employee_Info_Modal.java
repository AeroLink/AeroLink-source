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
public class SP_Employee_Info_Modal {
    
    public static String employee_code, fullname,title;
    
    public static void init_EmpInfo(String ec, String f, String t)
    {
        employee_code = ec;
        fullname = f;
        title = t;

    }
    
}
