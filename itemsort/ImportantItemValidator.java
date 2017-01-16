/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsort;

/**
 *
 * @author Fabian
 */
public class ImportantItemValidator {
    public boolean validateImportantItem(String itemClassification){
        String[] importantItemNames = new String[]{"revenue", "income", "profit", "assets", "liabilities", "liability", "earning", "dividend", "share", "equity", "outstanding", "sale", "debt"};
        for(String importantItem : importantItemNames){
            if(itemClassification.toLowerCase().contains(importantItem)){
                System.out.println(true);
                return true;
            }
        }
        return false;
    }
}
