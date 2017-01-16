/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialextraction;

import items.Descriptions;
import report.FilingDictionary;
import org.xml.sax.Attributes;

/**
 * Validates the elements and attributes of the xbrl document
 * @author Fabian
 */
public class XBRLValidator {

    private boolean extractFlag, qFlag, companyNameFlag, fisicalYearFlag;
    
    private final Descriptions descriptions;

    
    public XBRLValidator(Descriptions descriptions){
        this.descriptions = descriptions;
    }
    
    /**
     * Checks if the element is a gaap element and if so the method permits
     * extraction of the element's value
     *
     * @param element
     * @param attr
     * @return true if the element passed is a gaap element and false if it is
     * not
     */
    public boolean check(String element, Attributes attr) {
        if (element.contains(FilingDictionary.ITEM) && isItemRelevant(element.replaceAll("us-gaap:", ""))) {
            extractFlag = true;
            return true;
        } else if (element.contains(FilingDictionary.COMPANY_NAME)) {
            companyNameFlag = true;
        } else if (element.contains(FilingDictionary.FISICAL_YEAR)) {
            fisicalYearFlag = true;
        } else if (element.contains(FilingDictionary.Q)) {
            qFlag = true;
        }
        return false;
    }


    /**
     *
     * @return true if the xbrl extractor may extract the value of the gaap item
     */
    public boolean isGaapValue() {
        boolean flag = extractFlag;
        extractFlag = false;
        return flag;
    }

    public boolean isFisicalYearValue() {
        boolean flag = fisicalYearFlag;
        fisicalYearFlag = false;
        return flag;
    }

    public boolean isQValue() {
        boolean flag = qFlag;
        qFlag = false;
        return flag;
    }

    public boolean isCompanyName() {
        boolean flag = companyNameFlag;
        companyNameFlag = false;
        return flag;
    }
    private boolean isItemRelevant(String element){
       return descriptions.getDescriptionByGaap(element) != null;
    }

}
