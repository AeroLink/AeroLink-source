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
public class TableModel_I {

    public StringProperty originI;
    public StringProperty APHregularI;
    public StringProperty APHxlI;
    public StringProperty APHssI;
    public StringProperty APHaI;
    public StringProperty APksmallI;
    public StringProperty APKlargeI;
    public StringProperty KBminiI;
    public StringProperty KBsmallI;
    public StringProperty KBslimI;
    public StringProperty KBmediumI;
    public StringProperty KBlargeI;
    public StringProperty KBxlI;
    public StringProperty KBd1I;

    public TableModel_I(String originI, String APHregularI,
            String APHxlI, String APHssI, String APHaI,
            String APksmallI, String APKlargeI, String KBminiI,
            String KBsmallI, String KBslimI, String KBmediumI,
            String KBlargeI, String KBxlI, String KBd1I) {
        this.originI = new SimpleStringProperty(originI);
        this.APHregularI = new SimpleStringProperty(APHregularI);
        this.APHxlI = new SimpleStringProperty(APHxlI);
        this.APHssI = new SimpleStringProperty(APHssI);
        this.APHaI = new SimpleStringProperty(APHaI);
        this.APksmallI = new SimpleStringProperty(APksmallI);
        this.APKlargeI = new SimpleStringProperty(APKlargeI);
        this.KBminiI = new SimpleStringProperty(KBminiI);
        this.KBsmallI = new SimpleStringProperty(KBsmallI);
        this.KBslimI = new SimpleStringProperty(KBslimI);
        this.KBmediumI = new SimpleStringProperty(KBmediumI);
        this.KBlargeI = new SimpleStringProperty(KBlargeI);
        this.KBxlI = new SimpleStringProperty(KBxlI);
        this.KBd1I = new SimpleStringProperty(KBd1I);
    }
}
