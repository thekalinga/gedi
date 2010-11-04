import info.sargis.gedi.builder.EDIBuilder
import info.sargis.gedi.parser.EDIReader
import info.sargis.gedi.parser.EDIXPath
import info.sargis.gedi.parser.SAX2DOM
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.w3c.dom.Document
import org.xml.sax.InputSource

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 4, 2010
 */
class EDIBuildingAndParsingTest {

  private static final String DS1_DE4 = "D:D"
  private static final String DS3_DE1 = "SSS:XX+X"
  private static final String DS4_DE1 = "XXX?AAA"

  private EDIBuilder ediBuilder
  private EDIXPath ediXPath

  @BeforeMethod
  public void setUp() {
    ediBuilder = new EDIBuilder()
    ediXPath = new EDIXPath()
  }

  @Test
  public void testBuildingAndParsingEDIWithEDISpecialCharacters() throws Exception {


    ediBuilder.UNB {
      data {
        ["UNOB", 1] + ["gslg071", "ZZ"] + ["gcms003", "ZZ"] + [101013, 1129] + 1013115727000 + "" + "CLSVAL"
      }

      UNH {
        data {
          "001" + ["CLSVAL", 1, 1000, "MN"]
        }

        XS0 {
          data {
            ["XXX", "", 233, DS1_DE4] + 12222.33 + DS3_DE1 + [DS4_DE1, 999]
          }
        }

      } // END OF UNH

    }
    StringWriter sw = new StringWriter()
    ediBuilder.build(sw)

    Document document = getDocument(sw.toString())

    Assert.assertEquals(evaluateXPathExpression(document, "/EDI/XS0/DS[1]/DE[4]/text()"), DS1_DE4)
    Assert.assertEquals(evaluateXPathExpression(document, "/EDI/XS0/DS[3]/DE[1]/text()"), DS3_DE1)
    Assert.assertEquals(evaluateXPathExpression(document, "/EDI/XS0/DS[4]/DE[1]/text()"), DS4_DE1)
  }

  private String evaluateXPathExpression(Document document, String xpathExpr) {
    XPathExpression expr = ediXPath.compile(xpathExpr)
    return expr.evaluate(document, XPathConstants.STRING)
  }

  private Document getDocument(String ediSource) {
    return SAX2DOM.transform(new EDIReader(), new InputSource(new StringReader(ediSource)))
  }

}
