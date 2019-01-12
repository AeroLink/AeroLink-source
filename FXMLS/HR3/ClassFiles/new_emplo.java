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
public class new_emplo {
    String Id,  Name, Position;
    
      public new_emplo(String Id, String Name, String Position){
       this.Id = Id;
        this.Name = Name;
         this.Position = Position;
      }
      
      public String getId(){
            return Id;
        }
      public void setId(String Id){
        this.Id = Id;
    }
      
         public String getName(){
            return Name;
        }
      public void setName(String Name){
        this.Name = Name;
    }
      
         public String getPosition(){
            return Position;
        }
      public void setPosition(String Position){
        this.Position = Position;
    }
}
