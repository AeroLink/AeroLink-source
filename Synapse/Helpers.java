/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Synapse;

/**
 *
 * @author Lei
 */
public class Helpers {
    
    public static Boolean isNumeric(String str){
        
        Boolean passed = true;
        
        for(char s : str.toCharArray()){
            if (Character.isDigit(s)) {
                passed = false;
                break;
            } 
        }
        
        return passed;
    }
    
    public static String combine(String[] cols, String separator){
        String final_string = "";
        for(int i = 0; i < cols.length; i++){
            if(i == (cols.length - 1)) {
                final_string += cols[i];
            }else{
                final_string += cols[i] + separator + " ";
            }
        }
        
        return final_string;
    }
    
    public static String combine(Object[] cols, String separator){
        String final_string = "";
        for(int i = 0; i < cols.length; i++){
            if(i == (cols.length - 1)) {
                final_string += cols[i];
            }else{
                final_string += cols[i] + separator + " ";
            }
        }
        
        return final_string;
    }
    

    
    public static String Prepared_combine(int length, String separator){
        String final_string = "";
        for(int i = 0; i < length; i++){
            if(i == (length - 1)) {
                final_string += "?";
            }else{
                final_string += "?" + separator + " ";
            }
        }
        
        return final_string;
    }
    
}
