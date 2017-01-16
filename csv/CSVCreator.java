/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import companies.Company;
import companies.QuarterlyReportRelease;
import companies.ReportRange;
import items.DurationItem;
import items.InstantItem;
import items.Item;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import report.AnnualReport;
import report.QuarterlyReport;
import report.Report;
import rssdata.RSSFilingData;

/**
 * Creates csv files out of companies. This class should be used when a
 * company's reports are to be outputted as csv.
 *
 * @author Fabian
 */
public class CSVCreator {

    /**
     * Creates two csv files for every annual, and quarterly report that the
     * passed company has issued.
     *
     * @param company
     */
    public void createCSV(Company company) {
        ReportRange rr = company.getReportRange();

        for (String date : rr.getAnnualReportDates()) {
            AnnualReport ar = rr.getAnnualReport(date);
            createCSVForReport(ar);
        }

        for (QuarterlyReportRelease release : rr.getQuarterlyReportDates()) {
            QuarterlyReport qr = rr.getQuarterlyReport(release);
            createCSVForReport(qr);
        }

    }

    /**
     * Creates csv files for a certain report
     *
     * @param report
     */
    private void createCSVForReport(Report report) {

        Set<Item> items = report.getItemsOfReport();

        Iterator<Item> it = items.iterator();
        List<DurationItem> durationItems = new ArrayList<>();
        List<InstantItem> instantItems = new ArrayList<>();
        Set<String> durationDates = new HashSet<>();
        Set<String> instantDates = new HashSet<>();

        while (it.hasNext()) {
            Item item = it.next();
            if (item instanceof InstantItem) {
                InstantItem instantItem = (InstantItem) item;
                instantItems.add(instantItem);
                instantDates.add((convertInstantToString(instantItem.getInstant())));
            } else if (item instanceof DurationItem) {
                DurationItem durationItem = (DurationItem) item;
                durationItems.add(durationItem);
                durationDates.add(convertDurationToString(durationItem.getDuration()));
            }
        }

        String csvName = report.getYear();
        String folder = CSVDictionary.ANNUAL_REPORT_FOLDER;

        if (report instanceof QuarterlyReport) {
            csvName += "-" + ((QuarterlyReport) report).getQ(); // adds a hyphen to seperate qyarterly reports
            folder = CSVDictionary.QUARTERLY_REPORT_FOLDER;
        }

        CSVWriter durationWriter = new CSVWriter(createPath('D', report.getCompanyName(), folder, csvName));
        CSVWriter instantWriter = new CSVWriter(createPath('I', report.getCompanyName(), folder, csvName));

        instantWriter.writeCSV(createInstantItemTable(sort(instantDates), instantItems, report.getRssData()));
        durationWriter.writeCSV(createDurationItemTable(sort(durationDates), durationItems, report.getRssData()));
    }

    /**
     * Creates the instant item table for a list of instantitems
     *
     * @param dates
     * @param items
     * @param rss
     * @return A CSVTable with the the items stored in a strctured way
     */
    private CSVTable createInstantItemTable(String[] dates, List<InstantItem> items, RSSFilingData rss) {
        CSVTable table = new CSVTable(dates);

        Iterator<InstantItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            InstantItem item = iterator.next();
            // finds when to add an item to a new row nad when to add item to an existing row
            if (table.indexOfRow(item.getDescription().getGaap()) == -1) {
                table.addNewRow(item.getDescription().getGaap(), item.getDescription().getLabel(), item.getDescription().getDescription(), item.getUnit() + " " + item.getValue(), table.indexOfDate(convertInstantToString(item.getInstant())));
            } else {
                table.insertValueAt(table.indexOfDate(convertInstantToString(item.getInstant())), table.indexOfRow(item.getDescription().getGaap()), item.getUnit() + " " + item.getValue());
            }

        }

        table.addLastRowData(rss);

        return table;

    }

    /**
     * Creates a csv table with the given dates items a rssData
     *
     * @param dates
     * @param items
     * @param rss
     * @return a CSV table with the duration items stored systematically
     */
    private CSVTable createDurationItemTable(String[] dates, List<DurationItem> items, RSSFilingData rss) {
        CSVTable table = new CSVTable(dates);

        Iterator<DurationItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            DurationItem item = iterator.next();

            if (table.indexOfRow(item.getDescription().getGaap()) == -1) {
                table.addNewRow(item.getDescription().getGaap(), item.getDescription().getLabel(), item.getDescription().getDescription(), item.getUnit() + " " + item.getValue(), table.indexOfDate(convertDurationToString(item.getDuration())));
            } else {
                table.insertValueAt(table.indexOfDate(convertDurationToString(item.getDuration())), table.indexOfRow(item.getDescription().getGaap()), item.getUnit() + " " + item.getValue());
            }

        }
        table.addLastRowData(rss);

        return table;
    }

    /**
     * Sorts dates
     *
     * @param dates
     * @deprecated
     * @return an array of dates
     */
    private String[] sort(Set<String> dates) {
        Iterator<String> it = dates.iterator();
        String[] sortedDates = new String[dates.size()];

        int counter = 0;
        while (it.hasNext()) {
            sortedDates[counter] = it.next();
            counter++;
        }

        return sortedDates;
    }

    /**
     * Converts a duration object to a descriptive string of that object
     *
     * @param interval
     * @return a descriptive string of the given input
     */
    private String convertDurationToString(Interval interval) {
        DateTime start = interval.getStart();
        DateTime end = interval.getEnd();

        DateTimeFormatter df = DateTimeFormat.forPattern("y-MM-dd");

        return df.print(start) + " - " + df.print(end);
    }

    /**
     * converts an instant object to a descriptive string
     *
     * @param instant
     * @return a descriptive string of the instant object
     */
    private String convertInstantToString(DateTime instant) {
        DateTimeFormatter df = DateTimeFormat.forPattern("y-MM-dd");
        return df.print(instant);
    }

    /**
     * Create a path for the output folder given the supplied input
     *
     * @param c
     * @param companyName
     * @param folder
     * @param year
     * @return an output path
     */
    private String createPath(char c, String companyName, String folder, String year) {

        String path = CSVDictionary.OUTPUT_FOLDER + File.separatorChar
                + companyName + File.separatorChar + folder;

        new PathValidator().ensurePath(path);
        return path + File.separatorChar + c + year + ".csv";
    }
}
