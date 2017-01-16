/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.io.Serializable;


/**
 * Quarterly reports are submitted quarterly and on a specific quarter of the fisical year.
 * @author Fabian
 */
public class QuarterlyReport extends Report implements Serializable{
    private final long serialVersionUID = 12318893L;
    private String q;
    private String fisicalYearEnded;

   
    /**
     * 
     * @return the specific quarter of the fisical year
     */
    public String getQ() {
        return q;
    }
    /**
     * Sets the specific quarter of the fisical year
     * @param q 
     */
    public void setQ(String q) {
        this.q = q;
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
