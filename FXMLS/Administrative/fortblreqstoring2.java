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
public class fortblreqstoring2 {
    private String docno;
    private String docname;
    private String doccat;
    private String doctime;
    private String docdate;
    private String docstat;

    public fortblreqstoring2(String docno, String docname, String doccat, String doctime, String docdate, String docstat) {
        this.docno = docno;
        this.docname = docname;
        this.doccat = doccat;
        this.doctime = doctime;
        this.docdate = docdate;
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
     * @return the doctime
     */
    public String getDoctime() {
        return doctime;
    }

    /**
     * @param doctime the doctime to set
     */
    public void setDoctime(String doctime) {
        this.doctime = doctime;
    }

    /**
     * @return the docdate
     */
    public String getDocdate() {
        return docdate;
    }

    /**
     * @param docdate the docdate to set
     */
    public void setDocdate(String docdate) {
        this.docdate = docdate;
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
