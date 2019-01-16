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
public class CORE2_discount extends Synapse.Model{
    public CORE2_discount(){
        this.initTable("tbl_core2_discount");
        setColumns("subject","discount");
    }
}
