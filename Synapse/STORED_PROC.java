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
import org.json.JSONObject;

/**
 *
 * @author BlackMoon
 */
public class STORED_PROC {

    static JSONObject json = new JSONObject();

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

            String query = " call " + PROC_NAME + "(" + columns + ") ";

            System.out.println("[SQL QUERY] : -> " + query);
            System.err.println("[Values] : -> " + Helpers.combine(values.toArray(), ","));

            if (Session.offline) {
                CallableStatement call = Session.INSTANCE.getConnection().prepareCall(query);

                for (int i = 1; i <= values.size(); i++) {
                    call.setObject(i, values.get(i - 1));
                }
                return R2SL.convert(call.executeQuery());

            } else {
                if (Session.isConnected) {

                    HttpClient.post(HttpClient.METHOD.STORED_PROC, "{\"A1009\" : \"" + query + "\",\"B1009\" : \"" + Helpers.combine(values.toArray(), ",,") + "\" }", (error, obj) -> {
                        if (error) {
                            System.err.println("Error -> " + error);
                        }
                        json = obj;
                    });

                    return R2SL.convert(json);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List executeCall(String PROC_NAME) {

        try {

            String query = " call " + PROC_NAME;

            System.out.println("[SQL QUERY] : -> " + query);

            if (Session.offline) {
                CallableStatement call = Session.INSTANCE.getConnection().prepareCall(query);
                return R2SL.convert(call.executeQuery());
            } else {
                if (Session.isConnected) {

                    HttpClient.post(HttpClient.METHOD.STORED_PROC, "{\"A1009\" : \"" + query + "\"}", (error, obj) -> {
                        if (error) {
                            System.err.println("Error -> " + error);
                        }
                        json = obj;
                    });

                    return R2SL.convert(json);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
