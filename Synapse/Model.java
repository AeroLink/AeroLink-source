/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lei
 */
public class Model {


    //TODO : Update and Delete .. Joins and Relationships;
    //TODO : Re illuminate - will fix redudancy later
    private String joinConstruct = "";
    private Boolean joined = false;
    
    public enum JOIN {
        INNER, LEFT, RIGHT
    }
    
    private String whereConstruct = "";
    
    private String finalQuery;
    private ArrayList<Object> finalValues = new ArrayList<Object>();
            
    private ArrayList<Object> whereValues = new ArrayList<>();
    private Boolean where = false;
    private PreparedStatement pst;
    private static String[] cols;
    
    public static void setTable(String table) {
        Session.table = table;
    }
    
    public static void setColumns(String... vals ){
        cols = vals;
    }

    public String[] getCols() {
        return cols;
    }
    
    public List get(){
        
        String Query = "SELECT * From " + Session.table + " ";
        
        if (Session.INSTANCE.hasConnection()) {
            
            try {
                
                if(joined) {
                    Query += this.joinConstruct;
                    this.joined = false;
                }
                
                if(where){
                    Query += "WHERE " + this.whereConstruct;
                    this.pst = Session.INSTANCE.getConnection().prepareStatement(Query);
                    for(int i = 1; i <= this.whereValues.size(); i++) {
                        System.out.println(this.whereValues.get(i - 1));
                        this.pst.setObject(i, this.whereValues.get(i - 1));
                    }
                }else {
                    this.pst = Session.INSTANCE.getConnection().prepareStatement(Query);
                }
                
                this.clear();
                return R2SL.convert(pst.executeQuery());
            } catch (SQLException ex) {
                System.out.println("SQL Error -> " + ex.getMessage());
            }
            
        }
        
        return null;
    }
    
    public List get(String... cols){
        
        String Query = "SELECT " + Helpers.combine(cols, ",") + " From " + Session.table + " ";
        
        if (Session.INSTANCE.hasConnection()) {
            
            try {
                
                if(where){
                    Query += "WHERE " + this.whereConstruct;
                    this.pst = Session.INSTANCE.getConnection().prepareStatement(Query);
                    for(int i = 1; i <= this.whereValues.size(); i++) {
                        this.pst.setObject(i, this.whereValues.get(i - 1));
                    }
                }else {
                    this.pst = Session.INSTANCE.getConnection().prepareStatement(Query);
                }
                
                this.clear();
                return R2SL.convert(pst.executeQuery());
            } catch (SQLException ex) {
                System.out.println("SQL Error -> " + ex.getMessage());
            }
            
        }
        
        return null;
    }
    
    public Model where(Object[][] values){
        
        this.where = true;
        this.whereConstruct = "";
        
        for(int i = 0; i < values.length; i++) {
            
            if(values[i].length < 3) {
                System.out.println(Response.ORM_ERR_01);
                break;
            }
            
            if ( i == (values.length - 1)){
                this.whereConstruct += values[i][0] + " " + values[i][1] + " ?";
            }else {
                this.whereConstruct += values[i][0] + " " + values[i][1] + " ? AND ";
            }
            
            this.whereValues.add(values[i][2]);
            
        }
        
        return this;
    }
   
    public Model where(String col, String operator, String value){
        this.where = true;
        this.whereConstruct += col + " " + operator + " ? ";
        this.whereValues.add(value);
    
        return this;
    }
    
    public Model whereIn(String... values ) {
    
        return this;
    }
    
    //insertions
    public Boolean insert(Object... vals){
         
        if(Session.INSTANCE.hasConnection()) {
            
            String valC = "";
            
            for(int i = 0; i < vals.length; i++) {
                if(i == (vals.length - 1)) {
                    valC += "?";
                }else {
                    valC += "?, ";
                }
            }
            try {
                
                String Query = "INSERT INTO " + Session.table + "(" + Helpers.combine(cols, ",") + ") VALUES (" + valC + ")";
                System.out.println(Query);
                this.pst = Session.INSTANCE.getConnection().prepareStatement(Query);
                
                for(int i = 1; i <= vals.length; i++){
                    this.pst.setObject(i, vals[i - 1]);
                }
                
                return this.pst.execute();
                
            } catch(SQLException ex) {
                System.out.println("SQL Error -> " + ex.getMessage());
            }
            
        }
        
        return false;
    }
    
