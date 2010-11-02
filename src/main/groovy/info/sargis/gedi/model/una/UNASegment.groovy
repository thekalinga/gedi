package info.sargis.gedi.model.una

import info.sargis.gedi.model.InterchangeMessage
import info.sargis.gedi.model.seg.EDISegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNASegment extends EDISegment {

  char compDataSep = ':' as char
  char dataElemSeparator = '+' as char
  char decimalNotation = '.' as char
  char releaseIndicator = '?' as char
  char reserved = ' ' as char
  char segmentTerminator = "'" as char

  String tagName = "UNA"

  def UNASegment() {
  }

  def UNASegment(InterchangeMessage interchangeMessage) {
    super(interchangeMessage)
  }

  String toEDI() {
    StringBuilder sb = new StringBuilder(tagName)

    sb << compDataSep << dataElemSeparator << decimalNotation << releaseIndicator
    sb << reserved << segmentTerminator << interchangeMessage.eol

    return sb.toString()
  }

}
