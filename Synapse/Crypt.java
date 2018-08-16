/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ARIELLECIAS
 */
public class Crypt {

    public Crypt() {
    
    }
    
    public static String Encrypt(String target) {
        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, CreateSecretKey());
            AlgorithmParameters params = cipher.getParameters();
            byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] ciphertext = cipher.doFinal(target.getBytes("UTF-8"));
            
            ArrayList<Object> l = new ArrayList<>();
        
            for(byte b : ciphertext) {
                String hex = (int) b < 0 ? "n" + Integer.toHexString(Math.abs(b)) : Integer.toHexString(b) ; 
                l.add(hex + "ü");
            }

            l.add("xipvx");

            for(byte b : iv) {
                String hex = (int) b < 0 ? "n" + Integer.toHexString(Math.abs(b)) : Integer.toHexString(b) ; 
                l.add(hex + "ü");
            }

            String finalString = "";

            for(Object obj : l) {
                finalString += obj;
            }
            
            return finalString;
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidParameterSpecException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String Decrypt(String encrypted){
        
        String[] arr = encrypted.split("xipvx");
        
        String[] arr2 = arr[0].split("ü");
        String[] arr1 = arr[1].split("ü");
        
        ByteBuffer b = ByteBuffer.allocate(arr2.length);
        
        for(String a : arr2) {
            if(a != null && a != "") {
                int value = a.contains("n") ? Math.negateExact(Integer.parseUnsignedInt(a.substring(1), 16)) : Integer.parseUnsignedInt(a, 16);
                b.put((byte) value);
            }
        }
        
        ByteBuffer iv = ByteBuffer.allocate(arr1.length);
        
        for(String a : arr1) {
            if(a != null && a != "") {
                int value = a.contains("n") ? Math.negateExact(Integer.parseUnsignedInt(a.substring(1), 16)) : Integer.parseUnsignedInt(a, 16);
                iv.put((byte) value);
            }
        }
        
        try {
            
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, CreateSecretKey() , new IvParameterSpec(iv.array()));
            return new String(c.doFinal(b.array()), "UTF-8");
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    private static SecretKey CreateSecretKey(){
        try {
            
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            char[] p = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
            byte[] s = "test".getBytes("UTF-8");
            KeySpec spec = new PBEKeySpec(p, s, 65536, 256);
            SecretKey tmp = f.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            
            return secret;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeySpecException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
