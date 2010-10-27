package info.sargis.gedi.model

import static info.sargis.gedi.EDIConfig.EOL

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNASegment extends EDISegment {

  String compDataSep = ":"
  String dataElemSeparator = "+"
  String decimalNotation = "."
  String releaseIndicator = "?"
  String reserved = " "
  String segmentTerminator = "'"

  def UNASegment() {
    tagName = "UNA"
  }

  String toEDI() {
    StringBuilder sb = new StringBuilder(tagName)
    sb << compDataSep << dataElemSeparator << decimalNotation << releaseIndicator << reserved << segmentTerminator << EOL

    return sb.toString()
  }

}
