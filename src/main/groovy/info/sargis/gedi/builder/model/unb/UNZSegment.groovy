package info.sargis.gedi.builder.model.unb

import info.sargis.gedi.EDIBuilderException
import info.sargis.gedi.builder.model.InterchangeMessage
import info.sargis.gedi.builder.model.seg.EDISegment
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
class UNZSegment extends EDISegment {

  Integer msgCount
  String unbEdi

  def UNZSegment() {
    tagName = "UNZ"
  }

  def UNZSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    prepareEdiData()
    return super.toEDI()
  }

  private def prepareEdiData() {
    ediDataString = msgCount + interchangeMessage.dataElemSeparator + getUNBMsgRefNumber()
  }

  private String getUNBMsgRefNumber() {
    try {
      EDIXPath ediXPath = new EDIXPath()
      return ediXPath.evaluate("/EDI/UNB/DS[5]/DE[1]/text()", new InputSource(new StringReader(unbEdi)))
    } catch (XPathExpressionException ex) {
      throw new EDIBuilderException("Cannot get UNB 'Interchange Control Reference'", ex)
    }
  }

}
