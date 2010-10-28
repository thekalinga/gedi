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

  EDIInterchangeMessage interchangeMessage

  @BeforeMethod
  public void setUp() {
    interchangeMessage = new EDIInterchangeMessage()
  }

  @Test
  public void testToEDIWithFunctionalGroups() throws Exception {

    def expectedEDI = '''\
      UNB+'
      UNG+'
      UNH+'
      C01+'
      C22+'
      C55+'
      UNT+3+UNH0111DUMMY'
      UNE+1+UNG0111DUMMY'
      UNG+'
      UNH+'
      X01+'
      X22+'
      UNT+2+UNH0111DUMMY'
      UNE+1+UNG0111DUMMY'
      UNZ+2+UNB0111DUMMY'
      '''

    InterchangePayload interchangePayload = interchangeMessage.createInterchangePayload()

    interchangePayload.addFunctionalSegment(createFirstFunctionalMessage())
    interchangePayload.addFunctionalSegment(createSecondFunctionalMessage())

    Assert.assertEquals(interchangePayload.toEDI(), expectedEDI.stripIndent());
  }

  @Test
  public void testToEDIWithoutFunctionalGroups() throws Exception {

    def expectedEDI = '''\
      UNB+'
      UNH+'
      C01+'
      C22+'
      C55+'
      UNT+3+UNH0111DUMMY'
      UNH+'
      X01+'
      X22+'
      UNT+2+UNH0111DUMMY'
      UNZ+2+UNB0111DUMMY'
      '''

    InterchangePayload interchangePayload = interchangeMessage.createInterchangePayload()

    FunctionalGroupPayload functionalSegment = interchangeMessage.createConditionalFunctionalPayload()
    functionalSegment.addMessageSegment(createFirstMessageSegment())
    functionalSegment.addMessageSegment(createSecondMessageSegment())

    interchangePayload.addFunctionalSegment(functionalSegment)

    Assert.assertEquals(interchangePayload.toEDI(), expectedEDI.stripIndent());
  }

  private FunctionalGroupPayload createFirstFunctionalMessage() {
    FunctionalGroupPayload functionalSegment = interchangeMessage.createFunctionalPayload()
    functionalSegment.addMessageSegment(createFirstMessageSegment())

    return functionalSegment
  }

  private FunctionalGroupPayload createSecondFunctionalMessage() {
    FunctionalGroupPayload functionalSegment = interchangeMessage.createFunctionalPayload()
    functionalSegment.addMessageSegment(createSecondMessageSegment())

    return functionalSegment
  }

  private MessagePayload createFirstMessageSegment() {
    MessagePayload messageSegment = interchangeMessage.createMessagePayload()

    messageSegment.addUserSegment(interchangeMessage.createUserSegment("C01"))
    messageSegment.addUserSegment(interchangeMessage.createUserSegment("C22"))
    messageSegment.addUserSegment(interchangeMessage.createUserSegment("C55"))
    return messageSegment
  }

  private MessagePayload createSecondMessageSegment() {
    MessagePayload messageSegment = interchangeMessage.createMessagePayload()

    messageSegment.addUserSegment(interchangeMessage.createUserSegment("X01"))
    messageSegment.addUserSegment(interchangeMessage.createUserSegment("X22"))
    return messageSegment
  }

}
