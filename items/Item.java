/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a financial data item and its value, the unit in which it is
 * represented in, the period which it concerns, the value of it and the xbrl
 * denotation for the item
 *
 * @author Fabian
 */
public class Item implements Serializable {

    // The value of the element
    private String value;

    // The unit in which the value is represented in
    private String unit;

    // the corresponding id to the date of which the item is associated with
    private String contextRef;

    private Description description;

    /**
     * Constructs an item with the specific gaap type
     *
     * @param description
     */
    public Item(Description description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.value);
        hash = 89 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    /**
     * Creates an item
     */
    public Item() {

    }
    /**
     * Gets the underlying value of the item
     * @return 
     */
    public String getValue() {
        return value;
    }
    /**
     * Set value of item
     * @param value 
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * 
     * @return the unit of the item
     */
    public String getUnit() {
        return unit;
    }
    /**
     * Sets the unit of the item
     * @param unit 
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    /**
     * Sets the contextRef which is used to recoginize the Instant or duration of which this item is presented
     * @param contextRef 
     */
    public void setContextRef(String contextRef) {
        this.contextRef = contextRef;
    }
    /**
     * Gets the contextRef
     * @return the context ref
     */
    public String getCotnextRef() {
        return this.contextRef;
    }
    /**
     * Gets the description
     * @return 
     */
    public Description getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description 
     */
    public void setDescription(Description description) {
        this.description = description;
    }

}
