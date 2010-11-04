package info.sargis.gedi.builder.model.seg

import info.sargis.gedi.builder.model.InterchangeMessage

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 28, 2010
 */
class EDISegment extends DataSupportSegment {

  String tagName = ""
  private String tagData = ""

  InterchangeMessage interchangeMessage

  def EDISegment() {
  }

  def EDISegment(InterchangeMessage interchangeMessage) {
    this.interchangeMessage = interchangeMessage;
  }

  public void setTagData(Object tagData) {
    def list

    if (tagData instanceof List) list = tagData
    else {
      list = [tagData.toString()]
    }

    this.tagData = interchangeMessage.compDataSeparator + list.join(interchangeMessage.compDataSeparator)
  }

  public String getTagData() {
    this.tagData
  }

  String toEDI() {
    assert interchangeMessage

    StringBuilder sb = new StringBuilder()

    sb << tagName << this.tagData << interchangeMessage.dataElemSeparator << ediDataString
    sb << interchangeMessage.segmentTerminator << interchangeMessage.eol

    return sb.toString();
  }

}
