/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;



/**
 *
 * @author Lei
 */
public interface Integration{
    
    public <classModel extends Synapse.Model> classModel integrateTo(classModel c);
    
}
