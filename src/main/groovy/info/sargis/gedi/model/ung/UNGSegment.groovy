package info.sargis.gedi.model.ung

import info.sargis.gedi.model.AbstractSegment
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNGSegment extends AbstractSegment {

  String msgRefNbr = "UNG0111DUMMY"

  def UNGSegment() {
    tagName = "UNG"
  }

  String toEDI() {
    return "${tagName}${EOL}";
  }

}
