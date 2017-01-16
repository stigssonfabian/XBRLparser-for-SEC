package rssdata;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class is used to react on when the sax parser of XML data scans through the XML, in this case the
 * XML that is handles by the class is RSS
 * @author Fabian
 */
 class RSSHandler extends DefaultHandler {

    private List<RSSFilingData> rssFilings = new ArrayList<>();
    private RSSValidator validator = new RSSValidator();
    private RSSExtractor extractor = new RSSExtractor();

    private boolean cikFlag = false;
    private boolean dateFlag = false;
    private boolean typeFlag = false;

    /**
     * Is called when a start element is encountered when reading the xml document. The parameters that are passed
     * are validated to see if they contains the description tag, cikNumber tag, date tag and enclosure tag.
     * If so the the flag varaible of that data is set to true, which enables the data to be extracted.
     * @param uri
     * @param localName
     * @param element
     * @param attributes
     * @throws SAXException 
     */
    @Override
    public void startElement(String uri, String localName, String element,
            Attributes attributes) throws SAXException {

        if (validator.canExtractFilingURL(element)) {
            extractor.extractURL(attributes);
        } else if (validator.canExtractCIKNumber(element)) {
            cikFlag = true;
        } else if (validator.canExtractFilingDate(element)) {
            dateFlag = true;
        } else if (validator.canExtractFilingType(element)) {
            typeFlag = true;
        }

    }

    /**
     * Is called when the xml parser is inside tags and returns the characters therein. If the start tag is one of interest
     * the corresponding flag variable for that tag is true and the data is extracted from the passed characters. 
     * 
     * @param ch
     * @param start
     * @param length
     * @throws SAXException 
     */
    public void characters(char ch[], int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        if (cikFlag) {
            extractor.extractCIK(content);
            cikFlag = false;
        } else if (dateFlag) {
            extractor.extractDate(content);
            dateFlag = false;
        } else if (typeFlag) {
            extractor.extractType(content.replaceAll("/[a-zA-Z]", ""));
            typeFlag = false;
        }

    }
    /**
     * This method is called when the end of an element is reached by the xml parser. If the method detects
     * the end tag "item" and the filing is of correct type the data is saved, otherwise it is neglected.
     * @param uri
     * @param localName
     * @param endElement
     * @throws SAXException 
     */
    @Override
    public void endElement(String uri, String localName, String endElement) throws SAXException {
        if (validator.isDone(endElement)) {
            if (validator.isCorrectType(extractor.getExtractedData().getType())) {
                rssFilings.add(extractor.getExtractedData());
            }
            extractor.reset();
        }
    }
    /**
     * 
     * @return a list containing the nessecary data for downloading the flings
     */
    public List<RSSFilingData> getFilingsWithDownLoadData() {
        return this.rssFilings;
    }

}
