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
class InterchangePayload extends AbstractSegment {

  private List<FunctionalGroupPayload> functionalSegments = []

  EDIInterchangeMessage interchangeMessage

  def InterchangePayload() {
  }

  def InterchangePayload(EDIInterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage
  }

  def addFunctionalSegment(FunctionalGroupPayload functionalSegment) {
    functionalSegments << functionalSegment
  }

  List<FunctionalGroupPayload> getFunctionalSegments() {
    return Collections.unmodifiableList(functionalSegments)
  }

  String toEDI() {
    assert interchangeMessage
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
    return new UNBSegment(interchangeMessage: interchangeMessage, ediString: ediString)
  }

  private UNZSegment getUnzSegment(UNBSegment unbSegment) {
    UNZSegment unzSegment = new UNZSegment(
            msgCount: getMessageCount(), ctrlRef: unbSegment.msgRefNbr
    )
    unzSegment.interchangeMessage = interchangeMessage

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
