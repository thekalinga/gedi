package info.sargis.gedi.model

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
class EDIInterchangeMessage implements Segment {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIInterchangeMessage.class);

  private static final String EOL = System.getProperty("line.separator");

  private UNASegment unaSegment
  InterchangePayload interchangePayload

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

  String getEol() {
    return EOL
  }

}
