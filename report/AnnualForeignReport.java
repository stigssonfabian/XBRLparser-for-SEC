/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.io.Serializable;

/**
 * AnnualForeginReport contains key data associated with foreign filers of annual reports.
 * @author Fabian
 */
public class AnnualForeignReport extends AnnualReport implements Serializable{
    
    private final long serialVersionUID = 859163223L;
    
    private String nationality;
    private String fisicalYearEnded;
    
    /**
     * 
     * @return the nationality of the report issuer
     */
    public String getNationality() {
        return nationality;
    }
    /**
     * Sets the nationallity of the report issuer
     * @param nationality 
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public void setFisicalYearEnded(String fisicalYearEnded) {
        this.fisicalYearEnded = fisicalYearEnded;
    }

    @Override
    public String getFisicalYearEnded() {
        return this.fisicalYearEnded;
    }
}
