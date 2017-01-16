/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.File;

/**
 * Validates the path for cs
 * @author Fabian
 */
 class PathValidator {
    public void ensurePath(String path){
        String [] folderNames = path.split(File.separatorChar + "");
        String fileName = "";
        for(String folderName : folderNames){
            fileName += folderName + File.separatorChar;
            File folder = new File(fileName);
            if(!folder.exists()){
                folder.mkdir();
            }
        }
        
    }
}
