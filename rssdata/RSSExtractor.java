package rssdata;

import org.xml.sax.Attributes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Holds the content of the extracted data from the rss feed.
 *
 * @author Fabian
 */
class RSSExtractor{

    RSSFilingData rssData = new RSSFilingData();

    /**
     * Sets the url of the enclosure tag to the passed value 
     * @param attr 
     */
    public void extractURL(Attributes attr) {
        rssData.setURL(attr.getValue(0));
    }
    /**
     * Sets the cik of the edgar:cikNumber tag to the passed value 
     * @param content 
     */
    public void extractCIK(String content) {
        rssData.setCik(content);
    }
    /**
     * Sets the date of the edgar:filingDate tag to the passed value 
     * @param content 
     */
    public void extractDate(String content) {
        rssData.setDate(content);
    }
    /**
     * Sets the type of the description tag to the passed value 
     * @param content 
     */
    public void extractType(String content) {
        rssData.setType(content);
    }
    /**
     * Resets the variable holding the RSS data to a new RSSFilingData object
     */
    public void reset() {
        this.rssData = new RSSFilingData();
    }
    /**
     * 
     * @return The RSSFilingData object with its variables set
     */
    public RSSFilingData getExtractedData() {
        return this.rssData;
    }

  

}
