/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lei
 */
public class R2SL {
    
    public static List convert(ResultSet rs){
        
        ArrayList list = new ArrayList();
            
        try {
        
            while(rs.next()){
  
                    HashMap row = new HashMap(rs.getMetaData().getColumnCount());
                    for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++ ){
                        row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                    }
                    list.add(row);

            }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }
}
