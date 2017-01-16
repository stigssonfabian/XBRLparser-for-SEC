/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

/**
 * If the excel report read is to old this exception should be throw
 * @author Fabian
 * @deprecated 
 */
public class ReportTooOldException extends RuntimeException{

    @Override
    public String getMessage() {
        return "The excel report is to old to have a taxonomy standard.";
    }
    
}
