/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Lei
 */
public class FileTransfer {

    public static Boolean SendFile(String server, String fpath) {

        try {
            Socket socket = null;

            socket = new Socket(server, 4000);

            File file = new File(fpath);

            byte[] b = new byte[(int) file.length()];

            FileInputStream in = new FileInputStream(file);
            BufferedInputStream Buff_is = new BufferedInputStream(in);
            DataInputStream Data_is = new DataInputStream(Buff_is);
            Data_is.readFully(b, 0, b.length);

            OutputStream out = socket.getOutputStream();

            DataOutputStream Data_out = new DataOutputStream(out);
            Data_out.writeUTF(file.getName());
            Data_out.writeLong(b.length);
            Data_out.write(b, 0, b.length);
            Data_out.flush();

            out.write(b, 0, b.length);
            out.flush();

            out.close();
            Data_out.close();
            socket.close();
            
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
