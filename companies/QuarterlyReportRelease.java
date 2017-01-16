/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.io.Serializable;

/**
 * Represents a certain description of when a company was released
 *
 * @author Fabian
 */
public class QuarterlyReportRelease implements Serializable {

    private String q;
    private String year;

    /**
     * Creates a company release object with the given quarter an string
     *
     * @param q
     * @param year
     */
    public QuarterlyReportRelease(String q, String year) {
        this.q = q;
        this.year = year;
    }

    /**
     * Gets the quarter of the QuarterlyRelase object
     *
     * @return the quarter in a single 1-4 string
     */
    public String getQ() {
        return q;
    }

    /**
     * Sets the quarter, to a (should be) single 1-4 string
     *
     * @param q
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * Gets the string representation of the year
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the string representation of the quarterly report release object
     *
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

}
