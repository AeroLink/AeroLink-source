/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author lemnovo
 */
public class Core1_booking_table {
    
    private String customer_id; 
    private String fn;
    private String mid;
    private String ln;
    private String email;
    private String contact;
    private String company;
    private String house;
    private String street;
    private String brgy;
    private String city;
    private String province;
    private String zip;
    
    
    

    public Core1_booking_table(String customer_id, String fn , String mid ,String ln ,String email, String contact, String company, String house, String street, String brgy, String city, String province, String zip) {
          
            this.customer_id = customer_id ;
            this.fn = fn;
            this.mid = mid;
            this.ln = ln;
            this.email = email; 
             this.contact = contact; 
             this.company = company; 
             this.house = house;
             this.street = street; 
             this.brgy = brgy;
             this.city = city;
             this.province = province;
             this.zip = zip; 
    }

    /**
     * @return the customer_id
     */
    public String getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the fn
     */
    public String getFn() {
        return fn;
    }

    /**
     * @param fn the fn to set
     */
    public void setFn(String fn) {
        this.fn = fn;
    }

    /**
     * @return the mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * @return the ln
     */
    public String getLn() {
        return ln;
    }

    /**
     * @param ln the ln to set
     */
    public void setLn(String ln) {
        this.ln = ln;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the house
     */
    public String getHouse() {
        return house;
    }

    /**
     * @param house the house to set
     */
    public void setHouse(String house) {
        this.house = house;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the brgy
     */
    public String getBrgy() {
        return brgy;
    }

    /**
     * @param brgy the brgy to set
     */
    public void setBrgy(String brgy) {
        this.brgy = brgy;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

   
    
}