package info.sargis.gedi.model.unh

import info.sargis.gedi.EDISegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNHSegment extends EDISegment {

  String msgRefNbr = "UNH0111DUMMY"

  String tagName = "UNH"

  def UNHSegment() {
  }

  def UNHSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    assert interchangeMessage

    StringBuilder sb = new StringBuilder()
    sb << tagName << interchangeMessage.dataElemSeparator << ediDataString << interchangeMessage.segmentTerminator << interchangeMessage.eol
    return sb.toString()
  }
}
