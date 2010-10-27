package info.sargis.gedi.model

import info.sargis.gedi.model.unh.UNHSegment
import info.sargis.gedi.model.unh.UNTSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class MessagePayload implements Segment {

  private List<Segment> userSegments = []

  EDIInterchangeMessage ediMessage

  def MessagePayload() {
  }

  def MessagePayload(EDIInterchangeMessage ediMessage) {
    this.ediMessage = ediMessage;
  }

  def addUserSegment(UserSegment segment) {
    segment.interchangeMessage = ediMessage
    userSegments << segment
  }

  List<Segment> getUserSegments() {
    return Collections.unmodifiableList(userSegments)
  }

  String toEDI() {
    assert unhSegment

    StringBuilder sb = new StringBuilder()

    UNHSegment unhSegment = getUnhSegment()
    sb << unhSegment.toEDI()
    userSegments.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUntSegment(unhSegment).toEDI()

    return sb.toString()
  }

  UNHSegment getUnhSegment() {
    return new UNHSegment(ediMessage)
  }

  UNTSegment getUntSegment(UNHSegment unhSegment) {
    UNTSegment untSegment = new UNTSegment(
            msgCount: userSegments.size(), msgRefNbr: unhSegment.msgRefNbr
    )
    untSegment.interchangeMessage = ediMessage

    return untSegment
  }

}
