/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import financialextraction.XBRLHandler;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import rssdata.RSSFilingData;

/**
 * Reads a specific xbrl file and stores the extracted values in a Report object.
 * @author Fabian
 */
public class ReportReader {
    
    /**
     * Creates a ReportReader
     */
    public ReportReader() {
        
    }

    /**
     * Reads a xbrl file.
     *
     * @param xbrlFile
     * @param rssData
     * @return a Report containing all the financial gaap items and pertinent data for that particular report
     */
    public Report readReport(File xbrlFile, RSSFilingData rssData) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        XBRLHandler handler = new XBRLHandler(rssData.getType());
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException ex) {
        } catch (SAXException ex) {
        }
        
        try {
            saxParser.parse(xbrlFile, handler); // passing the xbrl file and the customized handler to extract xbrl data
            
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Report report = handler.getReport();
        report.setRssData(rssData); 
        return report;
    }
    
}
