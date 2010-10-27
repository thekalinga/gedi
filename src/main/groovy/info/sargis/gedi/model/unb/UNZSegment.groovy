package info.sargis.gedi.model.unb

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNZSegment extends AbstractSegment {

  Integer msgCount
  String ctrlRef

  String tagName = "UNZ"

  def UNZSegment() {
  }

  def UNZSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }

  String toEDI() {
    assert interchangeMessage
    return "${tagName}+${msgCount}+${ctrlRef}${interchangeMessage.eol}"
  }
}
