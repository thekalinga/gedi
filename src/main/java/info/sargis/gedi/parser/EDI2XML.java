package info.sargis.gedi.parser;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 4, 2010
 */
public class EDI2XML {

    public static void toXML(XMLReader xmlReader, InputSource inputSource, Writer writer) throws TransformerException {
        writeToTargetOutput(xmlReader, inputSource, new StreamResult(writer));
    }

    public static void toXML(XMLReader xmlReader, InputSource inputSource, OutputStream outputStream) throws TransformerException {
        writeToTargetOutput(xmlReader, inputSource, new StreamResult(outputStream));
    }

    private static void writeToTargetOutput(XMLReader xmlReader, InputSource inputSource, Result targetOutput) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new SAXSource(xmlReader, inputSource);

        transformer.transform(source, targetOutput);
    }

}
