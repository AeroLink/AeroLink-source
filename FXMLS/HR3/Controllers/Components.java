/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.Controllers;

import javafx.scene.Node;

/**
 *
 * @author my
 */
public class Components {
    public static void DisableComponents(Node[] d)
            {
                for(Node n : d)
                {
                    n.setDisable(true);
                }
            }
}
