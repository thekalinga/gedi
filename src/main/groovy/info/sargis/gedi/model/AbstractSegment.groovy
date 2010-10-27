package info.sargis.gedi.model

import info.sargis.gedi.EDIDSLCategory
import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
abstract class AbstractSegment implements Segment {

  String tagName = ""
  String ediString = ""

  def data(Closure closure) {
    use(EDIDSLCategory) {
      ediString = closure.call()
    }
  }

  String toEDI() {
    return "$tagName+$ediString'${EOL}";
  }

}
