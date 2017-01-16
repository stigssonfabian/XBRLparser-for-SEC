/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

/**
 * All classes that implements fisical year are assured to set and get a fisical year string. 
 * @author Fabian
 */
public interface FiscalYear {
    /**
     * Sets the fiscal year
     * @param fisicalYearEnded 
     */
    public void setFisicalYearEnded(String fisicalYearEnded);
    /**
     * 
     * @return the fiscal year
     */
    public String getFisicalYearEnded();
}
