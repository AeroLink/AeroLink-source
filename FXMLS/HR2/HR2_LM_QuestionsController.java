/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Eden Ramoneda
 */
public class HR2_LM_QuestionsController implements Initializable {

    @FXML
    private Pane question_format;
    @FXML
    private Pane multiple_choice_pane;
    @FXML
    private Pane true_or_false_pane;
    @FXML
    private JFXComboBox<String> choose_question_format;

    String question_format_choice_one = "MULTIPLE CHOICE";
    String question_format_choice_two = "TRUE OR FALSE";
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        true_or_false_pane.setVisible(false);
    
        //populate choose question format
        ObservableList<String> select_question_format = FXCollections.observableArrayList(question_format_choice_one, question_format_choice_two);
        choose_question_format.setItems(select_question_format);
        choose_question_format.valueProperty().addListener(e -> cmbMouseClicked());
    }    
    
     
    @FXML
    private void cmbMouseClicked() {
        try{
            
            if(String.valueOf(choose_question_format.getSelectionModel().getSelectedItem()).contains(question_format_choice_one)) {
                multiple_choice_pane.setVisible(true);
                true_or_false_pane.setVisible(false);
            }
            
            if(String.valueOf(choose_question_format.getSelectionModel().getSelectedItem()).contains(question_format_choice_two)) {
                true_or_false_pane.setVisible(true);
                multiple_choice_pane.setVisible(false);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
     
}
