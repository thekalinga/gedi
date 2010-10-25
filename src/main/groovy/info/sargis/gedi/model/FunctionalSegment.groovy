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
class FunctionalSegment implements Segment {

  private List<MessageSegment> messageSegments = []

  UNGSegment ungSegment;

  def addMessageSegment(MessageSegment messageSegment) {
    messageSegments << messageSegment
  }

  List<MessageSegment> getMessageSegments() {
    return Collections.unmodifiableList(messageSegments)
  }

  UNESegment getUneSegment() {
    new UNESegment(
            msgCount: messageSegments.size(), grpRefNbr: ungSegment.msgRefNbr
    )
  }

  String toEDI() {
    StringBuilder sb = new StringBuilder()

    sb << ungSegment.toEDI() << EOL
    messageSegments.each { seg ->
      sb << seg.toEDI() << EOL
    }
    sb << getUneSegment().toEDI() << EOL

    return sb.toString()
  }

}
