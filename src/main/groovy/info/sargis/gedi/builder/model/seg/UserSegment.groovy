package info.sargis.gedi.builder.model.seg

import info.sargis.gedi.builder.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UserSegment extends EDISegment {

  def UserSegment() {
  }

  def UserSegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage);
  }
}
