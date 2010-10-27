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

  MessagePayload messageSegment

  @BeforeMethod
  public void setUp() {
    messageSegment = new MessagePayload(new EDIInterchangeMessage())
  }

  @Test
  public void testToEDI() throws Exception {

    messageSegment.addUserSegment(new UserSegment(tagName: "C01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C22"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C55"))

    def expectedEDI = '''\
      UNH
      C01+'
      C22+'
      C55+'
      UNT+3+UNH0111DUMMY
    '''
    Assert.assertEquals(messageSegment.toEDI(), expectedEDI.stripIndent());
  }

}
