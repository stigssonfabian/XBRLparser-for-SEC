/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a abstract company that contains all the reports and items,
 * extracted from the xbrl documents
 *
 * @author Fabian
 */
public abstract class Company implements Serializable {

    private final String companyName;

    private ReportRange reportRange;

    private String cikNumber;

    /**
     * Creates a company with the specific name
     *
     * @param companyName
     */
    public Company(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the report range of a company.
     *
     * @return all the quarterly and annually reports of a company
     */
    public ReportRange getReportRange() {
        return reportRange;
    }

    /**
     * Sets the reportrange of a company
     *
     * @param reportRange
     */
    public void setReportRange(ReportRange reportRange) {
        this.reportRange = reportRange;
    }

    /**
     * Gets the cik number of a company, that is the central index key which is
     * unique for every individual company
     *
     * @return
     */
    public String getCikNumber() {
        return cikNumber;
    }

    /**
     * SEts the unique cik number
     *
     * @param cikNumber
     */
    public void setCikNumber(String cikNumber) {
        this.cikNumber = cikNumber;
    }

    /**
     * Gets the name of the company
     *
     * @return the name of a company
     */
    public String geCompanyName() {
        return this.companyName;
    }

    /**
     *
     * @return haschode value based on company name and cik number
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.companyName);
        hash = 43 * hash + Objects.hashCode(this.cikNumber);
        return hash;
    }

    /**
     *
     * @param obj
     * @return true if company name and cik number is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.cikNumber, other.cikNumber)) {
            return false;
        }
        return true;
    }

}
