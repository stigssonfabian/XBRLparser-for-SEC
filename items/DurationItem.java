/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.io.Serializable;
import org.joda.time.Interval;

/**
 * A Duration item is an item that which value has been accrued in a given period
 * @author Fabian
 */
public class DurationItem extends Item implements Serializable{
    
    
    private Interval duration;
    
    /**
     * Creates a Duration time object with the given classification (GAAP)
     * @param description 
     */
    public DurationItem(Description description) {
        super(description);
    }
    /**
     * Creates a duration time from the given data of the Item object.
     * @param item 
     */
    public DurationItem(Item item){
        this(item.getDescription());
        this.setContextRef(item.getCotnextRef());
        this.setUnit(item.getUnit());
        this.setValue(item.getValue());
    }
    /**
     * 
     * @return the duration for which the data of the item has been accumulated.
     */
    public Interval getDuration() {
        return duration;
    }
    /**
     * Sets the duration for which the the data of the item has been accumulated.
     * @param duration 
     */
    public void setDuration(Interval duration) {
        this.duration = duration;
    }
    
    
}
