package info.sargis.gedi.model.unb

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNBSegment extends AbstractSegment {

  String msgRefNbr = "UNB0111DUMMY"

  String tagName = "UNB"

  def UNBSegment() {
  }

  def UNBSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    assert interchangeMessage

    StringBuilder sb = new StringBuilder()
    sb << tagName << interchangeMessage.dataElemSeparator << ediString << interchangeMessage.segmentTerminator << interchangeMessage.eol
    return sb.toString()
  }

}
