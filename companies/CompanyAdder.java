/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import report.AnnualDomesticReport;
import report.AnnualForeignReport;
import report.AnnualReport;
import report.QuarterlyReport;
import report.Report;

/**
 * Adds Company from given reports
 *
 * @author Fabian
 */
class CompanyAdder {

    private final List<Company> companies;

    /**
     * Creates a company adder.
     */
    public CompanyAdder() {
        this.companies = new ArrayList<>();
    }

    /**
     * Adds a report to a company's report range. The report type is determined
     * by the method. If the company does not yet exist, it is created with a
     * new report range and the report is add. If the company who issued the
     * report already exists in the list, that company is retrieved and the
     * report is added to that report range
     *
     * @param report
     */
    public void addReportToCompany(Report report) {
        Iterator<Company> iterator = companies.iterator();
        while (iterator.hasNext()) {
            Company company = iterator.next();
            if (company.getCikNumber().equals(report.getRssData().getCik())) {
                if (report instanceof QuarterlyReport) {
                    company.getReportRange().addQuarterlyReport((QuarterlyReport) report);
                } else if (report instanceof AnnualReport) {
                    company.getReportRange().addAnnualReport((AnnualReport) report);
                }
                return; // if the company has been found there is no need to continue the loop, nor stay in the method
            }
        }

        createNewCompany(report); // if the company was not found a new company object is created and the report is added to the report range
    }

    /*
     * Empties the aray of companies
     * @return a list with the companies while at the same time clearing it
     */
    public List<Company> emptyCompanies() {
        List<Company> companies = new ArrayList<>();

        Iterator<Company> iterator = companies.iterator();
        while (iterator.hasNext()) {
            companies.add(iterator.next());
        }
        this.companies.clear();

        return companies;

    }

    /**
     * Gets the sisze of the company adder, that is how many companies there are
     * in the list
     *
     * @return the size of the how many company in the list containing the
     * company objects
     */
    public int getSize() {
        return companies.size();
    }

    /**
     * Gets all companies that have been read in based on the reports
     *
     * @return
     */
    public List<Company> getCompanies() {
        return this.companies;
    }

    /**
     * Creates a new Company, from the infoormation provided by the report
     * object
     *
     * @param report
     */
    private void createNewCompany(Report report) {
        if (report instanceof AnnualForeignReport) {
            ForeignCompany company = new ForeignCompany(report.getCompanyName());
            company.setCikNumber(report.getRssData().getCik());
            ReportRange range = new ReportRange(); // creates a new report range
            range.addAnnualReport((AnnualForeignReport) report);
            company.setReportRange(range);
            companies.add(company);

        } else if (report instanceof AnnualDomesticReport) {
            DomesticCompany company = new DomesticCompany(report.getCompanyName());
            company.setCikNumber(report.getRssData().getCik());
            ReportRange range = new ReportRange();
            range.addAnnualReport((AnnualDomesticReport) report);
            company.setReportRange(range);
            companies.add(company);
        } else if (report instanceof QuarterlyReport) {
            DomesticCompany company = new DomesticCompany(report.getCompanyName());
            company.setCikNumber(report.getRssData().getCik());
            ReportRange range = new ReportRange();
            range.addQuarterlyReport((QuarterlyReport) report);
            company.setReportRange(range);
            companies.add(company);
        }

    }

}
