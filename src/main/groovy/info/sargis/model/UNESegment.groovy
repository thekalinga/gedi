package info.sargis.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNESegment implements Segment {

  UNASegment una
  UNGSegment ung
  Integer count

  String toSegment() {
    return "UNE${una.des}${count}${una.des}${ung.ref}${una.st}";
  }

}


