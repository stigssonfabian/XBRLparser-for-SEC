/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.util.Iterator;
import java.util.List;
import report.Report;

/**
 * Creates companies out of report
 *
 * @author Fabian
 */
public class CompanyCreator {

    private final List<Report> reports;

    public CompanyCreator(List<Report> reports) {
        this.reports = reports;
    }

    public List<Company> createCompaniesWithReports() {
        Iterator<Report> iterator = reports.iterator();
        CompanyAdder adder = new CompanyAdder();

        while (iterator.hasNext()) {
            Report report = iterator.next();
            adder.addReportToCompany(report);
        }
        return adder.getCompanies();
    }

}
