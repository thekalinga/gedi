package info.sargis.gedi.model

import info.sargis.gedi.EDIBuilderException
import info.sargis.gedi.model.una.UNASegment
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class EDIInterchangeMessage implements Segment, InterchangeMessage {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIInterchangeMessage.class);

  private static final String EOL = System.getProperty("line.separator");

  private UNASegment unaSegment
  private InterchangePayload interchangePayload

  def EDIInterchangeMessage() {
  }

  void setUnaSegment(UNASegment unaSegment) {
    unaSegment.interchangeMessage = this
    this.unaSegment = unaSegment
  }

  UNASegment setUnaSegment(UNASegment una) {
    return unaSegment;
  }

  String toEDI() {
    assert unaSegment
    assert interchangePayload

    StringBuilder stringBuilder = new StringBuilder()
    stringBuilder << unaSegment.toEDI() << interchangePayload.toEDI()
    return stringBuilder.toString()
  }

  String getCompDataSeparator() {
    return unaSegment.compDataSep
  }

  String getDataElemSeparator() {
    return unaSegment.dataElemSeparator
  }

  String getDecimalNotation() {
    return unaSegment.decimalNotation
  }

  String getReleaseIndicator() {
    return unaSegment.releaseIndicator
  }

  String getReserved() {
    return unaSegment.reserved
  }

  String getSegmentTerminator() {
    return unaSegment.segmentTerminator
  }

  InterchangePayload createInterchangePayload() {
    if (interchangePayload) {
      throw new EDIBuilderException("Its allowed to create only one/once InterchangePayload per EDI Message")
    }

    interchangePayload = new InterchangePayload(this)
    return interchangePayload
  }

  FunctionalGroupPayload createFunctionalPayload() {
    return new FunctionalGroupPayload(this)
  }

  def ConditionalFunctionalGroupPayload createConditionalFunctionalPayload() {
    return new ConditionalFunctionalGroupPayload(this);
  }

  MessagePayload createMessagePayload() {
    return new MessagePayload(this)
  }

  UserSegment createUserSegment(String tagName) {
    UserSegment userSegment = new UserSegment(tagName: tagName)
    userSegment.interchangeMessage = this
    return userSegment
  }

  String getEol() {
    return EOL
  }

}
