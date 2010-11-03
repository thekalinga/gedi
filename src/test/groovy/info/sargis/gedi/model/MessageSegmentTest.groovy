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
class MessageSegmentTest {

  EDIInterchangeMessage interchangeMessage

  @BeforeMethod
  public void setUp() {
    interchangeMessage = new EDIInterchangeMessage()
  }

  @Test
  public void testToEDI() throws Exception {

    MessagePayload messageSegment = new MessagePayload(new EDIInterchangeMessage())

    messageSegment.addUserSegment(interchangeMessage.createUserSegment("C01"))
    messageSegment.addUserSegment(interchangeMessage.createUserSegment("C22"))
    messageSegment.addUserSegment(interchangeMessage.createUserSegment("C55"))

    def expectedEDI = '''\
      UNH+'
      C01+'
      C22+'
      C55+'
      UNT+3+'
    '''
    Assert.assertEquals(messageSegment.toEDI(), expectedEDI.stripIndent());
  }

}
