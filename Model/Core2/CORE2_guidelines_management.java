/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Core2;

/**
 *
 * @author jpeg
 */
public class CORE2_guidelines_management extends Synapse.Model{
    
    public CORE2_guidelines_management(){
        this.initTable("tbl_core2_guidelines_policy");
        setColumns("description");
    }
    
    public CORE2_guidelines_management(String trigger){
        
        switch(trigger){
            case "package-sorting":
                this.initTable("tbl_core2_package_sorting");
                setColumns("description");
                break;
            case "terms-condition":
                this.initTable("tbl_core2_terms_condition");
                setColumns("description");
                break;
            case "prohibited-item":
                this.initTable("tbl_core2_prohibited");
                setColumns("description");
                break;
            case "package-inspection":   
                this.initTable("tbl_core2_package_inspection");
                setColumns("description");
                break;
            default:
                break;
        }
    }
    
}
