package info.sargis.gedi.model.ung

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.EDIInterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNGSegment extends AbstractSegment {

  String msgRefNbr = "UNG0111DUMMY"

  String tagName = "UNG"

  def UNGSegment() {
  }

  def UNGSegment(EDIInterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    assert interchangeMessage
    return "${tagName}${interchangeMessage.eol}";
  }

}
