/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package download;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;

/**
 * Unzips the downloaded zip files containing the xbrl data
 *
 * @author Fabian
 */
public class XBRLUnzipper {

    /**
     * Unzips all files in the root folder
     *
     * @throws IOException
     * @throws net.lingala.zip4j.exception.ZipException
     */
    public void unzipFiles() throws IOException, ZipException {
        File rootFolder = new File(XBRLFilePaths.XBRL_FILING_DIRECTORY);

        // search through the folder strcuture storing only the zip files and not the folder in the variable
        Collection<File> files = FileUtils.listFiles(rootFolder, new String[]{"zip"}, true);

        decompressFiles(files);
    }

    /**
     * Decompresses all zip files in the folder highest in the tree hierarchy
     *
     * @param files
     * @throws ZipException
     */
    private void decompressFiles(Collection<File> files) {
        for (File file : files) {
            ZipFile zip = null;
            try {
                zip = new ZipFile(file);
            } catch (ZipException ex) {
                ex.printStackTrace();
            }

            try {
                zip.extractAll(constructOutputFolder(file.getAbsolutePath()));

            } catch (ZipException ex) {

            }
        }
    }

    /**
     * Constructs the path of which the zip file is located in
     *
     * @param absolutFilePatb
     * @return
     */
    private String constructOutputFolder(String absolutFilePatb) {
        String[] parts = absolutFilePatb.split("/");
        String path = "";
        for (int i = 0; i < parts.length - 1; i++) {
            path += parts[i] + "/";
        }
        return path;
    }

}
