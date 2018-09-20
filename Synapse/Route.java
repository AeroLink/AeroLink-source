/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author ARIELLECIAS
 */
public class Route implements IModule{
    
    public static HashMap routes = new HashMap();
    public static HashMap routePermission = new HashMap();
    
    @Override
    public void init() {
        for(String[] link : Session.links){
            routes.put(link[0], link[1]);
        }
        
        for(String[] link : Session.links){
            System.err.println(Arrays.asList(link));
            routePermission.put(link[0], link[2]);
        }
    
    }
    
}