    public Boolean insert(Object[][] vals) {
        if (Session.INSTANCE.hasConnection()) {
            String columns = "", values = "";
            for(int i = 0; i < vals.length; i++) {
                
                if(vals[i].length < 2) {
                    System.out.println(Response.ORM_ERR_02);
                    break;
                }
                
                if(i == (vals.length - 1)) {
                    columns += vals[i][0];
                    values += vals[i][1];
                }else{
                    columns += vals[i][0] + ",";
                    values += vals[i][1] + ",";
                }
                
            }
            
            try{

                String query = "INSERT INTO " + Session.table + "(" + columns + ") VALUES ("+ Helpers.Prepared_combine(values.split(",").length, ",") +")"; 
                this.pst = Session.INSTANCE.getConnection().prepareStatement(query);
                String[] sp = values.split(",");
                for(int i = 1; i <= sp.length; i++){
                    this.pst.setObject(i, sp[i - 1]);
                }
                
                return this.pst.executeUpdate() != 0;

            }catch(SQLException ex ){
                System.out.println("SQL Error -> " + ex.getMessage());
            }
        }
        
        
        return false;
    }
    //end insertions

    
    //Update 
    public Model update(Object[][] vals){
    
        this.finalQuery = "";
        this.finalValues = new ArrayList<Object>();
        
        if (Session.INSTANCE.hasConnection()) {
            
            String columns = "";
            
            for(int i = 0; i < vals.length; i++) {
                
                if(vals[i].length < 2) {
                    System.out.println(Response.ORM_ERR_02);
                    break;
                }
                
                if(i == (vals.length - 1)) {
                    columns += vals[i][0] + " = ?";
                    this.finalValues.add(vals[i][1]);
                }else{
                    columns += vals[i][0] + " = ?,";
                    this.finalValues.add(vals[i][1]);
                }
                
            }
            
            this.finalQuery = "UPDATE " + Session.table + " SET " + columns ; 
            System.out.println(this.finalQuery);
            return this;

        }
        
        return null;
    }
    
    public Model delete(){
       
        if (Session.INSTANCE.hasConnection()) {
            
            this.finalQuery = "DELETE FROM " + Session.table;
            return this;

        }
        
        return null;
    }
    
    public Boolean executeUpdate(){
       
        try {
            
            
            if(where) {
                
                this.pst = Session.INSTANCE.getConnection().prepareStatement(this.finalQuery + " WHERE " + this.whereConstruct);
                if (this.finalValues != null) {
                    this.finalValues.addAll(this.whereValues);
                    System.out.println(Arrays.asList(this.finalValues.toArray()));
                    for(int i = 1; i <= this.finalValues.size(); i++){
                        this.pst.setObject(i, this.finalValues.get(i - 1));
                    }
                }else {
                    System.out.println(Arrays.asList(this.whereValues.toArray()));
                    
                    for(int i = 1; i <= this.whereValues.size(); i++){
                        this.pst.setObject(i, this.whereValues.get(i - 1));
                    }
                }
                
                
            }
            
            
            
            Boolean rt = this.pst.executeUpdate() != 0;
            
            this.finalQuery = "";
            this.finalValues = new ArrayList<Object>();
            this.clear();
            
            return rt;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    //Joins
    
    /**
     * Usage Example:
     * 
     * Users.join(JOIN.INNER, "tbl_user_permissions", "user_id", "=", "id").get();
     * 
     * 
     * @param joinProc
     * @param table2
     * @param table2_key
     * @param logical_operator
     * @param table1_key
     * @return
     */
    public Model join(JOIN joinProc, String table2, String table2_key, String logical_operator, String table1_key) {
        
        this.joinConstruct = joinProc + " JOIN " + table2 + " ON " + table2 + "." + table2_key +  " " + logical_operator + " " + Session.table + "." + table1_key + " ";
        this.joined = true;
        return this;
    }
    

    public void clear(){
        this.whereValues = new ArrayList<>();
        this.whereConstruct = "";
        this.where = false;
    }
    
}
