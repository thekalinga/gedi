package info.sargis.gedi.builder.model

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
class FunctionalSegmentTest {

  EDIInterchangeMessage interchangeMessage


  @BeforeMethod
  public void setUp() {
    interchangeMessage = new EDIInterchangeMessage()
  }

  @Test
  public void testToEDI() throws Exception {

    FunctionalGroupPayload functionalSegment = interchangeMessage.createFunctionalPayload()

    functionalSegment.addMessagePayload(createFirstMessageSegment())
    functionalSegment.addMessagePayload(createSecondMessageSegment())

    def expectedEDI = '''\
      UNG+'
      UNH+'
      C01+'
      C22+'
      C55+'
      UNT+3+'
      UNH+'
      X01+'
      X22+'
      UNT+2+'
      UNE+2+'
    '''
    Assert.assertEquals(functionalSegment.toEDI(), expectedEDI.stripIndent());
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
