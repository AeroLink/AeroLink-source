/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author Lei
 */
public class Form {
    
    private String p = "";
    private Stage stage = new Stage();
    private Parent parent;
    
    private FXMLLoader loader;
    
    public Form(String Path){
        this.parent = null;
        try {

            this.loader = new FXMLLoader();
            this.parent = loader.load(getClass().getResource(Path));
    
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Form() {}

    
    public void setPath(String Path) {
        this.p = Path;
    }
    
    public String getPath(){
        return this.p;
    }

    public Parent getParent() {
        return this.parent;
    }
    
    
    public void open(){
        
        Scene s = new Scene(this.parent);
        
        this.stage.initStyle(StageStyle.DECORATED);
        this.stage.setScene(s);
        this.stage.show();
    }
    
    public void open(StageStyle stageSytle){
        
        Scene s = new Scene(this.parent);
        
        this.stage.initStyle(stageSytle);
        this.stage.setScene(s);
        this.stage.show();
    }

    public Stage getStage() {
        return stage;
    }
    
    
    
    public void open(StageStyle stageSytle, Boolean isFullScreen){
        
        Scene s = new Scene(this.parent);
        
        if (isFullScreen) {
           this.stage.setFullScreen(true);
           this.stage.fullScreenExitKeyProperty().setValue(KeyCombination.NO_MATCH);
           this.stage.setAlwaysOnTop(true);
        }
        
        this.stage.initStyle(stageSytle);
        this.stage.setScene(s);
        this.stage.show();
    }
    
    public static void close(Stage target){
        target.close();
    }
    
    public static void close(Window w){
        ( (Stage) w ).close();
    }
    
    
    public static void close(Node n) {
        ( (Stage) n.getScene().getWindow()).close();
    }

    public FXMLLoader getLoader() {
        return loader;
    }
    
    
}
