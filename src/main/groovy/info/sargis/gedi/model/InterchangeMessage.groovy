package info.sargis.gedi.model

import info.sargis.gedi.model.unb.UNBSegment
import info.sargis.gedi.model.unb.UNZSegment
import static info.sargis.gedi.EDIConfig.EOL

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
            msgCount: functionalSegments.size(), ctrlRef: unbSegment.msgRefNbr
    )
  }

  String toEDI() {
    assert unbSegment
    assert functionalSegments

    StringBuilder sb = new StringBuilder()

    sb << unbSegment.toEDI() << EOL
    functionalSegments.each { seg ->
      sb << seg.toEDI() << EOL
    }
    sb << getUnzSegment().toEDI() << EOL

    return sb.toString()
  }

}
