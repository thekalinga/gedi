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
class FunctionalSegmentTest {

  EDIInterchangeMessage interchangeMessage
  FunctionalGroupPayload functionalSegment


  @BeforeMethod
  public void setUp() {
    interchangeMessage = new EDIInterchangeMessage()
    functionalSegment = new FunctionalGroupPayload(interchangeMessage)
  }

  @Test
  public void testToEDI() throws Exception {

    functionalSegment.addMessageSegment(createFirstMessageSegment())
    functionalSegment.addMessageSegment(createSecondMessageSegment())

    def expectedEDI = '''\
      UNG
      UNH
      C01+'
      C22+'
      C55+'
      UNT+3+UNH0111DUMMY
      UNH
      X01+'
      X22+'
      UNT+2+UNH0111DUMMY
      UNE+2+UNG0111DUMMY
    '''
    Assert.assertEquals(functionalSegment.toEDI(), expectedEDI.stripIndent());
  }

  private MessagePayload createFirstMessageSegment() {
    MessagePayload messageSegment = new MessagePayload(interchangeMessage)

    messageSegment.addUserSegment(new UserSegment(tagName: "C01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C22"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C55"))
    return messageSegment
  }

  private MessagePayload createSecondMessageSegment() {
    MessagePayload messageSegment = new MessagePayload(interchangeMessage)

    messageSegment.addUserSegment(new UserSegment(tagName: "X01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "X22"))
    return messageSegment
  }

}
