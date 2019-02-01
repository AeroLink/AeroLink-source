/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.USM;

import static FXMLS.USM.Controllers.SetPermissionUSMController.UserID;
import Model.UserPermissions;
import Model.Users;
import Synapse.Helpers;
import Synapse.HttpClient;
import Synapse.HttpClient.METHOD;
import Synapse.Model;
import Synapse.R2SL;
import Synapse.Session;
import Synapse.SessionPermission;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Lei
 */
public class loginController {

    protected static JSONObject json = new JSONObject();

    public static Boolean doLogin(String u, String p) {

        if (!Session.offline) {
            if (Session.isConnected) {

                HttpClient.post(METHOD.LOGIN, "{\"A1009\" : \"" + u + "\",\"B1009\" : \"" + p + "\", \"sestok\" : \"" + Session.sestoken + "\"}", (error, obj) -> {
                    if (error) {
                        System.err.println("Error -> " + error);
                    }
                    json = obj;
                });

                JSONObject list = new JSONObject(json.toString());

                HashMap hash = (HashMap) list.toMap();

                if (Boolean.parseBoolean(String.valueOf(hash.get("success")))) {

                    //Changing Token
                    Session.token = String.valueOf(hash.get("token"));

                    //Getting Permissions
                    UserPermissions up = new UserPermissions();

                    Session.putIfNotExist("user_id", hash.get("id").toString());
                    Session.putIfNotExist("employee_code", hash.get("employee_code").toString());

                    List listPermissions = up
                            .join(Model.JOIN.INNER, "aerolink.tbl_users", "id", "=", "user_id")
                            .join(Model.JOIN.INNER, "aerolink.tbl_permissions", "id", "=", "permission_id")
                            .where(new Object[][]{
                        {"user_id", "=", hash.get("id").toString()}
                    }).get("aerolink.tbl_permissions.permission as UserPermission");

                    listPermissions.stream().forEach((row) -> {
                        HashMap crow = (HashMap) row;
                        Session.addPermission(String.valueOf(crow.get("UserPermission")));
                    });
                    return true;
                }

            }
        } else {
            Users user = new Users();

            List<HashMap> res = user.where(new Object[][]{
                {"username", "=", u}

            }).get("id", "username", "password", "employee_code");

            UserPermissions up = new UserPermissions();

            Session.putIfNotExist("user_id", res.get(0).get("id").toString());
            Session.putIfNotExist("employee_code", res.get(0).get("employee_code").toString());

            List listPermissions = up
                    .join(Model.JOIN.INNER, "aerolink.tbl_users", "id", "=", "user_id")
                    .join(Model.JOIN.INNER, "aerolink.tbl_permissions", "id", "=", "permission_id")
                    .where(new Object[][]{
                {"user_id", "=", res.get(0).get("id").toString()}
            }).get("aerolink.tbl_permissions.permission as UserPermission");

            listPermissions.stream().forEach((row) -> {
                HashMap crow = (HashMap) row;
                Session.addPermission(String.valueOf(crow.get("UserPermission")));
            });

            if (res.size() != 0) {
                System.out.println();
                if (Synapse.Crypt.Decrypt(res.get(0).get("password").toString()).equals(p)) {
                    return true;
                }
            }
        }

        return false;
    }
}
