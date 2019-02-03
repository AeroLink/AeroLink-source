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
public class TableModel_M {
    public StringProperty originM;
    public StringProperty APHregularM;
    public StringProperty APHxlM;
    public StringProperty APHssM;
    public StringProperty APHaM;
    public StringProperty APksmallM;
    public StringProperty APKlargeM;
    public StringProperty KBminiM;
    public StringProperty KBsmallM;
    public StringProperty KBslimM;
    public StringProperty KBmediumM;
    public StringProperty KBlargeM;
    public StringProperty KBxlM;
    public StringProperty KBd1M;

    public TableModel_M(String originM, String APHregularM,
            String APHxlM, String APHssM, String APHaM,
            String APksmallM, String APKlargeM, String KBminiM,
            String KBsmallM, String KBslimM, String KBmediumM,
            String KBlargeM, String KBxlM, String KBd1M) {
        this.originM = new SimpleStringProperty(originM);
        this.APHregularM = new SimpleStringProperty(APHregularM);
        this.APHxlM = new SimpleStringProperty(APHxlM);
        this.APHssM = new SimpleStringProperty(APHssM);
        this.APHaM = new SimpleStringProperty(APHaM);
        this.APksmallM = new SimpleStringProperty(APksmallM);
        this.APKlargeM = new SimpleStringProperty(APKlargeM);
        this.KBminiM = new SimpleStringProperty(KBminiM);
        this.KBsmallM = new SimpleStringProperty(KBsmallM);
        this.KBslimM = new SimpleStringProperty(KBslimM);
        this.KBmediumM = new SimpleStringProperty(KBmediumM);
        this.KBlargeM = new SimpleStringProperty(KBlargeM);
        this.KBxlM = new SimpleStringProperty(KBxlM);
        this.KBd1M = new SimpleStringProperty(KBd1M);
    }
}
