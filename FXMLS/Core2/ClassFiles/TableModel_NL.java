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
public class TableModel_NL {

    public StringProperty originNL;
    public StringProperty APHregularNL;
    public StringProperty APHxlNL;
    public StringProperty APHssNL;
    public StringProperty APHaNL;
    public StringProperty APksmallNL;
    public StringProperty APKlargeNL;
    public StringProperty KBminiNL;
    public StringProperty KBsmallNL;
    public StringProperty KBslimNL;
    public StringProperty KBmediumNL;
    public StringProperty KBlargeNL;
    public StringProperty KBxlNL;
    public StringProperty KBd1NL;

    public TableModel_NL(String originNL, String APHregularNL,
            String APHxlNL, String APHssNL, String APHaNL,
            String APksmallNL, String APKlargeNL, String KBminiNL,
            String KBsmallNL, String KBslimNL, String KBmediumNL,
            String KBlargeNL, String KBxlNL, String KBd1NL) {
        this.originNL = new SimpleStringProperty(originNL);
        this.APHregularNL = new SimpleStringProperty(APHregularNL);
        this.APHxlNL = new SimpleStringProperty(APHxlNL);
        this.APHssNL = new SimpleStringProperty(APHssNL);
        this.APHaNL = new SimpleStringProperty(APHaNL);
        this.APksmallNL = new SimpleStringProperty(APksmallNL);
        this.APKlargeNL = new SimpleStringProperty(APKlargeNL);
        this.KBminiNL = new SimpleStringProperty(KBminiNL);
        this.KBsmallNL = new SimpleStringProperty(KBsmallNL);
        this.KBslimNL = new SimpleStringProperty(KBslimNL);
        this.KBmediumNL = new SimpleStringProperty(KBmediumNL);
        this.KBlargeNL = new SimpleStringProperty(KBlargeNL);
        this.KBxlNL = new SimpleStringProperty(KBxlNL);
        this.KBd1NL = new SimpleStringProperty(KBd1NL);
    }
}
