/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Onodera
 */
    

public class AlertBox {
    private static Connection con = DBconnection.con();
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;
    private static Alert a;
    private static Optional <ButtonType> action;
    
    public static void display(String title, String message){
    
      
          a = new Alert(AlertType.INFORMATION);
          a.setTitle(title);
          a.setHeaderText(null);
          a.setContentText(message);
          action = a.showAndWait();
     
   
    }
    
    public static void error(String title, String message){
    
     
          a = new Alert(AlertType.ERROR);
          a.setTitle(title);
          a.setHeaderText(null);
          a.setContentText(message);
          action = a.showAndWait();
    
   
    }
    
  
     public static void confirm(String title, String message, TextField txt,String query){
       
             a = new Alert(AlertType.CONFIRMATION);
             a.setTitle(title);
             a.setHeaderText(null);
             a.setContentText(message);
             
            action = a.showAndWait();
           
            if(action.get()== ButtonType.OK){
                try{
                    
                    pst = con.prepareStatement(query);
                    pst.setString(1,txt.getText());
                    pst.execute();
                    
                }catch(Exception ex){
                    System.out.print(ex.getMessage());
                  }
            }
     }
     
     public static void close(Button close){
         Stage st = (Stage) close.getScene().getWindow();
         st.close();    
     }
     
     public static void iconclose(FontAwesomeIconView close){
         Stage st = (Stage) close.getScene().getWindow();
         st.close();    
     }
     
     
     public void loadfxml(String fxml, Object obj){
         FXMLLoader load = new FXMLLoader();
         load.setLocation(getClass().getResource(fxml));
         try{
             load.load();
         }catch(Exception ex){
             System.out.print(ex.getMessage());
         }
         obj = load.getController();
         Parent p = load.getRoot();
         Stage st = new Stage();
         st.setScene(new Scene(p));
         st.setResizable(false);
         st.initStyle(StageStyle.UNDECORATED);
         st.initModality(Modality.APPLICATION_MODAL);
         st.show();
     }
    
     final static Tooltip tp = new Tooltip();
     public static void tooltip(String text, Button btn){
         tp.setText(text);
         btn.setTooltip(tp);
     }
     
     
     
     public void fxmlloader(String text,Pane pane) throws IOException{
       
       AnchorPane  p = FXMLLoader.load(getClass().getResource(text));
       pane.getChildren().setAll(p);
     }
     
     public static void cleartxt(Pane p){
     TextField txt = null;
     for(Node n: p.getChildren()){
     if(n.getClass().toString().contains("TextField")){
         txt = (TextField)n;
         txt.setText(null);
     }
     }  
     }
     
    
     
   
     
    
    
    
}


    
    
   

