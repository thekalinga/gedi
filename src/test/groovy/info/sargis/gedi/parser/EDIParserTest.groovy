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

  private static final EDI_WITH_CUSTOM_UNA = """\
  UNA:-.? '
  UNB-UNOB:1-gslg071:ZZ-gcms003:ZZ-101013:1129-1013115727000--CLSVAL'
  """

  private EDIReader ediReader;

  @BeforeMethod
  public void setUp() {
    ediReader = new EDIReader();
  }

  @Test
  public void testUNASegmentResolving() throws Exception {
    Document document = getDocument(EDI_WITH_CUSTOM_UNA.stripIndent());

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile("/EDI/UNB/DS[4]/DE[1]/text()");

    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "101013")
  }

  @Test
  public void testXPathEvaluation() throws Exception {
    Document document = getDocument("UNB+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'");

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile("/EDI/UNB/DS[1]/DE[1]/text()");

    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "UNOB")
  }

  @Test
  public void testXPathEvaluationForCompositeSegmentTag() throws Exception {
    Document document = getDocument("UNB:1::3+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'");

    XPathFactory factory = XPathFactory.newInstance();
    XPath xpath = factory.newXPath();
    XPathExpression expr = xpath.compile("/EDI/UNB/@att3");

    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "3")
  }

  private Document getDocument(String ediSource) {

    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    Source source = new SAXSource(ediReader, new InputSource(new StringReader(ediSource)));
    DOMResult result = new DOMResult();
    transformer.transform(source, result);

    return (Document) result.getNode()
  }

  @BeforeMethod
  public void tearDown() {
  }

}
