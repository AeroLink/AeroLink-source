/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.USM;

import static FXMLS.USM.Controllers.SetPermissionUSMController.UserID;
import Model.UserPermissions;
import Model.Users;
import Synapse.Model;
import Synapse.Session;
import Synapse.SessionPermission;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lei
 */
public class loginController {

    public static Boolean doLogin(String u, String p) {
        Users user = new Users();

        List<HashMap> res = user.where(new Object[][]{
            {"username", "=", u}

        }).get("id", "username", "password");

        UserPermissions up = new UserPermissions();

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
        return false;
    }
}
