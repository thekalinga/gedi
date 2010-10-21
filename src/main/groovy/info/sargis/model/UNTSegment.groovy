package info.sargis.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNTSegment implements Segment {

  UNASegment una
  UNHSegment unh
  Integer count

  String toSegment() {
    return "UNT${una.des}${count}${una.des}${unh.ref}${una.st}";
  }

}

