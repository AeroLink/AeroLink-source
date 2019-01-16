/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2;

import FXMLS.Log2.ClassFiles.Log2_Fleet_ManagementClass;
import FXMLS.Log2.ClassFiles.Log2_Vehicle_ReservationClass;
import FXMLS.USM.Controllers.IUsers;
import Model.Log2_Fleet_Management;
import Model.Log2_Vehicle_Reservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

/**
 * FXML Controller class
 *
 * @author Randelle
 */
public class Log2_Vehicle_ReservationController implements Initializable {

    ObservableList<Log2_Vehicle_ReservationClass> data = FXCollections.observableArrayList();
    
    ObservableList<Log2_Vehicle_ReservationClass> monitoringdata = FXCollections.observableArrayList();

    @FXML
    private JFXButton model;
    @FXML
    private ImageView imageview;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> vehiclecode;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> vehiclemodel;

    @FXML
    private TableView<Log2_Vehicle_ReservationClass> vehiclemaintenance;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> lastmaintenance;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> details;
    @FXML
    private JFXTextField typeofvehicle;
    @FXML
    private JFXTextField location;
    @FXML
    private JFXTextField vehicleid;
    @FXML
    private JFXTextField purpose;
    @FXML
    private JFXButton btnsubmitreservation;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass , String> name;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass , String> daterented;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass , String> time;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass , String> timeend;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass , String> status;
    @FXML
    private TableColumn<Log2_Vehicle_ReservationClass, String> monitoringlocation;
    @FXML
    private TableView<Log2_Vehicle_ReservationClass> tblmonitoring;

    /**-
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loaddata();
        Cols();
        
        reserveCols();
        reserveloaddata();
        
         btnsubmitreservation.setOnMouseClicked(e -> Save());

    }

    private void Cols() {
        vehiclecode.setCellValueFactory(new PropertyValueFactory<>("vehiclecode"));
        vehiclemodel.setCellValueFactory(new PropertyValueFactory<>("vehiclemodel"));
        lastmaintenance.setCellValueFactory(new PropertyValueFactory<>("lastmaintenance"));
         details.setCellValueFactory(new PropertyValueFactory<>("button"));
       

        
    }

    private void loaddata() {
        data.removeAll(data);
        data.addAll(new Log2_Vehicle_ReservationClass("1kz23","Yamaha","07/02/18"));
        data.addAll(new Log2_Vehicle_ReservationClass("5kf2m","Ford","04/12/18"));
        data.addAll(new Log2_Vehicle_ReservationClass("6ls4k7","Honda","11/22/18"));
        vehiclemaintenance.getItems().addAll(data);

    }

    private void reserveCols() {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        monitoringlocation.setCellValueFactory(new PropertyValueFactory<>("monitoringlocation"));
        daterented.setCellValueFactory(new PropertyValueFactory<>("daterented"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeend.setCellValueFactory(new PropertyValueFactory<>("timeend"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void reserveloaddata() {
        monitoringdata.removeAll(monitoringdata);
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Truck", "Batangas", "10/21/18", "6am", "11am", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Motor", "Cubao", "10/25/18", "9am", "1pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Van", "Makati", "10/16/18", "11am", "5pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Car", "Laguna", "11/11/18", "7am", "2pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Truck", "Bulacan", "11/26/18", "10am", "3pm", "Ongoing"));
        monitoringdata.addAll(new Log2_Vehicle_ReservationClass("Van", "Marikina", "12/21/18", "1pm", "7pm", "Ongoing"));
        tblmonitoring.getItems().addAll(monitoringdata);
    }

    @FXML
    private void btn_selectmodel(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();

        Parent parent = loader.load(getClass().getResource("Log2_Vehicle_Reservation_SelectV.fxml"));

        Scene scene = new Scene(parent);

        stage.setFullScreen(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.show();
    }

    
     public void Save() {
        
         Log2_Vehicle_Reservation vr = new Log2_Vehicle_Reservation();

        try {

            String[][] vr_data
                    = {
                        {"purpose", purpose.getText()},
                        {"location", location.getText()},
                        {"typeofvehicle", typeofvehicle.getText()},
                        {"vehicleid", vehicleid.getText()}
                        
                       
                    };

            vr.insert(vr_data);
            
          
            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setContentText("Saved");
            saved.showAndWait();

        } catch (Exception e) {
            
        }
    

    }


  
}
