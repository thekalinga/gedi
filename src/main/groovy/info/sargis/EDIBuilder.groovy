package info.sargis

import info.sargis.model.Segment
import info.sargis.model.UNASegment

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class EDIBuilder {

  Writer out

  boolean writeUNASegment = false

  Segment unaSegment = new UNASegment()
  Segment unbSegment
  Segment unzSegment

  def EDIBuilder(Writer writer) {
    this.out = writer
  }

  def withUna(Map params) {
    writeUNASegment = true
    unaSegment = new UNASegment(
            cdes: params.cdes, des: params.des, decn: params.decn, ri: params.ri, rs: params.rs, st: params.st
    )
    return this
  }

  def withDefaultUna() {
    writeUNASegment = true
    return this
  }

  def final buildEDI() {
    writeUNA(out)

    writeUNB(out)
    writeUNG(out)
    writeUNH(out)

    writeUserData(out)

    writeUNT(out)
    writeUNE(out)
    writeUNZ(out)

    out.flush()
  }

  /**
   * EDI 'Service String Advice' segment
   */
  def writeUNA(Writer writer) {
    if (writeUNASegment) {
      writer.write(unaSegment.toSegment())
    }
  }

  /**
   * EDI 'Interchange Header' segment
   */
  def writeUNB(Writer writer) {
    assert unbSegment
    writer.write(unbSegment.toSegment())
  }

  /**
   * EDI 'Functional Group Header' segment
   */
  def writeUNG(Writer writer) {
  }

  /**
   * EDI 'Message Header' segment
   */
  def writeUNH(Writer writer) {
  }

  /**
   * EDI 'User Data' segments
   */
  def writeUserData(Writer writer) {


  }

  /**
   * EDI 'Message Trailer' segments
   */
  def writeUNT(Writer writer) {
  }

  /**
   * EDI 'Message Trailer' segments
   */
  def writeUNE(Writer writer) {
  }

  /**
   * EDI 'Interchange Trailer' segments
   */
  def writeUNZ(Writer writer) {
  }

}
