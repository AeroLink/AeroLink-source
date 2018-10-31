/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_LM_EditQuestion_for_Modal {
    
    public static String c,q;
    public static HashMap choice_description = new HashMap();
    
    public static void init_Question(String course, String question /*String cd*/)
    {
        c = course;
        q = question;
       // choice_description = cd;
    }
    
}
