package info.sargis.gedi.model.unh

import info.sargis.gedi.model.InterchangeMessage
import info.sargis.gedi.model.seg.EDISegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNTSegment extends EDISegment {

  Integer msgCount
  String msgRefNbr

  def UNTSegment() {
    tagName = "UNT"
  }

  def UNTSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    ediDataString = msgCount + interchangeMessage.dataElemSeparator + msgRefNbr
    return super.toEDI()
  }

}
