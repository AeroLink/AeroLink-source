/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samplepack;

import Model.a;
import Synapse.Database;
import Synapse.DB.MYSQL;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Eden Ramoneda
 */
public class sample {
    
        public static void main(String[] args)
        {
            //Database Initialization
            
            Database d = Database.getInstance();
            d.DB_INIT(new MYSQL());
            d.startConnection();
            
            a s = new a();
         //   s.insert(0 , "Wow" ,"WOOOWWW");//VALUES
            
            String[][] t = {
              
                {"name" , "woww"},
                {"description" , "fkdf"}
            };
            
            s.insert(t);
            
            List list = s.get();
            
            System.out.println(Arrays.toString(list.toArray()));
        }
}


/*WHERE CLAUSE
    String[][] d = {

{"id", "=" , "2"}
};

*/