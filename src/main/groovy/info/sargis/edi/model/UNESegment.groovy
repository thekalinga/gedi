package info.sargis.edi.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNESegment implements Segment {

  UNASegment una

  Integer numbersOfMessages = 1
  String functionalGroupRefNumber

  String toSegment() {
    return "UNE${una.des}${numbersOfMessages}${una.des}${functionalGroupRefNumber}${una.st}";
  }

}


