/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR4.Modals;

import FXMLS.HR4.ClassFiles.HR4_Deductions1Class;
import FXMLS.HR4.Filler.HR4_DeductionsFill;
import FXMLS.HR4.HR4_Compensation_and_PlanningController;
import FXMLS.HR4.Model.HR4_Deductions1Model;
import Synapse.Model;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jaeeeee
 */
public class HR4_AddNewDeductionTableController implements Initializable {

    @FXML
    private Label idxx;
    @FXML
    private Label title;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;
    @FXML
    private TextField e;
    @FXML
    private TextField f;
    @FXML
    private TextField g;
    @FXML
    private TextField h;
    @FXML
    private TextField i;
    @FXML
    private TextField j;
    @FXML
    private TextField k;
    @FXML
    private TextField l;
    @FXML
    private TextField m;
    @FXML
    private TextField n;
    @FXML
    private TextField o;
    @FXML
    private TableView<HR4_Deductions1Class> tbl_dd;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton clearBtn;
    @FXML
    private Label ddxd;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idxx.setText(HR4_DeductionsFill.a);
        title.setText(HR4_DeductionsFill.b);
        this.generateDeductionsTable1();
        this.populateDeductionsTable1();
        
        saveBtn.setOnMouseClicked(e ->{
            Save();
        });
        updateBtn.setOnMouseClicked(e ->{
           Update(); 
        });
        deleteBtn.setOnMouseClicked(e ->{
           Delete(); 
        });
        clearBtn.setOnMouseClicked(e ->{
           Clear(); 
        });
        tbl_dd.setOnMouseClicked(e -> {
        SelectedItems();
        });
    }   
    public void Save(){
        
    HR4_Deductions1Model fbr = new HR4_Deductions1Model();
        try
        {
           String[][] b_tbl =
        {
        {"deduc_code",idxx.getText()},
        {"C1",a.getText()},
        {"C2",b.getText()},
        {"C3",c.getText()},
        {"C4",d.getText()},
        {"C5",e.getText()},
        {"C6",f.getText()},
        {"C7",g.getText()},
        {"C8",h.getText()},
        {"C9",i.getText()},
        {"C10",j.getText()},
        {"C11",k.getText()},
        {"C12",l.getText()},
        {"C13",m.getText()},
        {"C14",n.getText()},
        {"C15",o.getText()},
        };           
           
        if(fbr.insert(b_tbl)){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("Added");
             alert.setContentText("Employee Added"); 
             alert.showAndWait();
             this.populateDeductionsTable1();
             
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.initStyle(StageStyle.UNDECORATED);
             alert.setTitle("ERROR");
             alert.setContentText("Error"); 
             alert.showAndWait();
        }
                                       
            }catch(Exception e)
               {
            e.printStackTrace();
               }
    
    }
    
    public void Update(){
        
    HR4_Deductions1Model mm = new HR4_Deductions1Model();

            Boolean ab = mm.where(new Object[][]{
                {"id", "=", ddxd.getText()}
            }).update(new Object[][]{
                {"C1", a.getText()},
                {"C2", b.getText()},
                {"C3", c.getText()},
                {"C4", d.getText()},
                {"C5", e.getText()},
                {"C6", f.getText()},
                {"C7", g.getText()},
                {"C8", h.getText()},
                {"C9", i.getText()},
                {"C10", j.getText()},
                {"C11", k.getText()},
                {"C12", l.getText()},
                {"C13", m.getText()},
                {"C14", n.getText()},
                {"C15", o.getText()}
                    
            }).executeUpdate();
             this.populateDeductionsTable1();
             
    }
    
    public void Delete(){
        
        HR4_Deductions1Model m = new HR4_Deductions1Model();
        m.delete().where(new Object[][]{
        {"id", "=", tbl_dd.getSelectionModel().getSelectedItem().z.getValue()}
        }).executeUpdate();
        this.populateDeductionsTable1();
        
    }
    
    public void Clear(){
        a.setText("");
        b.setText("");
        c.setText("");
        d.setText("");
        e.setText("");
        f.setText("");
        g.setText("");
        h.setText("");
        i.setText("");
        j.setText("");
        k.setText("");
        l.setText("");
        m.setText("");
        n.setText("");
        o.setText("");
    }
     
    public void SelectedItems(){
        HR4_Deductions1Class r = tbl_dd.getSelectionModel().getSelectedItem();
            a.setText(r.getC1());
            b.setText(r.getC2());
            c.setText(r.getC3());
            d.setText(r.getC4());
            e.setText(r.getC5());
            f.setText(r.getC6());
            g.setText(r.getC7());
            h.setText(r.getC8());
            i.setText(r.getC9());
            j.setText(r.getC10());
            k.setText(r.getC11());
            l.setText(r.getC12());
            m.setText(r.getC13());
            n.setText(r.getC14());
            o.setText(r.getC15());
            ddxd.setText(r.getID());
    }
    public void generateDeductionsTable1() {

        tbl_dd.getItems().clear();
        tbl_dd.getColumns().removeAll(tbl_dd.getColumns());
        TableColumn<HR4_Deductions1Class, String> C1 = new TableColumn<>("Column 1");
        TableColumn<HR4_Deductions1Class, String> C2 = new TableColumn<>("Column 2");
        TableColumn<HR4_Deductions1Class, String> C3 = new TableColumn<>("Column 3");
        TableColumn<HR4_Deductions1Class, String> C4 = new TableColumn<>("Column 4");
        TableColumn<HR4_Deductions1Class, String> C5 = new TableColumn<>("Column 5");
        TableColumn<HR4_Deductions1Class, String> C6 = new TableColumn<>("Column 6");
        TableColumn<HR4_Deductions1Class, String> C7 = new TableColumn<>("Column 7");
        TableColumn<HR4_Deductions1Class, String> C8 = new TableColumn<>("Column 8");
        TableColumn<HR4_Deductions1Class, String> C9 = new TableColumn<>("Column 9");
        TableColumn<HR4_Deductions1Class, String> C10 = new TableColumn<>("Column 10");
        TableColumn<HR4_Deductions1Class, String> C11 = new TableColumn<>("Column 11");
        TableColumn<HR4_Deductions1Class, String> C12 = new TableColumn<>("Column 12");
        TableColumn<HR4_Deductions1Class, String> C13 = new TableColumn<>("Column 13");
        TableColumn<HR4_Deductions1Class, String> C14 = new TableColumn<>("Column 14");
        TableColumn<HR4_Deductions1Class, String> C15 = new TableColumn<>("Column 15");
        

        //<editor-fold defaultstate="collapsed" desc="Cell value factories">
        C1.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().b);
        C2.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().c);
        C3.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().d);
        C4.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().e);
        C5.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().f);
        C6.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().g);
        C7.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().h);
        C8.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().i);
        C9.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().j);
        C10.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().k);
        C11.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().l);
        C12.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().m);
        C13.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().n);
        C14.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().o);
        C15.setCellValueFactory((TableColumn.CellDataFeatures<HR4_Deductions1Class, String> param) -> param.getValue().p);
        
        //</editor-fold>
        tbl_dd.getColumns().addAll(C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C13,C14,C15);
    }
    
    public void populateDeductionsTable1() {
        HR4_Deductions1Model dm1 = new HR4_Deductions1Model();
        try {
            List e = dm1
                    .join(Model.JOIN.INNER, "aerolink.tbl_hr4_deductions", "deduc_code", "tblD", "=", "deduc_code")
                    .where(new Object[][]{{"tblD.deduc_code", "=", idxx.getText().toString()}})
                                        .get(
                                            "aerolink.tbl_hr4_deductions1.id as id",
                                            "tblD.deduc_code as deduc_code",
                                            "C1",      
                                            "C2",
                                            "C3",
                                            "C4",
                                            "C5",
                                            "C6",
                                            "C7",
                                            "C8",
                                            "C9",
                                            "C10",
                                            "C11",
                                            "C12",
                                            "C13",
                                            "C14",
                                            "C15");
            Stored1(e);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
   
    public void Stored1(List b1) {
    ObservableList<HR4_Deductions1Class> obj = FXCollections.observableArrayList();
        obj.clear();
        try {
            for (Object d : b1) {

                HashMap hm = (HashMap) d;
                obj.add(
                        new HR4_Deductions1Class(
                                String.valueOf(hm.get("id")),
                                String.valueOf(hm.get("deduc_code")),
                                String.valueOf(hm.get("C1")),
                                String.valueOf(hm.get("C2")),
                                String.valueOf(hm.get("C3")),
                                String.valueOf(hm.get("C4")),
                                String.valueOf(hm.get("C5")),
                                String.valueOf(hm.get("C6")),
                                String.valueOf(hm.get("C7")),
                                String.valueOf(hm.get("C8")),
                                String.valueOf(hm.get("C9")),
                                String.valueOf(hm.get("C10")),
                                String.valueOf(hm.get("C11")),
                                String.valueOf(hm.get("C12")),
                                String.valueOf(hm.get("C13")),
                                String.valueOf(hm.get("C14")),
                                String.valueOf(hm.get("C15"))
                        ));

            }
            ddxd.setText(String.valueOf("id"));
            tbl_dd.setItems(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
