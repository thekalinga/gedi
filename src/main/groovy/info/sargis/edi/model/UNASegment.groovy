package info.sargis.edi.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNASegment implements Segment {

  String cdes = ":"
  String des = "+"
  String decn = "."
  String ri = "?"
  String rs = " "
  String st = "'"

  String toSegment() {
    return "UNA${cdes}${des}${decn}${ri}${rs}${st}";
  }

}
