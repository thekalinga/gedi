package info.sargis.gedi.model

import info.sargis.gedi.model.unh.UNHSegment
import org.testng.Assert
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Oct 26, 2010
 */
class MessageSegmentTest {

  private MessageSegment messageSegment

  @BeforeTest
  public void setUp() {
    messageSegment = new MessageSegment()
    messageSegment.unhSegment = new UNHSegment()

    messageSegment.addUserSegment(new UserSegment(tagName: "C01"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C22"))
    messageSegment.addUserSegment(new UserSegment(tagName: "C55"))
  }

  @Test
  public void testToEDI() throws Exception {
    def expectedEDI = '''\
      UNH
      C01
      C22
      C55
      UNT+3+UNH0111DUMMY
    '''
    Assert.assertEquals(messageSegment.toEDI(), expectedEDI.stripIndent());
  }

}
