/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core2.Reports;

/**
 *
 * @author jpeg
 */
public class branch {

    private String bcode;
    private String bcountry;
    private String bcity;

    public branch(String bcode, String bcountry, String bcity) {
        this.bcode = bcode;
        this.bcountry = bcountry;
        this.bcity = bcity;
    }

    /**
     * @return the bcode
     */
    public String getBcode() {
        return bcode;
    }

    /**
     * @return the bcountry
     */
    public String getBcountry() {
        return bcountry;
    }

    /**
     * @return the bcity
     */
    public String getBcity() {
        return bcity;
    }

}
