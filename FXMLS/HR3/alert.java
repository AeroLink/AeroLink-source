/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author my
 */
class alert {

    static void setContentText(String saved) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void showAndWait() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void JOPTION()
    {
        
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setContentText("INFUCKYOU");
                   Optional <ButtonType> result = alert.showAndWait();
                   if(result.get() == ButtonType.OK){
                       System.out.println("OKAY");
                       
                   }else {
                       System.out.println("Cancel");
                   }
                   
    }
}
