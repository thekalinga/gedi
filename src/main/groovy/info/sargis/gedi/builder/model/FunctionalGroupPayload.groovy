package info.sargis.gedi.builder.model

import info.sargis.gedi.builder.model.seg.DataSupportSegment
import info.sargis.gedi.builder.model.ung.UNESegment
import info.sargis.gedi.builder.model.ung.UNGSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class FunctionalGroupPayload extends DataSupportSegment {

  private List<MessagePayload> messagePayloads = []

  EDIInterchangeMessage interchangeMessage

  def FunctionalGroupPayload() {
  }

  def FunctionalGroupPayload(EDIInterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage
  }

  def addMessagePayload(MessagePayload messageSegment) {
    messageSegment.interchangeMessage = interchangeMessage
    messagePayloads << messageSegment
  }

  List<MessagePayload> getMessagePayloads() {
    return Collections.unmodifiableList(messagePayloads)
  }

  String toEDI() {
    assert interchangeMessage
    assert messagePayloads

    StringBuilder sb = new StringBuilder()

    UNGSegment ungSegment = getUngSegment()
    String ungEDI = ungSegment.toEDI()

    sb << ungEDI
    messagePayloads.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUneSegment(ungEDI).toEDI()

    return sb.toString()
  }

  private UNGSegment getUngSegment() {
    return new UNGSegment(interchangeMessage: interchangeMessage, ediDataString: ediDataString)
  }

  private UNESegment getUneSegment(String ungEDI) {
    UNESegment uneSegment = new UNESegment(
            msgCount: messagePayloads.size(), ungEDI: ungEDI
    )
    uneSegment.interchangeMessage = interchangeMessage

    return uneSegment
  }

}
