/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.ClassFiles;

/**
 *
 * @author Lei
 */
public class HR4_MIZ {
    
    public static String t, d, dpt, des, c, id;
    public static void init_viewJob(String idx, String title, String Desc, String department, String designation, String classification){
        t = title;
        d = Desc;
        dpt = department;
        des = designation;
        c = classification;
        id = idx;
    }
}
