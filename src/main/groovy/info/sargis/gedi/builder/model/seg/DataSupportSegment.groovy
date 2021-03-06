package info.sargis.gedi.builder.model.seg

import info.sargis.gedi.builder.EDIDSLCategory
import info.sargis.gedi.builder.model.Segment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
abstract class DataSupportSegment implements Segment {

  String ediDataString = ""

  def data(Closure closure) {
    use(EDIDSLCategory) {
      ediDataString = closure.call()
    }
  }

}
