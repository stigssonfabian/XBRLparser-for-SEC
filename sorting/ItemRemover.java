/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import items.Item;
import java.util.Iterator;
import java.util.Set;

/**
 * Removes items that with the removable interface
 * @author Fabian
 * @param <T>
 */
 public class ItemRemover <T extends Item> {
    
    private Set<T> items;
    /**
     * Creates an items remover with items
     * @param items 
     */
    public ItemRemover(Set<T> items){
        this.items = items;
    }
    /**
     * Removes all the items that are removable
     * @param removeable 
     */
    public void remove(Removable removeable){
        Iterator<T> iterator = items.iterator();
        
        while(iterator.hasNext()){
            T t = iterator.next();
            if(removeable.remove()){
                iterator.remove();
            }
        }
    }
    /**
     * 
     * @return the new set which items being removable removed 
     */
    public Set<T> getItems(){
        return this.items;
    }
    
}
