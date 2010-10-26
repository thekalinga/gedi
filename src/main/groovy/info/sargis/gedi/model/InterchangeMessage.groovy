package info.sargis.gedi.model

import info.sargis.gedi.model.unb.UNBSegment
import info.sargis.gedi.model.unb.UNZSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class InterchangeMessage implements Segment {

  private List<FunctionalSegment> functionalSegments = []

  UNBSegment unbSegment

  def addFunctionalSegment(FunctionalSegment functionalSegment) {
    functionalSegments << functionalSegment
  }

  List<FunctionalSegment> getFunctionalSegments() {
    return Collections.unmodifiableList(functionalSegments)
  }

  UNZSegment getUnzSegment() {
    new UNZSegment(
            msgCount: getMessageCount(), ctrlRef: unbSegment.msgRefNbr
    )
  }

  String toEDI() {
    assert unbSegment
    assert functionalSegments

    StringBuilder sb = new StringBuilder()

    sb << unbSegment.toEDI()
    functionalSegments.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUnzSegment().toEDI()

    return sb.toString()
  }

  private int getMessageCount() {
    assert functionalSegments

    if (functionalSegments[0] instanceof ConditionalFunctionalSegment) {
      return functionalSegments[0].getMessageSegments().size()
    }
    return functionalSegments.size()

  }

}
