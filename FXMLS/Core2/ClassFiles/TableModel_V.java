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
 * @author jpeg
 */
public class TableModel_V {

    public StringProperty originV;
    public StringProperty APHregularV;
    public StringProperty APHxlV;
    public StringProperty APHssV;
    public StringProperty APHaV;
    public StringProperty APksmallV;
    public StringProperty APKlargeV;
    public StringProperty KBminiV;
    public StringProperty KBsmallV;
    public StringProperty KBslimV;
    public StringProperty KBmediumV;
    public StringProperty KBlargeV;
    public StringProperty KBxlV;
    public StringProperty KBd1V;

    public TableModel_V(String originV, String APHregularV,
            String APHxlV, String APHssV, String APHaV,
            String APksmallV, String APKlargeV, String KBminiV,
            String KBsmallV, String KBslimV, String KBmediumV,
            String KBlargeV, String KBxlV, String KBd1V) {
        this.originV = new SimpleStringProperty(originV);
        this.APHregularV = new SimpleStringProperty(APHregularV);
        this.APHxlV = new SimpleStringProperty(APHxlV);
        this.APHssV = new SimpleStringProperty(APHssV);
        this.APHaV = new SimpleStringProperty(APHaV);
        this.APksmallV = new SimpleStringProperty(APksmallV);
        this.APKlargeV = new SimpleStringProperty(APKlargeV);
        this.KBminiV = new SimpleStringProperty(KBminiV);
        this.KBsmallV = new SimpleStringProperty(KBsmallV);
        this.KBslimV = new SimpleStringProperty(KBslimV);
        this.KBmediumV = new SimpleStringProperty(KBmediumV);
        this.KBlargeV = new SimpleStringProperty(KBlargeV);
        this.KBxlV = new SimpleStringProperty(KBxlV);
        this.KBd1V = new SimpleStringProperty(KBd1V);
    }
}
