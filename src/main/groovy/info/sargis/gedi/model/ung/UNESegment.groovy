package info.sargis.gedi.model.ung

import info.sargis.gedi.model.InterchangeMessage
import info.sargis.gedi.model.seg.EDISegment

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

  def UNESegment() {
    tagName = "UNE"
  }

  def UNESegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    ediDataString = msgCount + interchangeMessage.dataElemSeparator + grpRefNbr
    return super.toEDI()
  }
}
