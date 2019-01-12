/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR3.ClassFiles;

/**
 *
 * @author my
 */
public class leaveaccountClass {
    String L_ID, V_L, S_L, M_L, P_L, E_L,ID,Name,Gender,Age,Address,BirthDate,Date;
    public leaveaccountClass(String L_ID, String V_L, String S_L, String M_L, String P_L, String E_L){
    this.L_ID = L_ID;
    this.V_L = V_L;
    this.S_L = S_L;
    this.M_L = M_L;
    this.P_L = P_L;
    this.E_L = E_L;

    
    }
     public String getL_ID(){
        return L_ID;
    }
     public void setL_ID(String L_ID){
        this.L_ID = L_ID;
    }
       public String getV_L(){
        return V_L;
    }
     public void setV_L(String V_L){
        this.V_L = V_L;
    }
     
     
      public String getS_L(){
        return S_L;
    }
     public void setS_L(String S_L){
        this.S_L = S_L;
    }
     
    
      public String getM_L(){
        return M_L;
    }
     public void setM_L(String M_L){
        this.M_L = M_L;
    }
     
      public String getP_L(){
        return P_L;
    }
     public void setP_L(String P_L){
        this.P_L = P_L;
    }
     
      public String getE_L(){
        return E_L;
    }
     public void setE_L(String E_L){
        this.E_L = E_L;
    }
     
          public String getID(){
        return ID;
    }
           public String getName(){
        return Name;
    }
            public String getGender(){
        return Gender;
    }
             public String getAge(){
        return Age;
    }
              public String getDate(){
        return Date;
    }
               public String getBirthDate(){
        return BirthDate;
    }
               public String getAddress(){
        return Address;
    }     
    
}
