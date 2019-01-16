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
public class Core2_WebQuestion extends Synapse.Model{
    public Core2_WebQuestion(){
        this.initTable("tbl_core2_webcomment");
        setColumns("id","name","email","comment");
    }
}
