package info.sargis.gedi.model

import info.sargis.gedi.model.unb.UNBSegment
import info.sargis.gedi.model.ung.UNGSegment
import info.sargis.gedi.model.unh.UNHSegment
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

  InterchangeMessage interchangeMessage

  @BeforeMethod
  public void setUp() {
    interchangeMessage = new InterchangeMessage()
    interchangeMessage.unbSegment = new UNBSegment()
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

    FunctionalSegment functionalSegment = new ConditionalFunctionalSegment()
    functionalSegment.addMessageSegment(createFirstMessageSegment())
    functionalSegment.addMessageSegment(createSecondMessageSegment())

    interchangeMessage.addFunctionalSegment(functionalSegment)

    Assert.assertEquals(interchangeMessage.toEDI(), expectedEDI.stripIndent());
  }

  private FunctionalSegment createFirstFunctionalMessage() {
    FunctionalSegment functionalSegment = new FunctionalSegment()
    functionalSegment.ungSegment = new UNGSegment()

    functionalSegment.addMessageSegment(createFirstMessageSegment())

    return functionalSegment
  }

  private FunctionalSegment createSecondFunctionalMessage() {
    FunctionalSegment functionalSegment = new FunctionalSegment()
    functionalSegment.ungSegment = new UNGSegment()

    functionalSegment.addMessageSegment(createSecondMessageSegment())

    return functionalSegment
  }

  private MessageSegment createFirstMessageSegment() {
    MessageSegment messageSegment = new MessageSegment()
    messageSegment.unhSegment = new UNHSegment()

    messageSegment.addUserSegment(new UserSegment(tagName: "C01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C22"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C55"))
    return messageSegment
  }

  private MessageSegment createSecondMessageSegment() {
    MessageSegment messageSegment = new MessageSegment()
    messageSegment.unhSegment = new UNHSegment()

    messageSegment.addUserSegment(new UserSegment(tagName: "X01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "X22"))
    return messageSegment
  }

}
