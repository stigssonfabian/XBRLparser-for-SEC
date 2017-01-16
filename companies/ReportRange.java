/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import report.AnnualReport;
import report.QuarterlyReport;

/**
 * Represents a range of reports, that a company have issued.
 *
 * @author Fabian
 */
public class ReportRange implements Serializable {

    private final Map<String, AnnualReport> annualReportsWithDate;
    private final Map<QuarterlyReportRelease, QuarterlyReport> quaterlyReportsWithDate;

    /**
     * Creates a report range from the given input
     *
     * @param quarterlyReports
     * @param annualReports
     */
    public ReportRange(List<QuarterlyReport> quarterlyReports, List<AnnualReport> annualReports) {
        annualReportsWithDate = getAnnualReportsWithDate(annualReports);
        quaterlyReportsWithDate = getQuarterlyReportsWithDate(quarterlyReports);
    }

    /**
     * Creates a report range that should get values added to it
     */
    public ReportRange() {
        quaterlyReportsWithDate = new HashMap<>();
        annualReportsWithDate = new HashMap<>();
    }

    /**
     * Gets the quarterly report release time and a quarterly report object
     *
     * @param quarterlyReports
     * @return a Map with dates associated to the report
     */
    private Map<QuarterlyReportRelease, QuarterlyReport> getQuarterlyReportsWithDate(List<QuarterlyReport> quarterlyReports) {
        Iterator<QuarterlyReport> it = quarterlyReports.iterator();

        Map<QuarterlyReportRelease, QuarterlyReport> reportsWithDate = new HashMap<>();

        while (it.hasNext()) {
            QuarterlyReport report = it.next();
            reportsWithDate.put(new QuarterlyReportRelease(report.getQ(), report.getFisicalYearEnded()), report); // puts the report with its date in a map
        }

        return reportsWithDate;
    }

    /**
     * Gets annual reports with its date
     *
     * @param annualReports
     * @return A map associated with the report
     */
    public final Map<String, AnnualReport> getAnnualReportsWithDate(List<AnnualReport> annualReports) {
        Iterator<AnnualReport> it = annualReports.iterator();

        Map<String, AnnualReport> reportsWithDate = new HashMap<>();

        while (it.hasNext()) {
            AnnualReport report = it.next();
            reportsWithDate.put(report.getFisicalYearEnded(), report); // puts the report with its date in a map
        }

        return reportsWithDate;
    }

    /**
     * Adds an annual report to the report range
     *
     * @param annualReport
     */
    public void addAnnualReport(AnnualReport annualReport) {
        annualReportsWithDate.put(annualReport.getRssData().getDate(), annualReport);
    }

    /**
     * Add a quarterly report to the report range
     *
     * @param quarterlyReport
     */
    public void addQuarterlyReport(QuarterlyReport quarterlyReport) {
        quaterlyReportsWithDate.put(
                new QuarterlyReportRelease(quarterlyReport.getQ(), quarterlyReport.getRssData().getDate()),
                quarterlyReport);
    }

    /**
     *
     * @return the annual report with dates
     */
    public Set<String> getAnnualReportDates() {
        return this.annualReportsWithDate.keySet();
    }

    /**
     *
     * @return all the dates of which quarterly reports have been issued
     */
    public Set<QuarterlyReportRelease> getQuarterlyReportDates() {
        return this.quaterlyReportsWithDate.keySet();
    }

    /**
     * Get a specific quarterly report based on when it was issued
     *
     * @param ofDate
     * @return the quarterly report matching the date of which it was issued
     */
    public QuarterlyReport getQuarterlyReport(QuarterlyReportRelease ofDate) {
        return this.quaterlyReportsWithDate.get(ofDate);
    }

    /**
     * Gets the annual report of a certain date
     *
     * @param ofDate
     * @return the annual report that is associated with the input date
     */
    public AnnualReport getAnnualReport(String ofDate) {
        return this.annualReportsWithDate.get(ofDate);
    }

}
