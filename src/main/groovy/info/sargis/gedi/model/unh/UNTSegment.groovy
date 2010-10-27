package info.sargis.gedi.model.unh

import info.sargis.gedi.model.AbstractSegment
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNTSegment extends AbstractSegment {

  Integer msgCount
  String msgRefNbr

  def UNTSegment() {
    tagName = "UNT"
  }

  String toEDI() {
    return "${tagName}+${msgCount}+${msgRefNbr}${EOL}"
  }
}
