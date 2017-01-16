/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import items.Item;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import rssdata.RSSFilingData;

/**
 * Represents a report of financial us-gaap data and other pertinent information to the report. 
 *
 * @author Fabian
 */
public abstract class Report implements FiscalYear, Serializable{
    private final Set<Item> items;
    private String companyName;
    private RSSFilingData rssData;
    /**
     * Creates a report 
     */
    public Report() {
        this.items = new HashSet<>();
    }
    /**
     * Adds an item to the set which holds all the items that a report has.
     * @param item 
     */
    public void addItem(Item item) {
        items.add(item);
    }
    /**
     * Sets the company of the issuer of the report.
     * @param name 
     */
    public void setCompanyName(String name) {
        this.companyName = name;
    }
    /**
     * 
     * @return the company name of the issuer of the report
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 
     * @return all the items in the report.
     */
    public Set<Item> getItemsOfReport() {
        return items;
    }
    /**
     * 
     * @return the meta data for the report
     */
    public RSSFilingData getRssData() {
        return rssData;
    }
    /**
     * Sets the meta data for this report.
     * @param rssData 
     */
    public void setRssData(RSSFilingData rssData) {
        this.rssData = rssData;
    }
    /**
     * Gets the year of which this report was files
     * @return 
     */
    public String getYear(){
        return rssData.getDate().split("/")[2];
    }

    
    
    
}
