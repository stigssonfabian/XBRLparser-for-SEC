/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Reads a write company objects to folders.
 *
 * @author Fabian
 */
public class CompanyIO {

    /**
     * Creates a companyIO
     */
    public CompanyIO() {
        new PathValidator().validatePath();
    }

    /**
     * Reads the companies in the specific company folder
     *
     * @return a list with companies
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public List<Company> readCompaniesFromFolder() throws FileNotFoundException, IOException, ClassNotFoundException {
        File[] companyObjects = new File(CompanyDictionary.COMPANY_OBJECT_FOLDER).listFiles();

        List<Company> companies = new ArrayList<>();

        for (File companyObject : companyObjects) {
            FileInputStream fis = new FileInputStream(companyObject);
            ObjectInputStream in = new ObjectInputStream(fis);
            companies.add((Company) in.readObject());
        }

        return companies;

    }

    /**
     * Writes the company objects passed to the folder path specified by the
     * company dictionary's field.
     *
     * @param companyObjects
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeCompaniesToFolder(Collection<Company> companyObjects) throws FileNotFoundException, IOException {

        for (Company companyObject : companyObjects) {
            FileOutputStream fos = new FileOutputStream(new File(CompanyDictionary.COMPANY_OBJECT_FOLDER + File.separatorChar + companyObject.getCikNumber()));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(companyObject);
        }

    }
}
