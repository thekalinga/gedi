package info.sargis.gedi.model.unb

import info.sargis.gedi.model.InterchangeMessage
import info.sargis.gedi.model.seg.EDISegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNZSegment extends EDISegment {

  Integer msgCount
  String ctrlRef

  def UNZSegment() {
    tagName = "UNZ"
  }

  def UNZSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    ediDataString = msgCount + interchangeMessage.dataElemSeparator + ctrlRef
    return super.toEDI()
  }
}
