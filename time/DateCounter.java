/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import java.util.Calendar;

/**
 * Counts the date from when the SEC started reporting RSS data until today
 *
 * @author Fabian
 */
public class DateCounter {

    private final int STARTYEAR = 2015; //changed temporarily
    private final int STARTMONTH = 4;

    private final Calendar currentDate;
    private final Calendar startDate;

    /**
     * Creates a Date counter with the start time of 2005 04
     */
    public DateCounter() {
        currentDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate.set(STARTYEAR, STARTMONTH, 1);
    }

    /**
     * Increments the month one while returning the current
     *
     * @return the month incremented dependent upon how many tomes this method
     * has been called
     */
    public String countMonth() {
        // One month is added for each call to this method
        int month = startDate.get(Calendar.MONTH);
        startDate.add(Calendar.MONTH, 1);
        return formatMonth(month + 1); // accounting for that January is denoted with 0 instead of 1
    }

    /**
     *
     * @return the year dependent upon how many times the countMonth() method
     * has been called
     */
    public String countYear() {
        return String.valueOf(startDate.get(Calendar.YEAR));
    }

    /**
     * Formats the month so it starts with a zero if less than 10
     *
     * @param month
     * @return textual representation of a numeric month
     */
    private String formatMonth(int month) {
        if (month < 10) {
            return "0" + String.valueOf(month);
        } else {
            return String.valueOf(month);
        }
    }

    /**
     * Should be called before counting month to ensure that the the month
     * returned is not in the furure
     *
     * @return true if the month extracted have not exceeded the current month
     * in time
     */
    public boolean hasNotEnteredFuture() {
        return startDate.getTimeInMillis() < currentDate.getTimeInMillis();
    }

}
