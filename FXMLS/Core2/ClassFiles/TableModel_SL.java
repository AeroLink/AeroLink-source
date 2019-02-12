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
public class TableModel_SL {

    public StringProperty originSL;
    public StringProperty APHregularSL;
    public StringProperty APHxlSL;
    public StringProperty APHssSL;
    public StringProperty APHaSL;
    public StringProperty APksmallSL;
    public StringProperty APKlargeSL;
    public StringProperty KBminiSL;
    public StringProperty KBsmallSL;
    public StringProperty KBslimSL;
    public StringProperty KBmediumSL;
    public StringProperty KBlargeSL;
    public StringProperty KBxlSL;
    public StringProperty KBd1SL;

    public TableModel_SL(String originSL, String APHregularSL,
            String APHxlSL, String APHssSL, String APHaSL,
            String APksmallSL, String APKlargeSL, String KBminiSL,
            String KBsmallSL, String KBslimSL, String KBmediumSL,
            String KBlargeSL, String KBxlSL, String KBd1SL) {
        this.originSL = new SimpleStringProperty(originSL);
        this.APHregularSL = new SimpleStringProperty(APHregularSL);
        this.APHxlSL = new SimpleStringProperty(APHxlSL);
        this.APHssSL = new SimpleStringProperty(APHssSL);
        this.APHaSL = new SimpleStringProperty(APHaSL);
        this.APksmallSL = new SimpleStringProperty(APksmallSL);
        this.APKlargeSL = new SimpleStringProperty(APKlargeSL);
        this.KBminiSL = new SimpleStringProperty(KBminiSL);
        this.KBsmallSL = new SimpleStringProperty(KBsmallSL);
        this.KBslimSL = new SimpleStringProperty(KBslimSL);
        this.KBmediumSL = new SimpleStringProperty(KBmediumSL);
        this.KBlargeSL = new SimpleStringProperty(KBlargeSL);
        this.KBxlSL = new SimpleStringProperty(KBxlSL);
        this.KBd1SL = new SimpleStringProperty(KBd1SL);
    }
}
