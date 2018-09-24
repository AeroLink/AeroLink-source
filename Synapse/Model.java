/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lei
 */
public class Model {

    private String global_table = "";
    //TODO : Update and Delete .. Joins and Relationships;
    //TODO : Re illuminate - will fix redudancy later
    private String joinConstruct = "";
    private Boolean joined = false;

    private String groupBy = "";

    public enum JOIN {
        INNER, LEFT, RIGHT
    }

    private String whereConstruct = "";

    private String finalQuery = "SELECT * From ";

    private ArrayList<Object> finalValues = new ArrayList<Object>();

    private ArrayList<Object> whereValues = new ArrayList<>();
    private Boolean where = false;
    private PreparedStatement pst;
    private static String[] cols;

    public static void setTable(String table) {
        Session.table = (Session.provider.equals("mssql") ? Session.schema + "." : "") + table;
    }

    public void initTable(String table) {
        this.global_table = (Session.provider.equals("mssql") ? Session.schema + "." : "") + table;
    }

    public static void setColumns(String... vals) {
        cols = vals;
    }

    public String[] getCols() {
        return cols;
    }

    public void Where_PrepareStatementSession() {
        try {
            this.pst = Session.INSTANCE.getConnection().prepareStatement(this.finalQuery);
            for (int i = 1; i <= this.whereValues.size(); i++) {
                System.out.println(this.whereValues.get(i - 1));
                this.pst.setObject(i, this.whereValues.get(i - 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List get() {

        if (Session.INSTANCE.hasConnection()) {

            this.finalQuery += this.global_table + " ";
            try {

                if (this.joined) {
                    this.finalQuery += this.joinConstruct;
                    this.joined = false;
                }

                if (this.where) {
                    this.finalQuery += "WHERE " + this.whereConstruct;

                    if (!"".equals(this.groupBy)) {
                        this.finalQuery += this.groupBy;
                    }

                    this.Where_PrepareStatementSession();

                } else {
                    if (!"".equals(this.groupBy)) {
                        this.finalQuery += this.groupBy;
                    }
                    this.pst = Session.INSTANCE.getConnection().prepareStatement(this.finalQuery);
                }

                System.out.println(this.finalQuery);
                this.clear();
                return R2SL.convert(pst.executeQuery());
            } catch (SQLException ex) {
                Response.ErrorResponse();
                System.out.println("SQL Error -> " + ex.getMessage());
            }

        }

        return null;
    }

    public List get(String... cols) {

        this.finalQuery = "SELECT " + Helpers.combine(cols, ",") + " From " + this.global_table + " ";

        if (Session.INSTANCE.hasConnection()) {

            try {

                if (this.joined) {
                    this.finalQuery += this.joinConstruct;
                    this.joined = false;
                }

                if (this.where) {
                    this.finalQuery += "WHERE " + this.whereConstruct;

                    if (!"".equals(this.groupBy)) {
                        this.finalQuery += this.groupBy;
                    }

                    this.Where_PrepareStatementSession();

                } else {
                    if (!"".equals(this.groupBy)) {
                        this.finalQuery += this.groupBy;
                    }
                    this.pst = Session.INSTANCE.getConnection().prepareStatement(this.finalQuery);
                }
                System.out.println(this.finalQuery);
                this.clear();
                return R2SL.convert(pst.executeQuery());
            } catch (SQLException ex) {

                Response.ErrorResponse();
                System.out.println("SQL Error -> " + ex.getMessage());
            }

        }

        return null;
    }

    public Model where(Object[][] values) {

        this.where = true;
        this.whereConstruct = "";

        for (int i = 0; i < values.length; i++) {

            if (values[i].length < 3) {
                System.out.println(Response.ORM_ERR_01);
                break;
            }

            if (i == (values.length - 1)) {
                this.whereConstruct += values[i][0] + " " + values[i][1] + " ?";
            } else {
                this.whereConstruct += values[i][0] + " " + values[i][1] + " ? AND ";
            }

            this.whereValues.add(values[i][2]);

        }

        return this;
    }

    public Model where(String col, String operator, String value) {
        this.where = true;
        this.whereConstruct += col + " " + operator + " ? ";
        this.whereValues.add(value);

        return this;
    }

    public Model whereIn(String... values) {

        return this;
    }

    //insertions
    public Boolean insert(Object... vals) {

        if (Session.INSTANCE.hasConnection()) {

            String valC = "";

            for (int i = 0; i < vals.length; i++) {
                if (i == (vals.length - 1)) {
                    valC += "?";
                } else {
                    valC += "?, ";
                }
            }
            try {

                String Query = "INSERT INTO " + this.global_table + "(" + Helpers.combine(cols, ",") + ") VALUES (" + valC + ")";
                System.out.println(Query);
                this.pst = Session.INSTANCE.getConnection().prepareStatement(Query);

                for (int i = 1; i <= vals.length; i++) {
                    this.pst.setObject(i, vals[i - 1]);
                }

                return this.pst.execute();

            } catch (SQLException ex) {

                Response.ErrorResponse();
                System.out.println("SQL Error -> " + ex.getMessage());
            }

        }

        return false;
    }

    public Boolean insert(Object[][] vals) {
        if (Session.INSTANCE.hasConnection()) {
            String columns = "";
            List values = new ArrayList<>();
            for (int i = 0; i < vals.length; i++) {

                if (vals[i].length < 2) {
                    System.out.println(Response.ORM_ERR_02);
                    break;
                }

                if (i == (vals.length - 1)) {
                    columns += vals[i][0];
                } else {
                    columns += vals[i][0] + ",";
                }

                values.add(vals[i][1]);

            }
            String query = "";
            try {

                query = "INSERT INTO " + this.global_table + "(" + columns + ") VALUES (" + Helpers.Prepared_combine(values.toArray().length, ",") + ")";
                this.pst = Session.INSTANCE.getConnection().prepareStatement(query);
                Object[] sp = values.toArray();
                for (int i = 1; i <= sp.length; i++) {
                    this.pst.setObject(i, sp[i - 1]);
                }

                return this.pst.executeUpdate() != 0;

            } catch (SQLException ex) {

                Response.ErrorResponse();
                System.err.println("SQL Error -> " + ex.getMessage() + "\nSQL Query -> " + query + "\nSQL Values ->" + Arrays.asList(values));
            }
        }

        return false;
    }
    //end insertions

    public int insert(Object[][] vals, Boolean returnID) {
        if (Session.INSTANCE.hasConnection()) {
            String columns = "";
            List values = new ArrayList<>();
            for (int i = 0; i < vals.length; i++) {

                if (vals[i].length < 2) {
                    System.out.println(Response.ORM_ERR_02);
                    break;
                }

                if (i == (vals.length - 1)) {
                    columns += vals[i][0];
                } else {
                    columns += vals[i][0] + ",";
                }

                values.add(vals[i][1]);

            }

            String query = "";
            try {

                query = "INSERT INTO " + this.global_table + "(" + columns + ") VALUES (" + Helpers.Prepared_combine(values.toArray().length, ",") + ")";

                this.pst = returnID ? Session.INSTANCE.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : Session.INSTANCE.getConnection().prepareStatement(query);

                Object[] sp = values.toArray();
                for (int i = 1; i <= sp.length; i++) {
                    this.pst.setObject(i, sp[i - 1]);
                }

                Boolean success = this.pst.executeUpdate() != 0;

                return success ? (returnID ? Integer.parseInt(((HashMap) R2SL.convert(this.pst.getGeneratedKeys()).get(0)).get("GENERATED_KEYS").toString()) : 1) : 0;
            } catch (SQLException ex) {

                Response.ErrorResponse();
                ex.printStackTrace();
                System.err.println("SQL Error -> " + ex.getMessage());
                System.err.println(query);
            }
        }

        return 0;
    }

    //Update 
    public Model update(Object[][] vals) {

        this.finalQuery = "";
        this.finalValues = new ArrayList<Object>();

        if (Session.INSTANCE.hasConnection()) {

            String columns = "";

            for (int i = 0; i < vals.length; i++) {

                if (vals[i].length < 2) {
                    System.out.println(Response.ORM_ERR_02);
                    break;
                }

                if (i == (vals.length - 1)) {
                    columns += vals[i][0] + " = ?";
                    this.finalValues.add(vals[i][1]);
                } else {
                    columns += vals[i][0] + " = ?,";
                    this.finalValues.add(vals[i][1]);
                }

            }

            this.finalQuery = "UPDATE " + this.global_table + " SET " + columns;
            System.out.println(this.finalQuery);
            return this;

        }

        return null;
    }

    public Model delete() {

        if (Session.INSTANCE.hasConnection()) {

            this.finalQuery = "DELETE FROM " + this.global_table;
            return this;

        }

        return null;
    }

    public Boolean executeUpdate() {

        try {

            if (where) {

                this.pst = Session.INSTANCE.getConnection().prepareStatement(this.finalQuery + " WHERE " + this.whereConstruct);
                if (this.finalValues != null) {
                    this.finalValues.addAll(this.whereValues);
                    System.out.println(Arrays.asList(this.finalValues.toArray()));
                    for (int i = 1; i <= this.finalValues.size(); i++) {
                        this.pst.setObject(i, this.finalValues.get(i - 1));
                    }
                } else {
                    System.out.println(Arrays.asList(this.whereValues.toArray()));

                    for (int i = 1; i <= this.whereValues.size(); i++) {
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

            Response.ErrorResponse();
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    //Joins

    /**
     * Usage Example:
     *
     * Users.join(JOIN.INNER, "tbl_user_permissions", "user_id", "=",
     * "id").get();
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

        this.joinConstruct += joinProc + " JOIN " + table2 + " ON " + table2 + "." + table2_key + " " + logical_operator + " " + this.global_table + "." + table1_key + " ";
        this.joined = true;
        return this;
    }

    public Model join(JOIN joinProc, String table2, String table2_key, String logical_operator, String table1, String table1_key, Boolean otherJoin) {

        if (otherJoin) {
            this.joinConstruct += joinProc + " JOIN " + table2 + " ON " + table2 + "." + table2_key + " " + logical_operator + " " + table1 + "." + table1_key + " ";
            this.joined = true;
        }

        return this;
    }

    public Model join(JOIN joinProc, String table2, String table2_key, String table2_alias, String logical_operator, String table1_key) {

        this.joinConstruct += joinProc + " JOIN " + table2 + " as " + table2_alias + " ON " + table2_alias + "." + table2_key + " " + logical_operator + " " + this.global_table + "." + table1_key + " ";
        this.joined = true;
        return this;
    }

    /**
     *
     * Usage : Model.groupBy(column).get();
     *
     * @param column
     * @return
     */
    public Model groupBy(String column) {

        this.groupBy += " GROUP BY " + column;
        return this;
    }

    public void clear() {
        this.whereValues = new ArrayList<>();
        this.whereConstruct = "";
        this.where = false;
        this.groupBy = "";
        this.finalQuery = "Select * From ";
        this.joinConstruct = "";
        this.joined = false;
    }

}
