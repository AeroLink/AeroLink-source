/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import javafx.collections.ObservableList;  
 import javafx.concurrent.Service;  
 import javafx.concurrent.Task;  


public class a2 extends Service<ObservableList<fortblreqstoring>> {  
      @Override  
      protected Task createTask() {  
           return new a1();  
      }  
 }  
