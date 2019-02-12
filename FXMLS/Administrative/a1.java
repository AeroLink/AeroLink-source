/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

 import java.sql.Connection;  
 import java.sql.ResultSet;  
 import javafx.collections.FXCollections;  
 import javafx.collections.ObservableList;  
 import javafx.concurrent.Task;  

public class a1 extends Task<ObservableList<fortblreqstoring>> {      
      @Override protected ObservableList<fortblreqstoring> call() throws Exception {  
           for (int i = 0; i < 500; i++) {  
                updateProgress(i, 500);  
                Thread.sleep(2);  
           }  
           ObservableList<fortblreqstoring> accountList = FXCollections.observableArrayList();  
           Connection c ;  
           Connection con = DBconnection.con();
           //SQL FOR SELECTING ALL OF USER ACCOUNTS  
           String SQL = "SELECT * from aerolink.admin_document_reqstoring";  
           //ResultSet  
           ResultSet rs = con.createStatement().executeQuery(SQL);  
           while(rs.next()){  
                accountList.add(new fortblreqstoring(""+rs.getInt("Documents No"),rs.getString("Documents Name"),rs.getString("Department"),rs.getString("Category"),rs.getString("Status")));     
           }  
           return accountList;  
      }  
 } 
