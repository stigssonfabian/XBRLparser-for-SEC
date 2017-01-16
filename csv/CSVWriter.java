/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Writes the a csv table to the given output path
 *
 * @author Fabian
 */
 class CSVWriter {

    FileWriter fw;

    /**
     * Creates a csv writer with the given output path
     *
     * @param outputPath
     */
    public CSVWriter(String outputPath) {

        try {
            fw = new FileWriter(outputPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes a CSV Table to csv file
     *
     * @param table
     */
    public void writeCSV(CSVTable table) {
        List<CSVRow> csvRows = table.getCSVRows();

        Iterator<CSVRow> iterator = csvRows.iterator();

        while (iterator.hasNext()) {
            CSVRow row = iterator.next();
            String[] values = row.getRowValues();
            for (int i = 0; i < values.length; i++) {
                try {
                    if (values[i] == null) {
                        fw.append("-");
                    } else {
                        fw.append(values[i].replaceAll(",", ""));
                    }
                    if (i < values.length - 1) {
                        fw.append(',');
                    } else {
                        fw.append("\n");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }

        try {
            fw.flush();
            fw.close();
        } catch (IOException ex) {

        }

    }

}
