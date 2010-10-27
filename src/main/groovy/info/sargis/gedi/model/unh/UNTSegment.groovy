package info.sargis.gedi.model.unh

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.EDIInterchangeMessage

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

  def UNTSegment(EDIInterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    return "${tagName}+${msgCount}+${msgRefNbr}${interchangeMessage.eol}"
  }
}
