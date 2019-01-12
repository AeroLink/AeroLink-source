
import Config.DatabaseConfig;
import Model.Sample;
import Model.UserPermissions;
import Model.Users;
import Synapse.Crypt;
import Synapse.DB.MSSQL;
import Synapse.DB.MYSQL;
import Synapse.Database;
import Synapse.Model;
import Synapse.Session;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lei
 */
public class NewClass {

    private static Socket socket;

    public static void main(String[] args) {

        try {
            //FXMLS.HR1.ClassFiles.HR1_GenerateEC.generateEC("Software Engineer", "PHP 15,000.00", LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)), "Lecias, Ariel Jr. Calio", "Ma. Eden Ramoneda", "MIS Office");

            socket = IO.socket("http://127.0.0.1:5000");
            socket.connect();
        } catch (URISyntaxException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }   

}
