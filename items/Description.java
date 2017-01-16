/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Description of the financial item
 * @author Fabian
 */
public class Description implements Serializable{
    
    private String gaap, label, description; 
    /**
     *  Gets the gaap value
     * @return the gaap value of the item
     */
    public String getGaap() {
        return gaap;
    }
    /**
     * Sets the gaap value for the description
     * @param gaap 
     */
    public void setGaap(String gaap) {
        this.gaap = gaap;
    }
    /**
     * 
     * @return the label of the dscription
     */
    public String getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.gaap);
        hash = 89 * hash + Objects.hashCode(this.label);
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
        final Description other = (Description) obj;
        if (!Objects.equals(this.gaap, other.gaap)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }
    /**
     * Sets the label of the description
     * @param label 
     */
    public void setLabel(String label) {
        this.label = label;
    }   
    /**
     * 
     * @return gets the description 
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
