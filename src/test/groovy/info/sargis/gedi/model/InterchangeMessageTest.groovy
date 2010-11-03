package info.sargis.gedi.model

import info.sargis.gedi.EDIBuilderException
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
      UNT+3+'
      UNE+1+'
      UNG+'
      UNH+'
      X01+'
      X22+'
      UNT+2+'
      UNE+1+'
      UNZ+2+'
      '''

    InterchangePayload interchangePayload = interchangeMessage.createInterchangePayload()

    interchangePayload.addFunctionalPayload(createFirstFunctionalMessage())
    interchangePayload.addFunctionalPayload(createSecondFunctionalMessage())

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
      UNT+3+'
      UNH+'
      X01+'
      X22+'
      UNT+2+'
      UNZ+2+'
      '''

    InterchangePayload interchangePayload = interchangeMessage.createInterchangePayload()

    interchangePayload.addMessagePayload(createFirstMessageSegment())
    interchangePayload.addMessagePayload(createSecondMessageSegment())

    Assert.assertEquals(interchangePayload.toEDI(), expectedEDI.stripIndent());
  }

  @Test(expectedExceptions = EDIBuilderException.class)
  public void testMixingUngAndUnhSegments() throws Exception {
    InterchangePayload interchangePayload = interchangeMessage.createInterchangePayload()

    interchangePayload.addFunctionalPayload(createFirstFunctionalMessage())
    interchangePayload.addFunctionalPayload(createSecondFunctionalMessage())

    interchangePayload.addMessagePayload(createFirstMessageSegment())
  }

  @Test(expectedExceptions = EDIBuilderException.class)
  public void testMixingUnhAndUngSegments() throws Exception {
    InterchangePayload interchangePayload = interchangeMessage.createInterchangePayload()

    interchangePayload.addMessagePayload(createFirstMessageSegment())
    interchangePayload.addMessagePayload(createSecondMessageSegment())

    interchangePayload.addFunctionalPayload(createFirstFunctionalMessage())
  }

  private FunctionalGroupPayload createFirstFunctionalMessage() {
    FunctionalGroupPayload functionalSegment = interchangeMessage.createFunctionalPayload()
    functionalSegment.addMessagePayload(createFirstMessageSegment())

    return functionalSegment
  }

  private FunctionalGroupPayload createSecondFunctionalMessage() {
    FunctionalGroupPayload functionalSegment = interchangeMessage.createFunctionalPayload()
    functionalSegment.addMessagePayload(createSecondMessageSegment())

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
