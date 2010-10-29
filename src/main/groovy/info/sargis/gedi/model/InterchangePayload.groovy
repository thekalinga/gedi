package info.sargis.gedi.model

import info.sargis.gedi.model.seg.DataSupportSegment
import info.sargis.gedi.model.unb.UNBSegment
import info.sargis.gedi.model.unb.UNZSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class InterchangePayload extends DataSupportSegment {

  private List<FunctionalGroupPayload> functionalPayloads = []

  EDIInterchangeMessage interchangeMessage

  def InterchangePayload() {
  }

  def InterchangePayload(EDIInterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage
  }

  def addFunctionalPayload(FunctionalGroupPayload functionalPayload) {
    functionalPayloads << functionalPayload
  }

  List<FunctionalGroupPayload> getFunctionalPayloads() {
    return Collections.unmodifiableList(functionalPayloads)
  }

  String toEDI() {
    assert interchangeMessage
    assert functionalPayloads

    StringBuilder sb = new StringBuilder()

    UNBSegment unbSegment = getUNBSegment()
    sb << unbSegment.toEDI()
    functionalPayloads.each { seg ->
      sb << seg.toEDI()
    }
    sb << getUnzSegment(unbSegment).toEDI()

    return sb.toString()
  }

  private UNBSegment getUNBSegment() {
    return new UNBSegment(interchangeMessage: interchangeMessage, ediDataString: ediDataString)
  }

  private UNZSegment getUnzSegment(UNBSegment unbSegment) {
    UNZSegment unzSegment = new UNZSegment(
            msgCount: getMessageCount(), ctrlRef: unbSegment.msgRefNbr
    )
    unzSegment.interchangeMessage = interchangeMessage

    return unzSegment
  }

  private int getMessageCount() {
    assert functionalPayloads

    if (functionalPayloads[0] instanceof ConditionalFunctionalGroupPayload) {
      return functionalPayloads[0].getMessageSegments().size()
    }
    return functionalPayloads.size()

  }

}
