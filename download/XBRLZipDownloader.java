/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import rssdata.RSSFilingData;
/**
 * Downloads the zip files with the help of the RSS data extracted previpusly
 * @author Fabian
 */
public class XBRLZipDownloader {

    
    XBRLPathValidator validator;
    /**
     * Creates the download and the root direcotry in which the folders and files should be stored
     */
    public XBRLZipDownloader() {
        // ensures that the directory in which the filings are written to exists and not overwirtten
        File xbrlDirectory = new File(XBRLFilePaths.XBRL_FILING_DIRECTORY);
        if (!xbrlDirectory.exists()) {
            xbrlDirectory.mkdir();
        }
        this.validator = new XBRLPathValidator();
    }
    /**
     * Writes the downloaded file to the hard drive and saves it in the folder structure: date/type/cik.xml
     * @param rssData
     * @throws IOException 
     */
    public void downloadXBRLZip(RSSFilingData rssData) throws IOException {
        // gets a system independant file sperator
        String fileSeparator = "" + File.separatorChar;
        // Creates a file path for the output path. The regex reconstructs the format in which
        // the date is represented in the rss data, by removing the day of the month
        // A folder is created for every individual zip file and RSSFilingData object
        String outputPath = XBRLFilePaths.XBRL_FILING_DIRECTORY + fileSeparator
                + rssData.getDate().replaceAll("/([0-9]{2})/", "-") // changes the format of the date to (Month - Year)
                + fileSeparator
                + rssData.getType()
                + fileSeparator
                + rssData.getCik()
                + fileSeparator
                + rssData.getCik();
        
        // makes sure the directoris exists in which the file is written to
        validator.validatePath(outputPath);
        // Downloads the zip file and stores it in the given path
        System.out.println(rssData.getURL());
        System.exit(0);
        FileUtils.copyURLToFile(new URL(rssData.getURL()), new File(outputPath + ".zip"));
        
        System.out.println(outputPath + ".rsd" + "   output path");
        
        // Saves the rssobjects in the same folder
        saveRSSFilingData(outputPath + ".rsd", rssData);
    }
    
    /**
     * Saves the RSSFilingData in the same folder of the downloaded xbrl data
     * @param outputPath
     * @param rssData 
     */
    private void saveRSSFilingData(String outputPath, RSSFilingData rssData){
        try {
            FileOutputStream os = new FileOutputStream(new File(outputPath));
            ObjectOutputStream objStream = new ObjectOutputStream(os);
            objStream.writeObject(rssData);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
