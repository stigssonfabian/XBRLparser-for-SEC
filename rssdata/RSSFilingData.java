package rssdata;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class stores important data needed to download filings
 * @author Fabian
 */
 public class RSSFilingData implements Serializable{
    
    private String type;
    private String date;
    private String url;
    private String cik;
    
    /**
     * Creates a RSSFilingData object
     */
    public RSSFilingData(){}
    
    /**
     * 
     * @return the type of the filing, 10-k(annual report), 10-q(quarterly report) or 20-f(foreign report)
     */
    public String getType() {
        return type;
    }
    /**
     * Sets the type of the filing
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * 
     * @return the date of which the filing was filed to SEC
     */
    public String getDate() {
        return date;
    }
    /**
     * Sets the date of which the filing was filed to the SEC
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * 
     * @return the url which points towards the filing file that contains the financial data of the company
     */
    public String getURL() {
        return url;
    }
    /**
     * Sets the url which points towards the filing file that contains the financial data of the company
     * @param url 
     */
    public void setURL(String url) {
        this.url = url;
    }
    /**
     * 
     * @return the Central Identification Number used to identify companies by the SEC
     */
    public String getCik() {
        return cik;
    }
    /**
     * Sets the Central Identification Number used by the SEC to identify companies
     * @param cik 
     */
    public void setCik(String cik) {
        this.cik = cik;
    }

    @Override
    public String toString() {
        return "RSSFilingData{" + "type=" + type + ", date=" + date + ", url=" + url + ", cik=" + cik + '}';
    }

    
    
    
}
