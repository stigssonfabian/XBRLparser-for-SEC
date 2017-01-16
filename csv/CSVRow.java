/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

/**
 * Represents a CSV row that holds CSV values
 *
 * @author Fabian
 */
class CSVRow {

    private final String[] rowValues;

    /**
     * Creates a CSVRow object with the given column length and description
     *
     * @param columns
     * @param description
     */
    public CSVRow(int columns, String gaap, String label, String description) {
        rowValues = new String[columns];
        rowValues[0] = gaap;
        rowValues[columns - 1] = description;
        rowValues[columns - 2] = label;
    }

    /**
     * This constructor should only be used when the CSVrow is first initiated
     *
     * @param columns
     * @param descOfHeader
     */
    public CSVRow(int columns, String descOfHeader) {
        rowValues = new String[columns];
        rowValues[0] = descOfHeader;
        rowValues[columns - 1] = "Explination";
        rowValues[columns - 2] = "Label";
    }

    /**
     * Adds a value to the specified column
     *
     * @param column
     * @param value
     */
    public void addValueTo(int column, String value) {
        rowValues[column] = value;
    }

    /**
     *
     * @return all the values in the row
     */
    public String[] getRowValues() {
        return rowValues;
    }

}
