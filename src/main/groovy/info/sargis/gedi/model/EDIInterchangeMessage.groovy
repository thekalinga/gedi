package info.sargis.gedi.model

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

  UNASegment unaSegment
  InterchangePayload interchangeMessage

  def EDIInterchangeMessage() {
  }

  String toEDI() {
    assert unaSegment
    assert interchangeMessage

    StringBuilder stringBuilder = new StringBuilder()
    stringBuilder << unaSegment.toEDI() << interchangeMessage.toEDI()
    return stringBuilder.toString()
  }

}
