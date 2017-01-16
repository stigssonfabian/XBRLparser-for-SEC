/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and openExtactor the template in the editor.
 */
package financialextraction;

import items.Description;
import report.AnnualForeignReport;
import report.AnnualDomesticReport;
import report.FilingDictionary;
import report.QuarterlyReport;
import report.Report;
import items.DurationItem;
import items.InstantItem;
import items.Item;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.xml.sax.Attributes;
import time.DateReader;

/**
 * Extracts the xbrl data from the xbrl document and stores every individual data in items. Those items
 * are in turn stored in a report of which kind the data is presented, that is, annual, foreign annual or quarterly report.
 * When the handler has finished reading the xbrl document the getReport() method should be called to get the extracted data
 * in structured way. 
 * @author Fabian
 *
 */
public class XBRLExtractor {

    private Report report;
    private Item item = new Item();
    private final Set<Item> items = new HashSet<>();

    private final DateReader dr = new DateReader();
    /**
     * Should be called before any other methods is called on this object. This method sets the report type
     * of which data is extracted to
     * @param reportType 
     */
    public void setReportType(String reportType) {
        if (reportType.toLowerCase().contains("10-k")) {
            report = new AnnualDomesticReport();
        } else if (reportType.toLowerCase().contains("10-q")) {
            report = new QuarterlyReport();
        } else if (reportType.toLowerCase().contains("20-f")) {
            report = new AnnualForeignReport();
        }
    }
    /**
     * Extracts gaap data from the string and stores all attributes in the item object. This method should only
     * be invoked when a gaap element has been found.
     * @param description
     * @param attr 
     */
    public void extractAttributesAndGaap(Description description, Attributes attr) {
        item.setDescription(description);
        item.setUnit(attr.getValue(FilingDictionary.UNIT));
        item.setContextRef(attr.getValue(FilingDictionary.CONTEXT_REF));
    }
    /**
     * Reads the dateID of the attributes passed by validating it first. This method
     * can be invoked without for every element
     * @param tag
     * @param attributes 
     */
    public void readDateID(String tag, Attributes attributes) {
        dr.readID(tag, attributes);
    }
    /**
     * 
     * @return true if the caller of this function can call the readDate method, that is if a date has been
     * found.
     */
    public boolean canReadDate() {
        return dr.candReadDate();
    }
    /**
     *  Reads the date of the passed value 
     * @param value 
     */
    public void readDate(String value) {
        dr.readDate(value);
    }
    /**
     * This method should be called if the validator has flagged for a company name element. This method reads
     * the company name and store it.
     * @param value 
     */
    public void extractCompanyName(String value) {
        report.setCompanyName(value.replaceAll("[^a-zA-Z0-9\\s]", "")); // replaces all non alphanumerical chars and non whitespace. (Source:)  http://stackoverflow.com/questions/11796985/java-regular-expression-to-remove-all-non-alphanumeric-characters-except-spaces
    }
    /**
     * This method should be called when the quarter of a quarterly report has been spotted by the validator.
     * This method stores the quarter.
     * @param value 
     */
    public void extractQuarter(String value) {
        if (report instanceof QuarterlyReport) {
            ((QuarterlyReport) report).setQ(value);
        }
    }
    /**
     * This method should be called when the fisical year tag has been encountered. This method stores the fisical
     * year.
     * @param value 
     */
    public void extractFisicalYear(String value) {
        if (report instanceof AnnualDomesticReport) {
            ((AnnualDomesticReport) report).setFisicalYearEnded(value);
        } else if (report instanceof AnnualForeignReport) {
            ((AnnualForeignReport) report).setFisicalYearEnded(value);
        }
    }
    /**
     * This method should be called when a gaap item has been found. This method stores the given value of the gaap item.
     * @param value 
     */
    public void extractValueOfGAAP(String value) {
        item.setValue(value);
        items.add(item);
        item = new Item();
    }
    /**
     * This method should be called by the handler to ret the extracted data.
     * @return the report with the configured time for each item in the report.
     */
    public Report getReport() {
        configureTime();
        return this.report;
    }
    /**
     * Configures the time of the items and compare them to the contextsIds in the xbrl document, to make sure each
     * item has the correct corresponding duration or instant depending on the type of it.
     */
    public void configureTime() {
        Map<String, Interval> durations = dr.getDurationIds();
        Map<String, DateTime> instants = dr.getInstantIds();
        Iterator<Item> items = this.items.iterator();

        while (items.hasNext()) {
            Item item = items.next();

            if (durations.get(item.getCotnextRef()) != null) {
                DurationItem durationItem = new DurationItem(item);
                durationItem.setDuration(durations.get(item.getCotnextRef()));
                report.addItem(durationItem);
            } else if (instants.get(item.getCotnextRef()) != null) {
                InstantItem instantItem = new InstantItem(item);
                instantItem.setInstant(instants.get(item.getCotnextRef()));
                report.addItem(instantItem);
            } else {
                report.addItem(item);
            }

        }

    }

}
