package info.sargis.gedi.model.unh

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNTSegment extends AbstractSegment {

  Integer msgCount
  String msgRefNbr

  String tagName = "UNT"

  def UNTSegment() {
  }

  def UNTSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    assert interchangeMessage

    StringBuilder sb = new StringBuilder()

    sb << tagName << interchangeMessage.dataElemSeparator << msgCount << interchangeMessage.dataElemSeparator
    sb << msgRefNbr << interchangeMessage.segmentTerminator << interchangeMessage.eol

    return sb.toString()
  }
}
