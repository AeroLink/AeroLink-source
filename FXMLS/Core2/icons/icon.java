package FXMLS.Core2.icons;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class icon {
    private static final String IMAGE_LOC = "/Assets/300 x 300.png";
    
    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(IMAGE_LOC));
    }
}
