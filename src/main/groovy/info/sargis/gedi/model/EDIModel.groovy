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
class EDIModel implements Segment {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIModel.class);

  UNASegment unaSegment
  InterchangeMessage interchangeMessage

  def EDIModel() {
  }

  String toEDI() {
    assert unaSegment
    assert interchangeMessage

    StringBuilder stringBuilder = new StringBuilder()
    stringBuilder << unaSegment.toEDI() << interchangeMessage.toEDI()
    return stringBuilder.toString()
  }

}
