package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import rssdata.RSSFilingData;
import download.XBRLFilePaths;

/**
 * Handles the input and output of reports that have been stored or are to be
 * stored on the hard drive
 *
 * @author Fabian
 */
public class ReportIO {

    /**
     * Creates ReportIO
     */
    public ReportIO() {

    }

    /**
     * Writes the extracted gaap data to the hard drive in form of a Report
     * object.
     *
     * @return
     */
    public List<Report> readFinancialReportsFromXBRLDocuments() {
        // gets the system independant file sperator
        // Creates a file path for the output path. The regex reconstructs the format in which
        // the date is represented in the rss data, by removing the day of the month
        Collection<File> xbrlFiles = FileUtils.listFiles(new File(XBRLFilePaths.XBRL_FILING_DIRECTORY), new String[]{"xml"}, true);
        ReportReader reader = new ReportReader();

        Iterator<File> it = xbrlFiles.iterator();

        List<Report> reports = new ArrayList<>();

        while (it.hasNext()) {
            File xbrlFile = it.next();
            try {

                reports.add(reader.readReport(xbrlFile, getAssociatedRSSFilingData(xbrlFile.getAbsolutePath())));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        return reports;

    }

    /**
     * This method writes the report objects passed to the hard drive to the
     * specified folder in ReportPaths
     *
     * @param reports
     */
    public void writeReports(List<Report> reports) {
        PathValidator validator = new PathValidator();
        validator.validatePath(); // validates the path of the output stream to make sure the directories exists of which the data is to be stored

        reports.stream().forEach((report) -> {

            try {
                FileOutputStream stream = new FileOutputStream(ReportPaths.REPORT_DIRECTORY + File.separatorChar + report.getCompanyName());
                ObjectOutputStream objectOutput = new ObjectOutputStream(stream);
                objectOutput.writeObject(report);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    /**
     * Finds the RSSFiling object that contains meta data for the xbrl data in
     * the xml file.
     *
     * @param xbrlFileName
     * @return the RSSFiling that is associated with the xbrl file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private RSSFilingData getAssociatedRSSFilingData(String xbrlFileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(new File(xbrlFileName.replace(".xml", ".rsd")));
        ObjectInputStream objStream = new ObjectInputStream(in);
        return (RSSFilingData) objStream.readObject();
    }
    /**
     * 
     * @return A list of reports from the given folder where they should be stored
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public List<Report> readReportsFromFolder() throws IOException, ClassNotFoundException {
        List<Report> reports = new ArrayList<>();
        File[] reportObjects = new File(ReportPaths.REPORT_DIRECTORY).listFiles();
        int i = 0;

        for (File reportObject : reportObjects) {
            FileInputStream fis = new FileInputStream(reportObject);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Report report = (Report) ois.readObject();
            reports.add(report);
        }
        return reports;
    }
    /**
     * Deletes all reports in the specific folder
     */
    public void deleteReportsInFolder() {
        File[] files = new File(ReportPaths.REPORT_DIRECTORY).listFiles();

        for (File file : files) {
            file.delete();
        }
    }
    /**
     * Writes a given report to its output folder
     * @param report 
     */
    public void wirteReport(Report report) {
        PathValidator validator = new PathValidator();
        validator.validatePath(); // validates the path of the output stream to make sure the directories exists of which the data is to be stored


            try {
                FileOutputStream stream = new FileOutputStream(ReportPaths.REPORT_DIRECTORY + File.separatorChar + report.getCompanyName());
                ObjectOutputStream objectOutput = new ObjectOutputStream(stream);
                objectOutput.writeObject(report);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

       
    }

}
