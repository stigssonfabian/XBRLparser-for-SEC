/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import items.Description;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.*;


/**
 * Represents a Taxonomy that can read and extract all the gaapvalues from an excel document
 * @author Fabian
 */
public class Taxonomy {

    

    /**
     * Reads the taxonomies specified by the array
     *
     * @param gaapItems
     * @return 
     */
    public List<Description> readTaxonomyDefinitionsToReport(String [] gaapItems) {
      return  readFromExcel(ExcelDictionary.EXCEL_FOLDER + File.separatorChar + "2014" + ".xls", gaapItems);
    }

    /**
     * Reads the excel document in the file path and sets the extracted values
     * in the items of the report.
     *
     * @param filePath
     * @param report
     */
    private List<Description> readFromExcel(String filePath, String[] gaapItems) {
        List<Description> descriptions = null;
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet sheet = wb.getSheetAt(0);
            ExcelReader reader = new ExcelReader();
            descriptions = reader.readDescription(sheet, gaapItems);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return descriptions;
    }

}
