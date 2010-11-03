package info.sargis.gedi.model.unh

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
class UNTSegment extends EDISegment {

  Integer msgCount
  String unhEDI

  def UNTSegment() {
    tagName = "UNT"
  }

  def UNTSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    prepareEdiData()
    return super.toEDI()
  }

  private def prepareEdiData() {
    ediDataString = msgCount + interchangeMessage.dataElemSeparator + getUNHMsgRefNumber()
  }

  private String getUNHMsgRefNumber() {
    try {
      EDIXPath ediXPath = new EDIXPath()
      return ediXPath.evaluate("/EDI/UNH/DS[1]/DE[1]/text()", new InputSource(new StringReader(unhEDI)))
    } catch (XPathExpressionException ex) {
      throw new EDIBuilderException("Cannot get UNH 'Message Reference Number'", ex)
    }
  }

}
