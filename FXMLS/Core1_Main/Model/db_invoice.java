/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Core1_Main.Model;

/**
 *
 * @author lemnovo
 */
public class db_invoice {
    
    private String invoice_no;
    private String invoice_date;
    private String po_no;
    private String ref_no;

    public db_invoice(String invoice_no, String invoice_date, String po_no, String ref_no) {
        this.invoice_no = invoice_no;
        this.invoice_date = invoice_date;
        this.po_no = po_no;
        this.ref_no = ref_no;
    }

    /**
     * @return the invoice_no
     */
    public String getInvoice_no() {
        return invoice_no;
    }

    /**
     * @param invoice_no the invoice_no to set
     */
    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    /**
     * @return the invoice_date
     */
    public String getInvoice_date() {
        return invoice_date;
    }

    /**
     * @param invoice_date the invoice_date to set
     */
    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    /**
     * @return the po_no
     */
    public String getPo_no() {
        return po_no;
    }

    /**
     * @param po_no the po_no to set
     */
    public void setPo_no(String po_no) {
        this.po_no = po_no;
    }

    /**
     * @return the ref_no
     */
    public String getRef_no() {
        return ref_no;
    }

    /**
     * @param ref_no the ref_no to set
     */
    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }
    
    
    
}
