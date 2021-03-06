package info.sargis.gedi.builder.model.ung

import info.sargis.gedi.builder.model.InterchangeMessage
import info.sargis.gedi.builder.model.seg.EDISegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNGSegment extends EDISegment {

  def UNGSegment() {
    tagName = "UNG"
  }

  def UNGSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

}
