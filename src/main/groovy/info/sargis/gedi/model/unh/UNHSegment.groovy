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
class UNHSegment extends EDISegment {

  String msgRefNbr = "UNH0111DUMMY"

  def UNHSegment() {
    tagName = "UNH"
  }

  def UNHSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

}
