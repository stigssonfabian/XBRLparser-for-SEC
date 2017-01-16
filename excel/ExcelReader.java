/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import items.Description;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * The ExcelReader class reads and taxonomy excel file and extracts values associated with financial items
 * @author Fabian
 */
public class ExcelReader {

    private final int GAAP_DEFINITION = 2;
    private final int GAAP_LABEL = 9;
    private final int GAAP_DOCUMENTATION = 10;
    /**
     * Gets a more discrpitive description of the gaap items supplied
     * @param sheet
     * @param gaapItems
     * @return A Descriptitions for all the gaap items
     */
    public List<Description> readDescription(HSSFSheet sheet, String[] gaapItems) {
        int lastRow = sheet.getLastRowNum();
        List<Description> descriptions = new ArrayList<>();

        for (int i = 0; i < lastRow; i++) {
            HSSFRow row = sheet.getRow(i);
            Description description = readDescription(row);

            if (isAccepted(gaapItems, description.getGaap())) { // checks if the gaap is of intrest
                descriptions.add(description);
            }
        }

        return descriptions;
    }
    /**
     * Reads the Descriptions from one HSSFRow
     * @param row
     * @return a Description with the extracted values from the excel document
     */
    private Description readDescription(HSSFRow row) {
        Description description = new Description();
        description.setDescription(row.getCell(GAAP_DOCUMENTATION).getStringCellValue());
        description.setGaap(row.getCell(GAAP_DEFINITION).getStringCellValue());
        description.setLabel(row.getCell(GAAP_LABEL).getStringCellValue());

        return description;
    }
    /**
     *  Determines whether an item  should be included in the Description list or not
     * @param gaapItems
     * @param gaap
     * @return true if the gaap item is accepted, that is if if is of interest: false otherwise
     */
    private boolean isAccepted(String[] gaapItems, String gaap) {
        for (String gaapItem : gaapItems) {
            if (gaapItem.contains(gaap)) {
                return true;
            }
        }

        return false;
    }

}
