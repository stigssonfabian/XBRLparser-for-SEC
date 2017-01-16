/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financialextraction;

import excel.Taxonomy;
import items.Descriptions;
import report.Report;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handles the xbrl extraction-logic of an xbrl document.
 *
 * @author Fabian
 */
public class XBRLHandler extends DefaultHandler {

    private final XBRLValidator validator;
    private final XBRLExtractor extractor = new XBRLExtractor();
    private final Descriptions descriptions;

    /**
     * Creates a XBRL handler for the given reportType
     *
     * @param reportType
     */
    public XBRLHandler(String reportType) {
        extractor.setReportType(reportType);
        Taxonomy taxonomy = new Taxonomy();
        this.descriptions = new Descriptions(taxonomy.readTaxonomyDefinitionsToReport(new String[]{
            "EarningsPerShareBasic",
             "EarningsPerShareDiluted",
             "NetIncomeLoss",
             "WeightedAverageNumberOfSharesOutstandingBasic",
             "CommonStockDividendsPerShareDeclared",
             "Revenues",
             "AssetsNoncurrent",
             "NoncurrentAssets",
             "OperatingIncomeLoss",
             "Cash"
                
        
        }));
        validator = new XBRLValidator(descriptions);
    }

    /**
     * Is called when a start element is encountered when reading the xbrl
     * document. The parameters that are passed are validated to see if they
     * contains relevant data. If so the the flag varaible of that data is set
     * to true, which enables the data to be extracted.
     *
     * @param uri
     * @param localName
     * @param startTag
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String startTag,
            Attributes attributes) throws SAXException {
                
        if (validator.check(startTag, attributes)) {
            extractor.extractAttributesAndGaap(descriptions.getDescriptionByGaap(startTag.replaceAll("us-gaap:", "")), attributes);
        } else {
            extractor.readDateID(startTag, attributes);
        }
    }

    /**
     * This method is interannly called by the parser to return the
     * charatchers inside the the start and end tag
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String content = new String(ch, start, length);

        if (validator.isGaapValue()) {
            extractor.extractValueOfGAAP(content);
        } else if (validator.isCompanyName()) {
            extractor.extractCompanyName(content);
        } else if (validator.isFisicalYearValue()) {
            extractor.extractFisicalYear(content);
        } else if (validator.isQValue()) {
            extractor.extractQuarter(content);
        } else if (extractor.canReadDate()) {
            extractor.readDate(content);
        }

    }

    /**
     * Should be called after the parsing is done.
     *
     * @return the the extracted xbrl data in a Report object.
     */
    public Report getReport() {
        return extractor.getReport();
    }

}
