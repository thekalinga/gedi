package info.sargis.gedi.parser

import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
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

  private EDIXPath ediXPath;

  @BeforeMethod
  public void setUp() {
    ediXPath = new EDIXPath()
  }

  @Test
  public void testUNASegmentResolving() throws Exception {
    Document document = getDocument(EDI_WITH_CUSTOM_UNA.stripIndent());

    XPathExpression expr = ediXPath.compile("/EDI/UNB/DS[4]/DE[1]/text()");
    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "101013")
  }

  @Test
  public void testXPathEvaluation() throws Exception {
    Document document = getDocument("UNB+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'");

    XPathExpression expr = ediXPath.compile("/EDI/UNB/DS[1]/DE[1]/text()");
    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "UNOB")
  }

  @Test
  public void testXPathEvaluationForCompositeSegmentTag() throws Exception {
    Document document = getDocument("UNB:1::3+UNOB:1+gslg071:ZZ+gcms003:ZZ+101013:1129+1013115727000++CLSVAL'");

    XPathExpression expr = ediXPath.compile("/EDI/UNB/@att3");
    String result = expr.evaluate(document, XPathConstants.STRING);

    Assert.assertEquals(result, "3")
  }

  private Document getDocument(String ediSource) {
    return SAX2DOM.transform(new EDIReader(), new InputSource(new StringReader(ediSource)))
  }

  @BeforeMethod
  public void tearDown() {
  }

}
