package info.sargis.gedi.model.unb

import info.sargis.gedi.model.AbstractSegment
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class UNZSegment extends AbstractSegment {

  Integer msgCount
  String ctrlRef

  def UNZSegment() {
    tagName = "UNZ"
  }

  String toEDI() {
    return "${tagName}+${msgCount}+${ctrlRef}${EOL}"
  }
}
