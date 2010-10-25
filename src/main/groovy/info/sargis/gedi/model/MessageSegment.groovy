package info.sargis.gedi.model

import info.sargis.gedi.model.unh.UNHSegment
import info.sargis.gedi.model.unh.UNTSegment
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class MessageSegment implements Segment {

  private List<Segment> userSegments = []

  UNHSegment unhSegment

  def addUserSegment(Segment segment) {
    userSegments << segment
  }

  List<Segment> getUserSegments() {
    return Collections.unmodifiableList(userSegments)
  }

  UNTSegment getUntSegment() {
    new UNTSegment(
            msgCount: userSegments.size(), msgRefNbr: unhSegment.msgRefNbr
    )
  }

  String toEDI() {
    StringBuilder sb = new StringBuilder()

    sb << unhSegment.toEDI() << EOL
    userSegments.each { seg ->
      sb << seg.toEDI() << EOL
    }
    sb << getUntSegment().toEDI() << EOL

    return sb.toString()
  }

}
