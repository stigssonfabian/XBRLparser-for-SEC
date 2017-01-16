/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package download;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Makes sure that the folder exists to store the xbrl files
 * @author Fabian
 */
public class XBRLPathValidator {
    
    /**
     * Validates the path to which the file is to be saved, and create directories if those are not yet created
     * @param path 
     */
    public void validatePath(String path){
        String [] folders = path.split("" + File.separatorChar);
        
        String currentPath = "";
        
        for(String folder : folders){
            currentPath += folder;
            if(Files.exists(Paths.get(folder))){
                new File(currentPath).mkdir();
            }
        }
    }
    
}
