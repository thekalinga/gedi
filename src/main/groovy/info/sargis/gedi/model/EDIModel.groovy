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

  private static final String EOL = System.getProperty("line.separator");
  private static final UNASegment DEFAULT_UNA = new UNASegment();

  Writer writer

  def EDIModel() {
  }

  def EDIModel(writer) {
    this.writer = writer;
  }

  UNASegment unaSegment = DEFAULT_UNA
  UNBSegment unbSegment

  private List<Segment> ungSegments = []
  private List<Segment> unhSegments = []
  private List<Segment> userSegments = []

  UNTSegment untSegment
  UNESegment uneSegment
  UNZSegment unzSegment

  def buildEDI() {
    assert unaSegment
    assert unbSegment
    assert unhSegments
    assert untSegment
    assert unzSegment

    writeHeaders()
    writeSegments(userSegments)
    writeTails()
  }

  private def writeHeaders() {
    writeSegment(unaSegment)
    writeSegment(unbSegment)
    if (ungSegments) {
      writeSegments(ungSegments)
    }
    writeSegments(unhSegments)
  }

  private def writeTails() {
    writeSegment(untSegment)
    if (uneSegment) {
      assert ungSegments
      writeSegment(uneSegment)
    }
    writeSegment(unzSegment)
  }

  List<Segment> getUNGSegments() {
    return Collections.unmodifiableList(ungSegments)
  }

  List<Segment> getUNHSegments() {
    return Collections.unmodifiableList(unhSegments)
  }

  List<Segment> getUserSegments() {
    return Collections.unmodifiableList(userSegments)
  }

  def addUNGSegment(UNGSegment ungSegment) {
    ungSegments << ungSegment
  }

  def addUNHSegment(UNHSegment unhSegment) {
    unhSegments << unhSegment
  }

  def addUserSegment(Segment segment) {
    userSegments << segment
  }

  private def writeSegments(List<Segment> segments) {
    segments.each { seg ->
      writeSegment(seg)
    }
  }

  private def writeSegment(Segment segment) {
    writer.write(segment.toEDI())
    writer.write(EOL)
  }

}
