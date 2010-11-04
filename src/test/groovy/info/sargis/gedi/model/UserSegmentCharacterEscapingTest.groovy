package info.sargis.gedi.model

import info.sargis.gedi.model.seg.UserSegment
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
class UserSegmentCharacterEscapingTest {

  EDIInterchangeMessage interchangeMessage

  @BeforeMethod
  public void setUp() {
    interchangeMessage = new EDIInterchangeMessage()
  }

  @Test
  public void testStringsEscaping() throws Exception {
    def tag = "GCC"

    UserSegment segment = interchangeMessage.createUserSegment(tag)
    segment.data {
      "SS+XXX:TT" + "ZSS?S01:"
    }

    Assert.assertEquals(segment.toEDI(), "${tag}+SS?+XXX?:TT+ZSS??S01?:'${segment.interchangeMessage.eol}");
  }

  @Test
  public void testMixEscaping() throws Exception {
    def tag = "GCC"

    UserSegment segment = interchangeMessage.createUserSegment(tag)
    segment.data {
      "SS+XXX:TT" + 6555 + ["XX+-1135", 1256, "CC'RR"] + "TTT+XX" + 2333
    }

    Assert.assertEquals(segment.toEDI(), "${tag}+SS?+XXX?:TT+6555+XX?+-1135:1256:CC?'RR+TTT?+XX+2333'${segment.interchangeMessage.eol}");
  }

}
