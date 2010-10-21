package info.sargis.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNHSegment implements Segment {

  UNASegment una
  String ref

  String toSegment() {
    return "UNH${una.des}${count}${una.des}${unb.ref}${una.st}";
  }

}


