/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.io.Serializable;
import org.joda.time.DateTime;

/**
 * An Instant item's value is reported as of that date rather than a value that is accumulated over time.
 * @author Fabian
 */
public class InstantItem extends Item implements Serializable{

    private DateTime instant;
    /**
     * Creates an isntant item with the specific classification
     * @param description 
     */
    public InstantItem(Description description) {
        super(description);
    }
    /**
     * Creates an instant item with the data from the passed Item.
     * @param item 
     */
    public InstantItem(Item item){
        this(item.getDescription());
        this.setContextRef(item.getCotnextRef());
        this.setUnit(item.getUnit());
        this.setValue(item.getValue());
    }
    /**
     * 
     * @return the instant of which the value of the item concern
     */
    public DateTime getInstant() {
        return instant;
    }
    /**
     * Sets the instant of which the value of the item concern
     * @param instant 
     */
    public void setInstant(DateTime instant) {
        this.instant = instant;
    }


  
   
   
    
}
