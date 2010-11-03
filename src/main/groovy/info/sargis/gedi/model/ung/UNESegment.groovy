package info.sargis.gedi.model.ung

import info.sargis.gedi.EDIBuilderException
import info.sargis.gedi.model.InterchangeMessage
import info.sargis.gedi.model.seg.EDISegment
import info.sargis.gedi.parser.EDIXPath
import javax.xml.xpath.XPathExpressionException
import org.xml.sax.InputSource

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNESegment extends EDISegment {

  Integer msgCount
  String ungEDI

  def UNESegment() {
    tagName = "UNE"
  }

  def UNESegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    prepareEDIData()
    return super.toEDI()
  }

  private def prepareEDIData() {
    ediDataString = msgCount + interchangeMessage.dataElemSeparator + getUNGGrpRefNumber()
  }

  private String getUNGGrpRefNumber() {
    try {
      EDIXPath ediXPath = new EDIXPath()
      return ediXPath.evaluate("/EDI/UNG/DS[5]/DE[1]/text()", new InputSource(new StringReader(ungEDI)))
    } catch (XPathExpressionException ex) {
      throw new EDIBuilderException("Cannot get UNG 'Functional Group Reference Number'", ex)
    }
  }

}
