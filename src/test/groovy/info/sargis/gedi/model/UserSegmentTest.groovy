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
class UserSegmentTest {

  EDIInterchangeMessage interchangeMessage

  @BeforeMethod
  public void setUp() {
    interchangeMessage = new EDIInterchangeMessage()
  }

  @Test
  public void testToEDI() throws Exception {
    def tag = "GCC"

    UserSegment segment = interchangeMessage.createUserSegment(tag)
    segment.data {
      ["GCC", "", "XXX"] + 1222 + "ZSSS01"
    }

    Assert.assertEquals(segment.toEDI(), "${tag}+GCC::XXX+1222+ZSSS01'${segment.interchangeMessage.eol}");
  }
}
