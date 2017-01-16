/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.util.LinkedList;
import java.util.List;
import rssdata.RSSFilingData;

/**
 * Represetns a CSV table, that is a structured format for the CSVWriter to use
 * to write csv files.
 *
 * @author Fabian
 */
class CSVTable {

    private final List<CSVRow> rows = new LinkedList<>();

    /**
     * Creates a CSV Table with the given dates, note that the length of the
     * array passed determines the max column num.
     *
     * @param dates
     */
    public CSVTable(String[] dates) {
        CSVRow row = new CSVRow(dates.length + 3, "Dates");
        for (int i = 0; i < dates.length; i++) {
            row.addValueTo(i + 1, dates[i]);
        }
        rows.add(row);
    }

    /**
     * Gets the index of the string date, if it matched: that string is returned
     * else -1 is returned
     *
     * @param date
     * @return the index of the date
     */
    public int indexOfDate(String date) {
        String[] rowValues = rows.get(0).getRowValues();
        for (int i = 0; i < rowValues.length; i++) {
            if (rowValues[i].equals(date)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Adds a last row to the csv table, used for storing meta data for the
     * report such as RSS data
     *
     * @param rss
     */
    public void addLastRowData(RSSFilingData rss) {
        CSVRow row = new CSVRow(5, "Report Information");
        row.addValueTo(1, "Cik Number: " + rss.getCik());
        row.addValueTo(2, "Filing Date: " + rss.getDate());
        row.addValueTo(3, "Filing Type: " + rss.getType());
        row.addValueTo(4, "URL Source: " + rss.getURL());
        rows.add(row);
    }

    /**
     * Inserts a value of an item at the given column and row index
     *
     * @param columnIndex
     * @param rowIndex
     * @param value
     */
    public void insertValueAt(int columnIndex, int rowIndex, String value) {
        rows.get(rowIndex).addValueTo(columnIndex, value);
    }

    /**
     * Finds the index of a specfici row based on the gaap number supplied
     *
     * @param gaap
     * @return the row index of the gaap number
     */
    public int indexOfRow(String gaap) {
        for (int i = 0; i < rows.size(); i++) {
            if (seperateCamelCase(gaap).equals(rows.get(i).getRowValues()[0])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a new row to the table with all the data supplied
     *
     * @param gaap
     * @param label
     * @param description
     * @param value
     * @param columnIndex
     */
    public void addNewRow(String gaap, String label, String description, String value, int columnIndex) {
        CSVRow row = new CSVRow(getColumnCount(), seperateCamelCase(gaap), label, description);
        row.addValueTo(columnIndex, value);
        rows.add(row);
    }

    /**
     *
     * @return the column count of the first row, that is the length of how many
     * dates there are in the csv table
     */
    private int getColumnCount() {
        return rows.get(0).getRowValues().length;
    }

    /**
     * Gets all the CSV rows in the table
     *
     * @return a list with the csv rows in the table
     */
    public List<CSVRow> getCSVRows() {
        return this.rows;
    }

    /**
     * Serperates the camel case of string making the words seperated by space
     * instead of camel case
     *
     * @return
     */
    private String seperateCamelCase(String string) {
        String[] parts = string.split("(a-z)+(A-Z)");
        String newString = "";
        for (String part : parts) {
            newString += part;
        }

        return newString;
    }

}
