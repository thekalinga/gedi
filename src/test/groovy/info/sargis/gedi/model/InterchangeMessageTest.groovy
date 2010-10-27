package info.sargis.gedi.model

import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 26, 2010
 */
class InterchangeMessageTest {

  EDIInterchangeMessage ediMessage
  InterchangePayload interchangeMessage

  @BeforeMethod
  public void setUp() {
    ediMessage = new EDIInterchangeMessage()
    interchangeMessage = new InterchangePayload(ediMessage)
  }

  @Test
  public void testToEDIWithFunctionalGroups() throws Exception {

    def expectedEDI = '''\
      UNB
      UNG
      UNH
      C01+'
      C22+'
      C55+'
      UNT+3+UNH0111DUMMY
      UNE+1+UNG0111DUMMY
      UNG
      UNH
      X01+'
      X22+'
      UNT+2+UNH0111DUMMY
      UNE+1+UNG0111DUMMY
      UNZ+2+UNB0111DUMMY
      '''

    interchangeMessage.addFunctionalSegment(createFirstFunctionalMessage())
    interchangeMessage.addFunctionalSegment(createSecondFunctionalMessage())

    Assert.assertEquals(interchangeMessage.toEDI(), expectedEDI.stripIndent());
  }

  @Test
  public void testToEDIWithoutFunctionalGroups() throws Exception {

    def expectedEDI = '''\
      UNB
      UNH
      C01+'
      C22+'
      C55+'
      UNT+3+UNH0111DUMMY
      UNH
      X01+'
      X22+'
      UNT+2+UNH0111DUMMY
      UNZ+2+UNB0111DUMMY
      '''

    FunctionalGroupPayload functionalSegment = new ConditionalFunctionalGroupPayload(ediMessage)
    functionalSegment.addMessageSegment(createFirstMessageSegment())
    functionalSegment.addMessageSegment(createSecondMessageSegment())

    interchangeMessage.addFunctionalSegment(functionalSegment)

    Assert.assertEquals(interchangeMessage.toEDI(), expectedEDI.stripIndent());
  }

  private FunctionalGroupPayload createFirstFunctionalMessage() {
    FunctionalGroupPayload functionalSegment = new FunctionalGroupPayload(ediMessage)
    functionalSegment.addMessageSegment(createFirstMessageSegment())

    return functionalSegment
  }

  private FunctionalGroupPayload createSecondFunctionalMessage() {
    FunctionalGroupPayload functionalSegment = new FunctionalGroupPayload(ediMessage)
    functionalSegment.addMessageSegment(createSecondMessageSegment())

    return functionalSegment
  }

  private MessagePayload createFirstMessageSegment() {
    MessagePayload messageSegment = new MessagePayload(ediMessage)

    messageSegment.addUserSegment(new UserSegment(tagName: "C01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C22"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C55"))
    return messageSegment
  }

  private MessagePayload createSecondMessageSegment() {
    MessagePayload messageSegment = new MessagePayload(ediMessage)

    messageSegment.addUserSegment(new UserSegment(tagName: "X01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "X22"))
    return messageSegment
  }

}
