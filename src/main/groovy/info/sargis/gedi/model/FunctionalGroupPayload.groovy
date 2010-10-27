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

  UNGSegment ungSegment;

  def addMessageSegment(MessagePayload messageSegment) {
    messageSegments << messageSegment
  }

  List<MessagePayload> getMessageSegments() {
    return Collections.unmodifiableList(messageSegments)
  }

  UNESegment getUneSegment() {
    new UNESegment(
            msgCount: messageSegments.size(), grpRefNbr: ungSegment.msgRefNbr
    )
  }

  String toEDI() {
    assert ungSegment
    assert messageSegments

    StringBuilder sb = new StringBuilder()

    sb << ungSegment.toEDI()
    messageSegments.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUneSegment().toEDI()

    return sb.toString()
  }

}
