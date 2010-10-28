package info.sargis.gedi.model

import info.sargis.gedi.model.seg.DataSupportSegment
import info.sargis.gedi.model.ung.UNESegment
import info.sargis.gedi.model.ung.UNGSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class FunctionalGroupPayload extends DataSupportSegment {

  private List<MessagePayload> messageSegments = []

  EDIInterchangeMessage interchangeMessage

  def FunctionalGroupPayload() {
  }

  def FunctionalGroupPayload(EDIInterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage
  }

  def addMessageSegment(MessagePayload messageSegment) {
    messageSegment.interchangeMessage = interchangeMessage
    messageSegments << messageSegment
  }

  List<MessagePayload> getMessageSegments() {
    return Collections.unmodifiableList(messageSegments)
  }

  String toEDI() {
    assert interchangeMessage
    assert messageSegments

    StringBuilder sb = new StringBuilder()

    UNGSegment ungSegment = getUngSegment()
    sb << ungSegment.toEDI()
    messageSegments.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUneSegment(ungSegment).toEDI()

    return sb.toString()
  }

  private UNGSegment getUngSegment() {
    return new UNGSegment(interchangeMessage: interchangeMessage, ediDataString: ediDataString)
  }

  private UNESegment getUneSegment(UNGSegment ungSegment) {
    UNESegment uneSegment = new UNESegment(
            msgCount: messageSegments.size(), grpRefNbr: ungSegment.msgRefNbr
    )
    uneSegment.interchangeMessage = interchangeMessage

    return uneSegment
  }

}
