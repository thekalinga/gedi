package info.sargis.gedi.builder.model

import info.sargis.gedi.builder.model.seg.DataSupportSegment
import info.sargis.gedi.builder.model.seg.UserSegment
import info.sargis.gedi.builder.model.unh.UNHSegment
import info.sargis.gedi.builder.model.unh.UNTSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class MessagePayload extends DataSupportSegment {

  private List<Segment> userSegments = []

  EDIInterchangeMessage interchangeMessage

  def MessagePayload() {
  }

  def MessagePayload(EDIInterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage;
  }

  def addUserSegment(UserSegment segment) {
    userSegments << segment
  }

  List<Segment> getUserSegments() {
    return Collections.unmodifiableList(userSegments)
  }

  String toEDI() {
    assert unhSegment

    StringBuilder sb = new StringBuilder()

    UNHSegment unhSegment = getUnhSegment()
    String unhEDI = unhSegment.toEDI()

    sb << unhEDI
    userSegments.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUntSegment(unhEDI).toEDI()

    return sb.toString()
  }

  UNHSegment getUnhSegment() {
    return new UNHSegment(interchangeMessage: interchangeMessage, ediDataString: ediDataString)
  }

  UNTSegment getUntSegment(String unhEDI) {
    UNTSegment untSegment = new UNTSegment(
            msgCount: userSegments.size(), unhEDI: unhEDI
    )
    untSegment.interchangeMessage = interchangeMessage

    return untSegment
  }

}
