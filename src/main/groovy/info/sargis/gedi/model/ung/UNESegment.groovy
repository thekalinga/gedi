package info.sargis.gedi.model.ung

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNESegment extends AbstractSegment {

  Integer msgCount
  String grpRefNbr

  String tagName = "UNE"

  def UNESegment() {
  }

  def UNESegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    return "${tagName}+${msgCount}+${grpRefNbr}${interchangeMessage.eol}"
  }
}
