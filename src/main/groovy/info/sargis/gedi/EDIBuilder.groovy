package info.sargis.gedi

import info.sargis.gedi.model.UNASegment
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class EDIBuilder extends BuilderSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIBuilder.class);

  public static final String EOL = System.getProperty("line.separator");
  public static final UNASegment DEFAULT_UNA = new UNASegment()

  private StringBuilder segmentBuffer
  private UNASegment unaSegment = DEFAULT_UNA;

  def actions = []
  Writer writer

  def EDIBuilder() {
  }

  def EDIBuilder(Writer writer) {
    this.writer = writer
  }

  def writeUna(Map params) {
    unaSegment = new UNASegment(
            cdes: params.cdes, des: params.des, decn: params.decn, ri: params.ri, rs: params.rs, st: params.st
    )
    writeUNA(writer, unaSegment)
  }

  def writeDefaultUna() {
    writeUNA(writer, unaSegment)
  }

  /**
   * EDI 'Service String Advice' segment
   */
  private def writeUNA(Writer wr, UNASegment una) {
    wr.write(una.toSegment())
  }

  // ******************************** Builder support // ******************************** //

  protected void setParent(Object parent, Object child) {

  }

  protected void nodeCompleted(Object parent, Object node) {
    segmentBuffer.append(unaSegment.st)
    writer.write(segmentBuffer.toString())
    writer.flush()

    segmentBuffer = null;
  }


  protected Object createNode(Object name) {
    return createNode(name, Collections.EMPTY_LIST);
  }

  protected Object createNode(Object name, Object value) {
    segmentBuffer = new StringBuilder(name.toString())
    if (value) {
      segmentBuffer.append(unaSegment.cdes)
      setText(segmentBuffer, value)
    }
    return segmentBuffer;
  }


  String cde() {
    cde(Collections.EMPTY_MAP)
  }

  String cde(Map values) {
    values.each { entry ->
      segmentBuffer.append(unaSegment.des)
      segmentBuffer.append(entry.key)
      segmentBuffer.append(unaSegment.cdes)
      setText(segmentBuffer, entry.value)
    }
  }


  String sde() {
    sde(null)
  }

  String sde(Object value) {
    segmentBuffer.append(unaSegment.des)
    if (value) {
      segmentBuffer.append(value)
    }
  }


  def setText(StringBuilder sb, Object value) {
    if (value instanceof List) {
      sb.append(value.join(unaSegment.cdes))
    } else {
      sb.append(value.toString())
    }
  }

  def writeNewLine() {
    writer.write(EOL)
  }

  protected Object createNode(Object name, Map attributes, Object value) {
    LOGGER.warn("Not support builder method call: createNode(Object name, Map attributes, Object value)")
  }

  protected Object createNode(Object name, Map attributes) {
    LOGGER.warn("Not support builder method call: createNode(Object name, Map attributes)")
  }

}
