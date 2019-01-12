/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.Controllers;

/**
 *
 * @author my
 */
public class HR3_Modals {
     public static String t, d, dpt, des, c, id;
    public static void matindi(String or_no, String date, String department, String expenses, String totalamount, String particulars){
        t = or_no;
        d = date;
        dpt = department;
        des = expenses;
        c = totalamount;
        id = particulars;
    }
}
