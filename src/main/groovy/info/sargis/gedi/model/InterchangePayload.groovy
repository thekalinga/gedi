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
class InterchangePayload implements Segment {

  private List<FunctionalGroupPayload> functionalSegments = []

  EDIInterchangeMessage ediMessage

  def InterchangePayload() {
  }

  def InterchangePayload(EDIInterchangeMessage ediMessage) {
    this.ediMessage = ediMessage
  }

  def addFunctionalSegment(FunctionalGroupPayload functionalSegment) {
    functionalSegment.ediMessage = ediMessage
    functionalSegments << functionalSegment
  }

  List<FunctionalGroupPayload> getFunctionalSegments() {
    return Collections.unmodifiableList(functionalSegments)
  }

  String toEDI() {
    assert ediMessage
    assert functionalSegments

    StringBuilder sb = new StringBuilder()

    UNBSegment unbSegment = getUNBSegment()
    sb << unbSegment.toEDI()
    functionalSegments.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUnzSegment(unbSegment).toEDI()

    return sb.toString()
  }

  private UNBSegment getUNBSegment() {
    return new UNBSegment(ediMessage)
  }

  private UNZSegment getUnzSegment(UNBSegment unbSegment) {
    UNZSegment unzSegment = new UNZSegment(
            msgCount: getMessageCount(), ctrlRef: unbSegment.msgRefNbr
    )
    unzSegment.interchangeMessage = ediMessage

    return unzSegment
  }

  private int getMessageCount() {
    assert functionalSegments

    if (functionalSegments[0] instanceof ConditionalFunctionalGroupPayload) {
      return functionalSegments[0].getMessageSegments().size()
    }
    return functionalSegments.size()

  }

}
