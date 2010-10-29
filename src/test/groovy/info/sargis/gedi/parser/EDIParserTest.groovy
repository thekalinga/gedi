package info.sargis.gedi.parser

import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.sax.SAXSource
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathFactory
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.w3c.dom.Document
import org.xml.sax.InputSource

/**
 * Created by IntelliJ IDEA.
 * User: Sargis Harutyunyan
 * Date: 29 oct. 2010
 * Time: 21:03:59
 */
class EDIParserTest {

  private EDIReader ediReader;

  @BeforeMethod
  public void setUp() {
    ediReader = new EDIReader();
  }

  @Test
  public void testParse() throws Exception {
    Document document = getDocument();

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile("/EDI/UNB/DS[1]/DE[1]/text()");

    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "UNOB")
  }

  private Document getDocument() {

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    Source source = new SAXSource(ediReader, new InputSource(new StringReader("UNB+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'")));
    DOMResult result = new DOMResult();
    transformer.transform(source, result);

    return (Document) result.getNode()
  }

  @BeforeMethod
  public void tearDown() {
  }

}
