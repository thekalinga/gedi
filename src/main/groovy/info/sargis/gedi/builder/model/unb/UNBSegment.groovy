package info.sargis.gedi.builder.model.unb

import info.sargis.gedi.builder.model.InterchangeMessage
import info.sargis.gedi.builder.model.seg.EDISegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNBSegment extends EDISegment {

  def UNBSegment() {
    tagName = "UNB"
  }

  def UNBSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

}
