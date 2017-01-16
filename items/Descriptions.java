/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.Iterator;
import java.util.List;

/**
 * A list that holds descriptions a let the implements use function
 * @author Fabian
 */
public class Descriptions {
    private List<Description> descriptions;
    /**
     * Creates a descriptions object with the gioven descriptions
     * @param descriptions 
     */
    public Descriptions(List<Description> descriptions){
        this.descriptions = descriptions;
    }
    /**
     * Gets th Description
     * @param gaap
     * @return 
     */
    public Description getDescriptionByGaap(String gaap){
        Iterator<Description> it = descriptions.iterator();
        while(it.hasNext()){
          Description description = it.next();
                    
          if(description.getGaap().equals(gaap)){
              return description;
          }
          
        }
        
        return null;
    }
    
}
