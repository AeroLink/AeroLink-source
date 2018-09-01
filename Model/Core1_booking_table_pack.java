/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;





public class Core1_booking_table_pack {
    
    private String pack_id; 
    private String pack_type;
    private String pack_quantity;
    private String unit;
    private String dimension;
    private String weight;
    
    public Core1_booking_table_pack(String pack_id, String pack_type, String pack_quantity, String unit, String dimension, String weight){
        
        this.pack_id = pack_id;
         this.pack_type = pack_type;
          this.pack_quantity = pack_quantity;
           this.unit = unit;
            this.dimension = dimension;
             this.weight = weight;
        

    }

    /**
     * @return the pack_id
     */
    public String getPack_id() {
        return pack_id;
    }

    /**
     * @param pack_id the pack_id to set
     */
    public void setPack_id(String pack_id) {
        this.pack_id = pack_id;
    }

    /**
     * @return the pack_type
     */
    public String getPack_type() {
        return pack_type;
    }

    /**
     * @param pack_type the pack_type to set
     */
    public void setPack_type(String pack_type) {
        this.pack_type = pack_type;
    }

    /**
     * @return the pack_quantity
     */
    public String getPack_quantity() {
        return pack_quantity;
    }

    /**
     * @param pack_quantity the pack_quantity to set
     */
    public void setPack_quantity(String pack_quantity) {
        this.pack_quantity = pack_quantity;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the dimension
     */
    public String getDimension() {
        return dimension;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }
    
      
}


