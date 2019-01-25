/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author BlackMoon
 */
public class HttpClient {

    String res;
    HttpURLConnection connection;
    OutputStreamWriter request = null;
    URL url = null;
    private static String response = null;
    String jsonBuild = null;

    public static enum METHOD {
        GET, CONNECT, INSERT, INSERT_RT_ID, UPDATE, LOGIN
    }

    protected static String post(String path, String json) {

        try {

            String url = Session.HttpURL + path;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setUseCaches(false);

            // declare json as data
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");

            if (!path.equals("connect")) {
                con.setRequestProperty("authorization", Session.token);
            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            if(!json.contains("BINARY_CHECKSUM(*)")) {System.err.println(json);}
            out.write(json);
            out.flush();
            out.close();

            //JOptionPane.showMessageDialog(null, json);
            int responseCode = con.getResponseCode();

            String line;

            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                //JOptionPane.showMessageDialog(null, line);
                response = line;

            }

            return response;

            //System.out.println(res);
        } catch (Exception ex) {

            //handle exception here
            //JOptionPane.showMessageDialog(null, ex,"Access denied",JOptionPane.ERROR_MESSAGE);
            //preloader.setVisible(false);
            return ex.getMessage();

        }
    }

    public static void post(String json, ICallback<Boolean, JSONObject> callback) {
        JSONObject obj = new JSONObject(HttpClient.post("get", json));
        callback.result_set(false, obj);
    }

    public static void post(HttpClient.METHOD m, String json, ICallback<Boolean, JSONObject> callback) {

        JSONObject obj = new JSONObject();
        switch (m.name()) {
            case "CONNECT":
                try {
                    obj = new JSONObject(HttpClient.post("connect", json));
                    callback.result_set(obj.isEmpty(), obj);
                } catch (NullPointerException e) {
                    obj = new JSONObject();
                } finally {
                    callback.result_set(obj.isEmpty(), obj);
                }
                break;

            case "GET":
                obj = new JSONObject(HttpClient.post("get", json));
                callback.result_set(false, obj);
                break;

            case "INSERT":
                obj = new JSONObject(HttpClient.post("insert", json));
                callback.result_set(false, obj);
                break;

            case "INSERT_RT_ID":
                obj = new JSONObject(HttpClient.post("insert_rt_id", json));
                callback.result_set(false, obj);
                break;

            case "UPDATE":
                obj = new JSONObject(HttpClient.post("update", json));
                callback.result_set(false, obj);
                break;

            case "LOGIN":
                obj = new JSONObject(HttpClient.post("login", json));
                callback.result_set(false, obj);
                break;

            default:
                callback.result_set(true, null);
                break;
        }
    }

}
