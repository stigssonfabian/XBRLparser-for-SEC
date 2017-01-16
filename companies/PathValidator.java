/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.io.File;

/**
 * Validates paths associated with Company objects
 *
 * @author Fabian
 */
class PathValidator {

    /**
     * Validates the path for the in and output with companies, and should be
     * called before using the Company IO object
     */
    public void validatePath() {
        File file = new File(CompanyDictionary.COMPANY_OBJECT_FOLDER);

        if (!file.isDirectory()) {
            file.mkdir();
        }
    }
}
