/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssdata;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Download RSSData
 *
 * @author Fabian
 */
public class RSSDataDownloader {

    /**
     * Creates A RSSData downloader
     */
    public RSSDataDownloader() {
    }

    /**
     * Downloads important meta data needed to locate the filings files on the
     * Internet
     *
     * @param year
     * @param month
     * @return a list of RSS data for the year and month in which the RSS filing
     * meta data was published
     */
    public List<RSSFilingData> downlaodRSSData(String year, String month) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        RSSHandler handler = new RSSHandler();

        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }

        try {
            System.out.println(RSSPaths.RSS_URL_ROOT_PATH + year + "-" + month + ".xml");
            
            URL url = new URL(RSSPaths.RSS_URL_ROOT_PATH + year + "-" + month + ".xml");
                        
            saxParser.parse(RSSPaths.RSS_URL_ROOT_PATH + year + "-" + month + ".xml", handler);

        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return handler.getFilingsWithDownLoadData();

    }
}
