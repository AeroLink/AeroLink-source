/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author JPEG
 */
public class TableModel_FAQs {
    
    public StringProperty id;
    public StringProperty question;
    public StringProperty answer;
    
    public TableModel_FAQs(String id, String question, String answer){
        this.id = new SimpleStringProperty(id);
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
    }
   
    public String getId(){
        return id.get();
    }
    
    public String getQuestion(){
        return question.get();
    }
    public String getAnswer(){
        return answer.get();
    }
}
