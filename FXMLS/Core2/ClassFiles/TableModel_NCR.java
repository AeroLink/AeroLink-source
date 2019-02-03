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
public class TableModel_NCR {

    public StringProperty originNCR;
    public StringProperty APHregular;
    public StringProperty APHxl;
    public StringProperty APHss;
    public StringProperty APHa;
    public StringProperty APksmall;
    public StringProperty APKlarge;
    public StringProperty KBmini;
    public StringProperty KBsmall;
    public StringProperty KBslim;
    public StringProperty KBmedium;
    public StringProperty KBlarge;
    public StringProperty KBxl;
    public StringProperty KBd1;

    public TableModel_NCR(String originNCR, String APHregular,
            String APHxl, String APHss, String APHa,
            String APksmall, String APKlarge, String KBmini,
            String KBsmall, String KBslim, String KBmedium,
            String KBlarge, String KBxl, String KBd1) {
        this.originNCR = new SimpleStringProperty(originNCR);
        this.APHregular = new SimpleStringProperty(APHregular);
        this.APHxl = new SimpleStringProperty(APHxl);
        this.APHss = new SimpleStringProperty(APHss);
        this.APHa = new SimpleStringProperty(APHa);
        this.APksmall = new SimpleStringProperty(APksmall);
        this.APKlarge = new SimpleStringProperty(APKlarge);
        this.KBmini = new SimpleStringProperty(KBmini);
        this.KBsmall = new SimpleStringProperty(KBsmall);
        this.KBslim = new SimpleStringProperty(KBslim);
        this.KBmedium = new SimpleStringProperty(KBmedium);
        this.KBlarge = new SimpleStringProperty(KBlarge);
        this.KBxl = new SimpleStringProperty(KBxl);
        this.KBd1 = new SimpleStringProperty(KBd1);
    }
}
