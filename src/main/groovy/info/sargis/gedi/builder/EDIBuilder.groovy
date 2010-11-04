package info.sargis.gedi.builder

import info.sargis.gedi.EDIBuilderException
import info.sargis.gedi.builder.model.seg.DataSupportSegment
import info.sargis.gedi.builder.model.seg.EDISegment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import info.sargis.gedi.builder.model.*

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 21, 2010
 */
class EDIBuilder extends BuilderSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(EDIBuilder.class);

  private EDIInterchangeMessage ediMessage

  private Segment currentSegment

  def EDIBuilder() {
    ediMessage = new EDIInterchangeMessage(EDIDSLCategory.currentUnaConfig)
  }

  def build(Writer writer) {
    writer.write(ediMessage.toEDI())
    writer.flush()
  }

  // ******************************** Builder support // ******************************** //

  protected void setParent(Object parent, Object child) {

    LOGGER.debug("Parameters in setParent(): {}, {}", parent.class.name, child.class.name)

    switch (child) {
      case InterchangePayload:
        throw new EDIBuilderException("UNB segment should be top level segment for EDI Model");
      case FunctionalGroupPayload:

        if (parent instanceof InterchangePayload) {
          parent.addFunctionalPayload(child)
        } else {
          throw new EDIBuilderException("UNG segment can be added only to UNB");
        }
        break

      case MessagePayload:

        if (parent instanceof InterchangePayload) {
          parent.addMessagePayload(child)
        } else if (parent instanceof FunctionalGroupPayload) {
          parent.addMessagePayload(child)
        } else {
          throw new EDIBuilderException("UNH segment can be added only to UNB or UNG segments");
        }
        break

      case Segment:

        if (parent instanceof MessagePayload) {
          parent.addUserSegment(child)
        } else {
          throw new EDIBuilderException("User data segment can be added only to UNH segment");
        }
        break

      default:
        throw new EDIBuilderException("Cannot find case for object: " + child.class.getName())
    }

    currentSegment = child
  }

  protected Object createNode(Object name) {
    switch (name) {
      case "UNB":
        InterchangePayload interchangePayload = ediMessage.createInterchangePayload()
        currentSegment = interchangePayload
        return interchangePayload
      case "UNG":
        return ediMessage.createFunctionalPayload()
      case "UNH":
        return ediMessage.createMessagePayload()
      default:
        return ediMessage.createUserSegment(name)
    }
  }

  protected Object createNode(Object name, Object value) {
    Object segment = createNode(name)
    if (segment instanceof EDISegment) {
      segment.tagData = value
      return segment
    }

    throw new EDIBuilderException("Unsupported operation, can create segment with tag composite values only for EDISegment");
  }

  protected Object createNode(Object name, Map attributes) {
    throw new EDIBuilderException("Unsupported operation");
  }

  protected Object createNode(Object name, Map attributes, Object value) {
    throw new EDIBuilderException("Unsupported operation");
  }

  def data(Closure closure) {
    if (currentSegment instanceof DataSupportSegment) {
      DataSupportSegment ediSegment = (DataSupportSegment) currentSegment
      ediSegment.data(closure)
    } else {
      throw new EDIBuilderException("Data definition can be applied only for EDI Segment");
    }
  }

}
