/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.util.HashMap;

/**
 *
 * @author ARIELLECIAS
 */
public class Route implements IModule{
    
    public static HashMap routes = new HashMap();

    @Override
    public void init() {
        for(String[] link : Session.links){
            routes.put(link[0], link[1]);
        }
    }
    
}
