package info.sargis.gedi.parser;

import info.sargis.gedi.builder.model.una.UNASegment;
import info.sargis.gedi.utils.EDIEscapeSupport;
import info.sargis.gedi.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.Scanner;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 29, 2010
 */
public class EDIReader implements XMLReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(EDIReader.class);

    private static final int UNA_TAG_SIZE = 3;

    private static final String EMPTY_URI = "";
    private static final AttributesImpl EMPTY_ATTS = new AttributesImpl();
    public static final String EDI = "EDI";
    public static final String DS = "DS";
    public static final String DE = "DE";

    private final EDIRegexSupport ediRegexSupport = EDIRegexSupport.INSTANCE;

    private ContentHandler contentHandler;
    private ErrorHandler errorHandler;
    public EDIEscapeSupport escapeSupport;

    private final UNASegment defaultUnaSegment;

    public EDIReader() {
        this(new UNASegment());
    }

    public EDIReader(UNASegment defaultUnaSegment) {
        this.defaultUnaSegment = defaultUnaSegment;
    }

    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException();
    }

    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException();
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException();
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException();
    }

    @Override
    public void setContentHandler(ContentHandler handler) {
        this.contentHandler = handler;
    }

    @Override
    public ContentHandler getContentHandler() {
        return contentHandler;
    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {
        errorHandler = handler;
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    @Override
    public void setEntityResolver(EntityResolver resolver) {
    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {
    }

    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override
    public void parse(InputSource input) throws IOException, SAXException {
        Utils.checkNotNull(contentHandler);

        PushbackReader reader = new PushbackReader(input.getCharacterStream(), UNA_TAG_SIZE);
        UNASegment unaSegment = readUNASegment(reader);
        LOGGER.debug("Dumping UNA Segment: {}", unaSegment);

        doScan(unaSegment, reader);
    }

    private void configEscapeSupport(UNASegment unaSegment) {
        escapeSupport = new EDIEscapeSupport(unaSegment);
    }

    private void doScan(UNASegment unaSegment, PushbackReader reader) throws SAXException, IOException {
        configEscapeSupport(unaSegment);

        String segmentSplitPattern = ediRegexSupport.createSegmentSplitPattern(unaSegment);

        Scanner scanner = new Scanner(reader);
        scanner.useDelimiter(segmentSplitPattern);

        contentHandler.startDocument();
        startEDIDocument(scanner, unaSegment);
        contentHandler.endDocument();

        if (scanner.ioException() != null) {
            throw scanner.ioException();
        }
    }

    private UNASegment readUNASegment(PushbackReader reader) throws IOException {
        try {
            char[] unaChars = new char[UNA_TAG_SIZE];
            reader.read(unaChars);

            if ("UNA".equals(new String(unaChars))) {
                LOGGER.debug("Found UNA Segment in EDI Interchange....");
                UNASegment unaSegment = new UNASegment();

                unaSegment.setCompDataSep((char) reader.read());
                unaSegment.setDataElemSeparator((char) reader.read());
                unaSegment.setDecimalNotation((char) reader.read());
                unaSegment.setReleaseIndicator((char) reader.read());
                unaSegment.setReserved((char) reader.read());
                unaSegment.setSegmentTerminator((char) reader.read());

                return unaSegment;
            } else {
                LOGGER.debug("Not found UNA Segment in EDI Interchange, switching to default....");
                reader.unread(unaChars);
                return defaultUnaSegment;
            }

        } catch (IOException e) {
            throw e;
        }
    }

    private void startEDIDocument(Scanner scanner, UNASegment unaSegment) throws SAXException {
        contentHandler.startElement(EMPTY_URI, EDI, EDI, EMPTY_ATTS);
        while (scanner.hasNext()) {
            String segmentEDI = scanner.next().trim().replaceAll("\\n", "").replaceAll("\\t", "");
            if (!segmentEDI.isEmpty()) {
                parseSegment(segmentEDI, unaSegment);
            }
        }
        contentHandler.endElement(EMPTY_URI, EDI, EDI);
    }

    private void parseSegment(String segment, UNASegment unaSegment) throws SAXException {
        String dataElemSplitPattern = ediRegexSupport.createDataElemSplitPattern(unaSegment);
        String[] simpleDataElements = segment.split(dataElemSplitPattern);

        String tag = simpleDataElements[0];

        SegmentTagBuilder segmentTagBuilder = new SegmentTagBuilder(unaSegment);
        SegmentTag segmentTag = segmentTagBuilder.buildTag(tag);

        String segmentCode = segmentTag.getSegmentCode();
        contentHandler.startElement(EMPTY_URI, segmentCode, segmentCode, getAttributesListFromSegmentTag(segmentTag));

        for (int index = 1; index < simpleDataElements.length; index++) {
            String dataElementEDI = simpleDataElements[index];
            parseDataElements(dataElementEDI, unaSegment);
        }

        contentHandler.endElement(EMPTY_URI, tag, tag);
    }

    private Attributes getAttributesListFromSegmentTag(SegmentTag segmentTag) {
        int index = 1;

        AttributesImpl attributes = new AttributesImpl();
        for (String nestedElem : segmentTag.getNestedElements()) {
            String attributeName = String.format("att%s", index);
            attributes.addAttribute(EMPTY_URI, attributeName, attributeName, "CDATA", nestedElem);
            ++index;
        }

        return attributes;
    }

    private void parseDataElements(String dataElement, UNASegment unaSegment) throws SAXException {
        String compositeDataElemSplitPattern = ediRegexSupport.createCompositeDataElemSplitPattern(unaSegment);

        String[] compositeElements = dataElement.split(compositeDataElemSplitPattern);

        processCompositeElements(compositeElements);
    }

    private void processCompositeElements(String[] compositeElements) throws SAXException {
        contentHandler.startElement(EMPTY_URI, DS, DS, EMPTY_ATTS);

        if (!isEmptyCompositeElement(compositeElements)) {
            for (String compositeElement : compositeElements) {
                processSimpleElement(compositeElement);
            }
        }

        contentHandler.endElement(EMPTY_URI, DS, DS);
    }

    private void processSimpleElement(String compositeElement) throws SAXException {
        String unescapedString = escapeSupport.unescape(compositeElement);

        contentHandler.startElement(EMPTY_URI, DE, DE, EMPTY_ATTS);

        char[] chars = unescapedString.toCharArray();
        contentHandler.characters(chars, 0, chars.length);

        contentHandler.endElement(EMPTY_URI, DE, DE);
    }

    private boolean isEmptyCompositeElement(String[] compositeElements) {
        return compositeElements[0].isEmpty() && compositeElements.length == 1;
    }

    @Override
    public void parse(String systemId) throws IOException, SAXException {
        throw new SAXException("Unsupported implementation for EDIReader");
    }
}
