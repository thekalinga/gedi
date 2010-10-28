package info.sargis.gedi.model.ung

import info.sargis.gedi.EDISegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNESegment extends EDISegment {

  Integer msgCount
  String grpRefNbr

  String tagName = "UNE"

  def UNESegment() {
  }

  def UNESegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    assert interchangeMessage

    StringBuilder sb = new StringBuilder()

    sb << tagName << interchangeMessage.dataElemSeparator << msgCount << interchangeMessage.dataElemSeparator
    sb << grpRefNbr << interchangeMessage.segmentTerminator << interchangeMessage.eol

    return sb.toString()
  }
}
