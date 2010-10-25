package info.sargis.gedi.model

import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class ConditionalFunctionalSegment extends FunctionalSegment {

  def String toEDI() {
    StringBuilder sb = new StringBuilder()

    messageSegments.each { seg ->
      sb << seg.toEDI() << EOL
    }
    return sb.toString()
  }

}
