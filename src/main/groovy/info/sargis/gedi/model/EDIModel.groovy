package info.sargis.gedi.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 25, 2010
 */
class EDIModel {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIModel.class);

  UNASegment unaSegment
  InterchangeMessage interchangeMessage
  Writer writer

  def EDIModel() {
  }

  def EDIModel(writer) {
    this.writer = writer;
  }

  def buildEDI() {
    writeSegment(unaSegment)
    writeSegment(interchangeMessage)
  }


  private def writeSegments(List<Segment> segments) {
    segments.each { seg ->
      writeSegment(seg)
    }
  }

  private def writeSegment(Segment segment) {
    writer.write(segment.toEDI())
  }

}
