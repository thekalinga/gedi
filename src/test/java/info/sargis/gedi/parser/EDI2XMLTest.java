package info.sargis.gedi.parser;

import org.custommonkey.xmlunit.XMLAssert;
import org.testng.annotations.Test;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 4, 2010
 */
public class EDI2XMLTest {

    private static final String EDI_SEGMENT_EXAMPLE = "UNB+UNOB:1+gslg071:ZZ+1013115727000++CLSVAL'";

    @Test
    public void testToXML() throws Exception {
        StringWriter sw = new StringWriter();
        EDI2XML.toXML(new EDIReader(), new InputSource(new StringReader(EDI_SEGMENT_EXAMPLE)), sw);

        XMLAssert.assertXMLEqual("<EDI><UNB>" + getUNBXMLBody() + "</UNB></EDI>", sw.toString());
    }

    private String getUNBXMLBody() {
        return "<DS><DE>UNOB</DE><DE>1</DE></DS>" + "<DS><DE>gslg071</DE><DE>ZZ</DE></DS>" +
                "<DS><DE>1013115727000</DE></DS>" + "<DS></DS>" + "<DS><DE>CLSVAL</DE></DS>";
    }

}
