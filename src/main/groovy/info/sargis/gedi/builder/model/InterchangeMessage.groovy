package info.sargis.gedi.builder.model

import info.sargis.gedi.builder.model.seg.UserSegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 27, 2010
 */
public interface InterchangeMessage {
  String getEol()

  String getCompDataSeparator()

  String getDataElemSeparator()

  String getDecimalNotation()

  String getReleaseIndicator()

  String getReserved()

  String getSegmentTerminator()

  InterchangePayload createInterchangePayload()

  FunctionalGroupPayload createFunctionalPayload()

  MessagePayload createMessagePayload()

  UserSegment createUserSegment(String tagName)
}