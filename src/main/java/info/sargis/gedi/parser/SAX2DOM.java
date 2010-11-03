package info.sargis.gedi.parser;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 3, 2010
 */
public class SAX2DOM {

    public static Document transform(XMLReader xmlReader, InputSource inputSource) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new SAXSource(xmlReader, inputSource);
        DOMResult result = new DOMResult();
        transformer.transform(source, result);

        return (Document) result.getNode();
    }

}
