package info.sargis.edi.model

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class UNZSegment implements Segment {

  UNASegment una

  Integer numbersOfMessages = 1
  String interchangeControlReference

  String toSegment() {
    return "UNZ${una.des}${numbersOfMessages}${una.des}${interchangeControlReference}${una.st}";
  }

}


