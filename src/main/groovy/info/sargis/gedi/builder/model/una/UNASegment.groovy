package info.sargis.gedi.builder.model.una

import info.sargis.gedi.builder.model.Segment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNASegment implements Segment {

  char compDataSep = ':' as char
  char dataElemSeparator = '+' as char
  char decimalNotation = '.' as char
  char releaseIndicator = '?' as char
  char reserved = ' ' as char
  char segmentTerminator = "'" as char

  String tagName = "UNA"

  def UNASegment() {
  }

  String toEDI() {
    StringBuilder sb = new StringBuilder(tagName)

    sb << compDataSep << dataElemSeparator << decimalNotation << releaseIndicator
    sb << reserved << segmentTerminator

    return sb.toString()
  }

  public String toString() {
    return "UNASegment{" +
            "compDataSep=" + compDataSep +
            ", dataElemSeparator=" + dataElemSeparator +
            ", decimalNotation=" + decimalNotation +
            ", releaseIndicator=" + releaseIndicator +
            ", reserved=" + reserved +
            ", segmentTerminator=" + segmentTerminator +
            ", tagName='" + tagName + '\'' +
            '}';
  }

}
