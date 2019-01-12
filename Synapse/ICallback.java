/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

/**
 *
 * @author BlackMoon
 */
public interface ICallback<E, O> {
   public void result_set(E error, O obj);
}
