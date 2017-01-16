/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import report.FilingDictionary;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.xml.sax.Attributes;

/**
 * This object evaluates dates and associate them with a context id, which is used to determine the
 * instant or duration of which an item concern.
 * @author Fabian
 */
public class DateReader {

    private final Map<String, DateTime> instantIdentities;
    private final Map<String, Interval> durationIdentities;

    private String id;

    private boolean startFlag, endFlag;

    private boolean instantFlag;

    private DateTime startDate;
    /**
     * Creates a DateReader object
     */
    public DateReader() {
        this.instantIdentities = new HashMap<>();
        this.durationIdentities = new HashMap<>();
    }
        
    public void readID(String tag, Attributes attri) {
        if (tag.contains(FilingDictionary.CONTEXT_1) || tag.contains(FilingDictionary.CONTEXT_2)) {
            id = attri.getValue("id");
        } else if (tag.contains(TimeDictionary.START_DATE)) {
            startFlag = true;
        } else if (tag.contains(TimeDictionary.END_DATE)) {
            endFlag = true;
        } else if (tag.contains(TimeDictionary.INSTANT)) {
            instantFlag = true;
        }
    }
    /**
     * 
     * @return true if a date has been encountered and false if no date has been encountered
     */
    public boolean candReadDate () {
        return startFlag || endFlag || instantFlag;
    }
    /**
     * Reads the date of the value associated with a specific context id, and stores it
     * in a hashmap
     * @param value 
     */
    public void readDate(String value) {
        value = value.trim();
        if (endFlag) {
            durationIdentities.put(id, new Interval(startDate, evaluateDate(value)));
            endFlag = false;
        } else if (startFlag) {
            startDate = evaluateDate(value);
            startFlag = false;
        } else if (instantFlag) {
            instantIdentities.put(id, evaluateDate(value));
            instantFlag = false;
        }
    }
    /**
     * Evaluates the string representation of a date in the xbrl document and return a DateTome of it
     * @param date
     * @return the date of a xbrl date
     */
    private DateTime evaluateDate(String date) {
        String[] dateParts = date.split("-");
        return new DateTime().withDate(Integer.valueOf(dateParts[0]), Integer.valueOf(dateParts[1]), Integer.valueOf(dateParts[2]));
    }
    /**
     * 
     * @return id associated with a specific duration (Interval object)
     */
    public Map<String, Interval> getDurationIds(){
        return this.durationIdentities;
    }
    /**
     * 
     * @return the id associated with a specific Instant (DateTime object)
     */
    public Map<String, DateTime> getInstantIds(){
        return this.instantIdentities;
    }

   
}
