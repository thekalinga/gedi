package info.sargis.gedi

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import info.sargis.gedi.model.*

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class EDIBuilder extends BuilderSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIBuilder.class);

  private final EDIModel ediModel = new EDIModel()
  def actions = []

  def EDIBuilder() {
  }

  // ******************************** Builder support // ******************************** //

  protected void setParent(Object parent, Object child) {
    switch (child) {
      case UNASegment:
        ediModel.unaSegment = child
        break
      case UNBSegment:
        ediModel.unbSegment = child
        ediModel.unzSegment = new UNZSegment(msgCount: 1, ctrlRef: ediModel.unbSegment.ctrlRef);
        break
      case UNGSegment:
        ediModel.addUNGSegment(child)
        ediModel.uneSegment = new UNESegment(
                msgCount: 1, grpRefNbr: ediModel.ungSegment.grpRefNbr
        );
        break
      case UNHSegment:
        ediModel.addUNHSegment(child)
        ediModel.untSegment = new UNTSegment(
                msgCount: ediModel.getUserSegments().size(), msgRefNbr: ediModel.ungSegment.msgRefNbr
        );
        break
      case Segment:
        ediModel.addUserSegment(child)
        break
      default:
        throw new EDIBuilderException("Cannot find case for object: " + child.class.getName())
    }

  }

  protected Object createNode(Object name) {
    switch (name) {
      case "UNA":
        return new UNASegment()
      case "UNB":
        return new UNBSegment()
      case "UNG":
        return new UNGSegment()
      case "UNH":
        return new UNHSegment()
      default:
        return new UserSegment(tagName: name)
    }
  }

  protected Object createNode(Object name, Object value) {
    Object result = createNode(name)
    return result;
  }

  protected Object createNode(Object name, Map attributes) {
    switch (name) {
      case "UNA":
        return new UNASegment(attributes)
      default:
        throw new EDIBuilderException("Create segment with attributes allowed only for UNA: Service String Advice");
    }
  }

  protected Object createNode(Object name, Map attributes, Object value) {
    throw new EDIBuilderException("Unsupported operation");
  }

}
