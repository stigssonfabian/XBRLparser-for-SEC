/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.io.File;

/**
 * This class is used to validate the path which is used to write the report objects, by creating the folder if it is not yet created.
 * @author Fabian
 */
class PathValidator {
    /**
     * Validates the path which is used to write the report objects.
     */
    public void validatePath() {
        String path = ReportPaths.REPORT_DIRECTORY;

        File dir = new File(path);

        if (!dir.isDirectory()) {
            dir.mkdir();
        }

    }
}
