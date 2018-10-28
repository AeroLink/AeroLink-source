/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS;

import javafx.application.Application;
import javafx.stage.Stage;
import Synapse.Form;
import javafx.stage.StageStyle;

/**
 *
 * @author Lei
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Core.Bootstrap.build();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
