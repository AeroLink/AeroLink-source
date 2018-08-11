/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

/**
 *
 * @author ARIELLECIAS
 */
public class Module {
    
    private IModule module;

    public Module(IModule module){
        this.module = module;        
    }
    
    public void init() {
        this.module.init();
    }
    
}
