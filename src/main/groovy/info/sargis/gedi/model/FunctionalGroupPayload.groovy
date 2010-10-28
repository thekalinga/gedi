package info.sargis.gedi.model

import info.sargis.gedi.model.ung.UNESegment
import info.sargis.gedi.model.ung.UNGSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class FunctionalGroupPayload implements Segment {

  private List<MessagePayload> messageSegments = []

  EDIInterchangeMessage ediMessage

  def FunctionalGroupPayload() {
  }

  def FunctionalGroupPayload(EDIInterchangeMessage ediMessage) {
    this.ediMessage = ediMessage
  }

  def addMessageSegment(MessagePayload messageSegment) {
    messageSegment.interchangeMessage = ediMessage
    messageSegments << messageSegment
  }

  List<MessagePayload> getMessageSegments() {
    return Collections.unmodifiableList(messageSegments)
  }

  String toEDI() {
    assert ediMessage
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
    return new UNGSegment(ediMessage)
  }

  private UNESegment getUneSegment(UNGSegment ungSegment) {
    UNESegment uneSegment = new UNESegment(
            msgCount: messageSegments.size(), grpRefNbr: ungSegment.msgRefNbr
    )
    uneSegment.interchangeMessage = ediMessage

    return uneSegment
  }

}
