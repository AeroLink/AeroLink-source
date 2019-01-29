/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Administrative;

/**
 *
 * @author Onodera-Chan
 */
public class fortblreqstoring {
    
    public fortblreqstoring(){}
    private String docno;
    private String docname;
    private String docdep;
    private String doccat;
    private String docstat;

    public fortblreqstoring(String docno, String docname, String docdep, String doccat, String docstat) {
        this.docno = docno;
        this.docname = docname;
        this.docdep = docdep;
        this.doccat = doccat;
        this.docstat = docstat;
    }

    /**
     * @return the docno
     */
    public String getDocno() {
        return docno;
    }

    /**
     * @param docno the docno to set
     */
    public void setDocno(String docno) {
        this.docno = docno;
    }

    /**
     * @return the docname
     */
    public String getDocname() {
        return docname;
    }

    /**
     * @param docname the docname to set
     */
    public void setDocname(String docname) {
        this.docname = docname;
    }

    /**
     * @return the docdep
     */
    public String getDocdep() {
        return docdep;
    }

    /**
     * @param docdep the docdep to set
     */
    public void setDocdep(String docdep) {
        this.docdep = docdep;
    }

    /**
     * @return the doccat
     */
    public String getDoccat() {
        return doccat;
    }

    /**
     * @param doccat the doccat to set
     */
    public void setDoccat(String doccat) {
        this.doccat = doccat;
    }

    /**
     * @return the docstat
     */
    public String getDocstat() {
        return docstat;
    }

    /**
     * @param docstat the docstat to set
     */
    public void setDocstat(String docstat) {
        this.docstat = docstat;
    }
    
}
