package info.sargis.gedi.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNASegment extends EDISegment {

  String cdes = ":"
  String des = "+"
  String decn = "."
  String ri = "?"
  String rs = " "
  String st = "'"

  def UNASegment() {
    tagName = "UNA"
  }

  String toEDI() {
    return "${tagName}${cdes}${des}${decn}${ri}${rs}${st}";
  }

}
