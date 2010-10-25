package info.sargis.gedi

import info.sargis.gedi.model.unb.UNBSegment
import info.sargis.gedi.model.ung.UNGSegment
import info.sargis.gedi.model.unh.UNHSegment
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

  private final EDIModel EDI_MODEL = new EDIModel()

  def EDIBuilder() {
  }

  // ******************************** Builder support // ******************************** //

  protected void setParent(Object parent, Object child) {
    switch (child) {
      case InterchangeMessage:

        if (parent instanceof EDIModel) {
          EDI_MODEL.interchangeMessage = child
        } else {
          throw new EDIBuilderException("UNB segment should be top level segment for EDI Model");
        }
        break

      case FunctionalSegment:

        if (parent instanceof InterchangeMessage) {
          parent.addFunctionalSegment(child)
        } else {
          throw new EDIBuilderException("UNG segment can be added only to UNB");
        }
        break

      case MessageSegment:

        if (parent instanceof InterchangeMessage) {
          InterchangeMessage iMessage = (InterchangeMessage) parent

          def segments = iMessage.getFunctionalSegments()
          if (segments) {
            FunctionalSegment functionalSegment = new ConditionalFunctionalSegment()
            parent.addFunctionalSegment(functionalSegment)
          }
          segments[0].addMessageSegment(child)
        } else if (parent instanceof FunctionalSegment) {
          parent.addMessageSegment(child)
        } else {
          throw new EDIBuilderException("UNH segment can be added only to UNB or UNG segments");
        }
        break

      case Segment:

        if (parent instanceof MessageSegment) {
          parent.addUserSegment(child)
        } else {
          throw new EDIBuilderException("User data segment can be added only to UNH segment");
        }
        break

      default:
        throw new EDIBuilderException("Cannot find case for object: " + child.class.getName())
    }

  }

  protected Object createNode(Object name) {
    switch (name) {
      case "UNB":
        InterchangeMessage interchangeMessage = new InterchangeMessage()
        interchangeMessage.with {
          unbSegment = new UNBSegment()
        }
        return interchangeMessage
      case "UNG":
        FunctionalSegment functionalSegment = new FunctionalSegment()
        functionalSegment.with {
          ungSegment = new UNGSegment()
        }
        return functionalSegment
      case "UNH":
        MessageSegment messageSegment = new MessageSegment()
        messageSegment.with {
          unhSegment = new UNHSegment()
        }
        return messageSegment
      default:
        return new UserSegment(tagName: name)
    }
  }

  protected Object createNode(Object name, Object value) {
    return createNode(name);
  }

  protected Object createNode(Object name, Map attributes) {
    throw new EDIBuilderException("Unsupported operation");
  }

  protected Object createNode(Object name, Map attributes, Object value) {
    throw new EDIBuilderException("Unsupported operation");
  }

}
