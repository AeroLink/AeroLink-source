/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.util.HashMap;

/**
 *
 * @author Lei
 */
public class ModularPermissionConfig {
    
    public static HashMap modules = new HashMap();
    
    public static void initModuleConfig(){
        modules.put("HR", "canAccessHR");
        modules.put("CORE", "canAccessCORE");
        modules.put("FINCANCE", "canAccessFINANCE");
        modules.put("ADMIN", "canAccessADMIN");
        modules.put("LOG", "canAccessLOG");
    }
   
}
