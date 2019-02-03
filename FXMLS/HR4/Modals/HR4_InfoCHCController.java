/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.Filler.HR4_EmpInfoFill;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_InfoCHCController implements Initializable {

    @FXML
    private Tab tbl_pers;
    @FXML
    private TextField ln;
    @FXML
    private TextField fn;
    @FXML
    private TextField mn;
    @FXML
    private TextField suffix;
    @FXML
    private TextField gender;
    @FXML
    private TextField cs;
    @FXML
    private TextField wei;
    @FXML
    private TextField hei;
    @FXML
    private TextField cont_no;
    @FXML
    private TextField email;
    @FXML
    private TextField adds;
    @FXML
    private DatePicker dob;
    @FXML
    private DatePicker pob;
    @FXML
    private Label ec;
    @FXML
    private TableView<?> tbl_fb;
    @FXML
    private TableView<?> tbl_educ;
    @FXML
    private TableView<?> tbl_sems;
    @FXML
    private TableView<?> tbl_works;
    @FXML
    private Tab tbl_certs;
    @FXML
    private TableView<?> tbl_awards;
    @FXML
    private TableView<?> tbl_govs;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        //For 201 file
        ec.setText(HR4_EmpInfoFill.a);
        ln.setText(HR4_EmpInfoFill.b);
        fn.setText(HR4_EmpInfoFill.c);
        mn.setText(HR4_EmpInfoFill.d);
        suffix.setText(HR4_EmpInfoFill.e);
        gender.setText(HR4_EmpInfoFill.f);
        cs.setText(HR4_EmpInfoFill.g);
        wei.setText(HR4_EmpInfoFill.h);
        hei.setText(HR4_EmpInfoFill.i);
        cont_no.setText(HR4_EmpInfoFill.j);
        email.setText(HR4_EmpInfoFill.k);
        adds.setText(HR4_EmpInfoFill.l);
        dob.setValue(LocalDate.parse(HR4_EmpInfoFill.m));
        pob.setValue(LocalDate.parse(HR4_EmpInfoFill.n));
    }    
    
}
