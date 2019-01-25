/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BlackMoon
 */
public class STORED_PROC {

    //StoredProcedures
    public static List executeCall(String PROC_NAME, Object[][] vals) {
        String Query = "";
        List<Object> values = new ArrayList<>();

        String columns = "";

        for (int i = 0; i < vals.length; i++) {

            if (vals[i].length < 2) {
                System.out.println(Response.ORM_ERR_02);
                break;
            }

            if (i == (vals.length - 1)) {
                columns += " ?";
                values.add(vals[i][1]);

                System.err.println(vals[i][1]);
            } else {
                columns += " ?,";
                values.add(vals[i][1]);
            }

        }

        try {
            CallableStatement call = Session.INSTANCE.getConnection().prepareCall("{ call " + PROC_NAME + "(" + columns + ") }");

            System.out.println(Arrays.asList(values.toArray()));
            for (int i = 1; i <= values.size(); i++) {
                call.setObject(i, values.get(i - 1));
            }
            
            return R2SL.convert(call.executeQuery());
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List executeCall(String PROC_NAME) {
        String Query = "";

        try {
            CallableStatement call = Session.INSTANCE.getConnection().prepareCall("{ call " + PROC_NAME + " }");

            return R2SL.convert(call.executeQuery());

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
