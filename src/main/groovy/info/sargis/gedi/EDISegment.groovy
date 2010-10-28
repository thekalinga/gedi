package info.sargis.gedi

import info.sargis.gedi.model.AbstractSegment
import info.sargis.gedi.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 28, 2010
 */
class EDISegment extends AbstractSegment {

  String tagName = ""
  private String tagData = ""

  InterchangeMessage interchangeMessage

  def EDISegment() {
  }

  def EDISegment(InterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage;
  }

  public void setTagData(List tagData) {
    this.tagData = interchangeMessage.compDataSeparator + tagData.join(interchangeMessage.compDataSeparator)
  }

  public void setTagData(String tagData) {
    this.tagData = interchangeMessage.compDataSeparator + tagData
  }

  public String getTagData() {
    tagData
  }

  String toEDI() {
    assert interchangeMessage

    StringBuilder sb = new StringBuilder()

    sb << tagName << tagData << interchangeMessage.dataElemSeparator << ediDataString
    sb << interchangeMessage.segmentTerminator << interchangeMessage.eol

    return sb.toString();
  }

}
