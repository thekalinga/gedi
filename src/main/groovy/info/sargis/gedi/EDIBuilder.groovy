package info.sargis.gedi

import info.sargis.gedi.model.una.UNASegment
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

  private static final UNASegment DEFAULT_UNA = new UNASegment();

  private EDIInterchangeMessage ediMessage
  private Writer writer

  private Segment currentSegment

  def EDIBuilder(Writer writer) {
    this.writer = writer

    ediMessage = new EDIInterchangeMessage()
    ediMessage.unaSegment = DEFAULT_UNA;
  }

  public void serviceStringAdvice(UNASegment unaSegment) {
    ediMessage.unaSegment = unaSegment
  }

  def build() {
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
          parent.addFunctionalSegment(child)
        } else {
          throw new EDIBuilderException("UNG segment can be added only to UNB");
        }
        break

      case MessagePayload:

        if (parent instanceof InterchangePayload) {
          InterchangePayload iMessage = (InterchangePayload) parent

          def segments = iMessage.getFunctionalSegments()
          if (segments) {
            FunctionalGroupPayload functionalSegment = new ConditionalFunctionalGroupPayload()
            parent.addFunctionalSegment(functionalSegment)
          }
          segments[0].addMessageSegment(child)
        } else if (parent instanceof FunctionalGroupPayload) {
          parent.addMessageSegment(child)
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
        InterchangePayload iPayload = new InterchangePayload(ediMessage)
        ediMessage.interchangePayload = iPayload;
        return iPayload
      case "UNG":
        return new FunctionalGroupPayload(ediMessage)
      case "UNH":
        return new MessagePayload(ediMessage)
      default:
        UserSegment userSegment = new UserSegment(tagName: name)
        userSegment.with {
          interchangeMessage = ediMessage
        }
        return userSegment
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

  def data(Closure closure) {
    if (currentSegment instanceof AbstractSegment) {
      AbstractSegment ediSegment = (AbstractSegment) currentSegment
      ediSegment.data(closure)
    } else {
      throw new EDIBuilderException("data definition can be aplied only for EDI Segment");
    }
  }

}
