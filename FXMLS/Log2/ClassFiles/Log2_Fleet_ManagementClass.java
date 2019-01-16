/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.Log2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randelle
 */
public class Log2_Fleet_ManagementClass extends Synapse.Model {
   
    
    
    //pro
    public SimpleStringProperty pvehicleno;
    public SimpleStringProperty ptypeofvehicle;
    public SimpleStringProperty pdestination;
    public SimpleStringProperty ptime;
    public SimpleStringProperty pstatus;
    
    
    //proin
    public SimpleStringProperty proinvehicleno;
    public SimpleStringProperty prointypeofvehicle;
    public SimpleStringProperty proinitemname;
    public SimpleStringProperty proindestination;
    public SimpleStringProperty prointime;
    public SimpleStringProperty proinremarks;

   
  
  
    
      //pro
      public Log2_Fleet_ManagementClass(String pvehicleno,String ptypeofvehicle,String pdestination,String ptime,String pstatus){ 
       this.pvehicleno = new SimpleStringProperty(pvehicleno);
       this.ptypeofvehicle = new SimpleStringProperty(ptypeofvehicle);
       this.pdestination = new SimpleStringProperty(pdestination);
       this.ptime = new SimpleStringProperty(ptime);
       this.pstatus = new SimpleStringProperty(pstatus);
        
       }
      
      //proin
      public Log2_Fleet_ManagementClass(String proinvehicleno,String prointypeofvehicle,String proinitemname,String proindestination,String prointime,
                                          String proinremarks){ 
       this.proinvehicleno = new SimpleStringProperty(proinvehicleno);
       this.prointypeofvehicle = new SimpleStringProperty(prointypeofvehicle);
       this.proinitemname = new SimpleStringProperty(proinitemname);
       this.proindestination = new SimpleStringProperty(proindestination);
       this.prointime = new SimpleStringProperty(prointime);
       this.proinremarks = new SimpleStringProperty(proinremarks); 
       }

   
             
             
             //pro
             
             public String getPvehicleno()
             {
                 return pvehicleno.get();
             }
             
              public String getPtypeofvehicle()
             {
                 return ptypeofvehicle.get();
             }
              
               public String getPdestination()
             {
                 return pdestination.get();
             }
               
                public String getPtime()
             {
                 return ptime.get();
             }
                
                 public String getPstatus()
             {
                 return pstatus.get();
             }
                 
          //proin
                public String getProinvehicleno()
             {
                 return proinvehicleno.get();
             }
                
                public String getProintypeofvehicle()
             {
                 return prointypeofvehicle.get();
             }
                
                public String getProinitemname()
             {
                 return proinitemname.get();
             }
                
                public String getProindestination()
             {
                 return proindestination.get();
             }
                
                public String getProintime()
             {
                 return prointime.get();
             }
                
                public String getProinremarks()
             {
                 return proinremarks.get();
             }
                 
                 
                 
                 
                 
                 
             
             
    }
