
import companies.Company;
import companies.CompanyCreator;
import companies.CompanyIO;
import csv.CSVCreator;
import download.XBRLFileCleaner;
import download.XBRLUnzipper;
import download.XBRLZipDownloader;
import java.io.IOException;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import report.Report;
import report.ReportIO;
import rssdata.RSSDataDownloader;
import rssdata.RSSFilingData;
import time.DateCounter;

/**
 * The structure in this class is only a test structure convenient for error
 * handling and debugging
 *
 * @author Fabian
 */
public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        downloadZIP();
        
        XBRLUnzipper unzipper = new XBRLUnzipper();
        try {
            unzipper.unzipFiles();
        } catch (Exception ex) {
            System.err.println("Error unzipping files");
        }

        XBRLFileCleaner cleaner = new XBRLFileCleaner();

        cleaner.deleteNonXBRLFiles();

        ReportIO io = new ReportIO();

        io.writeReports(io.readFinancialReportsFromXBRLDocuments());

        System.out.println("Adding description");

        System.out.println("Creating compoanies");
        CompanyCreator cc = new CompanyCreator(io.readReportsFromFolder());
        List<Company> companies = cc.createCompaniesWithReports();
        CompanyIO cio = new CompanyIO();
        cio.writeCompaniesToFolder(companies);

        CSVCreator creator = new CSVCreator();

        List<Company> companiesFromFolder = cio.readCompaniesFromFolder();

        System.out.println("Creating  CSV");

        companiesFromFolder.forEach(c -> creator.createCSV(c));

       // creator.createCSV(cio.readCompaniesFromFolder().get(8));
    }

    private static void downloadZIP() {
        RSSDataDownloader rssDownloader = new RSSDataDownloader();
        DateCounter dc = new DateCounter();
        XBRLZipDownloader zipDownloader = new XBRLZipDownloader();

        while (dc.hasNotEnteredFuture()) {
            List<RSSFilingData> rssData = rssDownloader.downlaodRSSData(dc.countYear(), dc.countMonth());
            System.out.println(rssData.size());
            System.out.println(rssData.get(0).toString());
            System.exit(0);
            for (int i = 0; i < rssData.size(); i++) {
                try {
                    zipDownloader.downloadXBRLZip(rssData.get(i));
                    return;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
              
            }
        }
    }

}
