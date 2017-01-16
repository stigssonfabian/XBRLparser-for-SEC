package rssdata;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Validates the RSS data
 * @author Fabian
 */
 class RSSValidator{
    
    
    /**
     * 
     * @param element
     * @return true if the tag denoting the cik number is passed
     */
    public boolean canExtractCIKNumber(String element){
        return element.equalsIgnoreCase("edgar:cikNumber");
    }
     /**
     * 
     * @param element
     * @return true if the tag denoting the description is passed
     */
    public boolean canExtractFilingType(String element){
        return element.equalsIgnoreCase("description");
    }
    /**
     * 
     * @param element
     * @return true if the tag denoting the filing date is passed
     */
    public boolean canExtractFilingDate(String element){
        return element.equalsIgnoreCase("edgar:filingDate");
    }
    /**
     * 
     * @param element
     * @return true if the tag denoting the enclosure is passed
     */
    public boolean canExtractFilingURL(String element){
        return element.equalsIgnoreCase("enclosure");
    }
    /**
     * 
     * @param endTag
     * @return true if the tag denoting the item is passed
     */
    public boolean isDone(String endTag){
        return endTag.equalsIgnoreCase("item");
    }
    /**
     * 
     * @param type
     * @return true if the passed value indicates and annual filing, quarterly filing or foreign filing
     */
    public boolean isCorrectType(String type){
        type = type.toLowerCase();
        
        if(type.contains("10-q") || type.contains("10-k")){
            return true;
        }else if(type.contains("20-f")){
            return true;
        }else return false;
        
    }


    
}
