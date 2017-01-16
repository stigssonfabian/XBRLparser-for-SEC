/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.io.Serializable;

/**
 * Annual report which is submitted annually by filers and contains key data associated with those reports.
 * @author Fabian
 */
public class AnnualDomesticReport extends AnnualReport implements Serializable{

    private final long serialVersionUID = 999123123L;
    
    private String fisicalYearEnded;

    public AnnualDomesticReport() {
    }
    @Override
    public String getFisicalYearEnded() {
        return fisicalYearEnded;
    }

    @Override
    public void setFisicalYearEnded(String fisicalYearEnded) {
        this.fisicalYearEnded = fisicalYearEnded;
    }

   

    
}
