package info.sargis.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNZSegment implements Segment {

  UNASegment una
  UNBSegment unb
  Integer count

  String toSegment() {
    return "UNZ${una.des}${count}${una.des}${unb.ref}${una.st}";
  }

}


