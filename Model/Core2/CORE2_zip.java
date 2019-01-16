/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Core2;

/**
 *
 * @author JPEG
 */
public class CORE2_zip extends Synapse.Model{
    public CORE2_zip(){
        this.initTable("tbl_core2_zip");
        setColumns("zip_id","zipcode");
    }
}
