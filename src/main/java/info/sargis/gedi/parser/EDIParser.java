package info.sargis.gedi.parser;

import info.sargis.gedi.model.una.UNASegment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 29, 2010
 */
public class EDIParser extends SAXParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EDIParser.class);

    public static final UNASegment DEFAULT_UNA = new UNASegment();

    private final EDIReader ediReader = new EDIReader();

    private boolean closed = false;
    private Reader source;
    private UNASegment unaSegment;

    public EDIParser(String source) {
        this(new StringReader(source), DEFAULT_UNA);
    }

    public EDIParser(InputStream source) {
        this(new InputStreamReader(source), DEFAULT_UNA);
    }

    public EDIParser(File source) throws FileNotFoundException {
        this(new FileInputStream(source).getChannel());
    }

    public EDIParser(ReadableByteChannel source) {
        this(makeReadable(source), DEFAULT_UNA);
    }

    public EDIParser(Reader source) {
        this(source, DEFAULT_UNA);
    }

    private EDIParser(Reader source, UNASegment unaSegment) {
        this.source = source;
        this.unaSegment = unaSegment;
    }

    private static Reader makeReadable(ReadableByteChannel source) {
        if (source == null) {
            throw new NullPointerException("source");
        }
        return Channels.newReader(source, Charset.defaultCharset().name());
    }


    public void setContentHandler(ContentHandler contentHandler) {
        ediReader.setContentHandler(contentHandler);
    }

    public void parse() throws SAXException, IOException {
        unaSegment = readUNASegment();
        ediReader.setUnaSegment(unaSegment);

        ediReader.parse(new InputSource(source));
    }

    private UNASegment readUNASegment() {
        //TODO: read UNA segment if not found use EDIParser unaSegment
        return unaSegment;
    }


    @Override
    public Parser getParser() throws SAXException {
        return ediReader;
    }

    @Override
    public XMLReader getXMLReader() throws SAXException {
        return ediReader;
    }

    @Override
    public boolean isNamespaceAware() {
        return false;
    }

    @Override
    public boolean isValidating() {
        return false;
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException();
    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException();
    }


    public void close() {
        if (closed) {
            return;
        }
        if (source instanceof Closeable) {
            try {
                ((Closeable) source).close();
            } catch (IOException ioe) {
                LOGGER.debug("", ioe);
            }
        }
        source = null;
        closed = true;
    }

}
