package info.sargis.gedi.model.unb

import info.sargis.gedi.model.EDISegment
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNBSegment extends EDISegment {

  String msgRefNbr = "UNB0111DUMMY"

  def UNBSegment() {
    tagName = "UNB"
  }

  String toEDI() {
    return "${tagName}${EOL}";
  }

}
