/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.USM;

import Model.Users;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lei
 */
public class loginController {
    
    
    public static Boolean doLogin(String u, String p){
        Users user = new Users();
        
        List<HashMap> res = user.where(new Object[][]{ 
         
            { "username", "=", u }
        
        }).get("id","username", "password", "ivp");
        
        
        if(res.size() != 0) {
            if(Synapse.Crypt.Decrypt((byte[]) res.get(0).get("ivp"), (byte[]) res.get(0).get("password")).equals(p)) {
                return true;
            }
        }
        return false;
    }
}
