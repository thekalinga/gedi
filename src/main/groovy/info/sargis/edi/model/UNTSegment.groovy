package info.sargis.edi.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNTSegment implements Segment {

  UNASegment una

  Integer numbersOfSegments = 1
  String messageRefNumber

  String toSegment() {
    return "UNT${una.des}${numbersOfSegments}${una.des}${messageRefNumber}${una.st}";
  }

}


