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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Lei
 */
public class R2SL {

    public static List convert(ResultSet rs) {

        ArrayList list = new ArrayList();

        try {

            while (rs.next()) {

                HashMap row = new HashMap(rs.getMetaData().getColumnCount());
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                list.add(row);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }

    public static List convert(JSONObject json) {

        ArrayList list = new ArrayList();

        JSONObject result_set = new JSONObject(String.valueOf(json.get("result_set")));
        JSONArray results = result_set.getJSONArray("result");
        results.forEach(row -> {
            JSONObject b = new JSONObject(row.toString());
            HashMap hash = new HashMap(b.toMap());
            list.add(hash);
        });

        return list;

    }

    public static Object convert_rt_obj(JSONObject json) {

        ArrayList list = new ArrayList();

        JSONObject result_set = new JSONObject(String.valueOf(json.get("result_set")));
        return (Object) result_set.get("result");
    }

}
