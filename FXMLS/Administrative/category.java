    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author Onodera-Chan
 */
public class category {
    private String directory1 = "src/FXMLS/Administrative/RetrievedFiles/";
    private Connection con = DBconnection.con();
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    ObservableList<tbl_document_archieve> documentstable1 = FXCollections.observableArrayList();
    
    public void openfile(TableView<tbl_retrieved_files> filestable){
        try{
     tbl_retrieved_files trf = filestable.getItems().get(filestable.getSelectionModel().getSelectedIndex());
     File pdfFile = new File(directory1+trf.getDocname()+"-"+trf.getCatname()+".pdf");
    if (pdfFile.exists())
    {
     if (Desktop.isDesktopSupported())
     {
      try
      {
       Desktop.getDesktop().open(pdfFile);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
     }
     else
      {
       System.out.println("Awt Desktop is not supported!");
      }
    }
    else
    {
     System.out.println("File is not exists!");
    }
    }catch(Exception ex){
        AlertBox.display("Alert", "No File Selected");
     }
   }
    
    
    public void openfile1(TableView<tbl_document_archieve> tda){
        
        try{
     tbl_document_archieve tda1 = tda.getItems().get(tda.getSelectionModel().getSelectedIndex());
     File pdfFile = new File(directory1+tda1.getDocno()+"-"+tda1.getDocname()+".pdf");
    if (pdfFile.exists())
    {
     if (Desktop.isDesktopSupported())
     {
      try
      {
       Desktop.getDesktop().open(pdfFile);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
     }
     else
      {
       System.out.println("Awt Desktop is not supported!");
      }
    }
    else
    {
     AlertBox.display("Alert", "Document is not Retrieved");
    }
    }catch(Exception ex){
        AlertBox.display("Alert", "Document is not Retrieved");
     }
   }
    
    
    }
    
        
      
    

