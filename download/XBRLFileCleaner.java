/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package download;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

/**
 * Cleans up the directories from files that serve no purpose
 *
 * @author Fabian
 */
public class XBRLFileCleaner {

    /**
     * Deletes all non xbrl file in the, master directory
     *
     * @throws java.io.IOException
     */
    public void deleteNonXBRLFiles() throws IOException {
        File masterDirectory = new File(XBRLFilePaths.XBRL_FILING_DIRECTORY);

        // lists all files that are not directories except rfd object files
        Collection<File> files = FileUtils.listFiles(masterDirectory, new String[]{"zip", "xdr", "xsd", "xml"}, true);

        // deletes the files that are not xbrl files
        for (File file : files) {
            if (!isXBRLFile(file.getName())) {
                Files.delete(Paths.get(file.getAbsolutePath()));
            } else {
                file.renameTo(new File(file.getParent() + File.separatorChar + file.getParentFile().getName() + ".xml"));
            }
        }

    }

    /**
     *
     * @param fileName
     * @return true if the file name passed is the standard for a xbrl file
     */
    private boolean isXBRLFile(String fileName) {
        return !fileName.contains("_") && !fileName.contains("xsd") && !fileName.contains("zip");
    }

}
